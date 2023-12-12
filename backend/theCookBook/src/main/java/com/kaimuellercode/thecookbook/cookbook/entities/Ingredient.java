package com.kaimuellercode.thecookbook.cookbook.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing an Ingredient in a {@link Recipe}
 */

@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // the name of the ingredient
    @NotNull
    private String name;
    // the amount of the ingredient used
    @NotNull
    private Float amount;
    //the unit in which the amount is measured
    @NotNull
    private IngredientUnit unit;

    //the corresponding id of the recipe (is Foreign Key)
    private Long recipeId;

    public Ingredient(float amount, String name, IngredientUnit ingredientUnit) {
        this.amount = amount;
        this.name = name;
        this.unit = ingredientUnit;
    }

    public Ingredient() {
    }

}
