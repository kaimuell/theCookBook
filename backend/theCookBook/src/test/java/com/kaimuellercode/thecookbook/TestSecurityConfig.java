package com.kaimuellercode.thecookbook;

import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
@Order(1)
public class TestSecurityConfig {

    Logger logger = LoggerFactory.getLogger(TestSecurityConfig.class);

    @Bean
    public SecurityFilterChain mockApplicationSecurity(HttpSecurity http) throws Exception {
        logger.info("MOCK Security Filter Chain : start initialisation");
        http
                .cors(cors -> cors.disable())   //Disable CORS
                .csrf(csrf -> csrf.disable())   //Disable CrossSite Forgery protection
                .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .formLogin(login -> login.disable())
                .authorizeHttpRequests(registry ->
                        registry.anyRequest().permitAll());

        logger.info("MOCK Security Filter Chain : Initialised");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/**");
    }
}
