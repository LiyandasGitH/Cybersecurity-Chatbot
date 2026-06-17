package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import com.cyberchatbot.chatbot.*;
import com.cyberchatbot.ui.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUI.printBanner();
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
            // checks if name entered is "quit" or "exit"
            else if (userName.equalsIgnoreCase("exit") || (userName.equalsIgnoreCase("quit"))) {
//                System.out.println();
                ConsoleUI.printBotResponse("Goodbye " + userName + "! Stay safe online!");
                return;
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
//                System.out.println();
                ConsoleUI.printBotResponse("Goodbye " + userName + "! Stay safe online!");
                break;
            }

            // placeholder for bot response
            ResponseEngine.getResponse(userInput);

//            else {
//                isActive = false;
//            }

        }
        scanner.close();
    }
}