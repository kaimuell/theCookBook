package com.kaimuellercode.thecookbook.cookbook.service;

import com.kaimuellercode.thecookbook.cookbook.entities.User;
import com.kaimuellercode.thecookbook.cookbook.entities.UserRights;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@Setter
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    @Override
    public Optional<User> findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUserEntry(User user) {
        User user1 = new User();
        user1.setUserRights(UserRights.USER);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setRecipes(new HashSet<>());
        user1.setPwHash(user.getPwHash()); //TODO HOW TO COMMUNICATE PASSWORDS ???? NEED SALT AND PEPPER !
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
