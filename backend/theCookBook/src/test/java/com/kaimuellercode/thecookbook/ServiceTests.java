package com.kaimuellercode.thecookbook;


import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.core.IngredientUnit;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
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

    @Test
    public void testInsertRecipe(){
        Long firstID = userRepository.findAll().get(0).getId();
        Recipe r = new Recipe();
        r.setName("Test");
        r.setAuthorId(firstID);
        r.setInstructions("asdslakdak");
        Ingredient i = new Ingredient();
        i.setName("ZULUWARRIOR");
        i.setAmount(1F);
        i.setUnit(IngredientUnit.PINCH);
        r.setIngredientList(List.of(i));

        cookBookService.saveNewRecipe(r);
        recipeRepository.findAll().forEach(rec -> System.out.println(rec.getName()));
        assertFalse(cookBookService.getRecipeByAuthor(userRepository.findById(firstID).get().getName()).isEmpty());
        ingredientRepository.findAll().forEach(ing -> System.out.println("Ingredientname : " + ing.getName() + " with RecipeId: " + ing.getRecipe_id()));
        assertFalse(cookBookService.getRecipesBookableWithIngredients(List.of("ZULUWARRIOR")).isEmpty());
        Recipe r2 = new Recipe();
        r2.setName("A Recipe");
        r2.setAuthorId(600L);
        r2.setInstructions("asdslakdak");

        assertThrows(NoSuchUserIdError.class, () -> cookBookService.saveNewRecipe(r2));
    }
}
