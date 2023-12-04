package com.kaimuellercode.thecookbook;

import com.google.gson.reflect.TypeToken;
import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.core.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTests extends TestSetup {

    @Test
    public void testGetAllRecipes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/all")
                )
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
    }

    @Test
    public void testGetRecipesByAuthor() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/byauthor?author=bob2")
                )
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println("OUTPUT: " + response);
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
        //assertEquals("bob2", recipes.get(0).getAuthor().getName());
    }

    @Test
    public void testGetRecipesWithIngredient() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/with_ingredient?ingredientName=Mehl")
                )
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println("ANSWER : " + response);
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertFalse(recipes.isEmpty());
        Iterable<Recipe> recipies = recipeRepository.findAll();
        recipies.forEach(rec ->
                System.out.println("RECIPE: id=" + rec.getId() + ", name="+rec.getName() + ", author=" + rec.getAuthorId()));
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach(ing ->
                System.out.println("INGREDIENT: id="+ing.getId() + ", Name="+ing.getName() + ", recipe_id=" + ing.getRecipe_id()));

        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/with_ingredient?ingredientName=Uran")
                )
                .andExpect(status().isOk())
                .andReturn();
        String response2 = result2.getResponse().getContentAsString();
        System.out.println("ANSWER : " + response2);
        assertNotNull(response2);
        assertNotNull(result2);

        List<Recipe> recipes2 = gson.fromJson(response2, listType);

        assertNotNull(recipes2);
        assertFalse(recipes2.isEmpty());
        assertEquals(1, recipes2.size());
        assertEquals("Urankuchen", recipes2.get(0).getName());
    }

    @Test
    public void getCookableRecipes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/cookable_with_ingredients?ingredientNames=Mehl,Schokolade")
                )
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println("ANSWER : " + response);
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertFalse(recipes.isEmpty());
        assertEquals(1, recipes.size());

        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/cookable_with_ingredients?ingredientNames=Mehl,Schokolade,zucker")
                )
                .andExpect(status().isOk())
                .andReturn();
        String response2 = result2.getResponse().getContentAsString();
        List<Recipe> recipes2 = gson.fromJson(response2, listType);
        assertEquals(2, recipes2.size());
    }
    @Test
    public void testGetUsernameMapping() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(status().isOk())
                .andReturn();
        String allUsers = result.getResponse().getContentAsString();
        //Type listType = new TypeToken<List<User>>() {}.getType();
        //List<User> users = gson.fromJson(allUsers, listType);
        //long id = users.get(0).getId();
        //String name = users.get(0).getName();

        MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders
                .get("/users/username?id=3")).andExpect(status()
                .isOk())
                .andReturn();
        String nameReturned = result2.getResponse().getContentAsString();
        assertEquals("bob2", nameReturned);
    }

}
