package com.kaimuellercode.thecookbook.cookbook.repositories;

import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
