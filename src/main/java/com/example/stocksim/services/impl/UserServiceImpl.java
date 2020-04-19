package com.example.stocksim.services.impl;

import com.example.stocksim.model.User;
import com.example.stocksim.repositories.TransactionRepository;
import com.example.stocksim.repositories.UserRepository;
import com.example.stocksim.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TransactionRepository transactionRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Transactional
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public String newUser(String username, String password, String passwordConfirmation) {

        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            return "redirect:/signup?error=1";
        }

        if (!password.equals(passwordConfirmation)) {
            return "redirect:/signup?error=2";
        }

        userRepository.save(User.builder().username(username).passwordHash(passwordEncoder.encode(password)).build());

        return "redirect:/login?success=true";
    }

    @Transactional
    @Override
    public void deleteUserByUsername(String username) {
        transactionRepository.deleteTransactionsByUser(userRepository.getUserByUsername(username));
        userRepository.deleteUserByUsername(username);
    }
}
