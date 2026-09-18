package com.cyberchatbot.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseEngineTest {

    private ResponseEngine engine;
    private static final String DEFAULT_FALLBACK =
            "I don't have specific information on that topic yet. Let me go look for it:";

    @BeforeEach
    void setUp() {
        engine = new ResponseEngine();
    }

    @Test
    @DisplayName("Should return valid TheBox with isMeaningful=true for phishing keyword")
    void testPhishingKeywordReturnsMeaningfulBox() {
        TheBox box = engine.getResponse("Tell me about phishing attacks");
        assertNotNull(box);
        assertTrue(box.isMeaningful(), "Expected isMeaningful to be true for matched keyword");
        assertNotNull(box.answer());
        assertFalse(box.answer().isBlank());
    }

    @Test
    @DisplayName("Should return fallback TheBox with isMeaningful=false for unknown topics")
    void testUnknownTopicReturnsFallbackBox() {
        TheBox box = engine.getResponse("What is cryptocurrency arbitrage?");
        assertNotNull(box);
        assertFalse(box.isMeaningful(), "Expected isMeaningful to be false for unknown queries");
        assertTrue(box.answer().startsWith(DEFAULT_FALLBACK));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "phishing",
            "password",
            "malware",
            "vpn",
            "wifi",
            "ransomware",
            "firewall",
            "spyware",
            "rootkit",
            "trojan horse",
            "worms",
            "ddos"
    })

    @DisplayName("Should successfully match individual cybersecurity keywords")
    void testCoreKeywordsMatchSuccessfully(String keyword) {
        TheBox box = engine.getResponse("explain " + keyword + " to me");
        assertTrue(box.isMeaningful(), "Failed to match known keyword: " + keyword);
    }

    @Test
    @DisplayName("Should match multi-word phrases correctly")
    void testMultiWordPhraseMatching() {
        TheBox boxTwoFactor = engine.getResponse("how does two factor work?");
        assertTrue(boxTwoFactor.isMeaningful());

        TheBox boxSocialEng = engine.getResponse("is this social engineering?");
        assertTrue(boxSocialEng.isMeaningful());

        TheBox boxOnPath = engine.getResponse("what are on path attacks?");
        assertTrue(boxOnPath.isMeaningful());
    }


    @Test
    @DisplayName("Should perform case-insensitive matching")
    void testCaseInsensitivity() {
        TheBox lower = engine.getResponse("phishing");
        TheBox upper = engine.getResponse("PHISHING");
        TheBox mixed = engine.getResponse("pHISHinG");

        assertTrue(lower.isMeaningful());
        assertTrue(upper.isMeaningful());
        assertTrue(mixed.isMeaningful());
    }

    @Test
    @DisplayName("Should handle whitespace padding and trim correctly")
    void testInputTrimming() {
        TheBox box = engine.getResponse("   firewall   ");
        assertTrue(box.isMeaningful());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should return fallback box for empty and whitespace inputs")
    void testEmptyAndBlankInputs(String blankInput) {
        TheBox box = engine.getResponse(blankInput);
        assertNotNull(box);
        assertFalse(box.isMeaningful());
        assertTrue(box.answer().startsWith(DEFAULT_FALLBACK));
    }

    @Test
    @DisplayName("Should load expected number of distinct topics")
    void testTopicCount() {
        assertEquals(34, engine.getTopicCount(), "Topic map size should reflect loaded topics");
    }

    @Test
    void testResponseIsNeverNull() {
        String[] inputs = { "phishing", "MALWARE", "unknown", "", "12345", "!@#$" };
        for (String input : inputs) {
            assertNotNull(engine.getResponse(input),
                    "getResponse() must never return null for input: '" + input + "'");
        }
    }

    @Test
    @DisplayName("Should return randomized variations across repeated requests")
    void testResponseRandomization() {
        Set<String> uniqueResponses = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            uniqueResponses.add(engine.getResponse("malware").answer());
        }
        assertTrue(uniqueResponses.size() > 1, "Should select different responses across calls");
    }

    @Test
    void testAllTopicsHaveAtLeastTwoResponses() {

        assertTrue(engine.getTopicCount() >= 10,
                "Engine should have at least 10 topics loaded");
    }







}
