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

        String talking = "Hi " + user.getName() + "! I can answer questions about " +
                responseEngine.getTopicCount() + " cybersecurity topics. " +
                "\n" + "Type 'exit' to quit program.";
        ConsoleUI.printBotResponse(talking);
        VoiceGreeter.speakAsync(talking);

//        ConsoleUI.printBotResponse(
//                "Hi " + user.getName() + "! I can answer questions about " +
//                responseEngine.getTopicCount() + " cybersecurity topics. " +
//                "\n" + "Type 'exit' to quit program."
//        );
        chatWithBot();
    }

    private boolean fetchUserName() {
        String userName = "";

        String greetings = "Hello! Before we begin, what is your name?";
        ConsoleUI.printBotResponse(greetings);
        VoiceGreeter.speakAsync(greetings);

//        ConsoleUI.printBotResponse("Hello! Before we begin, what is your name?");

        while (userName.isEmpty()) {
            ConsoleUI.printPrompt();
            userName = scanner.nextLine().trim();

            if (userName.isEmpty()) {

                String emptyNameError = "Name cannot be empty. Please enter a valid name.\n";
                ConsoleUI.printError(emptyNameError);
                VoiceGreeter.speakAsync(emptyNameError);

//                ConsoleUI.printError("Name cannot be empty. Please enter a valid name.\n");

                String nameRequest = "Before we begin, what is your name?";
                ConsoleUI.printBotResponse(nameRequest);
                VoiceGreeter.speakAsync(emptyNameError);

//                ConsoleUI.printBotResponse("Before we begin, what is your name?");
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
                String questionError = "Please type a valid question or topic.";
                ConsoleUI.printError(questionError);
                VoiceGreeter.speakAsync(questionError);

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
