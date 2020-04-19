package com.example.stocksim.controllers;

import com.example.stocksim.DTOs.UserDetailsImpl;
import com.example.stocksim.model.Stock;
import com.example.stocksim.services.BackgroundFeedService;
import com.example.stocksim.services.StockService;
import com.example.stocksim.services.TransactionService;
import com.example.stocksim.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Controller
@AllArgsConstructor
public class BuyController {

    private StockService stockService;
    private UserService userService;
    private BackgroundFeedService backgroundFeedService;
    private TransactionService transactionService;

    @GetMapping("/buy")
    public String buy(Model model) {

        UserDetailsImpl currentUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Set<Stock> stocks = stockService.findAll();

        model.addAttribute("stocks", stocks);
        model.addAttribute("prices", backgroundFeedService.getStockPriceMapByStockSet(stocks));
        model.addAttribute("balance", userService.getUserById(currentUserDetails.getId()).getBalance());

        return "foundation/buy";
    }

    @PostMapping("/buy")
    public String buy(@RequestBody String tickerAmount) {

        UserDetailsImpl currentUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int result = transactionService.newPurchase(currentUserDetails.getId(), tickerAmount);

        return "redirect:/?status=" + result;
    }
}
