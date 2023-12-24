package com.example.ovapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String calculateArrivalTime(String departureTime, String duration) {
        try {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

            Date departureDate = timeFormat.parse(departureTime);

            int durationInMinutes = parseDurationToMinutes(duration);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.MINUTE, durationInMinutes);

            return timeFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int parseDurationToMinutes(String formattedDuration) {
        try {
            String[] timeParts = formattedDuration.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            return hours * 60 + minutes;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
