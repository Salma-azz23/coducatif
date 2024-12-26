package com.example.coducatif;

public class Message {
    private String text;
    private boolean sentByUser; // true si envoyé par l'utilisateur, false si reçu

    public Message(String text, boolean sentByUser) {
        this.text = text;
        this.sentByUser = sentByUser;
    }

    public String getText() {
        return text;
    }

    public boolean isSentByUser() {
        return sentByUser;
    }
}