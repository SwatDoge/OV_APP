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

import java.io.IOException;

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
    private Button planReisButton;

    @FXML
    private void onPlanReisButtonClick(ActionEvent event) {
        try {
            // Haal waarden op uit JavaFX-elementen
            String fromStation = startCityTextField.getText();
            String toStation = endCityTextField.getText();


            sendApiRequest(fromStation, toStation );

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ovapp/search-result-view.fxml"));
            Parent searchResultParent = loader.load();

            // Haal het podium op
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Stel het zoekresultaatscherm in als de nieuwe scene
            Scene scene = new Scene(searchResultParent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
