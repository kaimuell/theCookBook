package com.kaimuellercode.thecookbook.cookbook;

import com.kaimuellercode.thecookbook.cookbook.core.Ingredient;
import com.kaimuellercode.thecookbook.cookbook.core.Recipe;
import com.kaimuellercode.thecookbook.cookbook.repositories.IngredientRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.RecipeRepository;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Recipe> getRecipeByID(Long id){
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
                //.setParameter("ids", List.of(2L,3L))
                .setParameter("ingredientName", ingredientName)
                .getResultList();
    }
}
