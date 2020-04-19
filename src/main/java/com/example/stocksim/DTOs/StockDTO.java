package com.example.stocksim.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {

    private String ticker;
    private String name;
    private double price;
    private Long amount;

    public StockDTO() {
    }

    @Builder
    public StockDTO(String ticker, String name, double price, Long amount) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
