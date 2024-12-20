package com.example.coducatif;

public class Message {
    private String nickname;
    private String content;
    private String timestamp;

    public Message(String nickname, String content, String timestamp) {
        this.nickname = nickname;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
