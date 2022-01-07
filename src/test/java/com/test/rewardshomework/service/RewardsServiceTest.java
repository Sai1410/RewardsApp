package com.test.rewardshomework.service;

import com.test.rewardshomework.model.Reward;
import com.test.rewardshomework.model.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class RewardsServiceTest {

    private final RewardsService rewardsService = new RewardsService(
            mock(TransactionsService.class)
    );

    @Test
    public void shouldCalculateAllRewards() throws ParseException {
        //when
        Mockito.when(rewardsService.transactionsService.getAllTransactions()).thenReturn(testTransactions());
        Map<String, Reward> actualRewards = rewardsService.calculateAllRewards();

        //then
        assertThat(actualRewards).usingRecursiveComparison().isEqualTo(expectedRewards());
    }

    @Test
    public void shouldCalculateSingleReward() throws ParseException {
        //given
        Transaction[] testTransactions = testTransactions().stream().limit(2).collect(Collectors.toList()).toArray(new Transaction[2]);
        String testCustomerName = testTransactions[0].getCustomerName();

        //when
        Mockito.when(rewardsService.transactionsService.getTransactions(testCustomerName))
                .thenReturn(List.of(testTransactions));
        Reward actualReward = rewardsService.calculateReward(testCustomerName);

        //then
        assertThat(actualReward).usingRecursiveComparison().isEqualTo(expectedRewards().get(testCustomerName));
    }

    private List<Transaction> testTransactions(){
        return new ArrayList<>(){{
            add(new Transaction(
                    "testId1",
                    "testCustomerName1",
                    "12.10.2022",
                    new BigDecimal("51.14")
            ));
            add(new Transaction(
                    "testId2",
                    "testCustomerName1",
                    "12.09.2022",
                    new BigDecimal("120.14")
            ));
            add(new Transaction(
                    "testId3",
                    "testCustomerName2",
                    "13.10.2022",
                    new BigDecimal("120")
            ));
        }};
    }

    private Map<String, Reward> expectedRewards() {
        return new HashMap<>() {{
            put("testCustomerName1",
                    new Reward(new BigInteger("91"), expectedPerMonth() ));
            put("testCustomerName2",
                    new Reward(new BigInteger("90"), new HashMap<>(){{put("10", new BigInteger("90"));}}));
        }};
    }

    private Map<String, BigInteger> expectedPerMonth(){
        return new HashMap<>() {{
            put("10", new BigInteger("1"));
            put("9", new BigInteger("90"));
        }};
    }
}
