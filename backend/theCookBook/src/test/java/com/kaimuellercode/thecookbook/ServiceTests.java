package com.kaimuellercode.thecookbook;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ServiceTests extends TestSetup {

    @Test
    public void testInit(){
        assertFalse(recipeRepository.findAll().isEmpty());
        assertFalse(cookBookService.getAllRecipes().isEmpty());
        assertTrue(cookBookService.getRecipeByID(1L).isPresent());


    }
}
