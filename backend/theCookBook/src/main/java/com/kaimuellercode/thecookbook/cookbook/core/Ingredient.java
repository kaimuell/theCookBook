package com.kaimuellercode.thecookbook.cookbook.core;

import jakarta.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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



    // the name of the ingredient
    private String name;
    // the amount of the ingredient used
    private Float amount;
    //the unit in which the amount is measured
    private IngredientUnit unit;

    public Long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    private Long recipe_id;

}
