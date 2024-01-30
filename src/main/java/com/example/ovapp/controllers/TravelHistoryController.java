package com.example.ovapp.controllers;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.models.user.User;
import com.example.ovapp.tools.Page;
import com.example.ovapp.tools.TripDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
    private Pane sidebar;
    @FXML
    private Pane stop_details_pane;


    @FXML
    private void toggleSideBar() {
        sidebar.setVisible(true);
    }


    public void initialize() {
            System.out.println("Initializing TravelHistoryController...");
            stop_details_pane.setPadding(new Insets(5, 0, -10, 5));
            stops_details_history.setMaxHeight(Double.MAX_VALUE);

            User currentUser = getCurrentUser();

            if (currentUser != null) {
                try {
                    System.out.println("Reading JSON file...");

                    File file = new File("src/main/resources/json/users.json");

                    // Gebruik Gson om JSON naar object te deserialiseren
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<User>>() {}.getType();
                    List<User> userList = gson.fromJson(new FileReader(file), type);

                    if (!userList.isEmpty()) {
                        // Zoek de ingelogde gebruiker op basis van de gebruikersnaam
                        User loggedInUser = userList.stream()
                                .filter(user -> user.getUsername().equals(currentUser.getUsername()))
                                .findFirst()
                                .orElse(null);

                        if (loggedInUser != null && !loggedInUser.getTripDetails().isEmpty()) {
                            TripDetails tripDetails = loggedInUser.getTripDetails().get(0);

                            arrival_history1.setText(tripDetails.getArrivalTime());
                            departure_history1.setText(tripDetails.getDepartureTime());
                            during_history1.setText(tripDetails.getDuration());
                            transfer_history1.setText(tripDetails.getTransfers());
                            departure_details_history.setText(tripDetails.getDepartureTime());
                            during_details_history.setText(tripDetails.getDuration());
                            arrival_details_history.setText(tripDetails.getArrivalTime());
                            transfer_details_history.setText(tripDetails.getTransfers());
                            stops_details_history.setText(tripDetails.getStopsDetails());
                            track_details_history.setText(tripDetails.getTrackOrLine());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    private User getCurrentUser() {
        return Users.getInstance().currentUser;
    }



    public void history_handleRoute1ButtonClick(ActionEvent actionEvent) {
    }

    public void history_handleRoute2ButtonClick(ActionEvent actionEvent) {
    }

    public void history_handleRoute3ButtonClick(ActionEvent actionEvent) {
    }

    public void history_handleRoute4ButtonClick(ActionEvent actionEvent) {
    }

    public void history_handleRoute5ButtonClick(ActionEvent actionEvent) {
    }

    public void history_handleRoute6ButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}