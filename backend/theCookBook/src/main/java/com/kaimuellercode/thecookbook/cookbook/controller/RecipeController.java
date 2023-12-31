package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.service.RecipeService;
import com.kaimuellercode.thecookbook.cookbook.entities.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @GetMapping(path = "byauthor")
    public List<Recipe> getRecipeByAuthor(@RequestParam String author) {
        return recipeService.getRecipeByAuthor(author);
    }

    @GetMapping(path = "all")
    public List<Recipe> getAllRecipes() {
        logger.info("All recipes requested");
        return recipeService.getAllRecipes();
    }


    @GetMapping(path = "with_ingredient")
    public List<Recipe> getRecipesWhichContainIngredient(@RequestParam String ingredientName) {
        return recipeService.getRecipeWithIngredient(ingredientName);
    }

    @GetMapping(path = "cookable_with_ingredients")
    public List<Recipe> getRecipesCookableWithIngredients(@RequestParam List<String> ingredientNames) {
        return recipeService.getRecipesCookableWithIngredients(ingredientNames);
    }

    @GetMapping(path = "withId")
    public Recipe getRecipeWithId(@RequestParam long id) {
        Optional<Recipe> r = recipeService.getRecipeByID(id);
        //logger.info(String.valueOf(r.get().getAuthorId()));
        if (r.isEmpty()) throw new NoSuchElementException();
        return r.get();
    }

    @PostMapping(value = "newRecipe", consumes = "application/json")
    public String insertNewRecipe(@RequestBody Recipe recipe) {
        logger.info("New Recipe inserted by Author with ID : " + recipe.getAuthorId());
        try {
            recipeService.saveNewRecipe(recipe);
        } catch (NoSuchUserIdError e1) {
            return "USER NOT EXISTENT";
        }
        return "OK";
    }
}
