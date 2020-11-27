package cn.ycc.api.admin.commons.shiro;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 21:12
 */
public class JwtTokenAuthenticationException extends AuthenticationException {
    private JWTVerificationException exception;

    public JwtTokenAuthenticationException(JWTVerificationException exception) {
        this.exception = exception;
    }

    public JWTVerificationException getException() {
        return exception;
    }
}

