package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.entities.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.entities.Recipe;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service("cookbookservice")
@Setter
@RequiredArgsConstructor
public class RecipeServiceImplementation implements RecipeService {

    private IngredientRepository ingredientRepository;

    private RecipeRepository recipeRepository;

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
                        "SELECT r from Recipe r LEFT JOIN Ingredient i ON i.recipeId = r.id WHERE i.name LIKE :ingredientName",
                        Recipe.class)
                .setParameter("ingredientName", ingredientName)
                .getResultList();
    }

    @Override
    public List<Recipe> getRecipesWithExactlyMatchingIngredients(Collection<String> ingredientNames) {
        return entityManager.createQuery(
                        "SELECT r FROM Recipe r LEFT JOIN Ingredient i ON r.id = i.recipeId " +
                                "WHERE i.name IN (:ingList)" +
                                "GROUP BY r.id HAVING COUNT(DISTINCT i.name) = :all",
                        Recipe.class)
                .setParameter("ingList", ingredientNames)
                .setParameter("all", ingredientNames.size())
                .getResultList();
    }


    @Override
    public List<Recipe> getRecipesCookableWithIngredients(Collection<String> ingredientNames) {
        return entityManager.createQuery(
                        "SELECT r FROM Recipe r Inner JOIN Ingredient i ON r.id = i.recipeId " +
                                "WHERE i.name IN (:ingList) AND r.id NOT IN (SELECT r2.id FROM Recipe r2 " +
                                "JOIN Ingredient i2 ON r2.id = i2.recipeId WHERE NOT i2.name IN (:ingList))",


                        Recipe.class)
                .setParameter("ingList", ingredientNames).getResultList();

    }

    @Transactional
    @Override
    public void saveNewRecipe(Recipe recipe) throws NoSuchUserIdError {
        if (!userRepository.existsById(recipe.getAuthorId())) throw new NoSuchUserIdError();
        Recipe r = InitialiseNewRecipeWithValuesOf(recipe);
        if (recipe.getIngredientList() != null) {
            List<Ingredient> ingredients = createNewListWithIngredientsMatchingTheValuesOfIngredientsInRecipe(recipe);
            r.setIngredientList(ingredients);
        } else r.setIngredientList(new ArrayList<>());
        recipeRepository.save(r);   //Save first to let it generate an Id
        assertNotNull(r.getId());
        for (Ingredient ingredient : r.getIngredientList()) {
            ingredient.setRecipeId(r.getId());
            ingredientRepository.save(ingredient);
        }
    }

    private static List<Ingredient> createNewListWithIngredientsMatchingTheValuesOfIngredientsInRecipe(Recipe recipe) {
        List<Ingredient> ingredients = new ArrayList<>(recipe.getIngredientList().size());
        for (Ingredient ingredient : recipe.getIngredientList()) {
            Ingredient i = new Ingredient();
            i.setName(ingredient.getName());
            i.setUnit(ingredient.getUnit());
            i.setAmount(ingredient.getAmount());
            ingredients.add(i);
        }
        return ingredients;
    }

    private static Recipe InitialiseNewRecipeWithValuesOf(Recipe recipe) {
        Recipe r = new Recipe();
        r.setName(recipe.getName());
        r.setInstructions(recipe.getInstructions());
        r.setAuthorId(recipe.getAuthorId());
        r.setImagePath(recipe.getImagePath());
        return r;
    }


}
