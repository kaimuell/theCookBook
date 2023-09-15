package com.kaimuellercode.thecookbook.entities;

import jakarta.persistence.*;


@Entity
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // the cooking instructions
    private String instructions;
    //the Id of the User which authored this recipe
    private Long authorId;



}
