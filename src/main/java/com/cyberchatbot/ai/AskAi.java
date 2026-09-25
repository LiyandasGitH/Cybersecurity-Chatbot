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
    private static final String API_KEY;

    public AskAi() {
    }

    static {
        String key = null;
        try {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            key = dotenv.get("API_KEY");
        } catch (Exception ignored) {}

        if (key == null || key.isBlank()) {
            key = System.getenv("API_KEY");
        }
        API_KEY = key;
    }

    public static boolean isConfigured() {
        return API_KEY != null && !API_KEY.isBlank();
    }

    /**
     * Sends the question to Gemini.
     * Returns null on error so fallback logic handles it gracefully.
     */
    public static String askAi(String question) {
        if (!isConfigured()) {
            return null;
        }

        try (Client client = Client.builder()
                .apiKey(API_KEY)
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
            String text = response.text();
            return (text != null && !text.isBlank() ? text.trim() : null);

        } catch (Exception e) {
           return null;
        }
    }
}
