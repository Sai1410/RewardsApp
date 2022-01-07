package com.test.rewardshomework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rewardshomework.model.Reward;
import com.test.rewardshomework.model.Transaction;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RewardsHomeworkIT {

    private static final String REWARDS_URL = "/rewards";
    private static final String TRANSACTIONS_URL = "/transactions";
    private static final String DATASET_LOCATION = "/src/it/mocks/dataSet.json";
    JSONParser jsonParser = new JSONParser();
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void deleteTransactions() throws IOException {
        HttpDelete request = new HttpDelete( "http://localhost:8080" + TRANSACTIONS_URL);
        HttpClientBuilder.create().build().execute( request );
    }

    @Test
    void shouldProperlyGetAllRewards() throws IOException, ParseException {
        //given
        fillDbWithTransactions();
        HttpGet request = new HttpGet( "http://localhost:8080" + REWARDS_URL);

        //when
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        String result = EntityUtils.toString(response.getEntity());

        //then
        Map<String, Reward> rewards = mapper.readValue(result, new TypeReference<Map<String, Reward>>(){});
        assertEquals(rewards.keySet(), Set.of("Janusz", "Andrzej"));
        assertEquals(rewards.get("Andrzej").getTotal(), new BigInteger("340"));
        assertEquals(rewards.get("Andrzej").getPerMonth(), perMonthExpectedResult());

    }

    private void fillDbWithTransactions() throws IOException, ParseException {
        String content = jsonParser.parse(new FileReader(System.getProperty("user.dir") + DATASET_LOCATION)).toString();
        List<Transaction> transactions = mapper.readValue(content, new TypeReference<List<Transaction>>(){});
        for (Transaction transaction: transactions){
            addToDatabase(transaction);
        }
    }

    private void addToDatabase(Transaction transaction) throws IOException {
        HttpPost request = new HttpPost("http://localhost:8080" + TRANSACTIONS_URL);
        request.addHeader("content-type", "application/json");
        StringEntity params = new StringEntity(mapper.writeValueAsString(transaction));
        request.setEntity(params);
        HttpClientBuilder.create().build().execute( request );
    }

    private Map<String, BigInteger> perMonthExpectedResult() {
        return new HashMap<>() {{
            put("11", new BigInteger("0"));
            put("12", new BigInteger("90"));
            put("10", new BigInteger("250"));
        }};
    }

}
