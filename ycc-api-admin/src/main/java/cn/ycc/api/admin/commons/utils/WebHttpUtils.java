package cn.ycc.api.admin.commons.utils;

import cn.ycc.api.admin.commons.base.ResultBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 23:21
 */
public class WebHttpUtils {

    /**
     * 输出自定义json
     * @param response
     * @param resultBean
     */
    public static void writeCoutomResultBeanData(HttpServletResponse response, ResultBean resultBean){
        response.setHeader("Content-Type","application/json;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.println(JsonUtils.objectToJson(resultBean));
            writer.flush();
        } catch (IOException e) {
        }
    }

    /**
     *  获取request中的远程ip地址
     * @param request
     * @return
     */
    public static String getIPAddrForRequest(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Real-IP");
        }
        if("0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())){
            ip="127.0.0.1";
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }

    /**
     *  获取所有请求头信息
     * @param request
     * @return
     */
    public static Map<String,Object> getRequestHeaders(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        if( headerNames == null){
           return Collections.EMPTY_MAP;
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            resultMap.put(headerName,request.getHeader(headerName));
        }
        return resultMap;
    }
}
