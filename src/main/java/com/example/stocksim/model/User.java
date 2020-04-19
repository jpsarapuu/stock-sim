package com.example.stocksim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String passwordHash;

    private double balance = 10_000.00;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<>();

    public User() {
    }

    @Builder
    public User(Long id, String username, String passwordHash) {
        super(id);
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
