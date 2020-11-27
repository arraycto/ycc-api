package cn.ycc.api.admin.commons.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public abstract class PasswordEncoder {
    private static final  Base64.Encoder encoder;
    static {
        encoder = Base64.getEncoder();
    }

    public static String encode(String password,String salt){
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("获取Algorithm发生异常",e);
        }
        messageDigest.update(password.getBytes());
        messageDigest.update(salt.getBytes());
        byte[] digest = encoder.encode(messageDigest.digest());
        return DigestUtils.md5DigestAsHex(digest);
    }

    public static void main(String[] args) {
        String salt = UUID.randomUUID().toString().replace("-","");

        System.out.println(salt);
        System.out.println(PasswordEncoder.encode("admin",salt));
    }
}
