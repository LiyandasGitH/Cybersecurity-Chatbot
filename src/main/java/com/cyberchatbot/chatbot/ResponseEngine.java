package com.cyberchatbot.chatbot;

import com.cyberchatbot.ui.ConsoleUI;
import com.cyberchatbot.ui.VoiceGreeter;

import java.util.HashMap;
import java.util.Map;

public class ResponseEngine {

    private final Map<String, String> responses;

    public ResponseEngine() {
        this.responses = new HashMap<>();
        buildResponse();
    }

    public int getTopicCount() {
        return responses.size();
    }

    public String getResponse(String userInput) {
        String botResponse = userInput.trim().toLowerCase();

        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (botResponse.contains(entry.getKey())) {
                String reply = entry.getValue();

                VoiceGreeter.speakAsync(reply);

                return reply;
            }
        }

        String defaultReply = "I don't have specific information on that topic yet. Try asking about: " +
                "phishing, passwords, malware, 2FA, VPNs, Wi-Fi safety, " +
                "ransomware, backups, or firewalls.";

        VoiceGreeter.speakAsync("I don't have specific information on that topic yet. Try asking about phishing or passwords.");

        return defaultReply;
    }


    private void buildResponse() {
        responses.put("phishing",
            "Phishing attacks trick you into revealing sensitive info via fake emails or websites. " +
            "Always verify the sender's address and never click suspicious links.");

        responses.put("password",
                "Use a strong password of at least 12 characters mixing uppercase, lowercase, numbers, " +
                "and symbols. Never reuse passwords — use a reliable password manager.");

        responses.put("malware",
                "Malware includes viruses, ransomware, and spyware. Keep your OS and antivirus updated, " +
                "and avoid downloading software from untrusted sources.");

        responses.put("2fa",
                "Two-factor authentication (2FA) adds a second verification step beyond your password. " +
                "Enable it on every account that supports it — especially email and banking.");

        responses.put("two factor",
                "Two-factor authentication (2FA) adds a second verification step beyond your password. " +
                "Enable it on every account that supports it — especially email and banking.");

        responses.put("vpn",
                "A VPN encrypts your internet traffic and hides your IP address. " +
                "Use one on public Wi-Fi networks to prevent eavesdropping.");

        responses.put("wifi",
                "Public Wi-Fi is unencrypted and easy to sniff. Avoid accessing banking or personal " +
                "accounts on public networks — use your mobile data or a VPN instead.");

        responses.put("social engineering",
                "Social engineering manipulates people rather than systems. Be suspicious of urgent " +
                "requests for personal info, even from apparent colleagues or authority figures.");

        responses.put("update",
                "Software updates patch known security vulnerabilities. Enable automatic updates " +
                "for your OS, browser, and apps — unpatched software is a common attack vector.");

        responses.put("ransomware",
                "Ransomware encrypts your files and demands payment. Back up your data regularly " +
                "to an offline location and never open unexpected email attachments.");

        responses.put("backup",
                "Follow the 3-2-1 rule: keep 3 copies of your data, on 2 different media types, " +
                "with 1 stored offsite or offline. Test your backups regularly.");

        responses.put("firewall",
                "A firewall monitors and controls incoming and outgoing network traffic. " +
                "Keep your OS firewall enabled and consider a hardware firewall for your home network.");

    }

}
