package com.cyberchatbot.chatbot;


public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "User : " + name;
//        return "User{name='" + name + "'}";
    }
}
