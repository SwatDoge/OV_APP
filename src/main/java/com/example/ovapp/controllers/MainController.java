package com.example.ovapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.event.Event;
import javafx.event.EventType;


import java.io.IOException;

public class MainController {

    @FXML
    private TextField startCityTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField endCityTextField;

    @FXML
    private ChoiceBox<String> transportChoiceBox;

    @FXML
    private Button planReisButton;

    @FXML
    private void citySuggestion(MouseEvent event) {
        // Your implementation here
        configureCitySuggestions(startCityTextField);
        configureCitySuggestions(endCityTextField);
    }

    @FXML
    private void initialize() {
        configureCitySuggestions(startCityTextField);
        configureCitySuggestions(endCityTextField);
    }

    private final ObservableList<String> allCities = FXCollections.observableArrayList(
            "Amersfoort", "Amsterdam", "Utrecht", "Ede", "Alkmaar", "Den Haag", "Groningen", "Deventer", "Nijkerk", "Apeldoorn", "Enschede");

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
        CustomSuggestionPopup<String> suggestionPopup = new CustomSuggestionPopup<>(sortedCities);

        // Show suggestions when the user clicks the text field
        textField.setOnMouseClicked(event -> suggestionPopup.show(textField.getScene().getWindow(), event.getScreenX(), event.getScreenY()));

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
        String endCity = endCityTextField.getText();
        String transport = transportChoiceBox.getValue();
        String date = startDatePicker.getValue() != null ? startDatePicker.getValue().toString() : "Geen datum geselecteerd";

        System.out.println("Start Stad: " + startCity);
        System.out.println("Eind Stad: " + endCity);
        System.out.println("Transport: " + transport);
        System.out.println("Datum: " + date);
    }

    @FXML
    protected void onLogInButtonClick(ActionEvent event) throws IOException {
        ScreenController screenController = new ScreenController(event);
        screenController.activate("login", "welcome");
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

        public void setOnSuggestionSelected(EventHandler<SuggestionEvent<T>> eventHandler) {
            listView.setOnMouseClicked(event -> {
                T selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    SuggestionEvent<T> suggestionEvent = new SuggestionEvent<>(selectedItem);
                    eventHandler.handle(suggestionEvent);
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
