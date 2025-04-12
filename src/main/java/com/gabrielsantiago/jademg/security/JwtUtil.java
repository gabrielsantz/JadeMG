package com.gabrielsantiago.jademg.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    
    @Value("${jwt.secret}")
    private String secretKey;
    
    @Value("${jwt.expiration}")
    private long expirationTime;
    
    private SecretKey key;

    public JwtUtil() {
        // Construtor vazio para inicialização
    }

    private SecretKey getKey() {
        if (key == null) {
            key = Keys.hmacShaKeyFor(secretKey.getBytes());
        }
        return key;
    }

    public String generateToken(String username) {
        logger.debug("Generating token for user: {}", username);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey())
                .compact();
        logger.debug("Token generated successfully");
        return token;
    }

    public String extractUsername(String token) {
        try {
            Claims claims = extractAllClaims(token);
            String username = claims.getSubject();
            logger.debug("Extracted username from token: {}", username);
            return username;
        } catch (Exception e) {
            logger.error("Error extracting username from token: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isTokenValid(String token) {
        try {
            logger.debug("Validating token");
            Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token);
            logger.debug("Token is valid");
            return true;
        } catch (Exception e) {
            logger.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        logger.debug("Extracting claims from token");
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
