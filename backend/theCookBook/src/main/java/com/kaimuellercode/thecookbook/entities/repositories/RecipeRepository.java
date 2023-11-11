package com.kaimuellercode.thecookbook.entities.repositories;

import com.kaimuellercode.thecookbook.entities.core.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
