package com.example.ovapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.example.ovapp.Request.sendApiRequest;



public class HomeController {

    @FXML
    private TextField startCityTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField endCityTextField;

    @FXML
    private ChoiceBox<String> transportChoiceBox;

    @FXML
    private ChoiceBox<String> timeChoiceBox;

    @FXML
    private Button planReisButton;

    @FXML
    private ChoiceBox<String> timeSelectionBox;

        @FXML
    private void onPlanReisButtonClick(ActionEvent event) {
        try {
            String fromStation = startCityTextField.getText();
            String toStation = endCityTextField.getText();

            String transportType = getTransportTypeFromChoiceBox();

            boolean searchForArrival = timeChoiceBox.getValue().equals("Aankomst");

            String selectedTime = timeSelectionBox.getValue();
            String formattedTime = convertTime(selectedTime); // Nieuwe regel toegevoegd


            LocalDate selectedDate = startDatePicker.getValue();
            String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            sendApiRequest(fromStation, toStation, transportType, searchForArrival, formattedTime, formattedDate);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ovapp/search-result-view.fxml"));
            Parent searchResultParent = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(searchResultParent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertTime(String selectedTime) {
        return selectedTime + ":00";
    }

    private String getTransportTypeFromChoiceBox() {
        switch (transportChoiceBox.getValue()) {
            case "Trein":
                return "METRO,TRAM,FERRY,BUS";
            case "Bus":
                return "TRAIN,METRO,TRAM,FERRY";
            case "Geen Voorkeur":
            default:
                return "METRO,TRAM,FERRY";
        }
    }

    @FXML
    private void citySuggestion(MouseEvent event) {
        configureCitySuggestions(startCityTextField);
        configureCitySuggestions(endCityTextField);

    }

    @FXML
    private void initialize() {
        configureCitySuggestions(startCityTextField);
        configureCitySuggestions(endCityTextField);


        setDefaultDateInDatePicker();

        configureTimeSelectionBox();
    }

    private void setDefaultDateInDatePicker() {
        LocalDate currentDate = LocalDate.now();
        startDatePicker.setValue(currentDate);
    }

    private void configureTimeSelectionBox() {
        List<String> timeValues = generateTimeValues();
        ObservableList<String> timeOptions = FXCollections.observableArrayList(timeValues);

        LocalTime currentTime = LocalTime.now();
        LocalTime nextInterval = currentTime.plusMinutes(30).truncatedTo(java.time.temporal.ChronoUnit.HOURS);
        String formattedTime = nextInterval.format(DateTimeFormatter.ofPattern("HH:mm"));

        timeSelectionBox.setItems(timeOptions);
        timeSelectionBox.setValue(formattedTime);
    }


    private List<String> generateTimeValues() {
        List<String> timeValues = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                String formattedTime = String.format("%02d:%02d", hour, minute);
                timeValues.add(formattedTime);
            }
        }
        return timeValues;
    }

    private final ObservableList<String> allCities = FXCollections.observableArrayList(
            "Amersfoort Centraal", "Amsterdam Centraal", "Utrecht Centraal", "Ede Centrum", "Alkmaar", "Den Haag Centraal", "Groningen", "Deventer", "Nijkerk", "Apeldoorn", "Enschede");

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

        CustomSuggestionPopup<String> suggestionPopup = new CustomSuggestionPopup<>(sortedCities);

        textField.setOnMouseClicked(event -> suggestionPopup.show(textField.getScene().getWindow(), event.getScreenX(), event.getScreenY()));

        textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                suggestionPopup.hide();
            }
        });

        suggestionPopup.setOnSuggestionSelected(selectedItem -> {
            textField.setText(String.valueOf(selectedItem));
            suggestionPopup.hide();
        }, textField);
    }

    public class CustomSuggestionPopup<T> extends Popup {

        private final ListView<T> listView;

        public CustomSuggestionPopup(ObservableList<T> suggestions) {
            this.listView = new ListView<>(suggestions);
            this.listView.getStyleClass().add("suggestion-list");

            this.getContent().add(listView);
        }

        public void show(Window window, double anchorX, double anchorY) {
            if (!isShowing()) {
                show(window);
            }

            setX(anchorX);
            setY(anchorY);
        }

        public void hide() {
            super.hide();
        }

        public void setOnSuggestionSelected(EventHandler<SuggestionEvent<T>> eventHandler, TextField targetTextField) {
            listView.setOnMouseClicked(event -> {
                T selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    SuggestionEvent<T> suggestionEvent = new SuggestionEvent<>(selectedItem);
                    eventHandler.handle(suggestionEvent);
                    targetTextField.setText(String.valueOf(selectedItem));
                }
            });
        }
    }

    public class SuggestionEvent<T> extends Event {

        public static final EventType<SuggestionEvent> SUGGESTION_SELECTED = new EventType<>(Event.ANY, "SUGGESTION_SELECTED");

        private final T selectedItem;

        public SuggestionEvent(T selectedItem) {
            super(SUGGESTION_SELECTED);
            this.selectedItem = selectedItem;
        }

        public T getSelectedItem() {
            return selectedItem;
        }
    }
}
