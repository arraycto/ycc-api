package cn.ycc.api.admin.commons.exceptions;

import cn.ycc.api.admin.commons.base.ResultBean;
import cn.ycc.api.admin.commons.utils.WebHttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements InitializingBean {
    public static GlobalExceptionHandler INSTANCE = null;

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response){
        log.error("全局异常处理",e);
        if(e instanceof YccException){
            YccException yccException = (YccException) e;
            String code = yccException.getCode();
            ResultBean resultBean = ResultBean.builder().code(code).msg(yccException.getMessage()).build();
            WebHttpUtils.writeCoutomResultBeanData(response,resultBean);

        }else if (e instanceof HttpRequestMethodNotSupportedException){
            ResultBean resultBean = ResultBean.builder().code("405").msg(e.getMessage()).build();
            WebHttpUtils.writeCoutomResultBeanData(response,resultBean);

        }else if(e instanceof AuthorizationException){
            handlerAuthorizationException(response,(AuthorizationException)e);
        }
    }

    private void handlerAuthorizationException(HttpServletResponse response, AuthorizationException e) {
        if(e instanceof UnauthorizedException){
            ResultBean resultBean = ResultBean.builder().code("403").msg("您没有权限访问").build();
            WebHttpUtils.writeCoutomResultBeanData(response,resultBean);
        }else if(e instanceof UnauthenticatedException){
            ResultBean resultBean = ResultBean.builder().code("403").msg("您还没有登录").build();
            WebHttpUtils.writeCoutomResultBeanData(response,resultBean);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        GlobalExceptionHandler.INSTANCE = this;
    }
}
