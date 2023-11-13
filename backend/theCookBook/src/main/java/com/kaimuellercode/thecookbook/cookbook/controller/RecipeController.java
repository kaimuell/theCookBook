package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.CookBookService;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    CookBookService cookBookService;

    @GetMapping(path="/byauthor")
    public List<Recipe> getRecipeByAuthor(@RequestParam String author){
        return cookBookService.getRecipeByAuthor(author);
    }

    @GetMapping(path = "/all")
    public List<Recipe> getAllRecipes(){
        return cookBookService.getAllRecipes();
    }
}
