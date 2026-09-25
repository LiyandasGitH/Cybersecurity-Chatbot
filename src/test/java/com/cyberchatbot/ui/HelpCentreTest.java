package com.cyberchatbot.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class HelpCentreTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("display() should execute without throwing exceptions")
    void testDisplayDoesNotThrow() {
        assertDoesNotThrow(HelpCentre::display);
    }

    @Test
    @DisplayName("display() should output header and bot prompts to console")
    void testDisplayContainsGuidanceMessages() {
        HelpCentre.display();
        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("These are the topics currently available:"),
                "Should include the introductory summary");
        assertTrue(output.contains("If the topic of your interest is not in the list. Search and I'll find the information for you."),
                "Should include the fallback search note");
    }

    @Test
    @DisplayName("display() should print table borders and header column")
    void testDisplayTableStructure() {
        HelpCentre.display();
        String output = outputStreamCaptor.toString();

        String expectedBorder = "+--------------------+";
        String expectedSeparator = "+====================+";

        assertTrue(output.contains(expectedBorder), "Output should contain the top/bottom table borders");
        assertTrue(output.contains("| TOPIC / KEYWORD    |"), "Output should contain the formatted table column header");
        assertTrue(output.contains(expectedSeparator), "Output should contain the header separator");
    }

    @Test
    @DisplayName("display() should print all expected topic rows formatted within the table")
    void testDisplayContainsAllTopics() {
        HelpCentre.display();
        String output = outputStreamCaptor.toString();

        String[] expectedTopics = {
                "phishing",
                "passwords",
                "malware",
                "2FA / MFA",
                "VPNs",
                "Wi-Fi Safety",
                "Ransomware",
                "Backups",
                "Firewalls"
        };

        for (String topic : expectedTopics) {
            assertTrue(output.contains(topic), "Table should contain topic: " + topic);
            assertTrue(output.matches("(?s).*\\|\\s+" + java.util.regex.Pattern.quote(topic) + "\\s+\\|.*"),
                    "Topic '" + topic + "' should be formatted between table cell pipes (|)");
        }
    }
}
