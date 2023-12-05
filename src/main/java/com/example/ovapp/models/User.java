package com.example.ovapp.models;

import java.util.*;

// A user. Register a new user to create one, use passwordMatches before logging into the user.
public class User {
    static private int idIndex = 0;
    private int id = idIndex++;
    private String userName;
    private String password;
    private List<Route> favourites = new ArrayList<Route>();

    //<editor-fold desc="Getters & setters">
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Route> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Route> favourites) {
        this.favourites = favourites;
    }

    public void addFavourite(Route favourite) {
        this.favourites.add(favourite);
    }

    public void removeFavourite(Route favourite) {
        try {
            this.favourites.remove(favourite);
        }
        catch (Exception e) {

        }
    }
    //</editor-fold>

    User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean passwordMatches(String password) {
        return this.password.equals(password);
    }
}
