package com.example.stocksim.DTOs;

import com.example.stocksim.model.BaseEntity;
import com.example.stocksim.model.Stock;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StockPrice extends BaseEntity {

    private Double price;
    private Stock stock;
}
