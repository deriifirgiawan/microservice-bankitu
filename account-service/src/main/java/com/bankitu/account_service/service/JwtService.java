package com.bankitu.account_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final long EXPIRATION_TIME = 30L * 24 * 60 * 60 * 1000;
    private static final Date EXPIRED_DATE = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEYS;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String username, Map<String, Object> extraClaims) {
        return buildToken(extraClaims, username, EXPIRED_DATE);
    }

    public boolean isTokenValid(String token, Map<String, Object> claims) {
        try {
            Date expiration = ((Claims) claims).getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private String buildToken(Map<String, Object> extraClaims, String username, Date expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEYS);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
