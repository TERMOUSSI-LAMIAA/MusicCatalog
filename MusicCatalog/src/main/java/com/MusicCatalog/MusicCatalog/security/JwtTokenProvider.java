package com.MusicCatalog.MusicCatalog.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "secureJwtSecretKey";
    private final long jwtExpirationMs = 3600000;

    public String generateToken(String username, String role) {
        return JWT.create()
                .withIssuer("MusicCatalogApp")
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtSecret))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecret)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
