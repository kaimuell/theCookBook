package com.kaimuellercode.thecookbook;

import com.google.gson.reflect.TypeToken;
import com.kaimuellercode.thecookbook.cookbook.entities.*;
import com.kaimuellercode.thecookbook.cookbook.model.UserInitialInformation;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.List;

import static com.kaimuellercode.thecookbook.cookbook.entities.IngredientUnit.GRAMM;
import static com.kaimuellercode.thecookbook.cookbook.entities.IngredientUnit.KILOGRAMM;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTests extends TestSetup {

    @Test
    public void testGetAllRecipes() throws Exception {
        System.out.println("ControllerTests:testGetAllRecipes");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/all")
                )
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Type listType = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
        System.out.println("passed");
    }

    @Test
    public void testGetRecipesByAuthor() throws Exception {
        System.out.println("ControllerTests:testGetRecipesByAuthor");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/byauthor?author=bob2")
                )
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println("OUTPUT: " + response);
        Type listType = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertNotNull(recipes);
        assertFalse(recipes.isEmpty());
        System.out.println("passed");
    }

    @Test
    public void testGetRecipesWithIngredient() throws Exception {

        System.out.println("ControllerTests:testGetRecipesWithIngredient");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/with_ingredient?ingredientName=Mehl")
                )
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println("ANSWER : " + response);
        Type listType = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipes = gson.fromJson(response, listType);
        assertFalse(recipes.isEmpty());
        //printRecipes();

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
        System.out.println("passed");
    }

    private void printRecipes() {
        Iterable<Recipe> recipies = recipeRepository.findAll();
        recipies.forEach(rec ->
                System.out.println("RECIPE: id=" + rec.getId() + ", name=" + rec.getName() + ", author=" + rec.getAuthorId()));
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.forEach(ing ->
                System.out.println("INGREDIENT: id=" + ing.getId() + ", Name=" + ing.getName() + ", recipe_id=" + ing.getRecipeId()));
    }

    @Test
    public void getCookableRecipes() throws Exception {
        System.out.println("ControllerTests:getCookableRecipes");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipe/cookable_with_ingredients?ingredientNames=Mehl,Schokolade")
                )
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println("ANSWER : " + response);
        Type listType = new TypeToken<List<Recipe>>() {
        }.getType();
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
        System.out.println("passed");
    }
    @Test
    public void testGetUsernameMapping() throws Exception {
        System.out.println("ControllerTests:testGetUsernameMapping");
        User bob2 = userRepository.findByName("bob2").get();
         MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/username?id=" + bob2.getId())).andExpect(status()
                        .isOk())
                .andReturn();
        String nameReturned = result2.getResponse().getContentAsString();
        assertEquals("bob2", nameReturned);
        User u = new User("John Doe", "jk132i3j21990", "Johnathan@doe.net", UserRights.ROlE_USER);
        UserInitialInformation uAp = new UserInitialInformation(u.getName(), u.getEmail(), u.getPwHash());
        String json = gson.toJson(uAp);
        MvcResult mvc = mockMvc.perform(MockMvcRequestBuilders.post("/users/newUser")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvc.getResponse().getContentAsString();
        User u2 = gson.fromJson(body, User.class);
        assertEquals(u.getName(), u2.getName());
        System.out.println("passed");
    }


    @Test
    public void testPostNewRecipe() throws Exception {
        System.out.println("ControllerTests:testPostNewRecipe");
        Long userid = userRepository.findAll().get(0).getId();
        long before = recipeRepository.count();
        System.out.println("USERID : " + userid);
        userRepository.findAll().forEach(user -> System.out.println(user.getId()));


        Recipe recipe = new Recipe();
        recipe.setName("Brot");
        recipe.setAuthorId(userid);
        recipe.setIngredientList(List.of(new Ingredient(100F, "Gold", GRAMM),
                new Ingredient(1.5F, "Salz", KILOGRAMM)));
        recipe.setInstructions("COOK WITH CARE???");
        String recipeJSON = gson.toJson(recipe);
        System.out.println(recipeJSON);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/recipe/newRecipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(recipeJSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(before + 1, recipeRepository.count());
        assertEquals(result.getResponse().getContentAsString(), "OK");

        recipe.setAuthorId(userid + 105);
        recipeJSON = gson.toJson(recipe);
        result = mockMvc.perform(
                MockMvcRequestBuilders.post("/recipe/newRecipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(recipeJSON)
        ).andExpect(status().isOk()).andReturn();
        assertEquals(result.getResponse().getContentAsString(), "USER NOT EXISTENT");
        System.out.println("passed");
    }

}
