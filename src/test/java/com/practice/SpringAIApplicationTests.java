package com.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringAIApplicationTests {

    public static final String DUMMY_RESPONSE = "Mocked response";
    @MockBean
    ChatClient chatClient;

    @LocalServerPort
    private int port;
    private String uriBase;
    RestClient restClient;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        uriBase = "http://localhost:" + port + "/";
        restClient = RestClient.builder()
                .baseUrl(uriBase)
                .defaultStatusHandler(new ResponseHandler())
                .build();

        when(chatClient.call(anyString())).thenReturn(DUMMY_RESPONSE);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGreetingControllerReturnsSomeResponse() {
        final ResponseEntity<String> responseEntity = restClient.get()
                .uri("greeting")
                .retrieve()
                .toEntity(String.class);

        assertThat(responseEntity.getStatusCode())
                .withFailMessage("Server response code should have been 200 OK, while it was " + responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody())
                .withFailMessage("Server response body should have matched with " + DUMMY_RESPONSE + " but it was " + responseEntity.getBody())
                .isEqualTo(DUMMY_RESPONSE);
    }
}