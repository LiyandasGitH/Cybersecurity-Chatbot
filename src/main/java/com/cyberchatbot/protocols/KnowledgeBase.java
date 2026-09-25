package com.cyberchatbot.protocols;

import com.cyberchatbot.ui.ConsoleUI;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KnowledgeBase {

    private static final String KB_FILENAME = "knowledge_base.json";
    private final Path kbPath;

    public KnowledgeBase() {
        this.kbPath = Paths.get(KB_FILENAME);
    }

    public Map<String, List<String>> loadSortedKnowledgeBase() {
        Map<String, List<String>> responses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        try {
            if (!Files.exists(kbPath)) return responses;



        } catch (IOException ex) {
            ConsoleUI.printError("Trouble loading knowledge base: " + ex.getMessage());
        }
        return responses;
    }

}
