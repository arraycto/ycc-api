package cn.ycc.api.admin.commons.shiro;

import cn.ycc.api.admin.commons.base.ResultBean;
import cn.ycc.api.admin.commons.utils.WebHttpUtils;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 22:19
 */
@Slf4j
public class JwtTokenLoginAdapterFilter extends PathMatchingFilter {


    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);

        String token = httpServletRequest.getHeader("token");
        if(token == null){
            ResultBean resultBean = ResultBean.builder().code("402").msg("你还未登录").build();
            WebHttpUtils.writeCoutomResultBeanData(httpServletResponse, resultBean);
            return false;
        }

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new JwtAutheticationToken(token));
        }catch (JwtTokenAuthenticationException e){
            JWTVerificationException verificationException = e.getException();
            ResultBean.ResultBeanBuilder builder = ResultBean.builder();
            if(verificationException instanceof TokenExpiredException){
                builder.code("408").msg("登录身份已过期,请重新登录");
            }else if(verificationException instanceof SignatureVerificationException||verificationException instanceof AlgorithmMismatchException){
                builder.code("406").msg("Token签名验证失败");
            }
            WebHttpUtils.writeCoutomResultBeanData(httpServletResponse,builder.build());
            return false;
        }
        return true;
    }

}
