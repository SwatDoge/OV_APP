package com.example.ovapp.controllers;

import com.example.ovapp.TimeUtils;
import com.example.ovapp.models.nsapi.NSApiRoot;
import com.example.ovapp.models.nsapi.Trip;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

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
    private Button route1;
    @FXML
    private Button route2;
    @FXML
    private Button route3;
    @FXML
    private Button route4;
    @FXML
    private Button route5;
    @FXML
    private Button route6;

    @FXML
    private Label departure_details;
    @FXML
    private Label during_details;
    @FXML
    private Label arrival_details;
    @FXML
    private Label track_details;
    @FXML
    private Label transfer_details;

    @FXML
    //</editor-fold>

    private NSApiRoot apiResult;


    //Pak de namen van de resultaten en zet ze in de labels.
    public void updateResultsDisplay(NSApiRoot nsApiRoot) {
        Trip[] trips = nsApiRoot.trips;

        if (trips.length > 0) {
            transfer_route1.setText(String.format("%dx", trips[0].transfers));
            during_route1.setText(trips[0].getFormattedDuration());
            departure_route1.setText(trips[0].legs.get(0).origin.getFormattedTime());

            String formattedDepartureTime = trips[0].legs.get(0).origin.getFormattedTime();
            String formattedDuration = trips[0].getFormattedDuration();
            String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

            arrival_route1.setText(formattedArrivalTime);
        }

        if (trips.length >= 1) {
            transfer_route2.setText(String.format("%dx", trips[1].transfers));
            during_route2.setText(trips[1].getFormattedDuration());
            departure_route2.setText(trips[1].legs.get(0).origin.getFormattedTime());

            String formattedDepartureTime = trips[1].legs.get(0).origin.getFormattedTime();
            String formattedDuration = trips[1].getFormattedDuration();
            String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

            arrival_route2.setText(formattedArrivalTime);
        }

        if (trips.length >= 2) {
            transfer_route3.setText(String.format("%dx", trips[2].transfers));
            during_route3.setText(trips[2].getFormattedDuration());
            departure_route3.setText(trips[2].legs.get(0).origin.getFormattedTime());

            String formattedDepartureTime = trips[2].legs.get(0).origin.getFormattedTime();
            String formattedDuration = trips[2].getFormattedDuration();
            String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

            arrival_route3.setText(formattedArrivalTime);
        }

        if (trips.length >= 3) {
            transfer_route4.setText(String.format("%dx", trips[3].transfers));
            during_route4.setText(trips[3].getFormattedDuration());
            departure_route4.setText(trips[3].legs.get(0).origin.getFormattedTime());

            String formattedDepartureTime = trips[3].legs.get(0).origin.getFormattedTime();
            String formattedDuration = trips[3].getFormattedDuration();
            String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

            arrival_route4.setText(formattedArrivalTime);
        }

        if (trips.length >= 4) {
            transfer_route5.setText(String.format("%dx", trips[4].transfers));
            during_route5.setText(trips[4].getFormattedDuration());
            departure_route5.setText(trips[4].legs.get(0).origin.getFormattedTime());

            String formattedDepartureTime = trips[4].legs.get(0).origin.getFormattedTime();
            String formattedDuration = trips[4].getFormattedDuration();
            String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

            arrival_route5.setText(formattedArrivalTime);
        }

        if (trips.length >= 5) {
            transfer_route6.setText(String.format("%dx", trips[5].transfers));
            during_route6.setText(trips[5].getFormattedDuration());
            departure_route6.setText(trips[5].legs.get(0).origin.getFormattedTime());

            String formattedDepartureTime = trips[5].legs.get(0).origin.getFormattedTime();
            String formattedDuration = trips[5].getFormattedDuration();
            String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

            arrival_route6.setText(formattedArrivalTime);
        }
    }

    public void setApiResult(NSApiRoot apiResult) {
        this.apiResult = apiResult;
        System.out.println("apiResult set: " + apiResult);

    }

    public NSApiRoot getApiResult() {
        return apiResult;
    }

    private void updateDetails(int routeNumber) {
        System.out.println("Updating details for route " + routeNumber + "...");
        if (apiResult == null || apiResult.trips == null) {
            System.out.println("NSApiRoot or trips is null.");
            return;
        }

        Trip[] trips = apiResult.trips;
        System.out.println("Number of trips: " + trips.length);


        if (trips.length >= routeNumber) {
            Trip selectedTrip = trips[routeNumber - 1];
            System.out.println("Selected trip: " + selectedTrip);

            Platform.runLater(() -> {
                transfer_details.setText(String.format("%dx", selectedTrip.transfers));
                during_details.setText(selectedTrip.getFormattedDuration());
                departure_details.setText(selectedTrip.legs.get(0).origin.getFormattedTime());

                String formattedDepartureTime = selectedTrip.legs.get(0).origin.getFormattedTime();
                String formattedDuration = selectedTrip.getFormattedDuration();
                String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

                arrival_details.setText(formattedArrivalTime);
            });
        }
    }

    public void handleRoute1ButtonClick(ActionEvent actionEvent) {
        updateDetails(1);
    }

    public void handleRoute2ButtonClick(ActionEvent actionEvent) {
        updateDetails(2);

    }

    public void handleRoute3ButtonClick(ActionEvent actionEvent) {
    }

    public void handleRoute4ButtonClick(ActionEvent actionEvent) {
    }

    public void handleRoute5ButtonClick(ActionEvent actionEvent) {
    }

    public void handleRoute6ButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
