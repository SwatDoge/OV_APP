package com.example.ovapp.models;

// A stop link, links 2 stops together, so a time can be set between them.
public class StopLink {
    private Stop start;
    private Stop end;
    public int distanceInMeters;
    public void switchStartAndEndPoint() {
        Stop old_start = start;
        this.start = end;
        this.end = old_start;
    }


}
