package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static com.cyberchatbot.ConsoleUI.printPrompt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUI.printBanner();
        ConsoleUI.printBotResponse("Hi! Ask me anything about phishing, passwords or malware attacks.");

        boolean isActive = true;
        while(isActive) {
            ConsoleUI.printPrompt();
            String user = scanner.nextLine().trim();

            // check if user wants to exit and stop cleanly
            if (user.equalsIgnoreCase("exit") || user.equalsIgnoreCase("quit")) {
                System.out.println();
                ConsoleUI.printBotResponse("Goodbye! Stay safe online.");
                break;
            }
            // placeholder return user input
            System.out.println(user);

            // future implementation for Windows terminal display
//        AnsiConsole.systemInstall();
//        VoiceGreeter.greet();

//        Chatbot bot = new Chatbot();
//        bot.start();
        }
        scanner.close();

    }
}
