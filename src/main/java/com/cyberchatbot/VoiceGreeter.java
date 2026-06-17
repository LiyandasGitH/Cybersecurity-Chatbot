package com.cyberchatbot;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceGreeter {
    private final String name;
    static {
        System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"
        );
    }
    private final Voice voice;

    public VoiceGreeter(String name) {
        this.name = name;

        voice = VoiceManager.getInstance().getVoice(name);

        if (voice == null) {
            throw new IllegalStateException(
                    "Voice '" + this.name + "' could not be found."
            );
        }
        voice.allocate();
        voice.setRate(150);
        voice.setPitch(100);
        voice.setVolume(3.0f);

    }

    public void say(String something) {
        voice.speak(something);
    }

    public void sayMore(String[] somethingMore) {

        for (int i = 0; i < somethingMore.length; i++) {
            this.say(somethingMore[i]);
        }
    }

    public void speakSync(String something) {
        Thread speechThread = new Thread(() -> {
            try {
                this.say(something);
            } catch (Exception e) {
                ConsoleUI.printError("[System Warning] Voice reply cut short: " + e.getMessage() + "\n");
            }
        });
        speechThread.setDaemon(true);
        speechThread.start();

        // Forces greeting styling delay
        try {
            speechThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ConsoleUI.printError(" [System Warning] Audio playback interrupted.\n");
        }
    }

    public static void greet() {
        VoiceGreeter voice = null;
        try {
            voice = new VoiceGreeter("kevin16");

            voice.speakSync("Welcome to CyberBot. Your cybersecurity guide!");

        } catch (Exception e) {
            ConsoleUI.printError("[System Warning] Audio subsystem unavailable: " + e.getMessage() + "\n");
        } finally {
            if (voice != null && voice.voice != null) {
                voice.voice.deallocate();
            }
        }
    }

}
