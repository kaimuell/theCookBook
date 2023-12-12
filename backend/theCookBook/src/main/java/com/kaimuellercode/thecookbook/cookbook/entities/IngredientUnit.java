package com.kaimuellercode.thecookbook.cookbook.entities;

import lombok.Getter;

/**
 * The Unit in which the amount of an {@link Ingredient} is measured
 */

@Getter
public enum IngredientUnit {
    GRAMM("g"), KILOGRAMM("kg"), LITER("l"), MILLILITER("ml"), SPOON("Löffel"),
    PINCH("Prise"), PIECE("Stück");

    private final String stringRepresentationDE;

    IngredientUnit(String shortDE) {
        this.stringRepresentationDE = shortDE;
    }
}
