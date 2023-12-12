package com.kaimuellercode.thecookbook.cookbook.controller;

import com.kaimuellercode.thecookbook.cookbook.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("admin")
public class AdminController {
    @GetMapping(path = "test")
    public String getTestMessage(@AuthenticationPrincipal UserPrincipal principle){
        return "Hello Admin : " + principle.getUsername();
    }

}
