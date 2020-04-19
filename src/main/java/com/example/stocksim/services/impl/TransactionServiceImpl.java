package com.example.stocksim.services.impl;

import com.example.stocksim.DTOs.StockDTO;
import com.example.stocksim.model.Stock;
import com.example.stocksim.model.Transaction;
import com.example.stocksim.model.User;
import com.example.stocksim.repositories.TransactionRepository;
import com.example.stocksim.services.BackgroundFeedService;
import com.example.stocksim.services.StockService;
import com.example.stocksim.services.TransactionService;
import com.example.stocksim.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UserService userService;
    private StockService stockService;
    private BackgroundFeedService backgroundFeedService;

    @Transactional
    @Override
    public List<Transaction> getTransactionsByUser(User user) {
        return transactionRepository.getTransactionsByUser(user);
    }

    @Transactional
    @Override
    public List<Transaction> getTransactionsByUserId(Long id) {
        return transactionRepository.getTransactionsByUser(userService.getUserById(id));
    }

    @Transactional
    @Override
    public Map<Stock, StockDTO> getStockDTOMapByUserId(Long id) {

        List<Transaction> transactions = getTransactionsByUser(userService.getUserById(id));

        Map<Stock, StockDTO> stockDTOMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (stockDTOMap.containsKey(transaction.getStock())) {
                StockDTO current = stockDTOMap.get(transaction.getStock());
                current.setAmount(current.getAmount() + transaction.getAmount());

                if (current.getAmount() == 0) {
                    stockDTOMap.remove(transaction.getStock());
                } else {
                    stockDTOMap.put(transaction.getStock(), current);
                }
            } else {
                StockDTO newDTO = new StockDTO();
                newDTO.setTicker(transaction.getStock().getTicker());
                newDTO.setName(transaction.getStock().getName());
                newDTO.setPrice(backgroundFeedService.findByStock(transaction.getStock()).getPrice());
                newDTO.setAmount(transaction.getAmount());
                stockDTOMap.put(transaction.getStock(), newDTO);
            }
        }

        return stockDTOMap;
    }

    @Override
    public Double getTotalByStockDTOMap(Map<Stock, StockDTO> stockDTOMap) {

        double total = 0.00;

        for (Map.Entry<Stock, StockDTO> stockDTOEntry : stockDTOMap.entrySet()) {
            total += stockDTOEntry.getValue().getAmount() * stockDTOEntry.getValue().getPrice();
        }

        return total;
    }

    @Transactional
    @Override
    public int newPurchase(Long userId, String tickerAmount) {

        String[] data = tickerAmount.split("=");
        if (data.length != 2) {
            log.error("Invalid input.");
            return 1;
        }
        String ticker = data[0];
        Long amount = Long.valueOf(data[1]);

        if (amount <= 0) {
            log.error("Amount must be positive.");
            return 2;
        }

        User user = userService.getUserById(userId);
        double balance = user.getBalance();

        Double stockPrice = backgroundFeedService.findByStock(stockService.getStockByTicker(ticker)).getPrice();

        if (balance < stockPrice * amount) {
            log.error("Not enough funds.");
            return 3;
        }

        saveTransaction(ticker, amount, user, balance, stockPrice);
        return 0;
    }

    @Transactional
    @Override
    public int newSale(Long userId, String tickerAmount) {

        String[] data = tickerAmount.split("=");
        if (data.length != 2) {
            log.error("Invalid input.");
            return 1;
        }
        String ticker = data[0];
        long amount = -Math.abs(Long.parseLong(data[1]));

        if (amount == 0) {
            log.error("Amount cannot be zero.");
            return 5;
        }

        User user = userService.getUserById(userId);
        Double balance = user.getBalance();
        Long userAmount = getStockDTOMapByUserId(userId).get(stockService.getStockByTicker(ticker)).getAmount();

        Double stockPrice = backgroundFeedService.findByStock(stockService.getStockByTicker(ticker)).getPrice();

        if (Math.abs(amount) > userAmount) {
            log.error("Not enough stocks for transaction.");
            return 6;
        }

        saveTransaction(ticker, amount, user, balance, stockPrice);
        return 4;
    }

    private void saveTransaction(String ticker, Long amount, User user, Double balance, Double stockPrice) {
        user.setBalance(balance - stockPrice * amount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setPrice(stockPrice);
        transaction.setUser(user);
        transaction.setStock(stockService.getStockByTicker(ticker));
        transactionRepository.save(transaction);
    }
}
