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
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FavoriteTravelController {

    @FXML
    private Button favorite1;
    @FXML
    private Button favorite2;
    @FXML
    private Button favorite3;
    @FXML
    private Button favorite4;
    @FXML
    private Button favorite5;
    @FXML
    private Button favorite6;

    @FXML
    private Label arrival_favorite1;
    @FXML
    private Label departure_favorite1;
    @FXML
    private Label during_favorite1;
    @FXML
    private Label transfer_favorite1;

    @FXML
    private Label arrival_favorite2;
    @FXML
    private Label departure_favorite2;
    @FXML
    private Label during_favorite2;
    @FXML
    private Label transfer_favorite2;

    @FXML
    private Label arrival_favorite3;
    @FXML
    private Label departure_favorite3;
    @FXML
    private Label during_favorite3;
    @FXML
    private Label transfer_favorite3;

    @FXML
    private Label arrival_favorite4;
    @FXML
    private Label departure_favorite4;
    @FXML
    private Label during_favorite4;
    @FXML
    private Label transfer_favorite4;

    @FXML
    private Label arrival_favorite5;
    @FXML
    private Label departure_favorite5;
    @FXML
    private Label during_favorite5;
    @FXML
    private Label transfer_favorite5;

    @FXML
    private Label arrival_favorite6;
    @FXML
    private Label departure_favorite6;
    @FXML
    private Label during_favorite6;
    @FXML
    private Label transfer_favorite6;

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
    private Label departure_details_favorite;
    @FXML
    private Label during_details_favorite;
    @FXML
    private Label arrival_details_favorite;
    @FXML
    private Label track_details_favorite;
    @FXML
    private Label transfer_details_favorite;
    @FXML
    private Label stops_details_favorite;

    @FXML
    private void toggleSideBar() {
        sidebar.setVisible(true);
    }

    private int currentRouteNumber; // Houdt het huidige routenummer bij

    public void initialize() {

        System.out.println("Initializing FavoriteTravelController...");

        stop_details_pane.setPadding(new Insets(5, 0, -10, 5));
        stops_details_favorite.setMaxHeight(Double.MAX_VALUE);

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

                        if (loggedInUser != null && !loggedInUser.getFavoriteTrips().isEmpty()) {
                            for (int i = 0; i < loggedInUser.getFavoriteTrips().size(); i++) {
                                TripDetails favoriteTrip = loggedInUser.getFavoriteTrips().get(i);
                                setLabelsForFavoriteRoute(favoriteTrip, i + 1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearSelectedButtonStyle(Button selectedButton) {
        if (selectedButton != null) {
            selectedButton.getStyleClass().remove("selection-border-favorite");
        } else {
            System.out.println("Selected button is null");
        }
    }

    private void styleSelectedButton(Button selectedButton) {
        List<Button> allButtons = Arrays.asList(favorite1, favorite2, favorite3, favorite4, favorite5, favorite6);

        // Clear styling for all buttons
        allButtons.forEach(button -> button.getStyleClass().remove("selection-border-favorite"));

        // Style the selected button
        selectedButton.getStyleClass().add("selection-border-favorite");
    }


    @FXML
    public void deleteRouteButtonfavorite(ActionEvent actionEvent) {
        System.out.println("Delete route button clicked");

        User currentUser = getCurrentUser();

        if (currentUser != null) {
            System.out.println("Current user found");

            // Verwijder de geselecteerde route
            int indexToRemove = currentRouteNumber - 1;
            if (indexToRemove >= 0 && indexToRemove < currentUser.getFavoriteTrips().size()) {
                currentUser.getFavoriteTrips().remove(indexToRemove);

                Platform.runLater(() -> {
                    // Werk currentRouteNumber bij als deze groter is dan de nieuwe grootte
                    if (currentRouteNumber > currentUser.getFavoriteTrips().size()) {
                        currentRouteNumber = currentUser.getFavoriteTrips().size();
                    }

                    List<Button> allButtons = Arrays.asList(favorite1, favorite2, favorite3, favorite4, favorite5, favorite6);
                    allButtons.forEach(button -> button.getStyleClass().remove("selection-border-favorite"));

                    // Update de UI met de verwijderde route
                    updateDetailsForRoute(currentRouteNumber);


                    // Werk de JSON-opslag bij (schrijf terug naar het bestand)
                    updateJsonFile(currentUser);

                    // Laat het bericht zien nadat alles is bijgewerkt
                    showAlert("Succes", "Route succesvol verwijderd.", Alert.AlertType.INFORMATION);

                    // Refresh the UI immediately
                    refreshScreen();


                });
            } else {
                System.out.println("Invalid route number");
            }
        }
    }

    // Voeg deze methode toe aan FavoriteTravelController
    private Button getSelectedButtonFavorite() {
        switch (currentRouteNumber) {
            case 1:
                return favorite1;
            case 2:
                return favorite2;
            case 3:
                return favorite3;
            case 4:
                return favorite4;
            case 5:
                return favorite5;
            case 6:
                return favorite6;
            default:
                System.out.println("Invalid route number");
                return null;
        }
    }



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

                        if (loggedInUser != null && !loggedInUser.getFavoriteTrips().isEmpty()) {
                            // Clear all labels first for favorite trips
                            clearAllLabelsForFavorites();

                            for (int i = 0; i < loggedInUser.getFavoriteTrips().size(); i++) {
                                TripDetails favoriteTrip = loggedInUser.getFavoriteTrips().get(i);
                                setLabelsForFavoriteRoute(favoriteTrip, i + 1);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearAllLabelsForFavorites() {
        for (int i = 1; i <= 6; i++) {
            setLabelsForFavoriteRoute(new TripDetails(), i);
        }
    }

    private void setLabelsForFavoriteRoute(TripDetails favoriteTrip, int routeNumber) {
        String arrivalLabel = "arrival_favorite" + routeNumber;
        String departureLabel = "departure_favorite" + routeNumber;
        String duringLabel = "during_favorite" + routeNumber;
        String transferLabel = "transfer_favorite" + routeNumber;

        // Gebruik reflection om de juiste labels te krijgen op basis van het routenummer
        try {
            Label arrivalLabelField = (Label) getClass().getDeclaredField(arrivalLabel).get(this);
            Label departureLabelField = (Label) getClass().getDeclaredField(departureLabel).get(this);
            Label duringLabelField = (Label) getClass().getDeclaredField(duringLabel).get(this);
            Label transferLabelField = (Label) getClass().getDeclaredField(transferLabel).get(this);

            // Update de labels met de gegevens van de favoriteTrip
            Platform.runLater(() -> {
                System.out.println("update labels with favoriteTrip data");
                arrivalLabelField.setText(favoriteTrip.getArrivalTime());
                departureLabelField.setText(favoriteTrip.getDepartureTime());
                duringLabelField.setText(favoriteTrip.getDuration());
                transferLabelField.setText(favoriteTrip.getTransfers());
            });
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private User getCurrentUser() {
        return Users.getInstance().currentUser;
    }

    @FXML
    public void favorite_handleRoute1ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(1);
        currentRouteNumber = 1;
        styleSelectedButton(favorite1);
    }

    @FXML
    public void favorite_handleRoute2ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(2);
        currentRouteNumber = 2;
        styleSelectedButton(favorite2);
    }

    @FXML
    public void favorite_handleRoute3ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(3);
        currentRouteNumber = 3;
        styleSelectedButton(favorite3);
    }

    @FXML
    public void favorite_handleRoute4ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(4);
        currentRouteNumber = 4;
        styleSelectedButton(favorite4);
    }

    @FXML
    public void favorite_handleRoute5ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(5);
        currentRouteNumber = 5;
        styleSelectedButton(favorite5);
    }

    @FXML
    public void favorite_handleRoute6ButtonClick(ActionEvent actionEvent) {
        updateDetailsForRoute(6);
        currentRouteNumber = 6;
        styleSelectedButton(favorite6);
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

                        if (loggedInUser != null && !loggedInUser.getFavoriteTrips().isEmpty()) {
                            // Check if routeNumber is within a valid range
                            if (routeNumber >= 1 && routeNumber <= loggedInUser.getFavoriteTrips().size()) {
                                TripDetails selectedTrip = loggedInUser.getFavoriteTrips().get(routeNumber - 1);

                                Platform.runLater(() -> {
                                    // Update the labels with the data of the selected route
                                    departure_details_favorite.setText(selectedTrip.getDepartureTime());
                                    during_details_favorite.setText(selectedTrip.getDuration());
                                    arrival_details_favorite.setText(selectedTrip.getArrivalTime());
                                    track_details_favorite.setText((selectedTrip.getTrackOrLine()));
                                    transfer_details_favorite.setText(String.format(selectedTrip.getTransfers()));
                                    stops_details_favorite.setText(selectedTrip.getStopsDetails());
                                });
                            } else {
                                System.out.println("Invalid route number");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                            .forEach(user -> user.setFavoriteTrips(currentUser.getFavoriteTrips()));

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
    public void refreshScreen() {
        // Reset alle labels voor alle routes
        for (int i = 1; i <= 6; i++) {
            updateDetailsForRoute(i);
        }

        User currentUser = getCurrentUser();

        if (currentUser != null && currentUser.getFavoriteTrips().isEmpty()) {
            // If there are no more routes, navigate to the home page
            Page.navigateTo(EPage.HOME);
        } else {
            // If there are still routes, navigate to the history page
            Page.navigateTo(EPage.FAVORITE);
        }
    }

    @FXML
    public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
