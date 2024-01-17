package com.kaimuellercode.thecookbook.cookbook.configuration;
import com.kaimuellercode.thecookbook.cookbook.security.JwtAuthenticationFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EntityScan
public class WebSecurityConfig{

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);


    @Primary
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        logger.info("Security Filter Chain : start initialisation");
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .cors(cors -> cors.disable())   //Disable CORS TODO FIX IN PRODUCTION
                .csrf(csrf -> csrf.disable())   //Disable CrossSite Forgery protection TODO FIX IN PRODUCTION
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(login -> login.disable())
                .securityMatcher("/**")
                .authorizeHttpRequests( registry -> registry
                        .requestMatchers("/recipe/**").permitAll()
                        .requestMatchers("recipe/newRecipe").authenticated()
                        .requestMatchers("/users/username").permitAll()
                        .requestMatchers("users/newUser").permitAll()
                        .requestMatchers("/auth/login*").permitAll()
                        .requestMatchers("/users/all").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        logger.info("Security Filter Chain : Initialised");
        return http.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
