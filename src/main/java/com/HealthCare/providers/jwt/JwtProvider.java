package com.HealthCare.providers.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    private String secretKey = "secret-key";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email) {
        Claims claims = (Claims) Jwts.claims().setSubject(email);


        Date dateNow = new Date();
        long validityInMilliseconds = 3600000;
        Date tokenValidity = new Date(dateNow.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
