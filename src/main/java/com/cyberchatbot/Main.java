package com.cyberchatbot;

import org.fusesource.jansi.AnsiConsole;

import com.cyberchatbot.chatbot.*;
import com.cyberchatbot.ui.*;

import java.io.PrintStream;
import java.io.OutputStream;


public class Main {
    public static void main(String[] args) {
        /**
         * The following is meant to discard diphone errors from showing up in the terminal
         */
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing: discard raw engine logs completely
            }
        }));

        ConsoleUI.printBanner();
        VoiceGreeter.greet();

//         future implementation for Windows terminal display
        AnsiConsole.systemInstall();

        Chatbot bot = new Chatbot();
        bot.start();

    }
}