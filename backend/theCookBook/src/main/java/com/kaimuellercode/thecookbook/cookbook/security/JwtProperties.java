package com.kaimuellercode.thecookbook.cookbook.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Class for the JWT Properties
 */


@Setter
@Getter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {
    /**
     * Secret key, used for JWT
     */
    private String secretKey;
}
