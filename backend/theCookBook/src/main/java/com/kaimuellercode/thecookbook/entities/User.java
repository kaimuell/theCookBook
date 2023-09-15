package com.kaimuellercode.thecookbook.entities;


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
    @NotBlank
    @Size(max=20)
    private String name;

    // the Hash of the Password
    @NotBlank
    private int pwHash;

    @NotBlank
    @Size(max=50)
    @Email
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.pwHash = password.hashCode();
        this.email = email;
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
}
