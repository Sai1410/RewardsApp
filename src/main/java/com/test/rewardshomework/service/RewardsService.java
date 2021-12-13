package com.test.rewardshomework.service;

import com.test.rewardshomework.model.RewardModel;
import com.test.rewardshomework.model.TransactionModel;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

@Service
public class RewardsService {

    public Map<String, RewardModel> calculateRewards(List<TransactionModel> transactions) throws ParseException {

        Map<String, RewardModel> rewards = new HashMap<>();

        for(TransactionModel transaction : transactions){
            String customerId = transaction.getCustomerId();
            BigInteger points = calculateTotalPoints(transaction.getPayment().toBigInteger());
            String month = transaction.getMonth();

            RewardModel reward = rewards.get(customerId);
            if(reward == null){
                reward = new RewardModel();
            }

            reward.setTotal(reward.getTotal().add(points));

            Map<String, BigInteger> perMonth = reward.getPerMonth();
            perMonth.merge(month, points, BigInteger::add);

            reward.setPerMonth(perMonth);

            rewards.put(customerId, reward);
        }

        return rewards;
    }

    public BigInteger calculateTotalPoints(BigInteger payment){
        if(payment.compareTo(BigInteger.valueOf(50)) < 0 || payment.compareTo(BigInteger.valueOf(50)) == 0){
            return BigInteger.valueOf(0);
        }

        if(payment.compareTo(BigInteger.valueOf(100)) < 0 || payment.compareTo(BigInteger.valueOf(100)) == 0){
            return payment.subtract(BigInteger.valueOf(50));
        }

        return BigInteger.valueOf(50).add((payment.subtract(BigInteger.valueOf(100))).multiply(BigInteger.valueOf(2)));
    }
}
