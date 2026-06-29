package com.cyberchatbot.chatbot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ChatbotTest {

    private final InputStream originalSystemIn = System.in;

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    private void simulateUserInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testStartAndExitAtNamePrompt() {
        simulateUserInput("exit\n");

        Chatbot chatbot = new Chatbot();

        assertDoesNotThrow(chatbot::start);
    }

    @Test
    void testSuccessfulLoginAndExit() {
        String inputs = "Ink\nquit\n";
        simulateUserInput(inputs);

        Chatbot chatbot = new Chatbot();

        assertDoesNotThrow(chatbot::start);
    }

    @Test
    void testNameValidationHandlesEmptyInput() {
        String inputs = "\n     \nquit\n";
        simulateUserInput(inputs);

        Chatbot chatbot = new Chatbot();

        assertDoesNotThrow(chatbot::start);
    }

    @Test
    void testChatbotExecutesValidKeywordQueries() {
        String inputs = "Ink\npasswords\n\nexit\n";
        simulateUserInput(inputs);

        Chatbot chatbot = new Chatbot();

        assertDoesNotThrow(chatbot::start);
    }
}
