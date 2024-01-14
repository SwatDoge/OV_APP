package com.example.ovapp.models.nsapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Stops {
    public String name;
    public String plannedDepartureDateTime;
    public String plannedArrivalDateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlannedDepartureDateTime() {
        return getFormattedTime(plannedDepartureDateTime);
    }

    public String getPlannedArrivalDateTime() {
        return getFormattedTime(plannedArrivalDateTime);
    }

    private String getFormattedTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            return "";
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(dateTimeString);
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
