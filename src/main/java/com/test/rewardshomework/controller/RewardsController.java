package com.test.rewardshomework.controller;

import com.test.rewardshomework.model.Reward;
import com.test.rewardshomework.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.*;

@Controller
public class RewardsController {

    RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService){
        this.rewardsService = rewardsService;
    }

    @GetMapping("/rewards")
    public @ResponseBody
    ResponseEntity<Map<String, Reward>> calculateAllRewards() {
        try {
            Map<String, Reward> rewards = rewardsService.calculateAllRewards();
            return new ResponseEntity<>(rewards, HttpStatus.OK);
        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Parse exc", e);
        }
    }

    @GetMapping("/rewards/{customerName}")
    public @ResponseBody
    ResponseEntity<String> calculateReward(@PathVariable String customerName) {
        try {
            Reward reward = rewardsService.calculateReward(customerName);
            return new ResponseEntity<String>("GET Response" + reward.toString(), HttpStatus.OK);
        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Wrong input value", e);
        }
    }
}
