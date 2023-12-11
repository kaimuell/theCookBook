package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.service.RecipeService;
import com.kaimuellercode.thecookbook.cookbook.entities.Recipe;
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
    private RecipeService cookBookService;

    @GetMapping(path = "/byauthor")
    public List<Recipe> getRecipeByAuthor(@RequestParam String author) {
        return cookBookService.getRecipeByAuthor(author);
    }

    @GetMapping(path = "/all")
    public List<Recipe> getAllRecipes() {
        return cookBookService.getAllRecipes();
    }


    @GetMapping(path = "with_ingredient")
    public List<Recipe> getRecipesWhichContainIngredient(@RequestParam String ingredientName) {
        return cookBookService.getRecipeWithIngredient(ingredientName);
    }

    @GetMapping(path = "cookable_with_ingredients")
    public List<Recipe> getRecipesCookableWithIngredients(@RequestParam List<String> ingredientNames) {
        return cookBookService.getRecipesCookableWithIngredients(ingredientNames);
    }

    @GetMapping(path = "withId")
    public Recipe getRecipeWithId(@RequestParam long id) {
        Optional<Recipe> r = cookBookService.getRecipeByID(id);
        if (r.isEmpty()) throw new NoSuchElementException();
        return r.get();
    }

    @PostMapping(value = "newRecipe", consumes = "application/json")
    public String insertNewRecipe(@RequestBody Recipe recipe) {
        System.out.println("AUTHOR ID : " + recipe.getAuthorId());
        try {
            cookBookService.saveNewRecipe(recipe);
        } catch (NoSuchUserIdError e1) {
            return "USER NOT EXISTENT";
        }
        return "OK";
    }
}
