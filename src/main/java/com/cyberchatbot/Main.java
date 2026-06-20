package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import com.cyberchatbot.chatbot.*;
import com.cyberchatbot.ui.*;


public class Main {
    public static void main(String[] args) {
        ConsoleUI.printBanner();
        VoiceGreeter.greet();

//         future implementation for Windows terminal display
//        AnsiConsole.systemInstall();

        Chatbot bot = new Chatbot();
        bot.start();

    }
}