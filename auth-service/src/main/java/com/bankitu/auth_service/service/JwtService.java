package com.bankitu.auth_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Date EXPIRED_DATE = new Date(new Date().getTime() + 30 * 24 * 60 * 60 * 1000L);

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEYS;

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String userId) {
        return generateToken(new HashMap<>(), userId);
    }

    public String generateToken(Map<String, Object> extraClaims, String userId) {
        return buildToken(extraClaims, userId, EXPIRED_DATE);
    }

    public boolean isTokenValid(String token, String userId) {
        final String username = extractUserId(token);
        return (username.equals(userId));
    }

    public String buildToken(Map<String, Object> extraClaims, String userId, Date expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userId)  // We use userId (or email, etc.)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEYS);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
