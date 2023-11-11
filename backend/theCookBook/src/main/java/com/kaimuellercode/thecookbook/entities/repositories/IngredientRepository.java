package com.kaimuellercode.thecookbook.entities.repositories;

import com.kaimuellercode.thecookbook.entities.core.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    @Override
    boolean existsById(Long aLong);
}
