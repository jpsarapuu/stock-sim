package com.example.stocksim.controllers;

import com.example.stocksim.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/login")
    public String login() {

        SecurityContextHolder.clearContext();;

        return "foundation/login";
    }

    @GetMapping("/signup")
    public String signUp() {

        SecurityContextHolder.clearContext();

        return "foundation/signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password,
                         @RequestParam String passwordConfirmation) {

        SecurityContextHolder.clearContext();

        return userService.newUser(username, password, passwordConfirmation);
    }
}
