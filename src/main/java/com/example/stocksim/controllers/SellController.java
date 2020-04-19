package com.example.stocksim.controllers;

import com.example.stocksim.DTOs.UserDetailsImpl;
import com.example.stocksim.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class SellController {

    private TransactionService transactionService;

    @PostMapping("/sell")
    public String sell(@RequestBody String tickerAmount) {

        UserDetailsImpl currentUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        int result = transactionService.newSale(currentUserDetails.getId(), tickerAmount);

        return "redirect:/?status=" + result;
    }
}
