package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.CookBookService;
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
}
