package com.metacoding.springv2._core.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.metacoding.springv2.user.User;

import jakarta.servlet.http.HttpServletRequest;

public class JwtProvider {

    // Bearer JWT -> JWT만 추출하기
    public static String 토큰추출하기(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        if (jwt != null && jwt.startsWith(JwtUtil.TOKEN_PREFIX)) {
            jwt = jwt.replace("Bearer ", "");
            return jwt;
        }

        return null;
    }

    // 토큰을 검증하고 Authentication 반환
    public static Authentication 인증객체만들기(String token) {
        User user = JwtUtil.verify(token);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        return authentication;
    }

}