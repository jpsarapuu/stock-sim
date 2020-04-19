package com.example.stocksim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    // Positive for purchases, negative for sales
    private Long amount;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));

    public Transaction() {
    }

    @Builder
    public Transaction(Long id, Long amount, double price) {
        super(id);
        this.amount = amount;
        this.price = price;
    }
}
