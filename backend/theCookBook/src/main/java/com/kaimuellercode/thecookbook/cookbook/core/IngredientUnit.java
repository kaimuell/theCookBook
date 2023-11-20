package com.kaimuellercode.thecookbook.cookbook.core;

public enum IngredientUnit {
    GRAMM("g"), KILOGRAMM("kg"), LITER("l"), MILLILITER("ml"), SPOON("Löffel"),
    PINCH("Prise"), PIECE("Stück");

    private final String stringRepresentationDE;

    IngredientUnit(String shortDE) {
        this.stringRepresentationDE = shortDE;
    }

    public String getStringRepresentationDE() {
        return stringRepresentationDE;
    }
}
