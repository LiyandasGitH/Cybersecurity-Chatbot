package com.cyberchatbot.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class VoiceGreeterTest {

    @AfterEach
    void tearDown() {
        VoiceGreeter.closeAudioSubsystem();
    }

    @Test
    @DisplayName("Constructor should instantiate successfully with valid voice 'kevin16'")
    void testConstructorWithValidVoice() {
        assertDoesNotThrow(() -> {
            VoiceGreeter greeter = new VoiceGreeter("kevin16");
            assertNotNull(greeter);
        });
    }

    @Test
    @DisplayName("Constructor should throw IllegalStateException for non-existent voice")
    void testConstructorWithInvalidVoiceThrows() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            new VoiceGreeter("invalid_voice_name_xyz");
        });

        assertTrue(exception.getMessage().contains("could not be found"));
    }

    @Test
    @DisplayName("say() and sayMore() should execute without throwing uncaught exceptions")
    void testSayAndSayMoreExecution() {
        assertDoesNotThrow(() -> {
            VoiceGreeter greeter = new VoiceGreeter("kevin16");
            greeter.say("Test message");
            greeter.sayMore(new String[]{"Line one", "Line two"});
        });
    }

    @Test
    @DisplayName("speakSync() should execute synchronously and terminate within timeout")
    void testSpeakSyncExecution() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            VoiceGreeter greeter = new VoiceGreeter("kevin16");
            greeter.speakSync("Short sync test");
        }, "speakSync should not hang indefinitely");
    }

    @Test
    @DisplayName("greet() should run synchronously and complete without throwing")
    void testGreet() {
        assertTimeoutPreemptively(Duration.ofSeconds(7), () -> {
            assertDoesNotThrow(VoiceGreeter::greet);
        }, "greet() should finish execution within timeout");
    }

    @Test
    @DisplayName("speakAsync() should return immediately without blocking the caller")
    void testSpeakAsyncIsNonBlocking() {
        long startTime = System.currentTimeMillis();

        assertDoesNotThrow(() -> {
            VoiceGreeter.speakAsync("This is an asynchronous speech call that should not block.");
        });

        long elapsedTime = System.currentTimeMillis() - startTime;
        assertTrue(elapsedTime < 500, "speakAsync should return almost instantly (< 500ms)");
    }

    @Test
    @DisplayName("speakClosing() should terminate within safety timeout")
    void testSpeakClosing() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            assertDoesNotThrow(() -> VoiceGreeter.speakClosing("Goodbye test"));
        });
    }

    @Test
    @DisplayName("closeAudioSubsystem() should execute safely even when lines are idle")
    void testCloseAudioSubsystem() {
        assertDoesNotThrow(VoiceGreeter::closeAudioSubsystem);
    }

}
