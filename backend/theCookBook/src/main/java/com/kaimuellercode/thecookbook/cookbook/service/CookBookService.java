package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.core.Recipe;


import java.util.List;
import java.util.Optional;


public interface CookBookService {
    /**
     * Get all saved Recipes
     * @return The List of Recipes
     */
    List<Recipe> getAllRecipes();

    /**
     * Get The Recipe with the given id
     * @param id the id
     * @return the Recipe if Present
     */
    Optional<Recipe> getRecipeByID(Long id);

    /**
     * Get all Recipes wich are authored by the user with the specified username
     * @param username the username
     * @return All Recipes with the user as Author
     */
    List<Recipe> getRecipeByAuthor(String username);

    /**
     * Get all Recipes wich contain the given ingredient
     * @param ingredientName the name of the ingredient
     * @return all Recipes wich contain this ingredient
     */
    List<Recipe> getRecipeWithIngredient(String ingredientName);
}
