package com.test.rewardshomework.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
@NoArgsConstructor
public class TransactionModel {

    private String id;
    private String customerId;
    private String date;
    private BigDecimal payment;

    public TransactionModel(String id, String customerId, String date, BigDecimal payment) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.payment = payment;
    }

    public String getMonth() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = formatter.parse(this.date);
        return String.valueOf(date.getMonth() + 1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
}
