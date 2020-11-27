package cn.ycc.api.admin.commons.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.24 20:53
 */
public class JwtAutheticationToken extends UsernamePasswordToken {
    private String token;

    public JwtAutheticationToken(String token) {
        super("a","a");
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
