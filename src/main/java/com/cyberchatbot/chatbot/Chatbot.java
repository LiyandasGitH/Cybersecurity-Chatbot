package com.cyberchatbot.chatbot;

import com.cyberchatbot.ui.ConsoleUI;

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

        ConsoleUI.printBotResponse(
                "Hi " + user.getName() + "! I can answer questions about " +
                responseEngine.getTopicCount() + " cybersecurity topics. " +
                "\n" + "Type 'exit' to quit program."
        );
        chatWithBot();
    }

    private boolean fetchUserName() {
        String userName = "";

        while (userName.isEmpty()) {
            ConsoleUI.printBotResponse("Hello! Before we begin, what is your name?");
            ConsoleUI.printPrompt();
            userName = scanner.nextLine().trim();

            if (userName.isEmpty()) {
                ConsoleUI.printError("Name cannot be empty. Please enter a valid name.");
                System.out.println();
            }
            // checks if name entered is "quit" or "exit"
            else if (userName.equalsIgnoreCase("exit") || (userName.equalsIgnoreCase("quit"))) {
                goodbye();
                return false;
            }
        }
        this.user = new User(userName);
        return true;
    }

    private void chatWithBot() {
        boolean isActive = true;
        while (isActive) {
            ConsoleUI.printPrompt();
            String userInput = scanner.nextLine();

            if (userInput == null || userInput.trim().isEmpty()) {
                ConsoleUI.printError("Please type a valid question or topic.");
            }
            else if (userInput.equalsIgnoreCase("exit") || (userInput.equalsIgnoreCase("quit"))) {
                goodbye();
                break;
            } else {
                String botAnswer = responseEngine.getResponse(userInput);
                ConsoleUI.printBotResponse(botAnswer);
            }
        }
    }

    private void goodbye() {
        System.out.println();
        String nameGotten = (user != null) ? user.getName() : "User";
        ConsoleUI.printBotResponse("Goodbye " + nameGotten + "! Stay safe online!");
    }

}
