package com.example.ovapp.models;

import java.util.List;

// A route consists of links (stops that make the route) and the form of transport(s) that belong to that route.
public class Route {
    private List<StopLink> stopLinks;
    private List<TransportType> transportTypes;
    private int getTotalDistanceInM() {
        return stopLinks
                .stream()
                .map(stopLink -> stopLink.distanceInMeters)
                .reduce(0, Integer::sum);
    }
    public int getTravelTimeInSeconds(TransportType transportType) {
        double totalDistanceInKM = (double) getTotalDistanceInM() / 1000;
        return (int) (totalDistanceInKM / transportType.speedInKMH) * 60;
    }
}
