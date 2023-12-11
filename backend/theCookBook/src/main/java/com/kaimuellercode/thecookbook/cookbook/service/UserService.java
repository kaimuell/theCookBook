package com.kaimuellercode.thecookbook.cookbook.service;


import com.kaimuellercode.thecookbook.cookbook.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);

    User createUserEntry(User user);

    Iterable<User> findAll();

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<User> findById(long id);
}
