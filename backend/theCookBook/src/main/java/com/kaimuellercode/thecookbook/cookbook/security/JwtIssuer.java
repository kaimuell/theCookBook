package com.kaimuellercode.thecookbook.cookbook.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


/**
 * Issues an Encoded UserDetail
 */
@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties jwtProperties;
    public String issue(long userId, String email, String name, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus((Duration.of(1, ChronoUnit.HOURS))))
                .withClaim("e", email)
                .withClaim("n", name)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
