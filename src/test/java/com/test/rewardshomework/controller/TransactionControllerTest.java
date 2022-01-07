package com.test.rewardshomework.controller;

import com.test.rewardshomework.model.Transaction;
import com.test.rewardshomework.service.TransactionsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TransactionControllerTest {

    private final TransactionsController transactionsController = new TransactionsController(
            mock(TransactionsService.class)
    );

    private static final String testId = "testId";
    private Transaction testTransaction(){
        return new Transaction(
                testId, "testCustomerName", "12.10.2022", new BigDecimal("10.14")
        );
    }

    @Test
    void shouldGetTransactions(){
        //when
        Mockito.when(transactionsController.transactionsService.getTransaction(testId))
                .thenReturn(Optional.of(testTransaction()));
        ResponseEntity<String> result = transactionsController.getTransaction(testId);

        //then
        verify(transactionsController.transactionsService).getTransaction(testId);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldAddTransactions() {
        //given
        Transaction testTransaction = testTransaction();

        //when
        ResponseEntity<String> result = transactionsController.addTransaction(testTransaction);

        //then
        verify(transactionsController.transactionsService).addTransaction(testTransaction);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldPutTransactions() {
        //given
        Transaction testTransaction = testTransaction();

        //when
        ResponseEntity<String> result = transactionsController.updateTransaction(testTransaction);

        //then
        verify(transactionsController.transactionsService).updateTransaction(testTransaction);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldGetAllTransactions() {
        //when
        ResponseEntity<List<Transaction>> result = transactionsController.getAllTransactions();

        //then
        verify(transactionsController.transactionsService).getAllTransactions();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldDeleteTransactions() {
        //when
        ResponseEntity<String> result = transactionsController.deleteTransaction(testId);

        //then
        verify(transactionsController.transactionsService).deleteTransaction(testId);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldDeleteAllTransactions() {
        //when
        ResponseEntity<String> result = transactionsController.deleteAllTransactions();

        //then
        verify(transactionsController.transactionsService).deleteAllTransactions();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}
