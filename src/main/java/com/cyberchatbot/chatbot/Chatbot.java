package com.cyberchatbot.chatbot;

import com.cyberchatbot.ui.ConsoleUI;
import com.cyberchatbot.ui.VoiceGreeter;

import java.io.PrintStream;
import java.util.Scanner;

public class Chatbot {

    private User user;
    private final ResponseEngine responseEngine;
    private final Scanner scanner;

    public Chatbot() {
        this.responseEngine = new ResponseEngine();
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        if (!fetchUserName()) {
            return;
        }

        welcomeMessage();

        chatWithBot();
    }

    private void welcomeMessage() {
        String talking = "Hi " + user.getName() + "! I can answer questions about " +
                responseEngine.getTopicCount() + " cybersecurity topics. ";
        String helpMsg = "Type 'help' to find out more about the topics.";
        String exitMsg = "Type 'exit' to quit program.";

        ConsoleUI.printBotResponse(talking);
        ConsoleUI.printBotResponse(helpMsg);
        ConsoleUI.printBotResponse(exitMsg);

        String audioScript = talking + helpMsg + exitMsg;
        VoiceGreeter.speakClosing(audioScript);

    }

    private static void helpCentre() {
        /**
         * Display all the cybersecurity options available to the user
         */
        String suggestionMsg = "Try asking about: phishing, passwords, malware, 2FA, " +
                "VPNs, Wi-Fi, ransomware, backups, or firewalls.";
        ConsoleUI.printBotResponse(suggestionMsg);
        VoiceGreeter.speakAsync(suggestionMsg);
    }

    private boolean fetchUserName() {
        String userName = "";

        String greetings = "Hello! Before we begin, what is your name?";
        ConsoleUI.printBotResponse(greetings);
        VoiceGreeter.speakAsync(greetings);

        while (userName.isEmpty()) {
            ConsoleUI.printPrompt();

            String input = scanner.nextLine();

            if (input == null) {
                String couldNotReadError = "Could not read input. Exiting";
                ConsoleUI.printError(couldNotReadError);
                VoiceGreeter.speakClosing(couldNotReadError);
                System.exit(1);
            }

            userName = input.trim();

            if (userName.isEmpty()) {

                String emptyNameError = "Name cannot be empty. Please enter a valid name.\n";
                ConsoleUI.printError(emptyNameError);
                VoiceGreeter.speakClosing(emptyNameError);

                String nameRequest = "Before we begin, what is your name?";
                ConsoleUI.printBotResponse(nameRequest);
                VoiceGreeter.speakAsync(nameRequest);

                continue;
            }

            if (userName.matches("\\d+")) {
                String numberError = "That looks like a number, not a name. Please try again.\n";
                ConsoleUI.printError(numberError);
                VoiceGreeter.speakClosing(numberError);

                String nameRequest = "Before we begin, what is your name?";
                ConsoleUI.printBotResponse(nameRequest);
                VoiceGreeter.speakAsync(nameRequest);

                userName = "";
                continue;
            }

            if (userName.length() > 50) {
                String nameTooLong = "That name is too long. Please use 50 characters or fewer.\n";
                ConsoleUI.printError(nameTooLong);
                VoiceGreeter.speakClosing(nameTooLong);

                String nameRequest = "Before we begin, what is your name?";
                ConsoleUI.printBotResponse(nameRequest);
                VoiceGreeter.speakAsync(nameRequest);

                userName = "";
                continue;
            }
            // checks if name entered is "quit" or "exit"
            if (isExitCommand(userName)) {
                goodbye();
                return false;
            }

        }
        String cleanName = capitaliseString(userName);
        this.user = new User(cleanName);
        return true;
    }

    private String capitaliseString(String rawName) {
        String[] words = rawName.split("\\s+");
        StringBuilder capitaliseName = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitaliseName.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return capitaliseName.toString().trim();
    }

    private void chatWithBot() {
        while (true) {
            ConsoleUI.printPrompt();

            String userInput = scanner.nextLine();

            if (userInput == null) {
                goodbye();
                break;
            }

            if (userInput.trim().isEmpty()) {
                String questionError = "Please type a valid question or topic.\n";
                ConsoleUI.printError(questionError);
                VoiceGreeter.speakClosing(questionError);
                helpCentre();

                continue;
            }

            if (userInput.length() > 250) {
                String lengthError = "That input is too long. Please keep questions under 250 characters.\n";
                ConsoleUI.printError(lengthError);
                VoiceGreeter.speakClosing(lengthError);

                continue;
            }

            if (userInput.matches("\\d+")) {
                String digitError = "I only work with text based questions!\n";
                ConsoleUI.printError(digitError);
                VoiceGreeter.speakClosing(digitError);
                helpCentre();

                continue;
            }

            if (userInput.equalsIgnoreCase("help")) {
                helpCentre();
                continue;
            }

            if (isExitCommand(userInput)) {
                goodbye();
                break;
            }

            else {
                String botAnswer = responseEngine.getResponse(userInput);
                ConsoleUI.printBotResponse(botAnswer);
            }
        }
    }

    public boolean isExitCommand(String input) {
        String[] exitCommands = {
                "exit", "quit", "q"
        };
        String lower = input.toLowerCase();
        for (String command : exitCommands) {
            if (lower.equals(command)) {
                return true;
            }
        }
        return false;
    }

    private void goodbye() {
        String nameGotten = (user != null) ? user.getName() : "User";

        String goodbyeMsg = "Goodbye " + nameGotten + "! Stay safe online!";
        ConsoleUI.printBotResponse(goodbyeMsg);
        VoiceGreeter.speakClosing(goodbyeMsg);
    }

}
