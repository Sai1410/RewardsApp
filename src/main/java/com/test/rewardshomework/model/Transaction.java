package com.test.rewardshomework.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

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
    private String date;
    private BigDecimal payment;

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
}
