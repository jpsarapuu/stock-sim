package com.example.stocksim.services;

import com.example.stocksim.DTOs.StockPrice;
import com.example.stocksim.model.Stock;

import java.util.Map;
import java.util.Set;

public interface BackgroundFeedService {

    void startProcess();

    StockPrice findByStock(Stock stock);

    Map<Stock, Double> getStockPriceMapByStockSet(Set<Stock> stocks);
}
