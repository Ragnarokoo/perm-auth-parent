package com.zhaostudy.common.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * 生成JSON Web令牌的工具类
 * @User：mac
 * @Author: yykk
 * @Date: 2022/11/04/2:55 PM
 * @Description: https://www.cnblogs.com/zhaostudy/
 */
public class JwtHelper {


    /**
     * 令牌过期时间
     */
    private static long tokenExpiration = 365L * 24 * 60 * 60 * 1000;

    /**
     * 加密秘钥
     */
    private static final String tokenSignKey = "123456";

    /**
     * 创建令牌：根据用户id和用户名称
     *
     * @param userId   用户id
     * @param username 用户名
     * @return {@link String}
     */
    public static String createToken(String userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 从token字符串中得到用户id
     *
     * @param token 令牌
     * @return {@link Long}
     */
    public static String getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId = (String) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从token字符串中获得用户名
     *
     * @param token 令牌
     * @return {@link String}
     */
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return "";
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken("1", "admin");
        System.out.println(token);

        String userId = JwtHelper.getUserId(token);
        System.out.println(userId);

        String username = JwtHelper.getUsername(token);
        System.out.println(username);
    }
}