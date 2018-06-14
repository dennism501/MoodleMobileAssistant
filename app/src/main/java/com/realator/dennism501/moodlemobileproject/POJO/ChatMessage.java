package com.realator.dennism501.moodlemobileproject.POJO;

/**
 * Created by dennism501 on 5/25/17.
 */

public class ChatMessage {

    private String content;
    private boolean isMine;

    public ChatMessage(String content, boolean isMine) {
        this.content = content;
        this.isMine = isMine;
    }

    public String getContent() {
        return content;
    }

    public boolean isMine() {
        return isMine;
    }
}
