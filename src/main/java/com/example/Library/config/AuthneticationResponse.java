package com.example.Library.config;

public class AuthneticationResponse {
    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public AuthneticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
