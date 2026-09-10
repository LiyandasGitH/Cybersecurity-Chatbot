package com.cyberchatbot.ai;

import java.util.Scanner;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

// should only process data, should be concerned with question and answer

public class AskAi {
    public AskAi() {
    }

    public static String askAi(String question) {

        try (Client client = Client.builder()
                .apiKey(System.getenv("API_KEY"))
                .build()) {

            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.0-flash",
                    question,
                    null
            );
            System.out.println(System.getenv("API_KEY"));

            return response.text();

        } catch (Exception e) {
           return "Error communicating with Chatbot: " + e.getMessage();
        }
    }
}
