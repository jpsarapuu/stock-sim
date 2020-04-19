package com.example.stocksim.services.impl;

import com.example.stocksim.model.Stock;
import com.example.stocksim.repositories.StockRepository;
import com.example.stocksim.services.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Set<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Transactional
    @Override
    public Stock getStockByTicker(String ticker) {
        return stockRepository.getStockByTicker(ticker);
    }
}
