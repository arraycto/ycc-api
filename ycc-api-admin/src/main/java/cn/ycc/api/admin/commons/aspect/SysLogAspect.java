package cn.ycc.api.admin.commons.aspect;

import cn.ycc.api.admin.commons.annotation.LogInfo;
import cn.ycc.api.admin.commons.ext.YccSysLogManager;
import cn.ycc.api.admin.commons.utils.JsonUtils;
import cn.ycc.api.admin.commons.utils.LoginUserUtils;
import cn.ycc.api.admin.commons.utils.WebHttpUtils;
import cn.ycc.api.admin.entity.YccSysLogs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.26 20:38
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Autowired
    private YccSysLogManager yccSysLogHelder;

    @Pointcut(value = "execution(* cn.ycc.api.admin.controller.*Controller.*(..))")
    public void logPoint() {
    }

    /**
     * 环绕通知 记录日志
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPoint()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 获取注解 LogInfo
        LogInfo logInfo = method.getDeclaredAnnotation(LogInfo.class);
        if (!ObjectUtils.isEmpty(logInfo)) {
            if (logInfo.ignore()) {
                return joinPoint.proceed();
            }
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // ip地址
        String ipAddr = WebHttpUtils.getIPAddrForRequest(request);
        // 请求方法
        String reqMethod = request.getMethod();
        // 日志详情
        String logInfoContent = logInfo == null ? "" : logInfo.value();
        // 请求地址
        String url = request.getRequestURL().toString();
        // 参数
        String params = JsonUtils.objectToJson(joinPoint.getArgs());
        // 请求头
        String reqHeader = JsonUtils.objectToJson(WebHttpUtils.getRequestHeaders(request));
        String classMethod = method.getDeclaringClass().getName()+"#"+method.getName()+"()";


        Object result = null;
        long currentTime = System.currentTimeMillis();
        try {
            // 执行目标方法
            result = joinPoint.proceed();

            YccSysLogs yccSysLogs = buildSysLogs(1,
                    ipAddr,
                    classMethod,
                    logInfoContent,
                    reqMethod,
                    System.currentTimeMillis() - currentTime,
                    url,
                    reqHeader,
                    params,
                    result == null ? null : JsonUtils.objectToJson(result),
                    null
            );
            yccSysLogHelder.pushNewLog(yccSysLogs);
            printInfoLog(yccSysLogs);
        } catch (Exception e) {
            YccSysLogs yccSysLogs = buildSysLogs(0,
                    ipAddr,
                    classMethod,
                    logInfoContent,
                    reqMethod,
                    System.currentTimeMillis() - currentTime,
                    url,
                    reqHeader,
                    params,
                    null,
                    buildErrorMsg(e)
            );
            yccSysLogHelder.pushNewLog(yccSysLogs);
            throw e;
        }
        return result;

    }

    private String buildErrorMsg(Exception e) {
        StringBuilder builder = new StringBuilder();
        builder.append(e.getClass().getName()).append(":");
        builder.append(e.getMessage()).append("\n");
        builder.append(Arrays.toString(e.getStackTrace()));
        return builder.toString();
    }

    private void printInfoLog(YccSysLogs yccSysLogs) {
        if(!log.isInfoEnabled()){
            return ;
        }

        String format = "\n=============={0}==============\nURL:{1}\nMethod:{2}\nIncokeTimes:{3}\nRequestHeader:{4}\nRequestParams:{5}\nResponseBody:{6}";
       log.info( MessageFormat.format(format,
               yccSysLogs.getLogInfo(),
               yccSysLogs.getReqUrl(),
                yccSysLogs.getReqMethod(),
               yccSysLogs.getInvokeTimes(),
               yccSysLogs.getReqHeader(),
               yccSysLogs.getReqData(),
               yccSysLogs.getRespData()
       ));
    }

    /**
     *  构建SysLog类
     * @param successed 是否成功 1成功 0失败
     * @param ip IP地址
     * @param logInfo 日志详情
     * @param method http method
     * @param invokeTimes 调用耗时
     * @param url url地址
     * @param reqHeader 请求头
     * @param reqBody 请求参数
     * @param respBody 响应参数
     * @param errorMsg 异常消息
     * @return YccSysLogs
     */
    private YccSysLogs buildSysLogs(int successed, String ip,String classMethod, String logInfo, String method, long invokeTimes, String url, String reqHeader, String reqBody,String respBody, String errorMsg) {

        YccSysLogs yccSysLogs = new YccSysLogs();
        yccSysLogs.setSuccessed(successed);
        yccSysLogs.setCreateTime(new Date());
        yccSysLogs.setUpdateTime(new Date());
        yccSysLogs.setIpAddr(ip);
        yccSysLogs.setUserId(LoginUserUtils.getCurrentUser());
        yccSysLogs.setLogInfo(logInfo);
        yccSysLogs.setReqMethod(method);
        yccSysLogs.setInvokeTimes(invokeTimes);
        yccSysLogs.setReqUrl(url);
        yccSysLogs.setClassMethod(classMethod);

        if (!ObjectUtils.isEmpty(reqHeader)) {
            yccSysLogs.setReqHeader(reqHeader);
        }

        if (!ObjectUtils.isEmpty(reqBody)) {
            yccSysLogs.setReqData(reqBody);
        }
        if (!ObjectUtils.isEmpty(respBody)) {
            yccSysLogs.setRespData(respBody);
        }
        if (!ObjectUtils.isEmpty(errorMsg)) {
            yccSysLogs.setErrorMsg(errorMsg);
        }

        return yccSysLogs;
    }
}
