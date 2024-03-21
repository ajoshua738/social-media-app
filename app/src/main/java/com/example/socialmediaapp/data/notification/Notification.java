package com.example.socialmediaapp.data.notification;

public class Notification {

    private String name;
    private Long dateTimeInMillis;

    private boolean isViewed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDateTimeInMillis() {
        return dateTimeInMillis;
    }

    public void setDateTimeInMillis(Long dateTimeInMillis) {
        this.dateTimeInMillis = dateTimeInMillis;
    }

    public boolean getIsViewed() {
        return isViewed;
    }

    public void setIsViewed(boolean viewed) {
        isViewed = viewed;
    }

    public Notification(String name, Long dateTimeInMillis, boolean isViewed) {
        this.name = name;
        this.dateTimeInMillis = dateTimeInMillis;
        this.isViewed = isViewed;
    }
}
