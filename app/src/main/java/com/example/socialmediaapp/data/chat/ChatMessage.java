package com.example.socialmediaapp.data.chat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatMessage {
    private String message;
    private Sender sender;
    private long timeStamp;

    public enum Sender {
        FRIEND, USER
    }

    public ChatMessage() {
    }

    public ChatMessage(String message, Sender sender, long timeStamp) {
        this.message = message;
        this.sender = sender;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a", Locale.getDefault());
        return sdf.format(new Date(timeStamp));
    }
}
