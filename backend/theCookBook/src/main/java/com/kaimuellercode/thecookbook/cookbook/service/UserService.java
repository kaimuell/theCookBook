package com.kaimuellercode.thecookbook.cookbook.service;


import com.kaimuellercode.thecookbook.cookbook.entities.User;
import com.kaimuellercode.thecookbook.cookbook.errors.UserMailExistsException;
import com.kaimuellercode.thecookbook.cookbook.errors.UserNameExistsExeption;
import com.kaimuellercode.thecookbook.cookbook.model.UserInitialInformation;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);

    User createUserEntry(UserInitialInformation user) throws UserNameExistsExeption, UserMailExistsException;

    Iterable<User> findAll();

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<User> findById(long id);
}
