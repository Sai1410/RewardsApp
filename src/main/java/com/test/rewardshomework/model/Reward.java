package com.test.rewardshomework.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Reward {

    private BigInteger total;
    private Map<String, BigInteger> perMonth;

    public Reward() {
        this.total = BigInteger.valueOf(0);
        this.perMonth = new HashMap<>();
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public Map<String, BigInteger> getPerMonth() {
        return perMonth;
    }

    public void setPerMonth(Map<String, BigInteger> perMonth) {
        this.perMonth = perMonth;
    }
}
