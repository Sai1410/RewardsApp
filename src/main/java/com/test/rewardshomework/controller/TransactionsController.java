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

    @Autowired
    TransactionsService transactionsService;

    @PostMapping("/transaction/add")
    public @ResponseBody
    ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        transactionsService.addTransaction(transaction);
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }

    @PutMapping("/transaction/update")
    public @ResponseBody
    ResponseEntity<String> updateTransaction(@RequestBody Transaction transaction) {
        transactionsService.updateTransaction(transaction);
        return new ResponseEntity<String>("Update transaction", HttpStatus.OK);
    }

    @GetMapping("/transaction/get/{id}")
    public @ResponseBody
    ResponseEntity<String> getTransaction(@PathVariable String id) {
        Optional<Transaction> transaction = transactionsService.getTransaction(id);
        return transaction.map(value -> new ResponseEntity<>("GET Response : "
                + value.getId(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("GET Response", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/transaction/get")
    public @ResponseBody
    ResponseEntity<String> getAllTransactions() {
        List<Transaction> transactions = transactionsService.getAllTransactions();
        return new ResponseEntity<String>("GET Response" + transactions.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/transaction/delete/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionsService.deleteTransaction(id);
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }

    @DeleteMapping("/transaction/delete")
    public @ResponseBody
    ResponseEntity<String> deleteAllTransactions() {
        transactionsService.deleteAllTransactions();
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
}
