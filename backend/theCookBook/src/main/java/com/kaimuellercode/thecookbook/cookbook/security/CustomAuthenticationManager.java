package com.kaimuellercode.thecookbook.cookbook.security;

import com.kaimuellercode.thecookbook.cookbook.configuration.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Primary
@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder = WebSecurityConfig.passwordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String useremail = (String) authentication.getPrincipal();
        UserPrincipal user;
        try {
            user = (UserPrincipal) userDetailsService.loadUserByUsername(useremail);
        } catch (UsernameNotFoundException exception){
            user = null;
        }
        String password = (String) authentication.getCredentials();
        if (user == null) {
            throw new AuthenticationException("No Such User " + useremail) {
            };
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Wrong Password from " + useremail) {
            };
        }
        return new UserPrincipleAuthenticationToken(user);
    }
}
