package com.kuang.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kuang.entity.User;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static String SECRET = "token!Q@W#E$R";

    /**
     * 生产token
     */
    public static String getToken(Map<String, String> map) {  //map里面是前端传过来的用户信息，最后存到Claim里面
        JWTCreator.Builder builder = JWT.create();  //先创建

        //存储信息到 payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //默认7天过期

        builder.withExpiresAt(instance.getTime()); //指定令牌的过期时间
        String token = builder.sign(Algorithm.HMAC256(SECRET)); //签名 + 密钥
        return token;
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token) {
        //如果有任何验证异常，此处都会抛出异常
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        return decodedJWT;
    }

    public static User getCurrentUser(){
        try{
            return UserHolder.getUser();
        }catch (Exception e){
            return null;
        }
    }

//    /**
//     * 获取token中的 payload
//     */
//    public static DecodedJWT getToken(String token) {
//        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
//        return decodedJWT;
//    }

}
