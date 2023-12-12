package com.kaimuellercode.thecookbook;

import com.kaimuellercode.thecookbook.cookbook.configuration.WebSecurityConfig;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SecurityTests extends TestSetup{

    PasswordEncoder passwordEncoder = WebSecurityConfig.passwordEncoder();
    @Test
    public void testPasswordEncryption(){
        String pw = passwordEncoder.encode("test");
        assertTrue(passwordEncoder.matches("test", pw));
    }
}
