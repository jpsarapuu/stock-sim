package com.example.stocksim.repositories;

import com.example.stocksim.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User getUserById(Long id);

    User getUserByUsername(String username);

    void deleteUserByUsername(String username);
}
