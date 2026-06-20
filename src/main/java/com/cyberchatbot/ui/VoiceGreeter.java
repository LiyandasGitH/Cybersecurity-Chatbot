package com.cyberchatbot.ui;


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
                ConsoleUI.printError("Voice reply cut short: " + e.getMessage() + "\n");
            }
        });
        speechThread.setDaemon(true);
        speechThread.start();

        // Forces greeting styling delay
        try {
            speechThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ConsoleUI.printError("Audio playback interrupted.\n");
        }
    }

    public static void speakAsync(String message) {
        Thread speechThread = new Thread(() -> {
           VoiceGreeter voice = allocateVoice();

           if (voice != null) {
               try {
                   voice.say(message);
               } finally {
                   if (voice.voice != null) {
                       voice.voice.deallocate();
                   }
               }
           }
        });
        speechThread.setDaemon(true);
        speechThread.start();
    }

    public static void greet() {

        String welcomeMsg = "Welcome to CyberBot. Your cybersecurity guide!";

        VoiceGreeter voice = allocateVoice();

        if (voice != null) {
            try {
                voice.speakSync(welcomeMsg);
            }
            finally {
                if (voice.voice != null) {
                    voice.voice.deallocate();
                }
            }
        }
    }

    public static void speakClosing(String message) {
        VoiceGreeter voice = allocateVoice();

        if (voice != null) {
            try {
                Thread closingThread = new Thread(() -> voice.say(message));
                closingThread.start();
                closingThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            finally {
                if (voice.voice != null) {
                    voice.voice.deallocate();
                }
            }
        }
    }


    /**
     * Manage audio allocation and initilisation
     *
     * @return an allocated VoiceGreeter instance or null if audio fails
     */
    private static VoiceGreeter allocateVoice() {
        try {
            return new VoiceGreeter("kevin16");
        } catch (Exception e) {
            ConsoleUI.printError("Audio subsystem unavailable: " + e.getMessage() + "\n");
            return null;
        }
    }
}
