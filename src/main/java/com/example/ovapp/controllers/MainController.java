package com.example.ovapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML
    private TextField startCityTextField;

    @FXML
    private ChoiceBox<String> startDepartArriveChoiceBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField endCityTextField;

    @FXML
    private Button planReisButton;

    private ObservableList<String> allCities = FXCollections.observableArrayList("Amersfoort", "Amsterdam", "Utrecht", "Ede", "Alkmaar", "Den Haag", "Groningen", "Deventer", "Nijkerk", "Apeldoorn", "Enschede");

    @FXML
    private void initialize() {
        ObservableList<String> departArriveOptions = FXCollections.observableArrayList("Vertrek", "Aankomst");
        startDepartArriveChoiceBox.setItems(departArriveOptions);

        // Configure city suggestions for start and end fields
        configureCitySuggestions(startCityTextField);
        configureCitySuggestions(endCityTextField);
    }

    private void configureCitySuggestions(TextField textField) {
        FilteredList<String> filteredCities = new FilteredList<>(allCities, s -> true);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCities.setPredicate(city -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return city.toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<String> sortedCities = new SortedList<>(filteredCities);

        // Use a custom Popup for suggestions
        CustomSuggestionPopup suggestionPopup = new CustomSuggestionPopup(sortedCities);

        // Show suggestions when the user clicks the text field
        textField.setOnMouseClicked(event -> suggestionPopup.show(textField));

        // Hide suggestions when the user clicks outside the text field
        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                suggestionPopup.hide();
            }
        });

        // Set the selected suggestion in the text field when clicked
        suggestionPopup.setOnSuggestionSelected(selectedItem -> {
            textField.setText(String.valueOf(selectedItem));
            suggestionPopup.hide();
        });
    }

    @FXML
    private void onPlanReisButtonClick(ActionEvent event) {
        String startCity = startCityTextField.getText();
        String startDepartArrive = startDepartArriveChoiceBox.getValue();
        String endCity = endCityTextField.getText();
        String date = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "Geen datum geselecteerd";

        System.out.println("Start Stad: " + startCity);
        System.out.println("Vertrek/Aankomst: " + startDepartArrive);
        System.out.println("Eind Stad: " + endCity);
        System.out.println("Datum: " + date);
    }
}
