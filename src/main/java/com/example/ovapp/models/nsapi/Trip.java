package com.example.ovapp.models.nsapi;

import java.util.List;

public class Trip {
    public FareRoute fareRoute;
    public String uid;
    public int transfers;
    public int plannedDurationInMinutes;
    public List<Leg> legs;

    public String getFormattedDuration() {
        int hours = plannedDurationInMinutes / 60;
        int minutes = plannedDurationInMinutes % 60;

        return String.format("%02d:%02d", hours, minutes);
    }
}

