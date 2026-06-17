package com.cyberchatbot;

public class ResponseEngine {

    public static void getResponse(String userInput) {
        String botResponse = "";

        if (userInput.contains("phishing")) {
            botResponse = "Phishing is a deceptive technique used to steal sensitive data via fake links.";
        } else if (userInput.contains("passwords")) {
            botResponse = "Malware is malicious software designed to disrupt, damage, or gain unauthorized access.";
        } else if (userInput.contains("malware")) {
            botResponse = "Malware is malicious software designed to disrupt, damage, or gain unauthorized access.";
        }
        else {
            botResponse = "I'm sorry, I can only guide you on phishing, passwords, or malware attacks.";
        }

        ConsoleUI.printBotResponse(botResponse);

        VoiceGreeter.speakAsync(botResponse);
    }
}
