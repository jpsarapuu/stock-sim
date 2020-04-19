package com.example.stocksim.services;

import com.example.stocksim.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StockServiceTest {

    @Autowired
    StockService stockService;

    @Test
    void findAll() {
        Set<Stock> stocks = stockService.findAll();
        for (Stock stock : stocks) {
            assertNotNull(stock);
        }
    }

    @Test
    void getStockByTicker() {
        assertNotNull(stockService.getStockByTicker("ARC1T"));
    }
}