package com.cyberchatbot.chatbot;

import com.cyberchatbot.ui.ConsoleUI;
import com.cyberchatbot.ui.VoiceGreeter;

import java.util.HashMap;
import java.util.Map;

public class ResponseEngine {

    private final Map<String, String> response;

    public ResponseEngine() {
        this.response = new HashMap<>();
        buildResponse();
    }

    private void buildResponse() {
        response.put("phishing",
            "Phishing attacks trick you into revealing sensitive info via fake emails or websites. " +
            "Always verify the sender's address and never click suspicious links.");

        response.put("password",
                "Use a strong password of at least 12 characters mixing uppercase, lowercase, numbers, " +
                "and symbols. Never reuse passwords — use a password manager like Bitwarden.");

        response.put("malware",
                "Malware includes viruses, ransomware, and spyware. Keep your OS and antivirus updated, " +
                "and avoid downloading software from untrusted sources.");

        response.put("2fa",
                "Two-factor authentication (2FA) adds a second verification step beyond your password. " +
                "Enable it on every account that supports it — especially email and banking.");

        response.put("two factor",
                "Two-factor authentication (2FA) adds a second verification step beyond your password. " +
                "Enable it on every account that supports it — especially email and banking.");

        response.put("vpn",
                "A VPN encrypts your internet traffic and hides your IP address. " +
                "Use one on public Wi-Fi networks to prevent eavesdropping.");

        response.put("wifi",
                "Public Wi-Fi is unencrypted and easy to sniff. Avoid accessing banking or personal " +
                "accounts on public networks — use your mobile data or a VPN instead.");

        response.put("social engineering",
                "Social engineering manipulates people rather than systems. Be suspicious of urgent " +
                "requests for personal info, even from apparent colleagues or authority figures.");

        response.put("update",
                "Software updates patch known security vulnerabilities. Enable automatic updates " +
                "for your OS, browser, and apps — unpatched software is a common attack vector.");

        response.put("ransomware",
                "Ransomware encrypts your files and demands payment. Back up your data regularly " +
                "to an offline location and never open unexpected email attachments.");

        response.put("backup",
                "Follow the 3-2-1 rule: keep 3 copies of your data, on 2 different media types, " +
                "with 1 stored offsite or offline. Test your backups regularly.");

        response.put("firewall",
                "A firewall monitors and controls incoming and outgoing network traffic. " +
                "Keep your OS firewall enabled and consider a hardware firewall for your home network.");

    }

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
