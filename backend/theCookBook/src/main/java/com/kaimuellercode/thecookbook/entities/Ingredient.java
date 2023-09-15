package com.kaimuellercode.thecookbook.entities;

import jakarta.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    // the name of the ingredient
    private String name;
    // the amount of the ingredient used
    private Float amount;
    //the unit in which the amount is measured
    private String unit;
}
