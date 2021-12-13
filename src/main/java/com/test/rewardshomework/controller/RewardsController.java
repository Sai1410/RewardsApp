package com.test.rewardshomework.controller;

import com.test.rewardshomework.model.RewardModel;
import com.test.rewardshomework.model.TransactionModel;
import com.test.rewardshomework.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.*;

@Controller
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    @RequestMapping(value = "/rewards/calculate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, RewardModel> calculateReward(@RequestBody List<TransactionModel> transactions) {
        try {
            return rewardsService.calculateRewards(transactions);
        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Wrong input value", e);
        }
    }
}
