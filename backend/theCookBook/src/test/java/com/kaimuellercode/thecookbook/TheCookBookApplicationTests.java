package com.kaimuellercode.thecookbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.core.User;
import com.kaimuellercode.thecookbook.cookbook.core.UserRights;
import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.List;

import static com.kaimuellercode.thecookbook.cookbook.core.IngredientUnit.GRAMM;
import static com.kaimuellercode.thecookbook.cookbook.core.IngredientUnit.KILOGRAMM;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class TheCookBookApplicationTests {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    Gson gson = new Gson();



    @BeforeEach
    public void fillDatabase(){
        Ingredient i1 = new Ingredient();
        i1.setName("zucker");
        i1.setAmount(100.0F);
        i1.setUnit(KILOGRAMM);

        Ingredient i2 = new Ingredient();
        i2.setName("mehl");
        i2.setAmount(200.0F);
        i2.setUnit(GRAMM);

        ingredientRepository.save(i1);
        ingredientRepository.save(i2);

        User u = new User("bob2", "wdsadaswdasdasdas", "bob2@bob.net", UserRights.USER);

        userRepository.save(u);

        List<Ingredient> ingredients = List.of(i1,i2);

        Recipe r = new Recipe();
        r.setAuthor(u);
        r.setName("Teig");
        r.setInstructions("bla");
        r.setIngredientList(ingredients);
        r.setImagePath("./saddasd.jpg");
        recipeRepository.save(r);
        Iterable<Recipe> recipies = recipeRepository.findAll();
        recipies.forEach(rec -> System.out.println("RECIPE: id=" + rec.getId() + ", name="+rec.getName() + ", author=" + rec.getAuthor().getName()));
    }
    @AfterEach
    public void cleanUp(){
        recipeRepository.deleteAll();
        userRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

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
        assertTrue(recipes.get(0).getAuthor().getName().equals("bob2"));
    }

}
