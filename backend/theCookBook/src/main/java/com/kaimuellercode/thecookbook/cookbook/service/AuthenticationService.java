package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.model.LoginResponse;
import com.kaimuellercode.thecookbook.cookbook.security.JwtIssuer;
import com.kaimuellercode.thecookbook.cookbook.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Setter
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtIssuer jwtIssuer;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public LoginResponse attemptLogin(String email, String password) throws AuthenticationException {
        logger.info("authenticating Login from " + email);

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);

        var publicUsername = principal.getUserPublicName();

        return LoginResponse.builder().accessToken(token).username(publicUsername).build();
    }
}
