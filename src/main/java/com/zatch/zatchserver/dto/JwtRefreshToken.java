package com.zatch.zatchserver.dto;

public class JwtRefreshToken {

    private String token;

    public JwtRefreshToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
