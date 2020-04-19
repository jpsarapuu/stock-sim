package com.example.stocksim.services.impl;

import com.example.stocksim.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void loadUserByUsername() {
        userService.newUser("testuser", "testpass", "testpass");
        assertEquals(userDetailsService.loadUserByUsername("testuser").getUsername(), "testuser");
    }
}