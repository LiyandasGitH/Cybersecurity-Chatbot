package com.cyberchatbot;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceGreeter {

    private String name;

    private Voice voice;

    public VoiceGreeter(String name) {
        this.name = name;



        this.voice = VoiceManager.getInstance().getVoice(this.name);

        if (this.voice == null) {
            throw new IllegalStateException(
                    "Voice '" + this.name + "' could not be found."
            );
        }
        this.voice.allocate();
    }

    public void say(String something) {
        this.voice.speak(something);
    }

    public void sayMore(String[] somethingMore) {

        for (int i = 0; i < somethingMore.length; i++) {
            this.say(somethingMore[i]);
        }
    }
    public static void greet() {
    }
    
}
