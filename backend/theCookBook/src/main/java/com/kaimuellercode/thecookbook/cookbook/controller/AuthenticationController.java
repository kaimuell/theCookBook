package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.model.LoginRequest;
import com.kaimuellercode.thecookbook.cookbook.model.LoginResponse;
import com.kaimuellercode.thecookbook.cookbook.service.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/auth/login") @CrossOrigin
    public Optional<LoginResponse> login(@ModelAttribute @Validated LoginRequest request){
        logger.info("Login attempt by : " + request.getEmail());
        try {
            var token = authService.attemptLogin(request.getEmail(), request.getPassword());
            logger.info("Login succeded");
            return Optional.of(token);
        } catch (AuthenticationException ae){
            logger.info("Login not successfull");
            logger.info(ae.getMessage());
            return Optional.empty();
        }
    }
}
