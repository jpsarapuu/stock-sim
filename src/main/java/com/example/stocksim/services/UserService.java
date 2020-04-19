package com.example.stocksim.services;

import com.example.stocksim.model.User;

public interface UserService {

    User getUserById(Long id);

    User getUserByUsername(String username);

    String newUser(String username, String password, String passwordConfirmation);

    void deleteUserByUsername(String username);
}
