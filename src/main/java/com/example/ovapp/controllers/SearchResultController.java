package com.example.ovapp.controllers;

import com.example.ovapp.models.nsapi.NSApiRoot;
import com.example.ovapp.models.nsapi.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchResultController implements Initializable{
    @FXML
    private ScrollPane scrollPaneMain;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    //Dit zijn alle labels die ik nu aanpas
    //<editor-fold desc="Elements">
    @FXML
    private Label arrival_route1;
    @FXML
    private Label departure_route1;
    @FXML
    private Label during_route1;
    @FXML
    private Label transfer_route1;

    @FXML
    private Label arrival_route2;
    @FXML
    private Label departure_route2;
    @FXML
    private Label during_route2;
    @FXML
    private Label transfer_route2;

    @FXML
    private Label arrival_route3;
    @FXML
    private Label departure_route3;
    @FXML
    private Label during_route3;
    @FXML
    private Label transfer_route3;

    @FXML
    private Label arrival_route4;
    @FXML
    private Label departure_route4;
    @FXML
    private Label during_route4;
    @FXML
    private Label transfer_route4;


    @FXML
    private Label arrival_route5;
    @FXML
    private Label departure_route5;
    @FXML
    private Label during_route5;
    @FXML
    private Label transfer_route5;

    @FXML
    private Label arrival_route6;
    @FXML
    private Label departure_route6;
    @FXML
    private Label during_route6;
    @FXML
    private Label transfer_route6;

    @FXML
    private Label arrival_route7;
    @FXML
    private Label departure_route7;
    @FXML
    private Label during_route7;
    @FXML
    private Label transfer_route7;
    @FXML
    //</editor-fold>

    //Pak de namen van de resultaten en zet ze in de labels.
    public void updateResultsDisplay(NSApiRoot nsApiRoot) {
        Trip[] trips = nsApiRoot.trips;

        if (trips.length > 0) {
            transfer_route1.setText(String.format("%d", trips[0].transfers));
            during_route1.setText(String.format("%d", trips[0].plannedDurationInMinutes));
            departure_route1.setText(trips[0].legs.get(0).origin.plannedDateTime);

        }

        if (trips.length >= 1) {
            departure_route2.setText(trips[1].fareRoute.origin.name);
            arrival_route2.setText(trips[1].fareRoute.destination.name);
        }

        if (trips.length >= 2) {
            departure_route3.setText(trips[2].fareRoute.origin.name);
            arrival_route3.setText(trips[2].fareRoute.destination.name);
        }
    }
}
