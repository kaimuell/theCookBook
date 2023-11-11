package com.kaimuellercode.thecookbook.entities.controller;

import com.kaimuellercode.thecookbook.entities.core.User;
import com.kaimuellercode.thecookbook.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
