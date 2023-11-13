package com.kaimuellercode.thecookbook.cookbook;

import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CookBookServiceImplementation implements CookBookService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Recipe> getAllRecipes() {
        return entityManager.createQuery("SELECT r from Recipe r", Recipe.class).getResultList();
    }

    public Optional<Recipe> getRecipeByID(Long id){
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> getRecipeByAuthor(String username) {
        List<Recipe> res = entityManager
                .createQuery("SELECT r FROM Recipe r WHERE r.author.name LIKE :username", Recipe.class)
                .setParameter("username", username)
                .getResultList();
        return res;
    }


}
