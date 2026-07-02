# Cyber-Security Awareness Chatbot

## Executive Summary

The Cybersecurity Awareness Chatbot is an interactive, Java-based console application designed to bridge the gap between complex digital security concepts and everyday users.

By leveraging a conversational interface, vibrant terminal aesthetics, and multimedia elements like voice synthesis, the application delivers bite-sized, actionable cybersecurity education.

Built entirely on Object-Oriented Programming (OOP) principles, this tool serves as an engaging platform to train individuals on modern digital threats and defensive best practices.


## Project Overview 

A Java console application that educates users about common cybersecurity
threats through conversational interaction.

## Demo 

(youtube link)

## Project Structure 

```text
cybersecurity-chatbot/
├── LICENSE
├── README.md
├── .gitignore
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── cyberchatbot/
    │               ├── Main.java
    │               ├── ChatBot.java
    │               ├── ResponseEngine.java
    │               ├── ConsoleUI.java
    │               └── VoiceGreeter.java
    │
    └── test/
        └── java/
            └── com/
                └── cyberchatbot/
                    ├── MainTest.java
                    ├── ChatBotTest.java
                    ├── ResponseEngineTest.java
                    ├── ConsoleUITest.java
                    └── VoiceGreeterTest.java
```

## UML Diagram

## Features

- ASCII art banner with ANSI colour output
- Voice greeting on startup (FreeTTS)
- 12 cybersecurity topics with keyword matching
- Input validation (blank, numeric, length, exit commands)
- Animated typing effect for responses
- Session summary on exit
- Help command listing all available topics

## Requirements 

- JDK 11 or higher
- Apache Maven 3.6+
- Windows Terminal, PowerShell, or any Unix terminal (for ANSI colours)

## Running the Application

```text
mvn exec:java
```

## Running the Tests
```text
mvn test
```

## Known Limitations 

- FreeTTS may produce warnings on JDK 17+; the chatbot runs correctly without
  voice if TTS fails to initialise
- ANSI colours do not render in legacy Windows cmd.exe without Jansi

## Topics Covered 

phishing · passwords · malware · 2FA · VPN · Wi-Fi safety ·
ransomware · backups · firewalls · social engineering · software updates


## Disclaimer

This project is an original Java implementation inspired by the concept of a cybersecurity chatbot. 
No source code from the original implementation was used.

## LICENSE

GPL-3.0 license

## Author/s 

Liyanda Tonisi