# Cybersecurity Awareness Chatbot

## Stack
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Java Version](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://www.oracle.com/java/)
[![Build Tool](https://img.shields.io/badge/Maven-3.6%2B-red.svg)](https://maven.apache.org/)
[![Google Gemini](https://img.shields.io/badge/Google%20Gemini-8E75B2?logo=googlegemini&logoColor=white)](https://ai.google.dev/)
[![FreeTTS](https://img.shields.io/badge/Audio-FreeTTS-1DB954.svg)](#)

## Executive Summary

The Cybersecurity Awareness Chatbot is an interactive, Java-based console application designed to bridge the gap between complex digital security concepts and everyday users.

By leveraging a conversational interface, vibrant terminal aesthetics, and multimedia elements like voice synthesis, the application delivers bite-sized, actionable cybersecurity education.

Built entirely on Object-Oriented Programming (OOP) principles, this tool serves as an engaging platform to train individuals on modern digital threats and defensive best practices.

## Gen Ai Implementation

To further elevate the platform's educational capabilities, the application integrates advanced artificial intelligence using the official Google Gen AI SDK for Java. 

When users inquire about niche cybersecurity topics that fall outside the scope of the local rule-based dictionary, the application seamlessly routes their questions to a cloud-based generative AI model, such as Gemini 2.5 Flash.

This Generative AI implementation utilises targeted system instructions governed by a centralised master prompt, ensuring that the model's generated responses remain strictly focused, accurate, and contextually appropriate for digital security training. 

Additionally, the architecture incorporates defensive exception handling around the API calls; this guarantees that if a network dropout or transient HTTP error occurs, the chatbot gracefully manages the failure and falls back to its local knowledge base without crashing or exposing raw stack traces to the end user.


## Project Overview 

A Java console application that educates users about common cybersecurity
threats through conversational interaction.

### Key Highlights
* **Highly Extensible:** Built using robust architectural layers separating Core Logic, Input Validation Processing, Response Mapping, Console Interfaces, and Audio APIs.
* **Hybrid Intelligence Engine:** Seamlessly switches between local token-based keyword matches and cloud-based Google GenAI queries when unrecognised queries arise.
* **Resilient Design:** Safe input sanitization traps numeric noise, edge-case strings, and overflowing buffers gracefully without collapsing terminal threads.

## Demo 

**[![Watch the Demo](https://img.shields.io/badge/YouTube-Watch%20Demo-red?style=for-the-badge&logo=youtube)](YOUR_YOUTUBE_LINK_HERE)**

## Project Structure 

```text

Cyber-Security-Awareness-Chatbot/
├── .env
├── .gitignore
├── .script.sh
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
                ├── HelpCentre.java
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

- **Java Development Kit (JDK):** Version 17 or higher
- **Build Automation Tool:** Apache Maven 3.6+
- **Google Gen AI API Key:** Required for external AI fallback (configured via .env or system environment variable GEMINI_API_KEY)
- **Operating System Environment:** Pop!_OS, Ubuntu/Debian, macOS, or modern Windows environments (PowerShell / Windows Terminal) capable of processing raw ANSI character structures.

## Installation & Running the Application


git clone [Cyber-Security-Awareness-Chatbot](https://github.com/LiyandasGitH/Cyber-Security-Awareness-Chatbot.git)

cd Cyber-Security-Awareness-Chatbot


1. **Configure your API Key:**
    a. Create a .env file or export your Gemini API key


## Application Execution 

```text
mvn compile exec:java -Dexec.mainClass="com.cyberchatbot.Main"
```
**OR** 
```text
mvn clean compile exec:java -Dexec.vmArgs="--enable-native-access=ALL-UNNAMED"
```

## Running the Tests
```text
mvn clean test
```

## Known Limitations 

- **FreeTTS Engine Warnings:** 
- FreeTTS may produce warnings on JDK 17+; the chatbot runs correctly without
  voice if TTS fails to initialise
- ANSI colours do not render in legacy Windows cmd.exe without Jansi

## Topics Covered 

- **Phishing & Social Engineering** (Detecting malicious links and vectors)

- **Credential Lifecycle Management** (Building strong, resilient passwords)

- **Defence Layers** (Firewalls, 2FA configurations, and secure VPN routing)

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
