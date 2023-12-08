package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.core.User;
import com.kaimuellercode.thecookbook.cookbook.core.UserRights;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service("cookbookservice")
public class CookBookServiceImplementation implements CookBookService {


    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Recipe> getAllRecipes() {
        return entityManager.createQuery("SELECT r from Recipe r", Recipe.class).getResultList();
    }

    @Override
    public Optional<Recipe> getRecipeByID(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> getRecipeByAuthor(String username) {
        return entityManager
                .createQuery(
                        "SELECT r FROM Recipe r Join User u ON r.authorId = u.id " +
                                "WHERE u.name LIKE :username", Recipe.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Recipe> getRecipeWithIngredient(String ingredientName) {
        return entityManager.createQuery(
                        "SELECT r from Recipe r LEFT JOIN Ingredient i ON i.recipe_id = r.id WHERE i.name LIKE :ingredientName",
                        Recipe.class)
                .setParameter("ingredientName", ingredientName)
                .getResultList();
    }

    @Override
    public List<Recipe> getRecipesWithExactlyMatchingIngredients(Collection<String> ingredientNames) {
        return entityManager.createQuery(
                "SELECT r FROM Recipe r LEFT JOIN Ingredient i ON r.id = i.recipe_id " +
                        "WHERE i.name IN (:ingList)" +
                        "GROUP BY r.id HAVING COUNT(DISTINCT i.name) = :all",
                        Recipe.class)
                        .setParameter("ingList", ingredientNames)
                        .setParameter("all", ingredientNames.size())
                        .getResultList();
    }


    @Override
    public List<Recipe> getRecipesBookableWithIngredients(Collection<String> ingredientNames) {
        return entityManager.createQuery(
                        "SELECT r FROM Recipe r Inner JOIN Ingredient i ON r.id = i.recipe_id " +
                                "WHERE i.name IN (:ingList) AND r.id NOT IN (SELECT r2.id FROM Recipe r2 " +
                                "JOIN Ingredient i2 ON r2.id = i2.recipe_id WHERE NOT i2.name IN (:ingList))" ,


                        Recipe.class)
                .setParameter("ingList", ingredientNames).getResultList();

    }
    @Transactional
    @Override
    public void saveNewRecipe(Recipe recipe) throws NoSuchUserIdError {
        if (!userRepository.existsById(recipe.getAuthorId())) throw new NoSuchUserIdError();
        Recipe r = InitialiseNewRecipeWithvaluesOf(recipe);
        if (recipe.getIngredientList() != null) {
            List<Ingredient> ingredients = createNewListWithIngredientsMatchingTheValuesOfIngredientsInRecipe(recipe);
            r.setIngredientList(ingredients);
        }else r.setIngredientList(new ArrayList<>());
        recipeRepository.save(r);   //Save first to let it generate an Id
        assertNotNull(r.getId());
        for (Ingredient ingredient : r.getIngredientList()){
            ingredient.setRecipe_id(r.getId());
            ingredientRepository.save(ingredient);
        }
    }

    @Override
    public User createUserEntry(User user) {
        User user1 = new User();
        user1.setUserRights(UserRights.USER);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setRecipes(new HashSet<>());
        user1.setPwHash(user.getPwHash()); //TODO HOW TO COMMUNICATE PASSWORDS ???? NEED SALT AND PEPPER !
        userRepository.save(user1);
        return user1;
    }

    private static List<Ingredient> createNewListWithIngredientsMatchingTheValuesOfIngredientsInRecipe(Recipe recipe) {
        List<Ingredient> ingredients = new ArrayList<>(recipe.getIngredientList().size());
        for (Ingredient ingredient : recipe.getIngredientList()){
            Ingredient i = new Ingredient();
            i.setName(ingredient.getName());
            i.setUnit(ingredient.getUnit());
            i.setAmount(ingredient.getAmount());
            ingredients.add(i);
        }
        return ingredients;
    }

    private static Recipe InitialiseNewRecipeWithvaluesOf(Recipe recipe) {
        Recipe r = new Recipe();
        r.setName(recipe.getName());
        r.setInstructions(recipe.getInstructions());
        r.setAuthorId(recipe.getAuthorId());
        r.setImagePath(recipe.getImagePath());
        return r;
    }


}
