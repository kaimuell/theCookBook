package com.kaimuellercode.thecookbook.entities.core;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email" )
    })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(max=20)
    private String name;

    // the Hash of the Password
    private int pwHash;

    @Size(max=50)
    @Email
    private String email;

    private UserRights userRights;

    public User() {

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
}
