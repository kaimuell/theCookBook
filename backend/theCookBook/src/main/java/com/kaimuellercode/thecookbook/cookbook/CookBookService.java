package com.kaimuellercode.thecookbook.cookbook;

import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CookBookService {
    List<Recipe> getAllRecipes();

    Optional<Recipe> getRecipeByID(Long id);

    List<Recipe> getRecipeByAuthor(String username);

}
