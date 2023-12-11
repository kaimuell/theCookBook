package com.kaimuellercode.thecookbook.cookbook;

import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import com.kaimuellercode.thecookbook.cookbook.service.RecipeService;
import com.kaimuellercode.thecookbook.cookbook.service.RecipeServiceImplementation;
import com.kaimuellercode.thecookbook.cookbook.service.UserService;
import com.kaimuellercode.thecookbook.cookbook.service.UserServiceImplementation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

public class configuration {
    @Configuration
    @ComponentScan
    public static class WebConfig {

        @Autowired
        UserRepository userRepository;

        @Autowired
        RecipeRepository recipeRepository;

        @Autowired
        IngredientRepository ingredientRepository;

        @Autowired
        @PersistenceContext
        EntityManager entityManager;

        @Primary
        @Bean
        public RecipeService getCookBookService() {
            RecipeServiceImplementation cbs = new RecipeServiceImplementation();
            cbs.setUserRepository(userRepository);
            cbs.setRecipeRepository(recipeRepository);
            cbs.setIngredientRepository(ingredientRepository);
            cbs.setEntityManager(entityManager);
            return cbs;
        }

        @Bean
        @Primary
        public UserService getUserservice() {
            UserServiceImplementation us = new UserServiceImplementation();
            us.setUserRepository(userRepository);
            return us;
        }

    }
}
