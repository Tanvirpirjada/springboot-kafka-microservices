package com.HireMatrix.authservice.config;


import com.HireMatrix.authservice.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;



@Component
public class JwtUtils {

    private KeyPair keyPair;

    @PostConstruct
    public void initKeys() {
        this.keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("type",user.getType())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 30))) // 30 min
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("token_type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 7)) // 7 days
                )
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    public String extractUserId(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(keyPair.getPublic())   // now works ✔
                .build()
                .parseSignedClaims(token);
    }

}