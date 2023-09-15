package com.kaimuellercode.thecookbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name="recipe_ingredient_relations")
public class RecipeIngredientRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    // the id of the recipe
    private Long recipeId;
    // the id of the ingredient
    private Long ingredientId;
}
