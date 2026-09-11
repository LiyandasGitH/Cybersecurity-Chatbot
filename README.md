# Cyber-Security Awareness Chatbot

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Java Version](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://www.oracle.com/java/)
[![Build Tool](https://img.shields.io/badge/Maven-3.6%2B-red.svg)](https://maven.apache.org/)

## Executive Summary

The Cybersecurity Awareness Chatbot is an interactive, Java-based console application designed to bridge the gap between complex digital security concepts and everyday users.

By leveraging a conversational interface, vibrant terminal aesthetics, and multimedia elements like voice synthesis, the application delivers bite-sized, actionable cybersecurity education.

Built entirely on Object-Oriented Programming (OOP) principles, this tool serves as an engaging platform to train individuals on modern digital threats and defensive best practices.


## Project Overview 

A Java console application that educates users about common cybersecurity
threats through conversational interaction.

### Key Highlights
* **Highly Extensible:** Built using robust architectural layers separating Core Logic, Input Validation Processing, Response Mapping, Console Interfaces, and Audio APIs.
* **Resilient Design:** Safe input sanitization traps numeric noise, edge-case strings, and overflowing buffers gracefully without collapsing terminal threads.

## Demo 

**[![Watch the Demo](https://img.shields.io/badge/YouTube-Watch%20Demo-red?style=for-the-badge&logo=youtube)](YOUR_YOUTUBE_LINK_HERE)**

## Project Structure 

```text

Cyber-Security-Awareness-Chatbot/
├── .env
├── .gitignore
├── .stcript.sh
├── knowledge.txt
├── LICENSE
├── pom.xml
├── README.md
├── .idea/
├── .mvn/
├── .vscode/
├── src/
│   ├── main/
│   │   ├── java/com/cyberchatbot/
│   │   │   ├── Main.java
│   │   │   ├── ai/
│   │   │   │   └── AskAi.java
│   │   │   ├── chatbot/
│   │   │   │   ├── Chatbot.java
│   │   │   │   ├── ResponseEngine.java
│   │   │   │   ├── TheBox.java
│   │   │   │   └── User.java
│   │   │   ├── knowledgebase/
│   │   │   │   └── shortcuts.json
│   │   │   └── ui/
│   │   │       ├── ConsoleUI.java
│   │   │       └── VoiceGreeter.java
│   │   └── resources/
│   └── test/java/com/cyberchatbot/
│       ├── chatbot/
│       │   ├── ChatbotTest.java
│       │   └── ResponseEngineTest.java
│       └── ui/
│           ├── ConsoleUITest.java
│           └── VoiceGreeterTest.java
└── target/
```

## Features

- ASCII art banner with ANSI colour output
- Voice greeting on startup (FreeTTS)
- A range of cybersecurity topics with keyword matching
- Input validation (blank, numeric, length, exit commands)
- Animated typing effect for responses
- Session summary on exit
- Help command listing all available topics

## Requirements 

- **Java Development Kit (JDK):** Version 11 or higher
- **Build Automation Tool:** Apache Maven 3.6+
- **Operating System Environment:** Pop!_OS, Ubuntu/Debian, macOS, or modern Windows environments (PowerShell / Windows Terminal) capable of processing raw ANSI character structures.

## Installation & Running the Application

1. git clone [https://github.com/LiyandasGitH/Cyber-Security-Awareness-Chatbot.git](https://github.com/LiyandasGitH/Cyber-Security-Awareness-Chatbot.git)
2. cd Cyber-Security-Awareness-Chatbot


## Application Execution 

```text
mvn compile exec:java -Dexec.mainClass="com.cyberchatbot.Main"
```

## Running the Tests
```text
mvn clean test
```

## Known Limitations 

- FreeTTS may produce warnings on JDK 17+; the chatbot runs correctly without
  voice if TTS fails to initialise
- ANSI colours do not render in legacy Windows cmd.exe without Jansi

## Topics Covered 

- **Phishing & Social Engineering** (Detecting malicious links and vectors)

- **Credential Lifecycle Management** (Building strong, resilient passwords)

- **Defense Layers** (Firewalls, 2FA configurations, and secure VPN routing)

- **Threat Vectors** (Identifying ransomware, malware, and public Wi-Fi risks)

- **Data Integrity** (Proactive backup scheduling and software updates)

## Disclaimer

This project is an original Java implementation inspired by the concept of a cybersecurity chatbot. 
No proprietary source code from the original implementation was used.

## LICENSE

GPL-3.0 license

## WTC Repo Tracking 

WTC-947LSNL8

## Author/s 

Liyanda Tonisi
