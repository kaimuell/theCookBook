package com.kaimuellercode.thecookbook.cookbook.core;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="ingredients")
public class Ingredient {


    public Ingredient(float amount, String name, IngredientUnit ingredientUnit) {
        this.amount = amount;
        this.name = name;
        this.unit = ingredientUnit;
    }

    public Ingredient() {
    }


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

    private Long recipe_id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }





}
