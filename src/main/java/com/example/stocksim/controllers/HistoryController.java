package com.example.stocksim.controllers;

import com.example.stocksim.DTOs.UserDetailsImpl;
import com.example.stocksim.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class HistoryController {

    private TransactionService transactionService;

    @GetMapping("/history")
    public String history(Model model) {

        UserDetailsImpl currentUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("username", currentUserDetails.getUsername());
        model.addAttribute("transactions", transactionService.getTransactionsByUserId(currentUserDetails.getId())
                .stream()
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList()));

        return "foundation/history";
    }
}
