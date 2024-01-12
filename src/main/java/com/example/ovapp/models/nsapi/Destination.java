package com.example.ovapp.models.nsapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Destination {
    public String name;
    public String plannedDateTime;
    public String plannedTrack;
    public String exitSide;
    public String checkinStatus;

    public String getFormattedExit() {
        if (exitSide != null) {
            switch (exitSide) {
                case "RIGHT":
                    return "Rechts";
                case "LEFT":
                    return "Links";
                default:
                    throw new IllegalArgumentException("Rechts");
            }
        } else {
            return "Rechts";
        }
    }

    public String getFormattedTime() {
        // Parse de plannedDateTime-string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(plannedDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return outputFormat.format(date);
        } else {
            return "";
        }
    }
}

