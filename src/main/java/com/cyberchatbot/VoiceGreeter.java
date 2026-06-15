package com.cyberchatbot;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceGreeter {

    private String name;
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

        if (this.voice == null) {
            throw new IllegalStateException(
                    "Voice '" + this.name + "' could not be found."
            );
        }
        voice.allocate();
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
        try {
            VoiceGreeter voice = new VoiceGreeter("kevin16");

            String[] cyberTalk = new String[]{

                    "Ask me about passwords",
                    "Ask me about phishing",
                    "Ask me about malware attacks"
            };

//        for (String option : cyberTalk) {
//            System.out.println(option);
//        }
            voice.sayMore(cyberTalk);
        } catch (Exception e) {
            System.err.println("[System Warning] Audio subsystem unavailable: " + e.getMessage());
        }
    }

}
