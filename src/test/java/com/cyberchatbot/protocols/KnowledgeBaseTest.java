package com.cyberchatbot.protocols;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class KnowledgeBaseTest {

    private static final Path KB_LOCAL_PATH = Paths.get("knowledge_base.json");
    private Path backupPath;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        // back up existing working-directory knowledge_base.json if present
        if (Files.exists(KB_LOCAL_PATH)) {
            backupPath = tempDir.resolve("knowledge_base_backup.json");
            Files.copy(KB_LOCAL_PATH, backupPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @AfterEach
    void tearDown() throws IOException {
        // restore or clean up working-directory file to keep tests isolated
        if (backupPath != null && Files.exists(backupPath)) {
            Files.copy(backupPath, KB_LOCAL_PATH, StandardCopyOption.REPLACE_EXISTING);
        } else {
            Files.deleteIfExists(KB_LOCAL_PATH);
        }
    }

    @Test
    @DisplayName("loadSortedKnowledgeBase should parse JSON into a case-insensitive, sorted TreeMap")
    void testLoadSortedKnowledgeBaseStructure() throws IOException {
        String testJson = """
                {
                  "topics": [
                    {
                      "keyword": "vpn",
                      "responses": [
                        "A VPN encrypts your connection.",
                        "Use VPNs on public Wi-Fi."
                      ]
                    },
                    {
                      "keyword": "firewall",
                      "responses": [
                        "Firewalls monitor network traffic."
                      ]
                    }
                  ]
                }
                """;
        Files.writeString(KB_LOCAL_PATH, testJson, StandardCharsets.UTF_8);

        KnowledgeBase kb = new KnowledgeBase();
        Map<String, List<String>> map = kb.loadSortedKnowledgeBase();

        assertNotNull(map);
        assertEquals(2, map.size());

        // Verify alphabetical key ordering
        List<String> keys = new ArrayList<>(map.keySet());
        assertEquals("firewall", keys.get(0));
        assertEquals("vpn", keys.get(1));

        // Verify case-insensitive lookup
        assertTrue(map.containsKey("VPN"));
        assertTrue(map.containsKey("vpn"));
        assertEquals(2, map.get("vpn").size());
        assertEquals("A VPN encrypts your connection.", map.get("vpn").get(0));
    }

    @Test
    @DisplayName("appendToKB should add new entries, keep keys sorted, and write to disk")
    void testAppendToKBAndPersistence() {
        KnowledgeBase kb = new KnowledgeBase();
        Map<String, List<String>> responses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        responses.put("phishing", new ArrayList<>(List.of("Beware of suspicious links.")));

        // Append a topic that sorts alphabetically before "phishing"
        kb.appendToKB(responses, "antivirus", List.of("Keep your antivirus definitions updated."));

        // In-memory verification
        assertEquals(2, responses.size());
        assertEquals("antivirus", responses.keySet().iterator().next());
        assertTrue(responses.get("antivirus").contains("Keep your antivirus definitions updated."));

        // File persistence verification
        assertTrue(Files.exists(KB_LOCAL_PATH));
        Map<String, List<String>> reloaded = kb.loadSortedKnowledgeBase();
        assertTrue(reloaded.containsKey("antivirus"));
        assertTrue(reloaded.containsKey("phishing"));
        assertEquals(List.of("Keep your antivirus definitions updated."), reloaded.get("antivirus"));
    }

    @Test
    @DisplayName("appendToKB should append responses to an existing keyword without overwriting")
    void testAppendToExistingKeyword() {
        KnowledgeBase kb = new KnowledgeBase();
        Map<String, List<String>> responses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        responses.put("passwords", new ArrayList<>(List.of("Use strong passwords.")));
        kb.appendToKB(responses, "passwords", List.of("Never reuse passwords across sites."));

        assertEquals(1, responses.size());
        assertEquals(2, responses.get("passwords").size());
        assertEquals("Use strong passwords.", responses.get("passwords").get(0));
        assertEquals("Never reuse passwords across sites.", responses.get("passwords").get(1));
    }

    @Test
    @DisplayName("appendToKB should ignore null or empty answer lists")
    void testAppendWithNullOrEmptyAnswers() {
        KnowledgeBase kb = new KnowledgeBase();
        Map<String, List<String>> responses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        responses.put("malware", new ArrayList<>(List.of("Avoid suspicious downloads.")));

        kb.appendToKB(responses, "malware", null);
        kb.appendToKB(responses, "malware", List.of());

        assertEquals(1, responses.get("malware").size());
    }

    @Test
    @DisplayName("sanitiseGeminiResponse should clean markdown syntax, bullets, and split lines")
    void testSanitiseGeminiResponseFormatting() {
        String rawMarkdown = """
                ### What is Ransomware?
                **Ransomware** is malicious software that encrypts your personal files.
                ---
                1. Always keep offline backups of critical data.
                * Never pay the ransom demand.
                """;

        List<String> cleaned = KnowledgeBase.sanitiseGeminiResponse(rawMarkdown);

        assertNotNull(cleaned);
        assertEquals(3, cleaned.size());

        // Header and horizontal rule skipped
        assertFalse(cleaned.stream().anyMatch(s -> s.startsWith("#") || s.startsWith("---")));

        // Bolding stripped
        assertEquals("Ransomware is malicious software that encrypts your personal files.", cleaned.get(0));

        // Numbered and asterisk bullet markers stripped
        assertEquals("Always keep offline backups of critical data.", cleaned.get(1));
        assertEquals("Never pay the ransom demand.", cleaned.get(2));
    }

    @Test
    @DisplayName("sanitiseGeminiResponse should return an empty list for null or blank input")
    void testSanitiseGeminiResponseBlankInputs() {
        assertTrue(KnowledgeBase.sanitiseGeminiResponse(null).isEmpty());
        assertTrue(KnowledgeBase.sanitiseGeminiResponse("").isEmpty());
        assertTrue(KnowledgeBase.sanitiseGeminiResponse("   \n\t  ").isEmpty());
    }

    @Test
    @DisplayName("loadSortedKnowledgeBase and appendToKB should properly escape and unescape special characters")
    void testJsonEscapingAndUnescaping() {
        KnowledgeBase kb = new KnowledgeBase();
        Map<String, List<String>> responses = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        String keyword = "quote_test";
        List<String> answer = List.of("Use \"double quotes\" and newlines\nhere.");

        kb.appendToKB(responses, keyword, answer);

        Map<String, List<String>> reloaded = kb.loadSortedKnowledgeBase();
        assertTrue(reloaded.containsKey(keyword));
        assertEquals(answer.get(0), reloaded.get(keyword).get(0));
    }

}
