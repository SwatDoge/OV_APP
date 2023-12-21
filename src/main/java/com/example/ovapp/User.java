package com.example.ovapp;

public class User {
    private static User currentUser;
    private String username;

    User(String username) {
        if (currentUser == null) {
            currentUser = this;
        }

        currentUser.username = username;
    }

    public static void logOutCurrentUser() {
        currentUser = null;
    }
}
