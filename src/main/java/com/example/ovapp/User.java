package com.example.ovapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int idIndex = 0;
    private int id = idIndex++;
    private String username;
    private String password;
    private List<String> favouriteRoutes = new ArrayList<String>();

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
