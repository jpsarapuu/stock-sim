package com.example.stocksim.services;

import com.example.stocksim.model.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LiveFeedServiceTest {

    @Autowired
    LiveFeedService liveFeedService;

    @Autowired
    StockService stockService;

    @Autowired
    BackgroundFeedService backgroundFeedService;

    @BeforeAll
    void setUp() throws InterruptedException {
        backgroundFeedService.startProcess();

        // Give background service some time to get prices in order to avoid NPEs.
        Thread.sleep(5000);
    }

    @Test
    void getPriceByTicker() {
        assertNotNull(liveFeedService.getPriceByTicker("ARC1T"));
    }

    @Test
    void getStockPriceMapByStockSet() {

        Map<Stock, Double> stockPriceMap = backgroundFeedService.getStockPriceMapByStockSet(stockService.findAll());

        for (Stock stock : stockPriceMap.keySet()) {
            assertNotNull(stockPriceMap.get(stock));
        }
    }
}