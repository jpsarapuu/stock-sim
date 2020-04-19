package com.example.stocksim.services;

import com.example.stocksim.model.Stock;

import java.util.Set;

public interface StockService {

    Set<Stock> findAll();

    Stock getStockByTicker(String ticker);
}
