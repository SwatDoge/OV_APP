package com.example.ovapp.tools;

import com.example.ovapp.TimeUtils;
import com.example.ovapp.models.nsapi.Trip;

public class TripDetails {
    private String transfers;
    private String duration;
    private String departureTime;
    private String arrivalTime;
    private String stopsDetails;
    // Andere velden...

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


    // Andere setters en getters...

    // Nieuwe methode om gegevens in te stellen
    public void setFromTrip(Trip selectedTrip) {
        this.transfers = String.format("%dx", selectedTrip.transfers);
        this.duration = selectedTrip.getFormattedDuration();
        this.departureTime = selectedTrip.legs.get(0).origin.getFormattedTime();

        // Voeg hier de nieuwe velden toe
        this.arrivalTime = TimeUtils.calculateArrivalTime(
                selectedTrip.legs.get(0).origin.getFormattedTime(),
                selectedTrip.getFormattedDuration()
        );


        // Voeg hier andere velden toe die je nodig hebt
    }

    private String determineTrackOrLine(Trip selectedTrip) {
        if (selectedTrip.legs.get(0).origin.actualTrack != null && !selectedTrip.legs.get(0).origin.actualTrack.isEmpty()) {
            return "Spoor " + selectedTrip.legs.get(0).origin.actualTrack;
        } else if (selectedTrip.legs.get(0).origin.plannedTrack != null && !selectedTrip.legs.get(0).origin.plannedTrack.isEmpty()) {
            return "Perron " + selectedTrip.legs.get(0).origin.plannedTrack;
        } else if (selectedTrip.legs.get(0).product.number != null) {
            return "Lijn " + selectedTrip.legs.get(0).product.number;
        } else {
            return "";
        }
    }

    public void stopsDetails(String text) {
    }
}
