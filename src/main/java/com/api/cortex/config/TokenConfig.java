package com.api.cortex.config;


import com.api.cortex.model.entity.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenConfig {

    @Value("{jwt.secret}")
    private String secret;

    private Instant generationExpiration(){
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }


    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            String token = JWT.create()
                    .withIssuer("cortex-api")
                    .withSubject(user.getEmail())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(generationExpiration())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("error to generate the token: "  + e);
        }
    }


    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error validate token: " + e);
        }

    }






}
