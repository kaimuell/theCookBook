package com.kaimuellercode.thecookbook;

import com.kaimuellercode.thecookbook.entities.core.Ingredient;
import com.kaimuellercode.thecookbook.entities.core.Recipe;
import com.kaimuellercode.thecookbook.entities.core.User;
import com.kaimuellercode.thecookbook.entities.core.UserRights;
import com.kaimuellercode.thecookbook.entities.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.entities.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.entities.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.kaimuellercode.thecookbook.entities.core.IngredientUnit.GRAMM;
import static com.kaimuellercode.thecookbook.entities.core.IngredientUnit.KILOGRAMM;

@SpringBootTest
class TheCookBookApplicationTests {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void insert(){
        Ingredient i1 = new Ingredient();
        i1.setName("1");
        i1.setAmount(100.0F);
        i1.setUnit(KILOGRAMM);

        Ingredient i2 = new Ingredient();
        i1.setName("2");
        i1.setAmount(200.0F);
        i1.setUnit(GRAMM);

        ingredientRepository.save(i1);
        ingredientRepository.save(i2);

        User u = new User("bob", "wdsadaswdasdasdas", "bob@bob.net", UserRights.USER);

        userRepository.save(u);

        List ingredients = List.of(i1,i2);

        Recipe r = new Recipe();
        r.setAuthor(u);
        r.setInstructions("bla");
        r.setIngredientList(ingredients);
        r.setImagePath("./saddasd.jpg");

        recipeRepository.save(r);

    }

}
