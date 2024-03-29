package com.example.ovapp.models.nsapi;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Origin {
    public String name;
    public String city;
    public String type;
    public String plannedDateTime;
    public String checkinStatus;
    public String plannedTrack;
    public String actualTrack;
    public List<TransferMessages> transferMessages;  // Gebruik een lijst voor transferMessages
    public List<Stops> stops;  // Gebruik een lijst voor stops

    public String getFormattedTime() {
        // Parse de plannedDateTime-string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(plannedDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Formateer naar HH:mm
        if (date != null) {
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return outputFormat.format(date);
        } else {
            return "";
        }
    }
    public String getFormattedCheckin() {
        switch (checkinStatus) {
            case "CHECKOUT":
                return "Uit-Checken";
            case "CHECKIN":
                return "In-Checken";
            case "NOTHING":
                return "Geen in- of uitcheck vereist";
            default:
                throw new NullPointerException(checkinStatus);
        }
    }
}
