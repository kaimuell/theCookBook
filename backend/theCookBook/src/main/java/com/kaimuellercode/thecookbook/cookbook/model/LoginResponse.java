package com.kaimuellercode.thecookbook.cookbook.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class LoginResponse implements Serializable {
    private final String accessToken;
    private final String username;
}
