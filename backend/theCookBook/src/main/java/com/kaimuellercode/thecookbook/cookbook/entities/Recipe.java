package com.kaimuellercode.thecookbook.cookbook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="recipes")
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    // the cooking instructions
    private String instructions;

    //the Id of the User which authored this recipe
    private Long authorId;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<Ingredient> ingredientList;

    //the path to the Image of the Recipe
    private String imagePath;

}
