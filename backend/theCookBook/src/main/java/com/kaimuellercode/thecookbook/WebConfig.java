package com.kaimuellercode.thecookbook;

import com.kaimuellercode.thecookbook.cookbook.CookBookService;
import com.kaimuellercode.thecookbook.cookbook.CookBookServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class WebConfig {
    /*
    @Bean
    public CookBookService getCookBookService(){
        return new CookBookServiceImplementation();
    }

     */
}
