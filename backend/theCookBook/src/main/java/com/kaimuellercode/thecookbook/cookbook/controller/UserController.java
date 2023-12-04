package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.core.User;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path= "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    //TODO REMOVE THIS AFTER FIRST TESTS
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="username")
    public @ResponseBody String getUserName(@RequestParam long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new NoSuchUserIdError();
        return user.get().getName();
    }







}
