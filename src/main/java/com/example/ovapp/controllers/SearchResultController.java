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
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class SearchResultController implements Initializable{

    private Button lastClickedButton;

    private void setButtonStyle(Button button) {
        button.setStyle("-fx-background-color: white; -fx-border-color: transparent; -fx-border-width: 0px;");
    }
    @FXML
    private ScrollPane scrollPaneMain;

    @FXML
    private Pane sidebar;

    @FXML
    private void toggleSideBar() {
        sidebar.setVisible(true);
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
    private Label stops_details;
    @FXML
    private Pane stop_details_pane;

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
        clickedButton.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-border-width: 4px;");

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

                StringBuilder detailsText = new StringBuilder();

                for (Leg leg : selectedTrip.legs) {
                    if (leg.transferMessages != null) {
                        detailsText.append("Overstap: ").append(leg.idx).append("\n");
                        for (TransferMessages transferMessage : leg.transferMessages) {
                            detailsText.append(transferMessage.getAccessibilityMessage()).append("\n");
                        }
                        detailsText.append("\n"); // Extra lege regel na overstap
                    }

                    detailsText.append("Vertrek:\n");
                    detailsText.append(leg.origin.getFormattedTime()).append(" - ").append(leg.origin.name).append("\n");
                    detailsText.append("Vertrek Perron: ").append(leg.origin.plannedTrack).append("\n\n");

                    int numberOfStops = (leg.stops != null) ? leg.stops.size() : 0;
                    detailsText.append(numberOfStops).append("x Stops\n\n");

                    detailsText.append("Bestemming:\n");
                    detailsText.append(leg.destination.getFormattedTime()).append(" - ").append(leg.destination.name).append("\n");
                    detailsText.append("Aankomst Perron: ").append(leg.destination.plannedTrack).append("\n");
                    detailsText.append(leg.destination.getFormattedExit()).append(" uitstappen").append("\n");

                    detailsText.append("\n\n"); // Extra lege regel tussen trips
                }

                stops_details.setText(detailsText.toString());




                double height = stops_details.getBoundsInLocal().getHeight();
                stop_details_pane.setPrefHeight(height);

                // Pas de hoogte van de ScrollPane aan
                scrollPaneMain.setPrefHeight(height + 5); // Voeg eventueel een beetje extra hoogte toe
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

    public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
