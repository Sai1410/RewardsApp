package com.test.rewardshomework.controller;

import com.test.rewardshomework.model.Transaction;
import com.test.rewardshomework.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TransactionsController {

    TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("/transactions")
    public @ResponseBody
    ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        transactionsService.addTransaction(transaction);
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }

    @PutMapping("/transactions")
    public @ResponseBody
    ResponseEntity<String> updateTransaction(@RequestBody Transaction transaction) {
        transactionsService.updateTransaction(transaction);
        return new ResponseEntity<String>("Update transaction", HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public @ResponseBody
    ResponseEntity<String> getTransaction(@PathVariable String id) {
        Optional<Transaction> transaction = transactionsService.getTransaction(id);
        return transaction.map(value -> new ResponseEntity<>("GET Response : "
                + value.getId(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("GET Response", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/transactions")
    public @ResponseBody
    ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionsService.getAllTransactions();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionsService.deleteTransaction(id);
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }

    @DeleteMapping("/transactions")
    public @ResponseBody
    ResponseEntity<String> deleteAllTransactions() {
        transactionsService.deleteAllTransactions();
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
}
