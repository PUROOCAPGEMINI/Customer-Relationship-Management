package com.crm.auth_service.services;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtService {


    public static final String SECRET = "7863725367566B59703373367639792F423F4528482B4D625165";

    private javax.crypto.SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String validateToken(final String token) {
        Jws<Claims> claims = Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
        if (claims.getPayload().getExpiration().before(new Date())) {
            throw new RuntimeException("Token is expired");
        }
        if (claims.getPayload().get("username") == null) {
            throw new RuntimeException("Token is invalid");
        }
        if (claims.getPayload().get("authorities") == null) {
            throw new RuntimeException("Token is invalid");
        }
        return claims.getPayload().get("authorities") .toString();
    }

    public String generateToken(String userName, String role) {
        return createToken(userName, role);
    }

    private String createToken(String userName, String role) {
        return Jwts.builder().subject("JWT Token")
                .claim("username", userName)
                .claim("authorities", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey()).compact();
    }
}
