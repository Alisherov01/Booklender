package com.example.Library.authentificationRequest;

public class AuthneticationResponse {
    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public AuthneticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
