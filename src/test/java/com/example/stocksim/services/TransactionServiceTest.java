package com.example.stocksim.services;

import com.example.stocksim.DTOs.StockDTO;
import com.example.stocksim.model.Stock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceTest {

    static final String TEST_USER = "testuser";
    static final String TEST_PASS = "testpass";
    static final String ARC1T = "ARC1T";
    static final String BLT1T = "BLT1T";

    @Autowired
    TransactionService transactionService;

    @Autowired
    StockService stockService;

    @Autowired
    UserService userService;

    @Autowired
    BackgroundFeedService backgroundFeedService;

    @BeforeAll
    void setUp() throws InterruptedException {
        backgroundFeedService.startProcess();

        // Give background service some time to get prices in order to avoid NPEs.
        Thread.sleep(5000);

        userService.newUser(TEST_USER, TEST_PASS, TEST_PASS);
    }

    @AfterAll
    void tearDown() {
        userService.deleteUserByUsername(TEST_USER);
    }

    @Test
    @Order(1)
    void newPurchase() {
        assertEquals(0, transactionService.newPurchase(userService.getUserByUsername(TEST_USER).getId(), ARC1T + "=100"));
        assertEquals(0, transactionService.newPurchase(userService.getUserByUsername(TEST_USER).getId(), BLT1T + "=200"));
    }

    @Test
    @Order(2)
    void newSale() {
        assertEquals(4, transactionService.newSale(userService.getUserByUsername(TEST_USER).getId(), ARC1T + "=90"));
        assertEquals(4, transactionService.newSale(userService.getUserByUsername(TEST_USER).getId(), BLT1T + "=-180"));
    }

    @Test
    void getTransactionsByUser() {
        assertEquals(4, transactionService.getTransactionsByUser(userService.getUserByUsername(TEST_USER)).size());
    }

    @Test
    void getTransactionsByUserId() {
        assertEquals(4, transactionService.getTransactionsByUserId(userService.getUserByUsername(TEST_USER).getId()).size());
    }

    @Test
    void getStockDTOMapByUserId() {
        Map<Stock, StockDTO> stockDTOMap = transactionService.getStockDTOMapByUserId(userService.getUserByUsername(TEST_USER).getId());
        assertEquals(stockDTOMap.get(stockService.getStockByTicker(ARC1T)).getAmount(), 100 - Math.abs(90));
        assertEquals(stockDTOMap.get(stockService.getStockByTicker(BLT1T)).getAmount(), 200 - Math.abs(-180));
    }

    @Test
    void getTotalByStockDTOMap() {
        assertNotNull(transactionService.getTotalByStockDTOMap(transactionService.getStockDTOMapByUserId(
                userService.getUserByUsername(TEST_USER).getId())));
    }
}