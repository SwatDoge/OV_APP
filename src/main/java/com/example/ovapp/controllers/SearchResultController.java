package com.example.ovapp.controllers;

import com.example.ovapp.TimeUtils;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.models.nsapi.*;
import com.example.ovapp.tools.Page;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchResultController implements Initializable{

    private Button lastClickedButton;

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 0px;");
    }
    @FXML
    private ScrollPane scrollPaneMain;

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
    private Label stops_details;

    private NSApiRoot currentApiResult;


    public void initialize(URL url, ResourceBundle rb) {
        setButtonStyle(route1);
        setButtonStyle(route2);
        // Voeg de event handlers toe
        route1.setOnAction(event -> handleRouteButtonClick(1));
        route2.setOnAction(event -> handleRouteButtonClick(2));
    }

    private void handleRouteButtonClick(int routeNumber) {
        if (lastClickedButton != null) {
            lastClickedButton.setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 2px;");
        }

        Button clickedButton = getRouteButton(routeNumber);
        clickedButton.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-border-width: 2px;");

        updateDetails(routeNumber);

        lastClickedButton = clickedButton;
    }
    private Button getRouteButton(int routeNumber) {
        switch (routeNumber) {
            case 1:
                return route1;
            case 2:
                return route2;
            default:
                return null;
        }
    }

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

        currentApiResult = nsApiRoot;
    }

    private void updateDetails(int routeNumber) {
        System.out.println("Updating details for route " + routeNumber + "...");

        if (currentApiResult == null || currentApiResult.trips == null) {
            System.out.println("NSApiRoot or trips is null.");
            return;
        }

        Trip[] trips = currentApiResult.trips;

        if (trips.length >= routeNumber) {
            Trip selectedTrip = trips[routeNumber - 1];

            Platform.runLater(() -> {
                transfer_details.setText(String.format("%dx", selectedTrip.transfers));
                during_details.setText(selectedTrip.getFormattedDuration());
                departure_details.setText(selectedTrip.legs.get(0).origin.getFormattedTime());

                String formattedDepartureTime = selectedTrip.legs.get(0).origin.getFormattedTime();
                String formattedDuration = selectedTrip.getFormattedDuration();
                String formattedArrivalTime = TimeUtils.calculateArrivalTime(formattedDepartureTime, formattedDuration);

                arrival_details.setText(formattedArrivalTime);

                String trackOrLine = "";

                if (selectedTrip.legs.get(0).origin.actualTrack != null && !selectedTrip.legs.get(0).origin.actualTrack.isEmpty()) {
                    trackOrLine = "Spoor " + selectedTrip.legs.get(0).origin.actualTrack;
                } else if (selectedTrip.legs.get(0).origin.plannedTrack != null && !selectedTrip.legs.get(0).origin.plannedTrack.isEmpty()) {
                    trackOrLine = "Perron " + selectedTrip.legs.get(0).origin.plannedTrack;
                } else if (selectedTrip.legs.get(0).product.number != null) {
                    trackOrLine = "Lijn " + selectedTrip.legs.get(0).product.number;
                }

                track_details.setText(trackOrLine);

                System.out.println("Details van de reis");
                System.out.println();


                for (Leg leg : selectedTrip.legs) {
                System.out.println("Vertrek-punt: " + leg.origin.name);
                System.out.println("Vertrek-Tijd: " + leg.origin.getFormattedTime());
                System.out.println("Opstapperron: " + leg.origin.plannedTrack);
                System.out.println("Checkin-Status: " + leg.origin.checkinStatus);
                System.out.println();


                System.out.println("Bestemming: " + leg.destination.name);
                System.out.println("Aankomst-tijd: " + leg.destination.getFormattedTime());
                System.out.println("Aankomst-Parron: " + leg.destination.plannedTrack);
                System.out.println("Uitstap-kant: " + leg.destination.exitSide);
                System.out.println("Checkin-Status: " + leg.destination.checkinStatus);

                if (leg.transferMessages != null) {
                    for (TransferMessages transferMessage : leg.transferMessages) {
                        System.out.println("Overstap-Bericht: " + transferMessage.getAccessibilityMessage());
                    }
                }

                if (leg.stops != null) {
                    for (Stops stop : leg.stops) {
                        System.out.println();
                        System.out.println("Stop: " + stop.getName());
                        System.out.println("Aankomst-tijd: " + stop.getPlannedArrivalDateTime());
                        System.out.println("Vertrek-tijd: " + stop.getPlannedDepartureDateTime());
                        System.out.println();
                    }
                }

            }
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
        updateDetails(3);
    }

    public void handleRoute4ButtonClick(ActionEvent actionEvent) {
        updateDetails(4);
    }

    public void handleRoute5ButtonClick(ActionEvent actionEvent) {
        updateDetails(5);
    }

    public void handleRoute6ButtonClick(ActionEvent actionEvent) {
        updateDetails(6);
    }

    private void resetButtonStyles() {
        route1.setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 2px;");
        route2.setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 2px;");

        lastClickedButton = null;
    }
public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
