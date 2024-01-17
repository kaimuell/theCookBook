package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.entities.User;
import com.kaimuellercode.thecookbook.cookbook.model.UserInitialInformation;
import com.kaimuellercode.thecookbook.cookbook.errors.NoSuchUserIdError;
import com.kaimuellercode.thecookbook.cookbook.errors.UserMailExistsException;
import com.kaimuellercode.thecookbook.cookbook.security.UserPrincipal;
import com.kaimuellercode.thecookbook.cookbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService service;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Only Accessible by Admins
     * @param principal the principal
     * @return all users in Database
     */
    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers(@AuthenticationPrincipal UserPrincipal principal) {
        // This returns a JSON or XML with the users
        return service.findAll();
    }

    /**
     * Get mapping for the name of a user with a given id
     * @param id th id
     * @return the Username
     */
    @CrossOrigin(origins = "http://localhost:4200") //TODO WRITE INTO CONFIG??
    @GetMapping(path = "username")
    public String getUserName(@RequestParam long id) {
        Optional<User> user = service.findById(id);
        if (user.isEmpty()) throw new NoSuchUserIdError();
        return user.get().getName();
    }

    /**
     * Post mapping to create a new user
     * @param userInitialInformation the Information to create a user
     * @return the user information if successful
     */
    @PostMapping(path = "newUser", consumes = "application/json")
    public Optional<User> createNewUser(@RequestBody UserInitialInformation userInitialInformation){
        if(userInitialInformation.name() == null ||userInitialInformation.name().isEmpty()) return Optional.empty();
        if(userInitialInformation.email() == null ||userInitialInformation.email().isEmpty()) return Optional.empty();
        if(userInitialInformation.password() == null ||userInitialInformation.password().isEmpty()) return Optional.empty();

        try {
            return Optional.of(service.createUserEntry(userInitialInformation));
        } catch (Exception e){
            if (e instanceof UserMailExistsException) logger.info("Tried to create Account with existing Email "
                    + userInitialInformation.email());


            return Optional.empty();
        }
    }


}
