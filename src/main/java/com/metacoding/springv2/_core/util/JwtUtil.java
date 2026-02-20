package com.metacoding.springv2._core.util;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.metacoding.springv2.user.User;

public class JwtUtil {
    public static final String HEADER = "Authorization"; // HTTP 헤더 이름
    public static final String TOKEN_PREFIX = "Bearer "; // 토큰 접두사
    public static final String SECRET = "메타코딩시크릿키"; // 토큰 서명에 사용될 비밀 키 (강력하게 변경 필요!)
    public static final Long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7; // 토큰 유효기간 7일

    // JWT 토큰 생성
    public static String create(User user) {
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .withClaim("roles", user.getRoles())
                .sign(Algorithm.HMAC512(SECRET));
        return accessToken;
    }

    // JWT 토큰 검증 및 디코딩
    public static User verify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build()
                .verify(jwt); // 토큰 검증

        Integer id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getSubject();
        String roles = decodedJWT.getClaim("roles").asString();

        return User.builder().id(id).username(username).roles(roles).build();
    }
}
