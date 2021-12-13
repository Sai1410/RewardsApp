package com.test.rewardshomework;

import org.apache.catalina.connector.Response;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RewardsHomeworkAcceptanceTest {

    JSONParser jsonParser = new JSONParser();

    @Test
    void shouldProperlyCalculateReward() throws IOException, ParseException {
        // Given
        HttpPost request = new HttpPost( "http://localhost:8080/rewards/calculate" );
        String content = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/src/test/mocks/dataSet.json")).toString();
        StringEntity params = new StringEntity(content);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        assertEquals("{\"Andrzej\":{\"total\":340,\"perMonth\":{\"11\":0,\"12\":90,\"10\":250}},\"Janusz\":{\"total\":150,\"perMonth\":{\"11\":0,\"9\":150,\"10\":0}}}", EntityUtils.toString(httpResponse.getEntity()));
    }

}
