package com.kaimuellercode.thecookbook.entities.core;

public enum IngredientUnit {
    GRAMM("g"), KILOGRAMM("kg"), LITER("l"), MILLILITER("ml"), SPOON("Löffel"),
    PINCH("Prise");

    private final String stringRepresentationDE;

    IngredientUnit(String shortDE) {
        this.stringRepresentationDE = shortDE;
    }
}
