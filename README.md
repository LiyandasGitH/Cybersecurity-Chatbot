# Cybersecurity Awareness Chatbot

## Executive Summary

The Cybersecurity Awareness Chatbot is an interactive, Java-based console application designed to bridge the gap between complex digital security concepts and everyday users.

By leveraging a conversational interface, vibrant terminal aesthetics, and multimedia elements like voice synthesis, the application delivers bite-sized, actionable cybersecurity education.

Built entirely on Object-Oriented Programming (OOP) principles, this tool serves as an engaging platform to train individuals on modern digital threats and defensive best practices.

## Gen Ai Implementation

To further elevate the platform's educational capabilities, the application integrates advanced artificial intelligence using the official Google Gen AI SDK for Java. 

When users inquire about niche cybersecurity topics that fall outside the scope of the local rule-based dictionary, the application seamlessly routes their questions to a cloud-based generative AI model, such as Gemini 2.5 Flash.

This Generative AI implementation utilises targeted system instructions governed by a centralised master prompt, ensuring that the model's generated responses remain strictly focused, accurate, and contextually appropriate for digital security training. 

Additionally, the architecture incorporates defensive exception handling around the API calls; this guarantees that if a network dropout or transient HTTP error occurs, the chatbot gracefully manages the failure and falls back to its local knowledge base without crashing or exposing raw stack traces to the end user.


## Stack
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Java Version](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://www.oracle.com/java/)
[![Build Tool](https://img.shields.io/badge/Maven-3.6%2B-red.svg)](https://maven.apache.org/)
[![UI Style](https://img.shields.io/badge/Terminal%20UI-Jansi%202.4.1-blue?logo=gnometerminal&logoColor=white)](https://fusesource.github.io/jansi/)
[![Audio Engine](https://img.shields.io/badge/Audio-FreeTTS%201.2.2-1DB954.svg)](#)
[![Generative AI](https://img.shields.io/badge/Gen%20AI-Google%20Gen%20AI%20SDK%201.0.0-8E75B2?logo=googlegemini&logoColor=white)](https://ai.google.dev/)
[![Execution Plugin](https://img.shields.io/badge/Runner-Exec%20Maven%20Plugin%203.6.3-C71A36?logo=apachemaven&logoColor=white)](https://www.mojohaus.org/exec-maven-plugin/)
[![Testing Framework](https://img.shields.io/badge/Testing-JUnit%205.12.0-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Test Runner](https://img.shields.io/badge/Runner-Maven%20Surefire-red?logo=apachemaven&logoColor=white)](https://maven.apache.org/plugins/maven-surefire-plugin/)

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
├── LICENSE
├── README.md
├── knowledge_base.json
├── pom.xml
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
│   │   │   ├── protocols/
│   │   │   │   └── KnowledgeBase.java
│   │   │   └── ui/
│   │   │       ├── ConsoleUI.java
│   │   │       ├── HelpCentre.java
│   │   │       └── VoiceGreeter.java
│   │   └── resources/
│   │       └── knowledge_base.json
│   └── test/java/com/cyberchatbot/
│       ├── chatbot/
│       │   ├── ChatbotTest.java
│       │   └── ResponseEngineTest.java
│       ├── protocols/
│       │   └── KnowledgeBaseTest.java
│       └── ui/
│           ├── ConsoleUITest.java
│           ├── HelpCentreTest.java
│           └── VoiceGreeterTest.java
└── target/
```

## Features

- **ASCII Banner & Terminal Styling:** ANSI color escapes and stylized CyberBot logo with Jansi integration.
- **Voice Synthesis:** Asynchronous startup greetings and speech synthesis powered by FreeTTS.
- **Rich Local Knowledge Base:** A range of cybersecurity topics with multi-variant randomized answers.
- **Dynamic Help Center:** ASCII-styled reference table categorizing supported local topics and commands.
- **Cloud AI Fallback:** Live querying via Google Gen AI SDK (AskAi) for advanced and niche security questions.
- **Rigorous Input Validation:** Sanitisation traps for numeric input, empty lines, string overflows, and command flags (```exit```, ```quit```, ```q```).


## Requirements 

- **Java Development Kit (JDK):** Version 17 or higher
- **Build Automation Tool:** Apache Maven 3.6+
- **Google Gen AI API Key:** Required for external AI fallback (configured via .env or system environment variable GEMINI_API_KEY)
- **Operating System Environment:** Pop!_OS, Ubuntu/Debian, macOS, or modern Windows environments (PowerShell / Windows Terminal) capable of processing raw ANSI character structures.

## Installation & Running the Application

1. Clone the repository.
    - git clone **[Cyber-Security-Awareness-Chatbot](https://github.com/LiyandasGitH/Cyber-Security-Awareness-Chatbot.git)**
    - cd Cybersecurity-Chatbot


2. **Configure your API Key:**
    - Create a ```.env``` file 
    - Or **export** your Gemini API key in the terminal:
    ```
   export GEMINI_API_KEY="your-api-key-here"
   ```


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

- **FreeTTS Engine Warnings:** FreeTTS may produce warnings on JDK 17+. 
    - The chatbot runs correctly without voice if TTS fails to initialise.
- **Terminal ANSI Support:** ANSI colours do not render in legacy Windows ```cmd.exe``` without Jansi.

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
