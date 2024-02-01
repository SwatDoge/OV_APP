package com.example.ovapp.controllers;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.models.user.User;
import com.example.ovapp.tools.Page;
import com.example.ovapp.tools.TripDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.*;
import java.util.List;

public class TravelHistoryController {

    @FXML
    private Button history1;
    @FXML
    private Button history2;
    @FXML
    private Button history3;
    @FXML
    private Button history4;
    @FXML
    private Button history5;
    @FXML
    private Button history6;

    @FXML
    private Label arrival_history1;
    @FXML
    private Label departure_history1;
    @FXML
    private Label during_history1;
    @FXML
    private Label transfer_history1;

    @FXML
    private Label arrival_history2;
    @FXML
    private Label departure_history2;
    @FXML
    private Label during_history2;
    @FXML
    private Label transfer_history2;

    @FXML
    private Label arrival_history3;
    @FXML
    private Label departure_history3;
    @FXML
    private Label during_history3;
    @FXML
    private Label transfer_history3;

    @FXML
    private Label arrival_history4;
    @FXML
    private Label departure_history4;
    @FXML
    private Label during_history4;
    @FXML
    private Label transfer_history4;

    @FXML
    private Label arrival_history5;
    @FXML
    private Label departure_history5;
    @FXML
    private Label during_history5;
    @FXML
    private Label transfer_history5;

    @FXML
    private Label arrival_history6;
    @FXML
    private Label departure_history6;
    @FXML
    private Label during_history6;
    @FXML
    private Label transfer_history6;

    @FXML
    private Pane sidebar;
    @FXML
    private AnchorPane historyAnchorPane;

    @FXML
    private ScrollPane historyScrollPane;

    @FXML
    private VBox historyVBox;

    @FXML
    private Pane stop_details_pane;

    @FXML
    private Label departure_details_history;
    @FXML
    private Label during_details_history;
    @FXML
    private Label arrival_details_history;
    @FXML
    private Label track_details_history;
    @FXML
    private Label transfer_details_history;
    @FXML
    private Label stops_details_history;

    @FXML
    private void toggleSideBar() {
        sidebar.setVisible(true);
    }

    private int currentRouteNumber; // Houdt het huidige routenummer bij

    public void initialize() {

        System.out.println("Initializing TravelHistoryController...");

        stop_details_pane.setPadding(new Insets(5, 0, -10, 5));
        stops_details_history.setMaxHeight(Double.MAX_VALUE);

        currentRouteNumber = 1;

        updateDetailsForRoute(currentRouteNumber);


        User currentUser = getCurrentUser();

        if (currentUser != null) {
            try {
                System.out.println("Reading JSON file...");

                File file = new File("src/main/resources/json/users.json");

                // Gebruik Gson om JSON naar object te deserialiseren
                try (Reader reader = new FileReader(file)) {
                    Gson gson = new Gson();
                    TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
                    List<User> userList = gson.fromJson(reader, typeToken.getType());

                    if (!userList.isEmpty()) {
                        // Zoek de ingelogde gebruiker op basis van de gebruikersnaam
                        User loggedInUser = userList.stream()
                                .filter(user -> user.getUsername().equals(currentUser.getUsername()))
                                .findFirst()
                                .orElse(null);

                        if (loggedInUser != null && !loggedInUser.getTripDetails().isEmpty()) {
                            for (int i = 0; i < loggedInUser.getTripDetails().size(); i++) {
                                TripDetails tripDetails = loggedInUser.getTripDetails().get(i);
                                setLabelsForRoute(tripDetails, i + 1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void deleteRoute(ActionEvent actionEvent) {
        System.out.println("Delete route button clicked");

        // Krijg de huidige gebruiker
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            System.out.println("Current user found");

            // Verwijder de huidige route
            currentUser.getTripDetails().remove(currentRouteNumber - 1);

            Platform.runLater(() -> {
                // Update de UI met de verwijderde route
                updateDetailsForRoute(currentRouteNumber);

                // Vernieuw het scherm of voer andere benodigde acties uit

                // Werk de JSON-opslag bij (schrijf terug naar het bestand)
                updateJsonFile(currentUser);

                // Laat het bericht zien nadat alles is bijgewerkt
                showAlert("Succes", "Route succesvol verwijderd.", Alert.AlertType.INFORMATION);

                // Refresh the UI immediately
                refreshUI();
            });
        }
    }

    // Voeg deze methode toe aan TravelHistoryController

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void onSwitchToPage() {
        System.out.println("onswitchtopage called");
        // Call the setLabelsForRoute method for all routes
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            try {
                File file = new File("src/main/resources/json/users.json");

                try (Reader reader = new FileReader(file)) {
                    Gson gson = new Gson();
                    TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
                    List<User> userList = gson.fromJson(reader, typeToken.getType());

                    if (!userList.isEmpty()) {
                        User loggedInUser = userList.stream()
                                .filter(user -> user.getUsername().equals(currentUser.getUsername()))
                                .findFirst()
                                .orElse(null);

                        if (loggedInUser != null && !loggedInUser.getTripDetails().isEmpty()) {
                            for (int i = 0; i < loggedInUser.getTripDetails().size(); i++) {
                                TripDetails tripDetails = loggedInUser.getTripDetails().get(i);
                                setLabelsForRoute(tripDetails, i + 1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void setLabelsForRoute(TripDetails tripDetails, int routeNumber) {
        System.out.println("setLabelsForRoute called for route " + routeNumber);
        String arrivalLabel = "arrival_history" + routeNumber;
        String departureLabel = "departure_history" + routeNumber;
        String duringLabel = "during_history" + routeNumber;
        String transferLabel = "transfer_history" + routeNumber;

        // Gebruik reflection om de juiste labels te krijgen op basis van het routenummer
        try {
            Label arrivalLabelField = (Label) getClass().getDeclaredField(arrivalLabel).get(this);
            Label departureLabelField = (Label) getClass().getDeclaredField(departureLabel).get(this);
            Label duringLabelField = (Label) getClass().getDeclaredField(duringLabel).get(this);
            Label transferLabelField = (Label) getClass().getDeclaredField(transferLabel).get(this);

            // Update de labels met de gegevens van de tripDetails
            arrivalLabelField.setText(tripDetails.getArrivalTime());
            departureLabelField.setText(tripDetails.getDepartureTime());
            duringLabelField.setText(tripDetails.getDuration());
            transferLabelField.setText(tripDetails.getTransfers());
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }


    private User getCurrentUser() {
        return Users.getInstance().currentUser;
    }

    public void setCurrentUser(User user) {
        Users.getInstance().currentUser = user;
    }

    @FXML
    public void history_handleRoute1ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(1);
    }

    @FXML
    public void history_handleRoute2ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(2);
    }

    @FXML
    public void history_handleRoute3ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(3);
    }

    @FXML
    public void history_handleRoute4ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(4);
    }

    @FXML
    public void history_handleRoute5ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(5);
    }

    @FXML
    public void history_handleRoute6ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(6);
    }

    private void updateDetailsForRoute(int routeNumber) {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            try {
                File file = new File("src/main/resources/json/users.json");

                try (Reader reader = new FileReader(file)) {
                    Gson gson = new Gson();
                    TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
                    List<User> userList = gson.fromJson(reader, typeToken.getType());

                    if (!userList.isEmpty()) {
                        User loggedInUser = userList.stream()
                                .filter(user -> user.getUsername().equals(currentUser.getUsername()))
                                .findFirst()
                                .orElse(null);

                        if (loggedInUser != null && !loggedInUser.getTripDetails().isEmpty()) {
                            TripDetails selectedTrip = loggedInUser.getTripDetails().get(routeNumber - 1);

                            Platform.runLater(() -> {
                                // Update de labels met de gegevens van de geselecteerde route
                                departure_details_history.setText(selectedTrip.getDepartureTime());
                                during_details_history.setText(selectedTrip.getDuration());
                                arrival_details_history.setText(selectedTrip.getArrivalTime());
                                track_details_history.setText((selectedTrip.getTrackOrLine()));
                                transfer_details_history.setText(String.format(selectedTrip.getTransfers()));
                                stops_details_history.setText(selectedTrip.getStopsDetails());

                                // Voeg hier logica toe om de overige labels bij te werken
                                // bijvoorbeeld: track_details_history.setText(selectedTrip.getTrackOrLine());
                                // transfer_details_history.setText(String.format("%dx", selectedTrip.getTransfers()));
                                // stops_details_history.setText(selectedTrip.getStopsDetails());
                            });
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshUI() {
        // Reinitialize the controller to refresh the UI
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ovapp/travel-history-view.fxml"));
                Parent root = loader.load();
                TravelHistoryController controller = loader.getController();


                // Set the updated user data
                User currentUser = getCurrentUser();
                controller.setCurrentUser(currentUser);

                // Replace the current scene with the updated one
                historyAnchorPane.getScene().setRoot(root);

                System.out.println("UI refreshed!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateJsonFile(User currentUser) {
        try {
            File file = new File("src/main/resources/json/users.json");

            try (Reader reader = new FileReader(file)) {
                Gson gson = new Gson();
                TypeToken<List<User>> typeToken = new TypeToken<List<User>>() {};
                List<User> userList = gson.fromJson(reader, typeToken.getType());

                if (userList != null) {
                    // Zoek de gebruiker en werk de gegevens bij
                    userList.stream()
                            .filter(user -> user.getUsername().equals(currentUser.getUsername()))
                            .forEach(user -> user.setTripDetails(currentUser.getTripDetails()));

                    // Schrijf de bijgewerkte lijst terug naar het bestand
                    try (FileWriter writer = new FileWriter(file)) {
                        gson.toJson(userList, writer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
