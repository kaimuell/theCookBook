package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;


import java.util.Collection;
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

    /**
     * Get All Recipes wich contain exactly those Ingredients, which are in the List
     * @param ingredientNames The List of Ingredients
     * @return the Recipes whose Ingredients match the List of Ingredients
     */
    List<Recipe> getRecipesWithExactlyMatchingIngredients(Collection<String> ingredientNames);

    /**
     * Get All Recipes wich contain only Ingredients, which are in the List
     * and Contain no Ingredients which are not in the List. Not all Ingredients
     * in the List have to be part of the Recipe
     * @param ingredientNames The List of Ingredients
     * @return the Recipes which can be Cooked with these Ingredients
     */
    List<Recipe> getRecipesBookableWithIngredients(Collection<String> ingredientNames);

    void saveNewRecipe(Recipe recipe) throws NoSuchUserIdError;
}
