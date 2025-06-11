package com.example.admin.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final Key secretKey = Keys.hmacShaKeyFor("super-secret-key-12345678901234567890".getBytes());

    public String generateToken(String login, UUID uuid) {

        return Jwts.builder()
                .subject(login) // що записати в токен (користувача)
                .issuedAt(new Date()) // коли створено
                .claim("id", uuid)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // коли закінчується (24 години)
                .signWith(secretKey) // підписати токен секретом
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(new SecretKeySpec(secretKey.getEncoded(), SignatureAlgorithm.HS256.getJcaName()))
                    .build()
                    .parse(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public UUID getId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(new SecretKeySpec(secretKey.getEncoded(), SignatureAlgorithm.HS256.getJcaName()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String idString = claims.get("id", String.class);
            return UUID.fromString(idString);
        } catch (JwtException | IllegalArgumentException e) {
            return null; // або можна кинути помилку, якщо хочеш
        }
    }

}



