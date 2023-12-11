package com.kaimuellercode.thecookbook.cookbook.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The JWT Decoder Uses HMAC256 with the secret key, provided by the {@link JwtProperties}
 */


@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties properties;

    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }
}
