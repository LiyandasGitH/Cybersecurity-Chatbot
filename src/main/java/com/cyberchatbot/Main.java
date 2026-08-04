package com.cyberchatbot;

import org.fusesource.jansi.AnsiConsole;
import com.cyberchatbot.ai.*;

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

        AnsiConsole.systemInstall();

        ConsoleUI.printBanner();
        VoiceGreeter.greet();


        Chatbot bot = new Chatbot();
        bot.start();

        // will call askAi right here
        // AskAi.askAi();
//        ai.AskAi.askAi()

    }
}