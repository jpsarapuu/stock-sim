package com.example.stocksim.services.impl;

import com.example.stocksim.DTOs.StockPrice;
import com.example.stocksim.model.Stock;
import com.example.stocksim.repositories.StockRepository;
import com.example.stocksim.services.BackgroundFeedService;
import com.example.stocksim.services.LiveFeedService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Gets new stock prices via API every 3 minutes.

@Service
@AllArgsConstructor
public class BackgroundFeedServiceImpl implements BackgroundFeedService {

    private TaskExecutor taskExecutor;
    private StockRepository stockRepository;
    private LiveFeedService liveFeedService;

    private Map<Stock, StockPrice> stockPriceMap;

    @Override
    public void startProcess() {

        taskExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                // Initialization
                Set<Stock> stocks = stockRepository.findAll();
                for (Stock stock : stocks) {
                    StockPrice stockPrice = new StockPrice();
                    stockPrice.setStock(stock);
                    stockPrice.setPrice(liveFeedService.getPriceByTicker(stock.getTicker()));
                    stockPriceMap.put(stock, stockPrice);
                }

                // Refresh prices every 3 minutes
                while (true) {
                    Thread.sleep(3 * 60 * 1000);
                    for (Stock stock : stockPriceMap.keySet()) {
                        StockPrice stockPrice = stockPriceMap.get(stock);;
                        stockPrice.setPrice(liveFeedService.getPriceByTicker(stock.getTicker()));
                        stockPriceMap.put(stock, stockPrice);
                    }
                }
            }
        });
    }

    @Override
    public StockPrice findByStock(Stock stock) {
        return stockPriceMap.get(stock);
    }

    @Override
    public Map<Stock, Double> getStockPriceMapByStockSet(Set<Stock> stocks) {

        Map<Stock, Double> stockPriceMap = new HashMap<>();

        for (Stock stock : stocks) {
            stockPriceMap.put(stock, findByStock(stock).getPrice());
        }

        return stockPriceMap;
    }
}
