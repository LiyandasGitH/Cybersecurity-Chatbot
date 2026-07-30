package com.cyberchatbot.chatbot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.cyberchatbot.ui.VoiceGreeter;

public class ResponseEngine {

    private final Map<String, List<String>> responses;
    private final Random random;

    public ResponseEngine() {
        this.responses = new HashMap<>();
        this.random = new Random();
        buildResponse();
    }

    public int getTopicCount() {
        return responses.size();
    }

    public String getResponse(String userInput) {
        String botResponse = userInput.trim().toLowerCase();

        for (Map.Entry<String, List<String>> entry : responses.entrySet()) {
            if (botResponse.contains(entry.getKey())) {
                List<String> options = entry.getValue();

                String selectedReply = options.get(random.nextInt(options.size()));

                VoiceGreeter.speakAsync(selectedReply);

                return selectedReply;
            }
        }

        String defaultReply = "I don't have specific information on that topic yet. Try asking about: " +
                "phishing, passwords, malware, 2FA, VPNs, Wi-Fi safety, " +
                "ransomware, backups, or firewalls.";

        VoiceGreeter.speakAsync("I don't have specific information on that topic yet. Try asking about phishing or passwords.");

        return defaultReply;
    }


    private void buildResponse() {
        responses.put("phishing", List.of(
                "Phishing emails impersonate trusted senders to steal credentials. " +
                        "Always verify the sender's actual email address, not just the display name.",
                "Watch for urgency in emails — 'Your account will be closed in 24 hours' " +
                        "is a classic phishing pressure tactic. Legitimate companies don't rush you.",
                "Never click 'verify your account' links in unsolicited emails. " +
                        "Go directly to the website by typing the URL yourself instead.",
                "Spear phishing targets you specifically using personal details from social media. " +
                        "Be suspicious of emails that feel unusually personal from unknown senders."
        ));

        responses.put("password", List.of(
                "Use at least 12 characters mixing uppercase, lowercase, numbers, and symbols. " +
                        "Longer is stronger — a 20-character passphrase beats a complex 8-character password.",
                "Never reuse passwords across sites. If one service is breached, attackers " +
                        "try those credentials everywhere — this is called credential stuffing.",
                "A password manager like Bitwarden or KeePass generates and stores unique passwords " +
                        "for every account so you only need to remember one master password.",
                "Avoid using personal info in passwords — birthdays, pet names, and addresses " +
                        "are the first things attackers try in targeted attacks."
        ));

        responses.put("malware", List.of(
                "Malware is any software designed to harm your system. Keep your OS and antivirus " +
                        "updated — most infections exploit known vulnerabilities that patches already fix.",
                "Never download software from unofficial sources. Cracked software and free " +
                        "download sites are the most common malware delivery mechanism.",
                "If your computer suddenly slows down, shows unexpected ads, or behaves strangely, " +
                        "run a full antivirus scan immediately — these are classic malware symptoms.",
                "Trojans disguise themselves as legitimate software. Only install applications " +
                        "from official app stores or the developer's own verified website.",
                "Malware is any code that can be used to steal data, bypass access controls, or cause harm to or compromise a system."

        ));

        responses.put("2fa", List.of(
                "Two-factor authentication adds a second verification step beyond your password. " +
                        "Even if your password is stolen, attackers cannot access your account without it.",
                "Use an authenticator app like Google Authenticator or Authy rather than SMS codes — " +
                        "SMS can be intercepted via SIM-swapping attacks.",
                "Enable 2FA on every account that supports it, prioritising email, banking, " +
                        "and social media — these are the highest-value targets for attackers.",
                "Hardware security keys like a YubiKey provide the strongest form of 2FA " +
                        "and are completely phishing-resistant."
        ));

        responses.put("two factor", List.of(
                "Two-factor authentication requires something you know (password) plus " +
                        "something you have (phone or key). Both must be present to log in.",
                "Setting up 2FA takes under two minutes on most platforms. " +
                        "Go to your account security settings and look for 'Two-Step Verification'.",
                "Store your 2FA backup codes somewhere safe and offline — " +
                        "if you lose your phone without them, you can be locked out permanently."
        ));

        responses.put("vpn", List.of(
                "A VPN encrypts all traffic between your device and the internet, " +
                        "preventing your ISP and network observers from seeing what you're doing.",
                "On public Wi-Fi, a VPN stops attackers on the same network from " +
                        "intercepting your data through man-in-the-middle attacks.",
                "Choose a VPN with a strict no-logs policy that has been independently audited. " +
                        "Free VPNs often monetise your browsing data — the opposite of privacy.",
                "A VPN hides your traffic but does not make you anonymous. " +
                        "Websites can still identify you through cookies and browser fingerprinting."
        ));

        responses.put("wifi", List.of(
                "Public Wi-Fi is unencrypted by default. Anyone on the same network " +
                        "can potentially see your unencrypted traffic with basic tools.",
                "Avoid accessing banking or email on public Wi-Fi. " +
                        "Use your mobile data connection instead — it's encrypted by the carrier.",
                "Even password-protected café or hotel Wi-Fi is shared with strangers. " +
                        "A VPN encrypts your traffic so other users on the network can't read it.",
                "Evil twin attacks create a fake hotspot with a convincing name like 'Airport_Free_WiFi'. " +
                        "Always confirm the official network name with staff before connecting."
        ));

        responses.put("social engineering", List.of(
                "Social engineering manipulates people rather than systems. " +
                        "Attackers exploit trust, authority, and urgency to bypass technical defences.",
                "Be suspicious of any unsolicited request for credentials, access, or money — " +
                        "even if the caller claims to be from IT support or your bank.",
                "Pretexting involves creating a fabricated scenario to extract information. " +
                        "Verify identities through official channels before sharing anything sensitive.",
                "Tailgating is physically following someone into a restricted area. " +
                        "Never hold doors open for people you don't recognise, even if they look official."
        ));

        responses.put("update", List.of(
                "Software updates patch known security vulnerabilities. " +
                        "Attackers actively scan for unpatched systems within hours of a CVE being published.",
                "Enable automatic updates for your OS, browser, and apps. " +
                        "The WannaCry ransomware attack in 2017 spread almost entirely through unpatched Windows systems.",
                "Don't ignore update prompts. The longer you delay, the longer " +
                        "your system is exposed to vulnerabilities that are already public knowledge.",
                "Check for firmware updates on your router too — routers are rarely updated " +
                        "yet run constantly and have direct access to all your network traffic."
        ));

        responses.put("ransomware", List.of(
                "Ransomware encrypts your files and demands payment for the decryption key. " +
                        "Paying does not guarantee recovery and funds future attacks.",
                "The best defence against ransomware is offline backups. " +
                        "If your files are backed up somewhere the ransomware can't reach, you can restore without paying.",
                "Ransomware most commonly enters through phishing emails with malicious attachments " +
                        "or through unpatched remote desktop (RDP) vulnerabilities.",
                "Never open email attachments you weren't expecting, especially .zip, .exe, " +
                        "or Office files that ask you to enable macros."
        ));

        responses.put("backup", List.of(
                "Follow the 3-2-1 rule: 3 copies of your data, on 2 different media types, " +
                        "with 1 stored offsite or offline. This survives hardware failure, theft, and ransomware.",
                "Cloud backup is convenient but not sufficient alone — ransomware can encrypt " +
                        "synced cloud folders. Keep at least one backup that is not continuously connected.",
                "Test your backups regularly. A backup you've never restored from " +
                        "is a backup you don't know works.",
                "Automate your backups. Manual backup habits always fail eventually — " +
                        "set a schedule and let the software handle it."
        ));

        responses.put("firewall", List.of(
                "A firewall monitors incoming and outgoing traffic and blocks connections " +
                        "that don't match your security rules. Keep your OS firewall enabled at all times.",
                "Home routers include a basic NAT firewall that hides your devices from " +
                        "direct internet exposure. Never expose devices directly to the internet without one.",
                "Application-layer firewalls can inspect traffic content, not just port and IP. " +
                        "They can block malware that uses allowed ports like 80 and 443 to phone home.",
                "Review your firewall rules periodically. Old rules for software you've uninstalled " +
                        "can leave unnecessary ports open."
        ));
        
        responses.put("spyware", List.of(
                "Spyware is designed to track and spy on you, it often monitors your online activity",
                "Spyware can log every key you press on your keyboard, as well as capture almost any of your data",
                "Data often stolen by spyware include, sensitive personal information, online banking details, even your identity",
                "Spyware is operated by modifying the security settings on your devices, " + 
                "it often bundles itself with legitimate software or Trojan horses"
        ));

        responses.put("adware", List.of(

        ));

        responses.put("backdoor", List.of(

        ));

        responses.put("scareware", List.of(

        ));

        responses.put("rootkit", List.of(

        ));

        responses.put("virus", List.of(

        ));

        responses.put("trojan horse", List.of(

        ));

        responses.put("worms", List.of(

        ));
    }

}
