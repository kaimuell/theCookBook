package com.kaimuellercode.thecookbook.cookbook.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipleAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrincipal principle;
    public UserPrincipleAuthenticationToken(UserPrincipal principle) {
        super(principle.getAuthorities());
        this.principle = principle;
        setAuthenticated(true);
    }



    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principle;
    }
}
