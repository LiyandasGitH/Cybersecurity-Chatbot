package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUI.printBanner();
        // fix this so that "Welcome to CyberBot" is the first audio sound heard, reads below the banner
        VoiceGreeter.greet();

        String userName = "";
        while (userName.isEmpty()) {
            ConsoleUI.printBotResponse("Hello! Before we begin, what is your name?");
            ConsoleUI.printPrompt();
            userName = scanner.nextLine().trim();

            if (userName.isEmpty()) {
                ConsoleUI.printBotResponse("Name cannot be empty. Please enter a valid name.");
                System.out.println();
            }
        }
        ConsoleUI.printBotResponse("Hi " + userName + "! Ask me about phishing, password and malware attacks.");

//         future implementation for Windows terminal display
//        AnsiConsole.systemInstall();


//        Chatbot bot = new Chatbot();
//        bot.start();
//
        boolean isActive = true;
        while (isActive) {
            ConsoleUI.printPrompt();
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("exit") || (userInput.equalsIgnoreCase("quit"))) {
                System.out.println();
                ConsoleUI.printBotResponse("Goodbye " + userName + "! Stay safe online!");
                break;
            }

            // placeholder for bot response
//            ResponseEngine.getResponse(userInput);
//            System.out.println(userInput);

//            else {
//                isActive = false;
//            }

        }
        scanner.close();
    }
}