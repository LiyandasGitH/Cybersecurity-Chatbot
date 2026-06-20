package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import com.cyberchatbot.chatbot.*;
import com.cyberchatbot.ui.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        ConsoleUI.printBanner();
        VoiceGreeter.greet();

//         future implementation for Windows terminal display
//        AnsiConsole.systemInstall();

        Chatbot bot = new Chatbot();
        bot.start();

//        scanner.close();
    }
}