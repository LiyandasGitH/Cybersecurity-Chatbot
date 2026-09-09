package com.cyberchatbot.chatbot;

/**
 * "The Box" is a response
 */
public class TheBox {

    private boolean isMeaningful; 
    private String answer;

    public TheBox(boolean isMeaningful, String answer) {
        this.isMeaningful = isMeaningful;
        this.answer = answer;
    }

    public String answer() {
        return answer;
    }

    public boolean isMeaningful() {
        return isMeaningful;
    }

}
