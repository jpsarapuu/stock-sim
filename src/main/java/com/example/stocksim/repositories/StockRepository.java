package com.example.stocksim.repositories;

import com.example.stocksim.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Set<Stock> findAll();

    Stock getStockByTicker(String ticker);
}
