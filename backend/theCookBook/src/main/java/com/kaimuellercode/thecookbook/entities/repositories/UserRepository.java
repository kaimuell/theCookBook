package com.kaimuellercode.thecookbook.entities.repositories;

import com.kaimuellercode.thecookbook.entities.core.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
