package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.model.LoginRequest;
import com.kaimuellercode.thecookbook.cookbook.model.LoginResponse;
import com.kaimuellercode.thecookbook.cookbook.service.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/auth/login")
    public Optional<LoginResponse> login(@RequestBody @Validated LoginRequest request){
        try {
            return Optional.of(authService.attemptLogin(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException ae){
            logger.info(ae.getMessage());
            return Optional.empty();
        }
    }
}
