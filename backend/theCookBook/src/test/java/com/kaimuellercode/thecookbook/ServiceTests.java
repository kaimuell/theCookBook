package com.kaimuellercode.thecookbook;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class ServiceTests extends TestSetup {

    @Test
    public void testInit(){
        assertFalse(recipeRepository.findAll().isEmpty());
        assertFalse(cookBookService.getAllRecipes().isEmpty());
        assertTrue(cookBookService.getRecipeByID(1L).isPresent());
    }

    @Test
    public void testGetAllRecipesWithMatchingIngredients(){
        assertNotNull(cookBookService.getRecipesWithExactlyMatchingIngredients(List.of("Mehl")));
        assertEquals(2, cookBookService.getRecipesWithExactlyMatchingIngredients(List.of("Mehl")).size());
        assertEquals(1, cookBookService.getRecipesWithExactlyMatchingIngredients(List.of("Mehl", "Schokolade")).size());

        assertNotNull(cookBookService.getRecipesBookableWithIngredients(List.of("Mehl", "Schokolade")));
        assertEquals(1, cookBookService.getRecipesBookableWithIngredients(List.of("Mehl", "Schokolade")).size());
        assertEquals(2, cookBookService.getRecipesBookableWithIngredients(List.of("Mehl", "Schokolade", "zucker")).size());
    }
}
