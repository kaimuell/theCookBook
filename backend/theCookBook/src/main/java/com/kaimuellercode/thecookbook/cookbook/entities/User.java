package com.kaimuellercode.thecookbook.cookbook.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a User
 */

@Entity
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email" )
    })
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max=20)
    private String name;

    // the Hash of the Password
    @JsonIgnore
    private String pwHash;

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
        this.id = id;
        this.name = name;
        this.pwHash = password;
        this.email = email;
        this.userRights = userRights;
    }

    public User(String name, String password, String email, UserRights userRights) {
        this.name = name;
        this.pwHash = password;
        this.email = email;
        this.userRights = userRights;
    }
}
