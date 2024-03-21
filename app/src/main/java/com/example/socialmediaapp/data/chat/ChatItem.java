package com.example.socialmediaapp.data.chat;

public class ChatItem {
    private String friendName;
    private String lastMessage;
    private Long timeSinceLastMessage;


    public ChatItem(String friendName, String lastMessage, Long timeSinceLastMessage) {
        this.friendName = friendName;
        this.lastMessage = lastMessage;
        this.timeSinceLastMessage = timeSinceLastMessage;
    }


    public ChatItem() {
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getTimeSinceLastMessage() {
        return timeSinceLastMessage;
    }

    public void setTimeSinceLastMessage(Long timeSinceLastMessage) {
        this.timeSinceLastMessage = timeSinceLastMessage;
    }
}
