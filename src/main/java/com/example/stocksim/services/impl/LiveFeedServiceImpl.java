package com.example.stocksim.services.impl;

import com.example.stocksim.DTOs.Price;
import com.example.stocksim.services.LiveFeedService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Handles API requests

@Service
public class LiveFeedServiceImpl implements LiveFeedService {

    private RestTemplate restTemplate;

    public LiveFeedServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Double getPriceByTicker(String ticker) {

        Price price = restTemplate.getForObject("https://eodhistoricaldata.com/api/real-time/" + ticker +
                ".NB?api_token=5e47c84fb0eb02.52958335&fmt=json", Price.class);

        if (price != null && !price.getPrice().equals("NA")) {
            return Double.valueOf(price.getPrice());
        }
        return null;
    }
}
