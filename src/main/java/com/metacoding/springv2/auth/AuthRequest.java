package com.metacoding.springv2.auth;

import lombok.Data;

public class AuthRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;

    }

}