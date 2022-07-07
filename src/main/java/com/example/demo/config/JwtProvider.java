package com.example.demo.config;

public interface JwtProvider {
    String generateAuthToken(String login);

    boolean validateToken(String token);
}
