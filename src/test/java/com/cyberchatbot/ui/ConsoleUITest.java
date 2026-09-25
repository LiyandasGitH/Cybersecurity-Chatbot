package com.cyberchatbot.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleUITest {

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
    @DisplayName("Color and style constants should have expected ANSI escape codes")
    void testAnsiConstants() {
        assertEquals("\u001B[0M", ConsoleUI.RESET);
        assertEquals("\u001B[1m", ConsoleUI.BOLD);
        assertEquals("\u001B[31m", ConsoleUI.RED);
        assertEquals("\u001B[32m", ConsoleUI.GREEN);
        assertEquals("\u001B[33m", ConsoleUI.YELLOW);
        assertEquals("\u001B[34m", ConsoleUI.BLUE);
        assertEquals("\u001B[35m", ConsoleUI.PURPLE);
        assertEquals("\u001B[36m", ConsoleUI.CYAN);
    }

    @Test
    @DisplayName("printBanner should print ASCII logo, colors, and welcome text")
    void testPrintBanner() {
        ConsoleUI.printBanner();
        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("C Y B E R  A W A R E N E S S   B O T"), "Banner should contain the bot title");
        assertTrue(output.contains("Welcome to CyberBot - your cybersecurity guide!"), "Banner should contain the welcome line");
        assertTrue(output.contains(ConsoleUI.CYAN + ConsoleUI.BOLD), "Banner should start styling with CYAN and BOLD");
        assertTrue(output.contains(ConsoleUI.GREEN + "===================================================" + ConsoleUI.RESET), "Banner should print green dividers");
        assertTrue(output.contains(ConsoleUI.YELLOW), "Welcome line should use YELLOW color");
    }

    @Test
    @DisplayName("printPrompt should print [You] : with BLUE color and reset")
    void testPrintPrompt() {
        ConsoleUI.printPrompt();
        String output = outputStreamCaptor.toString();

        String expected = ConsoleUI.BLUE + "[You] : " + ConsoleUI.RESET;
        assertEquals(expected, output, "Prompt output must match the expected BLUE format without a trailing newline");
    }

    @Test
    @DisplayName("printBotResponse should format prefix in PURPLE and append message")
    void testPrintBotResponse() {
        String testMessage = "Always use two-factor authentication.";
        ConsoleUI.printBotResponse(testMessage);

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains(ConsoleUI.PURPLE + "[CyberBot] : " + ConsoleUI.RESET), "Should contain formatted PURPLE bot prefix");
        assertTrue(output.contains(testMessage), "Should include the provided message");
        assertTrue(output.endsWith(System.lineSeparator()), "Should end with a newline since println is used");
    }

    @Test
    @DisplayName("printError should format message with RED color and error badge")
    void testPrintError() {
        String errorMessage = "Invalid input entered";
        ConsoleUI.printError(errorMessage);

        String output = outputStreamCaptor.toString();

        String expected = ConsoleUI.RED + "[ERROR ❌] : " + errorMessage + ConsoleUI.RESET;
        assertEquals(expected, output, "Error output must match RED formatting without a trailing newline");
    }


}
