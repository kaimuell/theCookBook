package com.kaimuellercode.thecookbook.cookbook.repositories;

import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
