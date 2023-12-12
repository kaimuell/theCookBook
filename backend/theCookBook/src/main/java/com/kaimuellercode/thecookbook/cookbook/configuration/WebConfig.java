package com.kaimuellercode.thecookbook.cookbook.configuration;

import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import com.kaimuellercode.thecookbook.cookbook.service.RecipeService;
import com.kaimuellercode.thecookbook.cookbook.service.RecipeServiceImplementation;
import com.kaimuellercode.thecookbook.cookbook.service.UserService;
import com.kaimuellercode.thecookbook.cookbook.service.UserServiceImplementation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan
public class WebConfig {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  RecipeRepository recipeRepository;

    @Autowired
    private  IngredientRepository ingredientRepository;

    @Autowired
    @PersistenceContext
    private  EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    @Primary
    @Bean
    public RecipeService getCookBookService() {
        logger.info("Initialising RecipeService");
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
        logger.info("Initialising UserService");
        UserServiceImplementation us = new UserServiceImplementation();
        us.setUserRepository(userRepository);
        us.setPasswordEncoder(WebSecurityConfig.passwordEncoder());
        return us;
    }

}

