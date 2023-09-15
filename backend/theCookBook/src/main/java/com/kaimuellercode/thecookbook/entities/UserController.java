package com.kaimuellercode.thecookbook.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path= "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPwHash(password.hashCode());
        userRepository.save(user);
        return "Saved";
    }
    //TODO REMOVE THIS AFTER FIRST TESTS
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
