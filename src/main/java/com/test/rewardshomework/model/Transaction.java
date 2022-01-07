package com.test.rewardshomework.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Document
@NoArgsConstructor
public class Transaction {

    @Id
    private String id;
    private String customerName;
    @NotNull
    private String date;
    @NotNull
    private BigDecimal payment;

    public Transaction(String id, String customerName, String date, BigDecimal payment) {
        this.id = id;
        this.customerName = customerName;
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

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public void setId(String id) {
        this.id = id;
    }
}
