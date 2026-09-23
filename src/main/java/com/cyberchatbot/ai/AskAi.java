package com.cyberchatbot.ai;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

// should only process data, should be concerned with question and answer

public class AskAi {
    public static final String MASTER_PROMPT = """
            You are a knowledgeable, friendly, and concise Cyber Security Awareness Assistant.
            Your job is to educate users on best security practices, safe browsing, password hygiene,
            phishing prevention, and general threat mitigation.
            Keep explanations accessible to beginners, practical, and action-oriented.
            Do not provide functional exploit code, malware scripts, or assist in unauthorized attacks.
            Answer ONLY cybersecurity-related questions.
            If the question is not related to cybersecurity, politely decline and suggest a cybersecurity topic instead.
            """;
    public AskAi() {
    }

    public static String askAi(String question) {

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            return "Error: API_KEY environment variable is not set.";
        }

        try (Client client = Client.builder()
                .apiKey(apiKey)
                .build()) {

            GenerateContentConfig config = GenerateContentConfig.builder()
                    .systemInstruction(Content.builder()
                            .parts(List.of(Part.fromText(MASTER_PROMPT)))
                            .build())
                    .build();
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-3.6-flash",
                    question,
                    config
            );
            return response.text();

        } catch (Exception e) {
           return "Error communicating with Chatbot: " + e.getMessage();
        }
    }
}
