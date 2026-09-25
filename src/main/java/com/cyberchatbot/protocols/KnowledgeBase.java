package com.cyberchatbot.protocols;

import com.cyberchatbot.ui.ConsoleUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class KnowledgeBase {

    private static final String KB_FILENAME = "knowledge_base.json";
    private final Path kbPath;

    public KnowledgeBase() {
        this.kbPath = Paths.get(KB_FILENAME);
        ensureWritableFile();
    }

    public Map<String, List<String>> loadSortedKnowledgeBase() {
        Map<String, List<String>> responses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        try {
            if (!Files.exists(kbPath)) return responses;

            String json = Files.readString(kbPath, StandardCharsets.UTF_8);
            String[] blocks = json.split("\\{\\s*\"keyword\"");

            for (int i = 1; i < blocks.length; i++) {
                String block = blocks[i];

                int kStart = block.indexOf('"') + 1;
                int kEnd   = block.indexOf('"', kStart);
                if (kStart < 1 || kEnd < 0) continue;
                String keyword = block.substring(kStart, kEnd);

                int arrStart = block.indexOf('[');
                int arrEnd   = block.lastIndexOf(']');
                if (arrStart < 0 || arrEnd < 0) continue;
                String arrContent = block.substring(arrStart + 1, arrEnd);

                List<String> items = extractJsonStrings(arrContent);
                if (!items.isEmpty()) {
                    responses.put(keyword, items);
                }
            }
        } catch (IOException ex) {
            ConsoleUI.printError("Trouble loading knowledge base: " + ex.getMessage());
        }
        return responses;
    }

    public synchronized void appendAndSave(Map<String, List<String>> responses, String keyword, String answer) {

    }

    private void ensureWritableFile() {
        if (!Files.exists(kbPath)) {
            try {
                URL res = getClass().getClassLoader().getResource(KB_FILENAME);
                if (res != null) {
                    try (InputStream in = res.openStream()) {
                        Files.copy(in, kbPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            } catch (IOException e) {
                ConsoleUI.printError("Could not seed knowledge base: " + e.getMessage());
            }
        }
    }

    private List<String> extractJsonStrings(String json) {
        List<String> result = new ArrayList<>();
        int pos = 0;

        while (pos < json.length()) {
            int start = json.indexOf('"', pos);
            if (start < 0) break;
            int end = start + 1;
            while (end < json.length()) {
                char c = json.charAt(end);
                if (c == '"' && json.charAt(end - 1) != '\\') break;
                end++;
            }
            if (end >= json.length()) break;
            String value = json.substring(start + 1, end)
                    .replace("\\\"", "\"")
                    .replace("\\n", "\n")
                    .replace("\\\\", "\\");
            if (!value.isBlank()) result.add(value);
            pos = end + 1;
        }
        return result;
    }
}
