package com.kaimuellercode.thecookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //TODO ENABLE SECURITY WHEN IT IS SETUP PROPERLY
public class TheCookBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheCookBookApplication.class, args);
    }

}
