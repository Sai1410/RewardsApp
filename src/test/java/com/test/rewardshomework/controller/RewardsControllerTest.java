package com.test.rewardshomework.controller;

import com.test.rewardshomework.model.Reward;
import com.test.rewardshomework.service.RewardsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RewardsControllerTest {

    private final RewardsController rewardsController = new RewardsController(
            mock(RewardsService.class)
    );

    @Test
    void shouldCallCalculateAllRewards() throws ParseException {
        //when
        ResponseEntity<Map<String, Reward>> result = rewardsController.calculateAllRewards();

        //then
        verify(rewardsController.rewardsService).calculateAllRewards();
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldCallCalculateRewardsForSingleCustomer() throws ParseException {
        //when
        Mockito.when(rewardsController.rewardsService.calculateReward("testName")).thenReturn(new Reward());
        ResponseEntity<String> result = rewardsController.calculateReward("testName");

        //then
        verify(rewardsController.rewardsService).calculateReward("testName");
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

}