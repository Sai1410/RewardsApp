package com.test.rewardshomework.service;

import com.test.rewardshomework.model.Reward;
import com.test.rewardshomework.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

@Service
public class RewardsService {

    @Autowired
    TransactionsService transactionsService;

    public Map<String, Reward> calculateAllRewards() throws ParseException {

        List<Transaction> transactions = transactionsService.getAllTransactions();
        Map<String, Reward> rewards = new HashMap<>();

        for(Transaction transaction : transactions){
            String customerName = transaction.getCustomerName();
            Reward reward = rewards.get(customerName);
            if(reward == null){
                reward = new Reward();
            }

            calculatePoints(reward, transaction);

            rewards.put(customerName, reward);
        }

        return rewards;
    }

    public Reward calculateReward(String customerName) throws ParseException {
        List<Transaction> transactions = transactionsService.getTransactions(customerName);
        Reward reward = new Reward();

        for(Transaction transaction : transactions){
            calculatePoints(reward, transaction);
        }

        return reward;
    }

    private void calculatePoints(Reward currentReward, Transaction transaction) throws ParseException {
        BigInteger points = calculateTotalPoints(transaction.getPayment().toBigInteger());
        String month = transaction.getMonth();

        currentReward.setTotal(currentReward.getTotal().add(points));

        Map<String, BigInteger> perMonth = currentReward.getPerMonth();
        perMonth.merge(month, points, BigInteger::add);
        currentReward.setPerMonth(perMonth);
    }

    private BigInteger calculateTotalPoints(BigInteger payment){
        if(payment.compareTo(BigInteger.valueOf(50)) < 0 || payment.compareTo(BigInteger.valueOf(50)) == 0){
            return BigInteger.valueOf(0);
        }

        if(payment.compareTo(BigInteger.valueOf(100)) < 0 || payment.compareTo(BigInteger.valueOf(100)) == 0){
            return payment.subtract(BigInteger.valueOf(50));
        }

        return BigInteger.valueOf(50).add((payment.subtract(BigInteger.valueOf(100))).multiply(BigInteger.valueOf(2)));
    }
}
