package com.example.ovapp.models.user;

import com.example.ovapp.tools.TripDetails;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String username;
    public String password;
    public List<TripDetails> tripDetails;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tripDetails = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void addTripDetails(TripDetails historyItem) {
        this.tripDetails.add(historyItem);
    }

    public List<TripDetails> getTripDetails() {
        return tripDetails;
    }
}

