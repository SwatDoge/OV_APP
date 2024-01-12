package com.example.ovapp.models.nsapi;

import java.util.List;

public class Leg {
    public String name;
    public String idx;
    public Origin origin;
    public Product product;
    public Destination destination;
    public List<TransferMessages> transferMessages;  // Gebruik een lijst voor transferMessages
   public List<Stops> stops;  // Gebruik een lijst voor stops
}
