package com.example.ovapp.models.nsapi;

import java.util.List;

public class Trip {
    public FareRoute fareRoute;
    public String uid;
    public int transfers;
    public int plannedDurationInMinutes;
    public List<Leg> legs;
}
