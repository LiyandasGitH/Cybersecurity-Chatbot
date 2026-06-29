package com.cyberchatbot;

//import org.fusesource.jansi.AnsiConsole;

import com.cyberchatbot.chatbot.*;
import com.cyberchatbot.ui.*;


public class Main {
    public static void main(String[] args) {
        System.setErr(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing: discard raw engine logs completely
            }
        }));

        ConsoleUI.printBanner();
        VoiceGreeter.greet();

//         future implementation for Windows terminal display
//        AnsiConsole.systemInstall();

        Chatbot bot = new Chatbot();
        bot.start();

    }
}