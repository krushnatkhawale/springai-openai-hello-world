package com.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIGreetingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AIGreetingController.class);
    private final ChatClient chatClient;
    public AIGreetingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("greeting")
    String greeting(){
        LOGGER.info("Greeting request received");
        final String aiClientResponse = chatClient.call(
                """
                    As a modern generative AI model,
                    Generate a 5 liner greeting message in your style for a human in text form.
                    Thanks in advance.
                """
        );
        LOGGER.info("AI Response: {}", aiClientResponse);
        return aiClientResponse;
    }


}