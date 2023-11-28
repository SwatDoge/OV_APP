package com.example.ovapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML
    private ChoiceBox<String> startCityChoiceBox;

    @FXML
    private ChoiceBox<String> startDepartArriveChoiceBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ChoiceBox<String> endCityChoiceBox;

    @FXML
    private RadioButton busRadioButton;

    @FXML
    private RadioButton trainRadioButton;

    @FXML
    private Button planReisButton;

    @FXML
    private void initialize() {

        ObservableList<String> startCities = FXCollections.observableArrayList("Amersfoort", "Amsterdam", "Ede", "Alkmaar", "Den Haag");
        ObservableList<String> endCities = FXCollections.observableArrayList("Groningen", "Deventer", "Nijkerk", "Apeldoorn", "Enschede");

        startCityChoiceBox.setItems(startCities);
        endCityChoiceBox.setItems(endCities);
        startDepartArriveChoiceBox.setItems(FXCollections.observableArrayList("Vertrek", "Aankomst"));
    }

    @FXML
    private void onPlanReisButtonClick(ActionEvent event) {

        String startCity = startCityChoiceBox.getValue();
        String startDepartArrive = startDepartArriveChoiceBox.getValue();
        String endCity = endCityChoiceBox.getValue();
        String transport = busRadioButton.isSelected() ? "Bus" : "Trein";
        String date = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "Geen datum geselecteerd";


        System.out.println("Start Stad: " + startCity);
        System.out.println("Vertrek/Aankomst: " + startDepartArrive);
        System.out.println("Eind Stad: " + endCity);
        System.out.println("Transport: " + transport);
        System.out.println("Datum: " + date);
    }
}
