package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.entities.User;
import com.kaimuellercode.thecookbook.cookbook.entities.UserRights;
import com.kaimuellercode.thecookbook.cookbook.errors.UserMailExistsException;
import com.kaimuellercode.thecookbook.cookbook.model.UserInitialInformation;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@Setter
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    @Override
    public Optional<User> findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUserEntry(UserInitialInformation user) throws UserMailExistsException {
        if (existsByEmail(user.email())) throw new UserMailExistsException();
        User user1 = new User();
        user1.setUserRights(UserRights.ROlE_USER);
        user1.setName(user.name());
        user1.setEmail(user.email());
        user1.setRecipes(new HashSet<>());
        String encodedPw = passwordEncoder.encode(user.password());
        user1.setPwHash(encodedPw);
        userRepository.save(user1);
        return user1;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

}
