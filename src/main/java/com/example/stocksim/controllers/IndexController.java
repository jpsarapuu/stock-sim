package com.example.stocksim.controllers;

import com.example.stocksim.DTOs.StockDTO;
import com.example.stocksim.DTOs.UserDetailsImpl;
import com.example.stocksim.model.Stock;
import com.example.stocksim.services.TransactionService;
import com.example.stocksim.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class IndexController {

    private TransactionService transactionService;
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {

        UserDetailsImpl currentUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<Stock, StockDTO> stockDTOMap = transactionService.getStockDTOMapByUserId(currentUserDetails.getId());
        double total = transactionService.getTotalByStockDTOMap(stockDTOMap);
        double balance = userService.getUserById(currentUserDetails.getId()).getBalance();

        Set<Stock> stocks = stockDTOMap.keySet()
                .stream()
                .sorted(Comparator.comparing(Stock::getTicker))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("stocks", stocks);
        model.addAttribute("stockDTOMap", stockDTOMap);
        model.addAttribute("balance", balance);
        model.addAttribute("total", total);
        model.addAttribute("username", currentUserDetails.getUsername());

        return "foundation/index";
    }
}
