package com.booking.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {


    private final String SECRET = "ASDFGHJKLZXCVBNMQWERTYUIOP1234567890";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long TOKEN_EXPIRY_MILLI_SECONDS = 15*60 * 1000;

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY_MILLI_SECONDS))
                .signWith(key)
                .header().add("typ", "JWT")
                .and().compact();
    }

    public String extractUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
