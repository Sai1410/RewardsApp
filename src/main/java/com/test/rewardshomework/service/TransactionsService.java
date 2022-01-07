package com.test.rewardshomework.service;

import com.test.rewardshomework.model.Transaction;
import com.test.rewardshomework.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    TransactionRepository transactionRepository;

    TransactionsService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public Optional<Transaction> getTransaction(String id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> getTransactions(String customerName){
        return transactionRepository.findByCustomerName(customerName);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(String id){
        transactionRepository.deleteById(id);
    }

    public void deleteAllTransactions(){
        transactionRepository.deleteAll();
    }

    public void addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void updateTransaction(Transaction transaction){
        Optional<Transaction> repositoryTransaction = transactionRepository.findById(transaction.getId());
        if(repositoryTransaction.isPresent()){
            transactionRepository.save(transaction);
        }
    }

}
