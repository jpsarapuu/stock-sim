package com.example.stocksim.services;

import com.example.stocksim.DTOs.StockDTO;
import com.example.stocksim.model.Stock;
import com.example.stocksim.model.Transaction;
import com.example.stocksim.model.User;

import java.util.List;
import java.util.Map;

public interface TransactionService {

    List<Transaction> getTransactionsByUser(User user);

    List<Transaction> getTransactionsByUserId(Long id);

    Map<Stock, StockDTO> getStockDTOMapByUserId(Long id);

    Double getTotalByStockDTOMap(Map<Stock, StockDTO> stockDTOMap);

    int newPurchase(Long userId, String tickerAmount);

    int newSale(Long userId, String tickerAmount);
}
