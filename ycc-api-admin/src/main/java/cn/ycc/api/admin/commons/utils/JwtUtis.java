package cn.ycc.api.admin.commons.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.23 18:32
 */
public abstract class JwtUtis {

    private static final String PUBLIC_KEY = "88b597eada044a7ba2df4703d03c0ceecbbf4521991d4d9982df3bfece03fd46";

    /**
     *  创建jwttoken
     * @param payload  主要内容
     * @param avaliableTime 过期时间
     * @return
     */
    public static String createJwtStr(Map<String,String> payload, long avaliableTime, TimeUnit timeUnit){
        if(payload == null||payload.isEmpty()){
            return null;
        }
        long millis = timeUnit.toMillis(avaliableTime);
        Date date = new Date(System.currentTimeMillis() + millis);

        Algorithm algorithm = Algorithm.HMAC256(PUBLIC_KEY);
        JWTCreator.Builder builder = JWT.create();
        payload.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        builder.withExpiresAt(date);
        return builder.sign(algorithm);
    }

    public static DecodedJWT parseJwt(String jwtStr) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(PUBLIC_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                                .build();
        return verifier.verify(jwtStr);
    }
}
