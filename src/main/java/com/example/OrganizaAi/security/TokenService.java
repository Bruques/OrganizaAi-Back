package com.example.OrganizaAi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.OrganizaAi.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;
    public String generateToken(UserModel user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT
                    .create()
                    .withIssuer("OrganizaAi")
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException excepetion) {
            throw  new RuntimeException("Error while authenticating");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String returnTest = JWT
                    .require(algorithm)
                    .withIssuer("OrganizaAi")
                    .build()
                    .verify(token)
                    .getSubject();
            return returnTest;
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String extractUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("OrganizaAi")
                    .build()
                    .verify(token);

            return decodedJWT.getClaim("userId").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extractUserIdFromHeader(String header) {
        String token = header.replace("Bearer ", "");
        return extractUserId(token);
    }
}
