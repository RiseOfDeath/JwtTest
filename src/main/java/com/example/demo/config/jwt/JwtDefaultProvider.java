package com.example.demo.config.jwt;

import com.example.demo.config.JwtProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtDefaultProvider implements JwtProvider {

    @Value("${jwt.authSecret}")
    private String jwtAuthSecret;
    @Value("${jwt.refreshSecret}")
    private String jwtRefreshSecret;
    @Value("${jwt.refreshUidSecret}")
    private String jwtRefreshUidSecret;
    @Value("${jwt.minutesToExpireAuth}")
    private int minutesToExpireAuth;
    @Value("${jwt.hoursToExpireRefresh}")
    private int hoursToExpireRefresh;

    @Override public String generateAuthToken(String login) {
        Date date = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        Date expirationDate = Date.from(ldt.plusMinutes(minutesToExpireAuth).atZone(ZoneId.systemDefault()).toInstant());
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "Test");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(login)
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtAuthSecret)
                .compact();
    }

    @Override public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtAuthSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("invalid token");
        }
        return false;
    }
}
