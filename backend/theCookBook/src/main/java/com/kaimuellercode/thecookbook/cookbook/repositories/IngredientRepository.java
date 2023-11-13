package com.kaimuellercode.thecookbook.cookbook.repositories;

import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    @Override
    boolean existsById(Long aLong);


}
