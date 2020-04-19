package com.example.stocksim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "stocks")
public class Stock extends BaseEntity {

    private String ticker;
    private String name;

    @Column(length = 768)
    private String description;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stock")
//    private List<Transaction> transactions = new ArrayList<>();

    public Stock() {
    }

    @Builder
    public Stock(Long id, String ticker, String name) {
        super(id);
        this.ticker = ticker;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(ticker, stock.ticker) &&
                Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, name);
    }
}
