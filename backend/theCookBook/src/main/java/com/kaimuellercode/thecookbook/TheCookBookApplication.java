package com.kaimuellercode.thecookbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories
@EntityScan
public class TheCookBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheCookBookApplication.class, args);
    }

}
