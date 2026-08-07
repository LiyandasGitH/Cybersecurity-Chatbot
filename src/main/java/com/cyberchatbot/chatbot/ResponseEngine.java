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
                "Social engineering manipulates people into performing actions or divulging confidential information rather than systems.",
                "Attackers exploit trust, authority, and urgency to bypass technical defences.",
                "Be suspicious of any unsolicited request for credentials, access, or money — " +
                        "even if the caller claims to be from IT support or your bank.",
                "Pretexting involves creating a fabricated scenario to extract information. " +
                        "Verify identities through official channels before sharing anything sensitive.",
                "Tailgating is physically following someone into a restricted area. " +
                        "Never hold doors open for people you don't recognise, even if they look official.",
                "Social engineers often rely on people's willingness to be helpful, but they also prey on their weaknesses."
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
                "Adware is often installed with versions of software and " + 
                        "is designed to automatically deliver ads to a user", 
                "Most adware are found on web browsers, and it is hard to ignore " +
                        "when faced with constant pop up ads on your screen",
                "It is common for adware to come with spyware"
        ));

        responses.put("backdoor", List.of(
                "This malware is used to gain unauthorised access by bypassing the normal " +
                        "authentication procedures to access a system",
                "Hackers gain remote access to resources within an application and issue remote system commands", 
                "A backdoor malware works in the background and is difficult to detect"
        ));

        responses.put("scareware", List.of(
                "This is a type of malware that uses 'scare' tactics to trick you into taking a specific action.",
                "Scareware mainly consists of OS style windows that pop up to warn you that your system is at risk " +
                        "and needs to run a specific program for it to return to normal operation."
        ));

        responses.put("rootkit", List.of(
                "This malware is designed to modify the OS to create a backdoor, which attackers can then use to access your computer remotely",
                "Rootkits take advantage of software vulnerabilities to gain access to resources that normally shouldn't be accessible and modify system files",
                "Rootkits can also modify system forensics and monitoring tools, making them very hard to detect",
                "In most cases, a computer infected by a rootkit has to be wiped and any required software reinstalled."
        ));

        responses.put("virus", List.of(
                "A virus is a type of computer program that, when executed, replicates and attaches itself to other executable files, such as a document, by inserting its own code",
                "Most viruses require end-user interaction to initiate activation and can be written to act on a specific date or time.",
                "Viruses can be relatively harmless, such as those that display a funny image. " + 
                "Or they can be destructive, such as those that modify or delete data.",
                "Viruses can also be programmed to mutate in order to avoid detection.",
                "Most viruses are spread by USB drives, optical disks, network shares or email."
        ));

        responses.put("trojan horse", List.of(
                "Trojan horse malware carries out malicious operations by masking its true intent.",
                "A Trojan horse might appear legitimate but is, in fact, very dangerous.",
                "Trojans exploit your user privileges and are most often found in image files, audio files or games.",
                "Unlike viruses, Trojans do not self-replicate but act as a decoy to sneak malicious software past unsuspecting users."
        ));

        responses.put("worms", List.of(
                "This is a type of malware that replicates itself in order to spread from one computer to another. ",
                "Unlike a virus, which requires a host program to run, worms can run by themselves.",
                "Other than the initial infection of the host, worms do not require user participation and can spread very quickly over the network.",
                "Worms share similar patterns:" +
                "They exploit system vulnerabilities, they have a way to propagate themselves" +
                "and they all contain malicious code (payload) to cause damage to computer systems or networks.",
                "Worms are responsible for some of the most devastating attacks on the Internet."
        ));

        responses.put("DoS", List.of(
                "Denial-of-Service (DoS) attacks are a type of network attack that is relatively simple to carry out, even by an unskilled attacker.",
                " A DoS attack results in some sort of interruption of network service to users, devices or applications.",
                "DoS attacks are considered a major risk because they can easily interrupt communication and cause significant loss of time and money.",
                "DDoS attacks are extremely difficult to defend against because the attacks originate from hundreds, even thousands, of zombie hosts, and the attacks appear as legitimate traffic"
        ));

        responses.put("DDoS", List.of(
                "A Distributed Denial of Service (DDoS) attack is similar to a DoS attack but originates from multiple, coordinated sources",
                "An attacker builds a network (botnet) of infected hosts called zombies, which are controlled by handler systems." +
                "The zombie computers will constantly scan and infect more hosts, creating more and more zombies." + 
                "When ready, the hacker will instruct the handler systems to make the botnet of zombies carry out a DDoS attack."
        ));

        responses.put("Botnet", List.of(
                "A bot computer is typically infected by visiting an unsafe website or opening an infected email attachment or infected media file.",
                "A botnet is a group of bots, connected through the Internet, that can be controlled by a malicious individual or group.",
                "Bot can have tens of thousands, or even hundreds of thousands, of bots that are typically controlled through a command and control server.",
                "Bots can be activated to distribute malware, launch DDoS attacks, distribute spam email, or execute brute-force password attacks.",
                "Cybercriminals will often rent out botnets to third parties for nefarious purposes."
        ));

        responses.put("on path attacks", List.of(
                "On-path attackers intercept or modify communications between two devices, " +
                "such as a web browser and a web server, " + "either to collect information from or to impersonate one of the devices.",
                "This type of attack is also referred to as a man-in-the-middle or man-in-the-mobile attack.",
                "A man in the middle (MitM) attack happens when a cybercriminal takes control of a device without the user's knowledge",
                "With MitM level of access, an attacker can intercept and capture user information before it is sent to is intended destination",
                "MitM attacks are often used to steal financial information",
                "A man in the mobile (MitMo) attack is used to take control over a user's mobile device.",
                "With MitMo attack, the mobile device is instructed to exfiltrate user sensitive information and send it to attackers",
                "ZeuS is one example of a malware package with MitMo capabilities",
                "ZeuS allows attackes to quietly capture two step verification SMS messages that are sent to users"
        ));

        responses.put("seo poisoning", List.of(
                "Search engine optimisation (SEO) is about improving an organisaton's website so that it gains greater visibility in search engine results",
                "Attackers take advantage of popular search terms and use SEO to push malicious sites higher up the ranks of search results.",
                "The most common goal of SEO poisoning is to increase traffic to malicious sites that may host malware or attempt social engineering"
        ));

        responses.put("wifi password cracking", List.of(
                ""
        ));

        responses.put("password attacks", List.of());

        responses.put("cracking times", List.of(
                // Look into Ophcrack, L0phtCrack, THC Hydra, RainbowCrack, Medusa
        ));

        responses.put("advanced persistent threats", List.of());

        responses.put("ids", List.of(
                "Intrusion detection systems (IDSs) and intrusion prevention systems (IPSs) are security measures deployed on a network to detect and prevent malicious activities.",
                "An IDS can either be a dedicated network device or one of several tools in a server, firewall or even a host computer operating system, " +
                        "that scans data against signatures, looking for malicious traffic",
                "The job of an IDS is to detect, log and report to a network administrator",
                "The IDS will log the detection and create an alert for a network administrator." +
                        "It will not take action and therefore it will not prevent attacks from happening",
                "To prevent network delay (known as latency), an IDS is usually placed offline, separate from the regular network traffic"
        ));

        responses.put("ips", List.of(
                "Intrusion prevention systems (IPSs) and intrusion detection systems (IDSs) are security measures deployed on a network to detect and prevent malicious activities.",
                "An IPS can block or deny traffic based on a positive rule or signature match"
        ));

        responses.put("penetration testing", List.of(
                "Pen testing is the act of assessing a computer system, network or organization for security vulnerabilities",
                "A pen test seeks to breach systems, people, processes and code to uncover vulnerabilities which could be exploited",
                "Pen testers use gathered information to improve the system's defenses to ensure that it is better able to withstand cyber attacks in the future"
        ));

        responses.put("siem", List.of(
                "a security information and event management system collects and analyses security alerts, logs and other real-time and historical data from security devices on the network to facilitate early detection of cyber attacks"
        ));

        responses.put("dlp", List.of(
                "a data loss prevention system is designed to stop sensitive data from being stolen from or escaping a network.",
                "DLPs monitors and protects data in three different states: " +
                        "data in use (data being accessed by a user), " +
                        "data in motion (data travelling through the network)" +
                        "data at rest (data stored in a computer network or device)"
        ));

}

}
