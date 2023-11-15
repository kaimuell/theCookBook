package com.kaimuellercode.thecookbook;

import com.google.gson.Gson;
import com.kaimuellercode.thecookbook.cookbook.service.CookBookService;
import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.core.User;
import com.kaimuellercode.thecookbook.cookbook.core.UserRights;
import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.kaimuellercode.thecookbook.cookbook.core.IngredientUnit.GRAMM;
import static com.kaimuellercode.thecookbook.cookbook.core.IngredientUnit.KILOGRAMM;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@AutoConfigureMockMvc
public abstract class TestSetup {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CookBookService cookBookService;

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

        User u = new User("bob2", "wdsadaswdasdasdas", "bob2@bob.net", UserRights.USER);

        Recipe r = new Recipe();
        userRepository.save(u);

        List<Ingredient> ingredients = List.of(i1,i2);
        r.setAuthorId(u.getId());
        r.setName("Teig");
        r.setInstructions("bla");
        r.setIngredientList(ingredients);
        r.setImagePath("./saddasd.jpg");
        recipeRepository.save(r);

        ingredients.forEach(i -> i.setRecipe_id(r.getId()));


        ingredientRepository.saveAll(ingredients);


        User u2 = new User();
        u2.setEmail("sjidjsaio@fold.com");
        u2.setName("sdakl");
        u2.setPwHash(23781);

        Ingredient i = new Ingredient();
        i.setAmount(100F);
        i.setUnit(GRAMM);
        i.setName("Uran");

        Recipe r2 = new Recipe();
        r2.setName("Urankuchen");
        r2.setAuthorId(u.getId());
        r2.setIngredientList(List.of(i));
        r2.setImagePath("j9iweopdikp");
        r2.setInstructions("COOK!");

        userRepository.save(u2);
        r.setAuthorId(u2.getId());
        recipeRepository.save(r2);
        i.setRecipe_id(r2.getId());
        ingredientRepository.save(i);

        Iterable<Recipe> recipies = recipeRepository.findAll();
        recipies.forEach(rec -> {
            System.out.println("RECIPE: id=" + rec.getId() + ", name=" + rec.getName() + ", author=" + rec.getAuthorId());
            rec.getIngredientList().forEach(j -> System.out.println(j.getName()));
        });
        Iterable<Ingredient> inga = ingredientRepository.findAll();
        inga.forEach(ing -> System.out.println("INGREDIENT: id="+ing.getId() + ", Name="+ing.getName() + ", recipe_id=" + ing.getRecipe_id()));

    }

    @AfterEach
    public void cleanUp(){
        recipeRepository.deleteAll();
        userRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

    @Test
    public void testInit(){
        assertFalse(ingredientRepository.findAll().isEmpty());
        assertFalse(recipeRepository.findAll().isEmpty());
        assertFalse(userRepository.findAll().isEmpty());
    }

}
