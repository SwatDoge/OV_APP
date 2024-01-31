package com.example.ovapp.models.user;

import com.example.ovapp.tools.TripDetails;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String username;
    public String password;
    public List<TripDetails> tripDetails;
    public List<TripDetails> favoriteTrips;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tripDetails = new ArrayList<>();
        this.favoriteTrips = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<TripDetails> getTripDetails() {
        return tripDetails;
    }

    public List<TripDetails> getFavoriteTrips() {
        return favoriteTrips;
    }

    public void setTripDetails(List<TripDetails> tripDetails) {
        this.tripDetails = tripDetails;
    }

    public void setFavoriteTrips(List<TripDetails> favoriteTrips) {
        this.favoriteTrips = favoriteTrips;
    }

    public void addTripDetails(TripDetails historyItem) {
        tripDetails.add(historyItem);
    }

    public void addFavoriteTrip(TripDetails favoriteTrip) {
        favoriteTrips.add(favoriteTrip);
    }


}
