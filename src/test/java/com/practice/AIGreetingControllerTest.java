package com.practice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class AIGreetingControllerTest {

    private OpenAiChatModel chatModel;

    private AIGreetingController aiGreetingController;

    @BeforeEach
    void setUp() {
        chatModel = Mockito.mock(OpenAiChatModel.class);
        aiGreetingController = new AIGreetingController(chatModel);
    }

    @Test
    @DisplayName("Should return a greeting message from the AI chat client")
    void greeting_shouldReturnMessageFromChatClient() {
        // --- Arrange ---
        String expectedAiResponse = "Hello! As a generative AI, I'm here to assist you. Welcome!";
        String expectedPrompt = """
                As a modern generative AI model,
                Generate a 5 liner greeting message in your style for a human in text form.
                Thanks in advance.
                """;

        String mockResponse = "Hello! As a generative AI, I'm here to assist you. Welcome!";
        when(chatModel.call(expectedPrompt)).thenReturn(mockResponse);

        String actualResponse = aiGreetingController.greeting();

        assertEquals(expectedAiResponse, actualResponse, "The returned greeting should match the mock AI response.");

        ArgumentCaptor<String> promptCaptor = ArgumentCaptor.forClass(String.class);
        verify(chatModel).call(promptCaptor.capture());
        assertEquals(expectedPrompt, promptCaptor.getValue(), "The prompt sent to the AI client should be correct.");

    }
}