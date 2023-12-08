package com.kaimuellercode.thecookbook.cookbook.core;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email" )
    })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Size(max=20)
    private String name;

    // the Hash of the Password
    private int pwHash;

    @Size(max=50)
    @Email
    private String email;

    @NotNull
    private UserRights userRights;

    @OneToMany
    @JoinColumn(name = "authorId")
    private Set<Recipe> recipes = new HashSet<>();

    public User() {

    }
    public User(Long id, String name, String password, String email, UserRights userRights) {
        this.name = name;
        this.pwHash = password.hashCode();
        this.email = email;
        this.userRights = userRights;
    }

    public User(String name, String password, String email, UserRights userRights) {
        this.name = name;
        this.pwHash = password.hashCode();
        this.email = email;
        this.userRights = userRights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPwHash() {
        return pwHash;
    }

    public void setPwHash(int pwHash) {
        this.pwHash = pwHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return Id;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    public void setId(Long id) {
        Id = id;
    }
}
