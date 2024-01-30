package com.example.ovapp.tools;

import com.example.ovapp.TimeUtils;
import com.example.ovapp.models.nsapi.Trip;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TripDetails {

    @SerializedName("transfers")
    public String transfers;
    public String duration;
    public String departureTime;
    public String arrivalTime;
    public String stopsDetails;
    public String trackOrLine;


    // Nieuwe velden voor reishistorie
    @SerializedName("origin")
    public String origin;

    @SerializedName("destination")
    public String destination;

    public String getTransfers() {
        return transfers;
    }

    public String getStopsDetails() {
        return stopsDetails;
    }

    public void setStopsDetails(String stopsDetails) {
        this.stopsDetails = stopsDetails;
    }

    public void setTransfers(String transfers) {
        this.transfers = transfers;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTrackOrLine() {
        return trackOrLine;
    }

    public void setTrackOrLine(String trackOrLine) {
        this.trackOrLine = trackOrLine;
    }
}

