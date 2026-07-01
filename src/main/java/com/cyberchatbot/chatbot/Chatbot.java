package com.cyberchatbot.chatbot;

import com.cyberchatbot.ui.ConsoleUI;
import com.cyberchatbot.ui.VoiceGreeter;

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


//        ConsoleUI.printBotResponse(
//                "Hi " + user.getName() + "! I can answer questions about " +
//                responseEngine.getTopicCount() + " cybersecurity topics. " +
//                "\n" + "Type 'exit' to quit program."
//        );
        chatWithBot();
    }

    private void welcomeMessage() {
        String talking = "Hi " + user.getName() + "! I can answer questions about " +
                responseEngine.getTopicCount() + " cybersecurity topics. ";
        String helpMsg = "Type 'help to find out more about the program.";
        String suggestionMsg = "Try asking about: phishing, passwords, malware, 2FA, " +
                "VPNs, Wi-Fi, ransomware, backups, or firewalls.";
        String exitMsg = "Type 'exit' to quit program.";

        ConsoleUI.printBotResponse(talking);
        ConsoleUI.printBotResponse(suggestionMsg);
        ConsoleUI.printBotResponse(helpMsg);
        ConsoleUI.printBotResponse(exitMsg);

//        VoiceGreeter.speakAsync(talking);
//        VoiceGreeter.speakClosing(suggestionMsg);
//        VoiceGreeter.speakClosing(helpMsg);
//        VoiceGreeter.speakClosing(exitMsg);

        String audioScript = talking + helpMsg + exitMsg;
        VoiceGreeter.speakClosing(audioScript);

    }

    private boolean fetchUserName() {
        String userName = "";

        String greetings = "Hello! Before we begin, what is your name?";
        ConsoleUI.printBotResponse(greetings);
        VoiceGreeter.speakAsync(greetings);

//        ConsoleUI.printBotResponse("Hello! Before we begin, what is your name?");

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

//                ConsoleUI.printError("Name cannot be empty. Please enter a valid name.\n");

                String nameRequest = "Before we begin, what is your name?";
                ConsoleUI.printBotResponse(nameRequest);
                VoiceGreeter.speakAsync(nameRequest);

                continue;

//                ConsoleUI.printBotResponse("Before we begin, what is your name?");
            }

            if (userName.matches(".*\\d+.*")) {
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
            if (userName.equalsIgnoreCase("exit") || (userName.equalsIgnoreCase("quit"))) {
                goodbye();
                return false;
            }

        }
        userName = Character.toUpperCase(userName.charAt(0)) + userName.substring(1);
        this.user = new User(userName);
        return true;
    }

    private void chatWithBot() {
        boolean isActive = true;
        while (isActive) {
            ConsoleUI.printPrompt();
            String userInput = scanner.nextLine();

            if (userInput == null || userInput.trim().isEmpty()) {
                String questionError = "Please type a valid question or topic.\n";
                ConsoleUI.printError(questionError);
                VoiceGreeter.speakClosing(questionError);

//                ConsoleUI.printError("Please type a valid question or topic.");
                System.out.println();
            }
            else if (userInput.equalsIgnoreCase("exit") || (userInput.equalsIgnoreCase("quit"))) {
                goodbye();
                break;
            }
            else {
                String botAnswer = responseEngine.getResponse(userInput);
                ConsoleUI.printBotResponse(botAnswer);
            }
        }
    }

    private void goodbye() {
        String nameGotten = (user != null) ? user.getName() : "User";

        String goodbyeMsg = "Goodbye " + nameGotten + "! Stay safe online!";
        ConsoleUI.printBotResponse(goodbyeMsg);
        VoiceGreeter.speakClosing(goodbyeMsg);

//        ConsoleUI.printBotResponse("Goodbye " + nameGotten + "! Stay safe online!");
    }

}
