package com.example.ovapp.models.nsapi;

import com.google.gson.annotations.SerializedName;

public class NSApiRoot {
    public String source;
    public Trip[] trips;
    public String scrollRequestBackwardContext;
    public String scrollRequestForwardContext;
}
