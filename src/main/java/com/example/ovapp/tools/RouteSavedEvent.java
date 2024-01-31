// RouteSavedEvent.java
package com.example.ovapp.tools;

import javafx.event.Event;
import javafx.event.EventType;

public class RouteSavedEvent extends Event {
    public static final EventType<RouteSavedEvent> ROUTE_SAVED_EVENT_TYPE = new EventType<>(Event.ANY, "ROUTE_SAVED");

    public RouteSavedEvent() {
        super(ROUTE_SAVED_EVENT_TYPE);
    }
}
