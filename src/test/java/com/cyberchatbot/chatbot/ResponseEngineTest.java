package com.cyberchatbot.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseEngineTest {

    private ResponseEngine engine;

    @BeforeEach
    void setUp() {
        engine = new ResponseEngine();
    }

    @Test
    void testPhishingKeywordReturnsProperResponse() {
        String response = engine.getResponse("what is phishing?");
        assertTrue(response.toLowerCase().contains("phishing"), "Response should mention phishing");
    }

    @Test
    void testPasswordKeywordReturnsProperResponse() {
        String response = engine.getResponse("how do i create a string password?");
        assertFalse(response.isEmpty(), "Response should not be empty");
        assertTrue(response.toLowerCase().contains("password"), "Response should mention passwords");
    }

    @Test
    void testUppercaseInputMatchesKeyword() {
        String lower = engine.getResponse("phishing");
        String upper = engine.getResponse("PHISHING");
        assertEquals(lower, upper, "Response should be case-insensitive");
    }

    @Test
    void testMixedCaseInputMatchesKeyword() {
        String response = engine.getResponse("what is Malware?");
        assertNotNull(response);
        assertFalse(response.startsWith("I'm not sure"), "Mixed case should still match");
    }

    @Test
    void testUnknownTopicReturnsFallbackMessage() {
        String response = engine.getResponse("forex trading");
        assertTrue(response.contains("not sure") || response.contains("Try"), "Unknown topic should return fallback message");
    }

    @Test
    void testEmptyStringReturnsFallback() {
        String response = engine.getResponse("");
        assertNotNull(response, "Should never return null");
    }

    @Test
    void testWhitespaceOnlyInputReturnsFallback() {
        String response = engine.getResponse("   ");
        assertNotNull(response);
    }

    @Test
    void testTopicCountIsPositive() {
        assertTrue(engine.getTopicCount() > 0, "Engine should have at least one topic loaded");
    }

    @Test
    void testResponseIsNeverNull() {
        String[] inputs = { "phishing", "MALWARE", "unknown", "", "12345", "!@#$" };
        for (String input : inputs) {
            assertNotNull(engine.getResponse(input),
                    "getResponse() must never return null for input: '" + input + "'");
        }
    }



}
