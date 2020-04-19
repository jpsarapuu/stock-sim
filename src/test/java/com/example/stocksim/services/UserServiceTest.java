package com.example.stocksim.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    static final String TEST_USER = "testuser";
    static final String TEST_PASS = "testpass";

    @Autowired
    UserService userService;

    @Test
    @Order(1)
    void newUser() {
        assertNotNull(userService.newUser(TEST_USER, TEST_PASS, TEST_PASS));
    }

    @Test
    @Order(2)
    void getUserByUsername() {
        assertNotNull(userService.getUserByUsername(TEST_USER));
    }

    @Test
    @Order(3)
    void getUserById() {
        assertNotNull(userService.getUserById(userService.getUserByUsername(TEST_USER).getId()));
    }

    @Test
    @Order(4)
    void deleteUserByUsername() {
        userService.deleteUserByUsername(TEST_USER);
        assertNull(userService.getUserByUsername(TEST_USER));
    }
}