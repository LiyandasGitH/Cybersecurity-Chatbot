package com.cyberchatbot.protocols;

import com.cyberchatbot.ui.ConsoleUI;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class KnowledgeBase {

    private static final String KB_FILENAME = "knowledge_base.json";
    private final Path kbPath;

    public KnowledgeBase() {
        this.kbPath = Paths.get(KB_FILENAME);
        ensureWritableFile();
    }

    /**
     * Loads the JSON into a sorted TreeMap (alphabetical by keyword).
     */
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

    /**
     * Appends a new response to a keyword, keeping keys sorted, and persists to disk.
     */
    public synchronized void appendToKB(Map<String, List<String>> responses, String keyword, List<String> answers) {
        if (answers == null || answers.isEmpty()) {
            return;
        }

        responses.computeIfAbsent(keyword, k -> new ArrayList<>()).addAll(answers);

        try {
            String json = serialise(responses);
            Files.writeString(kbPath, json, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            ConsoleUI.printError("Failed to persist knowledge base: " + e.getMessage());
        }
    }

    private String serialise(Map<String, List<String>> responses) {

        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"topics\": [\n");

        Iterator<Map.Entry<String, List<String>>> it = responses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<String>> entry = it.next();
            sb.append("    {\n");
            sb.append("      \"keyword\": \"")
                    .append(escapeJson(entry.getKey()))
                    .append("\",\n");
            sb.append("      \"responses\": [\n");

            List<String> items = entry.getValue();
            for (int i = 0; i < items.size(); i++) {
                sb.append("        \"")
                        .append(escapeJson(items.get(i)))
                        .append("\"");
                if (i < items.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("      ]\n");
            sb.append("    }");
            if (it.hasNext()) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n}");
        return sb.toString();
    }

    public static List<String> sanitiseGeminiResponse(String rawGeminiText) {
        if (rawGeminiText == null || rawGeminiText.isBlank()) {
            return List.of();
        }

        String cleaned = rawGeminiText
                .replace("**", "")
                .replace("__", "")
                .replace("`", "");

        String[] lines = cleaned.split("\n+");
        List<String> validResponses = new ArrayList<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.startsWith("#") || trimmed.startsWith("---") || trimmed.isBlank()) {
                continue;
            }
            trimmed = trimmed.replaceFirst("^[0-9]+\\.\\s*", "").replaceFirst("^\\*\\s*", "");

            if (!trimmed.isBlank()) {
                validResponses.add(trimmed);
            }
        }

        if (validResponses.isEmpty()) {
            validResponses.add(cleaned.trim());
        }

        return validResponses;
    }

    private void ensureWritableFile() {
        if (!Files.exists(kbPath)) {
            try (InputStream in = getClass().getClassLoader().getResourceAsStream(KB_FILENAME)) {
                if (in != null) {
                    Files.copy(in, kbPath, StandardCopyOption.REPLACE_EXISTING);
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

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "")
                .replace("\t", "\\t");
    }
}
