package com.example.socialmediaapp.data.user;

public class UserDataHolder {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }
}
