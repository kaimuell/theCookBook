package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.entities.User;
import com.kaimuellercode.thecookbook.cookbook.entities.UserRights;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.errors.UserMailExistsException;
import com.kaimuellercode.thecookbook.cookbook.errors.UserNameExistsExeption;
import com.kaimuellercode.thecookbook.cookbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService service;


    //TODO REMOVE THIS AFTER FIRST TESTS
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return service.findAll();
    }

    @GetMapping(path = "username")
    public @ResponseBody String getUserName(@RequestParam long id) {
        Optional<User> user = service.findById(id);
        if (user.isEmpty()) throw new NoSuchUserIdError();
        return user.get().getName();
    }

    @PostMapping(path = "newUser", consumes = "application/json")
    public @ResponseBody User createNewUser(@RequestBody User user) throws UserMailExistsException, UserNameExistsExeption {
        if (service.existsByName(user.getName())) throw new UserNameExistsExeption();
        if (service.existsByEmail(user.getEmail())) throw new UserMailExistsException();

        User user1 = service.createUserEntry(user);
        user1.setUserRights(UserRights.USER);
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setRecipes(new HashSet<>());
        user1.setPwHash(user.getPwHash()); //TODO HOW TO COMMUNICATE PASSWORDS ???? NEED SALT AND PEPPER !


        return user1;
    }


}
