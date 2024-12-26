package com.example.coducatif;

public class Message {
    private String id; // Identifiant unique du message
    private String text; // Contenu du message
    private String type; // Type du message : "text" ou "image"
    private boolean sentByUser; // Indique si le message est envoyé par l'utilisateur
    private String messageKey; // Clé unique pour Firebase

    // Constructeur par défaut requis par Firebase
    public Message() {}

    // Constructeur avec texte et indication d'utilisateur
    public Message(String text, boolean sentByUser) {
        this.text = text;
        this.sentByUser = sentByUser;
    }

    // Constructeur avec texte et type
    public Message(String text, String type) {
        this.text = text;
        this.type = type;
    }

    // Constructeur complet avec id, texte et type
    public Message(String id, String text, String type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    // Getter et Setter pour id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter et Setter pour text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter et Setter pour type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter et Setter pour sentByUser
    public boolean isSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(boolean sentByUser) {
        this.sentByUser = sentByUser;
    }

    // Getter et Setter pour messageKey
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
}
