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
    public static void greet() {

        // Threading
        Thread speechThread = new Thread(() -> {
            try {
                VoiceGreeter voice = new VoiceGreeter("kevin16");

                String[] cyberTalk = new String[]{
                        "Welcome to CyberBot. Your cybersecurity guide!"
//                    "Hello! Before we begin, what is your name?"
//                    "Ask me about passwords",
//                    "Ask me about phishing",
//                    "Ask me about malware attacks"
                };

                voice.sayMore(cyberTalk);
            } catch (Exception e) {
                ConsoleUI.printError("[System Warning] Audio subsystem unavailable: " + e.getMessage() + "\n");
            }
        });
        speechThread.start();

        // pause the thread temporarily to create a delay between start of program (banner) and first botResponse
        // evaluate if this is necessary, will it be true for all following time kevin has to speak
        try {
            speechThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
