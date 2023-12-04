package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.service.CookBookService;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private CookBookService cookBookService;

    @GetMapping(path="/byauthor")
    public List<Recipe> getRecipeByAuthor(@RequestParam String author){
        return cookBookService.getRecipeByAuthor(author);
    }

    @GetMapping(path = "/all")
    public List<Recipe> getAllRecipes(){
        return cookBookService.getAllRecipes();
    }


    @GetMapping(path="with_ingredient")
    public List<Recipe> getRecipesWhichContainIngredient(@RequestParam String ingredientName){
        return cookBookService.getRecipeWithIngredient(ingredientName);
    }
    @GetMapping(path = "cookable_with_ingredients")
    public List<Recipe> getRecipesCookableWithIngredients(@RequestParam List<String> ingredientNames){
        return cookBookService.getRecipesBookableWithIngredients(ingredientNames);
    }

    @PostMapping(value="newRecipe", consumes = "application/json")
    public String insertNewRecipe(@RequestBody Recipe recipe){
        System.out.println("AUTHOR ID : " + recipe.getAuthorId());
        try {
            cookBookService.saveNewRecipe(recipe);
        } catch (NoSuchUserIdError e1){
            return "USER NOT EXISTENT";
        }
        return "OK";
    }
}
