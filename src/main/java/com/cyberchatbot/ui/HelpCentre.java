package com.cyberchatbot.ui;

public class HelpCentre {

    /**
     * Display all the cybersecurity options available to the user
     */

    private static final String[] TOPICS = {
            "phishing",
            "passwords",
            "malware",
            "2FA / MFA",
            "VPNs",
            "Wi-Fi Safety",
            "Ransomware",
            "Backups",
            "Firewalls"
    };

    public static void display() {
        String spokenSummary = "These are the topics currently available:";
        ConsoleUI.printBotResponse(spokenSummary);
        VoiceGreeter.speakClosing(spokenSummary);
        printTable();
        String ifNotIn = "If the topic of your interest is not in the list. Search and I'll find the information for you.";
        ConsoleUI.printBotResponse(ifNotIn);
        VoiceGreeter.speakAsync(ifNotIn);
    }

    private static void printTable() {
        int colWidth = 18;

        String border = "+" + "-".repeat(colWidth + 2) + "+";
        String separator = "+" + "=".repeat(colWidth + 2) + "+";

        System.out.println(border);
        System.out.printf("| %-" + colWidth + "s |\n", "TOPIC / KEYWORD");
        System.out.println(separator);

        for (String topic : TOPICS) {
            System.out.printf("| %-" + colWidth + "s |\n", topic);
        }

        System.out.println(border);
    }
}
