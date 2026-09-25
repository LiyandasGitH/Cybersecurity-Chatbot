package com.cyberchatbot.chatbot;

import java.util.*;

import com.cyberchatbot.ai.AskAi;
import com.cyberchatbot.protocols.KnowledgeBase;
import com.cyberchatbot.ui.VoiceGreeter;

public class ResponseEngine {

    private final KnowledgeBase protocol;
    private final Map<String, List<String>> responses;
    private final Random random;

    public ResponseEngine() {
        this.protocol = new KnowledgeBase();
        this.responses = protocol.loadSortedKnowledgeBase();
        this.random = new Random();
    }

    public int getTopicCount() {
        return responses.size();
    }
    // where an order is made from & for the chatbot
    public TheBox getResponse(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            return fallbackBox();
        }

        String botResponse = userInput.trim().toLowerCase();

        for (Map.Entry<String, List<String>> entry : responses.entrySet()) {
            if (botResponse.contains(entry.getKey())) {
                List<String> options = entry.getValue();
                if (!options.isEmpty()) {
                    String selectedReply = options.get(random.nextInt(options.size()));
                    // packing into the box
                    TheBox theBox = new TheBox(true, selectedReply);
                    VoiceGreeter.speakAsync(selectedReply);
                    // gives the chatbot the box w/ answer inside
                    return theBox;
                }
            }
        }

        if (AskAi.isConfigured()) {
            String geminiAnswer = AskAi.askAi(userInput);
            if (geminiAnswer != null && !geminiAnswer.isBlank()) {
                String inferredKeyword = inferredKeyword(botResponse);

                List<String> cleanResponses = KnowledgeBase.sanitiseGeminiResponse(geminiAnswer);

                protocol.appendToKB(responses, inferredKeyword, cleanResponses);

                VoiceGreeter.speakAsync(cleanResponses.get(0));
                return new TheBox(true, geminiAnswer);
            }
        }

        return fallbackBox();
    }

    private TheBox fallbackBox() {
        String defaultReply = "I don't have specific information on that topic yet. Let me go look for it: ";
        VoiceGreeter.speakAsync("I don't have specific information on that topic yet. Let me go look for it.");
        return new TheBox(false, defaultReply);
    }

    private String inferredKeyword(String normalInput) {
        Set<String> stopWords = Set.of(
                "what", "is", "are", "how", "do", "does", "can", "tell", "me",
                "about", "the", "a", "an", "i", "my", "your", "explain", "define"
        );

        for (String word : normalInput.split("\\s+")) {
            String clean = word.replaceAll("[^a-z0-9]", "");
            if (!clean.isEmpty() && !stopWords.contains(clean)) {
                return clean;
            }
        }
        return normalInput.length() > 20 ? normalInput.substring(0, 20) : normalInput;
    }
}
