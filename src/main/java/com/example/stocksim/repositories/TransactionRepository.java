package com.example.stocksim.repositories;

import com.example.stocksim.model.Transaction;
import com.example.stocksim.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> getTransactionsByUser(User user);

    void deleteTransactionsByUser(User user);
}
