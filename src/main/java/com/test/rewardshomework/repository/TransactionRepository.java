package com.test.rewardshomework.repository;

import com.test.rewardshomework.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransactionRepository  extends MongoRepository<Transaction, String> {

    @Query("{customerName:'?0'}")
    List<Transaction> findByCustomerName(String customerName);
}
