package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static com.cyberchatbot.ConsoleUI.printPrompt;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUI.printBanner();
        // future implementation for Windows terminal display
//        AnsiConsole.systemInstall();
        VoiceGreeter.greet();

        Chatbot bot = new Chatbot();
        bot.start();

//        String user = prompt(scanner);
    }
}
