package com.example.ovapp.controllers;

import com.example.ovapp.models.nsapi.NSApiRoot;
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
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.example.ovapp.Main.currentStage;
import static com.example.ovapp.tools.Request.sendApiRequest;



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
    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @FXML
    private Pane sidebar;

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
            String formattedTime = convertTime(selectedTime);


            LocalDate selectedDate = startDatePicker.getValue();
            String formattedDate = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            NSApiRoot nsApiRoot = sendApiRequest(fromStation, toStation, transportType, searchForArrival, formattedTime, formattedDate);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ovapp/search-result-view.fxml"));
            Parent searchResultParent = loader.load();

            SearchResultController searchResultController = loader.getController();
            searchResultController.updateResultsDisplay(nsApiRoot);

            Scene scene = new Scene(searchResultParent);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUITranslations() {
        startCityTextField.setPromptText(bundle.getString("startCityPrompt"));
        endCityTextField.setPromptText(bundle.getString("endCityPrompt"));
        transportChoiceBox.getItems().add(bundle.getString("transportChoiceBoxPrompt"));
        transportChoiceBox.setValue(bundle.getString("transportChoiceBoxPrompt"));

        planReisButton.setText(bundle.getString("planReisButton"));
        timeChoiceBox.getItems().setAll(
                bundle.getString("timeChoiceBoxDeparture"),
                bundle.getString("timeChoiceBoxArrival")
        );
        // Update other UI elements with text from the resource bundle
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
    private void toggleSideBar() {
        sidebar.setVisible(true);
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

        timeChoiceBox.setValue("Vertrek");

        transportChoiceBox.setValue("Geen Voorkeur");
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
            "Aalten", "Abcoude", "Akkrum", "Alkmaar", "Almelo", "Alkmaar Noord", "Alphen a/d Rijn", "Amersfoort Schothorst", "Amersfoort Centraal", "Amsterdam RAI", "Amsterdam Amstel", "Amsterdam Centraal", "Amsterdam Sloterdijk", "Amsterdam Muiderpoort", "Amsterdam Zuid", "Anna Paulowna", "Apeldoorn", "Appingedam", "Arkel", "Arnemuiden", "Arnhem Centraal", "Arnhem Velperpoort", "Assen", "Amsterdam Bijlmer ArenA", "Arnhem Presikhaaf", "Amsterdam Lelylaan", "Almere Centrum", "Almere Buiten", "Almere Muziekwijk", "Baarn", "Baflo", "Barendrecht", "Barneveld Centrum", "Barneveld Noord", "Bedum", "Beek-Elsloo", "Beesd", "Beilen", "Almere Parkwijk", "Bergen op Zoom", "Best", "Beverwijk", "Bilthoven", "Barneveld Zuid", "Blerick", "Bloemendaal", "Bodegraven", "Borne", "Boskoop", "Boskoop Snijdelwijk", "Bovenkarspel-Grootebroek", "Boxmeer", "Boxtel", "Breda", "Breda-Prinsenbeek", "Breukelen", "Lansingerland-Zoetermeer", "Brummen", "Buitenpost", "Bunde", "Bunnik", "Bussum Zuid", "Capelle Schollevaar", "Castricum", "Chevremont", "Coevorden", "Culemborg", "Cuijk", "Dalen", "Diemen Zuid", "Daarlerveen", "Diemen", "Delft Campus", "Dalfsen", "Deinum", "Delden", "Delft", "Delfzijl", "Deurne", "Deventer", "Didam", "Delfzijl West", "Dieren", "Doetinchem", "Den Dolder", "Dordrecht", "Dordrecht Zuid", "Driebergen-Zeist", "Driehuis", "Doetinchem De Huet", "Deventer Colmschate", "Dordrecht Stadspolders", "De Vink", "Dronryp", "Duiven", "Duivendrecht", "Echt", "Eindhoven Strijp-S", "Dronten", "Ede-Wageningen", "Ede Centrum", "Eemshaven", "Eindhoven Centraal", "Elst", "Emmen", "Enkhuizen", "Enschede", "Ermelo", "Enschede De Eschmarke", "Etten-Leur", "Eijsden", "Enschede Kennispark", "Almere Oostvaarders", "Arnhem Zuid", "Amersfoort Vathorst", "Apeldoorn Osseveld", "Bovenkarspel Flora", "Amsterdam Holendrecht", "Franeker", "Apeldoorn De Maten", "Eygelshoven Markt", "Amsterdam Science Park", "Boven-Hardinxveld", "Groningen Europapark", "Gaanderen", "Helmond Brandevoort", "Geldermalsen", "Geldrop", "Geleen Oost", "Geleen-Lutterade", "Gilze-Rijen", "Goes", "Goor", "Gorinchem", "Gouda Goverwelle", "Gouda", "Gramsbergen", "Glanerbrug", "Groningen", "Groningen Noord", "Grou-Jirnsum", "Grijpskerk", "Haarlem Spaarnwoude", "Den Haag Mariahoeve", "Den Haag Moerwijk", "Den Haag HS", "Den Haag Centraal", "Haarlem", "Hurdegaryp", "Hardenberg", "Harderwijk", "Hardinxveld-Giessendam", "Harlingen", "Haren", "Harlingen Haven", "Helmond Brouwhuis", "Helmond 't Hout", "Heerenveen IJsstadion", "Heemstede-Aerdenhout", "Den Helder Zuid", "Heerenveen", "Heerhugowaard", "Heerlen", "Heeze", "Heiloo", "Heino", "Den Helder", "Hengelo Oost", "Helmond", "Hemmen-Dodewaard", "Hengelo", "Heemskerk", "'s-Hertogenbosch", "'s-Hertogenbosch Oost", "Hilversum", "Hindeloopen", "Hilversum Media Park", "Hoensbroek", "Hollandsche Rading", "Holten", "Hillegom", "Hoogeveen", "Hoogezand-Sappemeer", "Hoofddorp", "Hoogkarspel", "Houten Castellum", "Hoorn Kersenboogerd", "Hoorn", "Eygelshoven", "Horst-Sevenum", "Houten", "Houthem-St. Gerlach", "Kampen", "Kapelle-Biezelinge", "Kerkrade Centrum", "Kesteren", "Kampen Zuid", "Klarenbeek", "Klimmen-Ransdaal", "Koog aan de Zaan", "Zaandijk Zaanse Schans", "Koudum-Molkwerum", "Krabbendijke", "Krommenie-Assendelft", "Kropswolde", "Kruiningen-Yerseke", "Den Haag Ypenburg", "Den Haag Laan v NOI", "Lage Zwaluwe", "Leiden Lammenschans", "Leerdam", "Leeuwarden", "'t Harde", "Leeuwarden Camminghaburen", "Leiden Centraal", "Lelystad Centrum", "Lichtenvoorde-Groenlo", "Lochem", "Halfweg-Zwanenburg", "Loppersum", "Heerlen Woonboulevard", "Lunteren", "Emmen Zuid", "Maarn", "Maarssen", "Maastricht", "Maastricht Randwyck", "Mantgum", "Mariënberg", "Martenshoek", "Maarheeze", "Hengelo Gezondheidspark", "Meerssen", "Meppel", "Middelburg", "Hardinxveld Blauwe Zoom", "Mook-Molenhoek", "Maastricht Noord", "Almere Poort", "Nijmegen Lent", "Naarden-Bussum", "Nieuw Amsterdam", "Nieuwerkerk a/d IJssel", "Bad Nieuweschans", "Nieuw Vennep", "Nunspeet", "Nuth", "Nijmegen Heyendaal", "Nijkerk", "Nijmegen", "Nijverdal", "Nijmegen Dukenburg", "Nijmegen Goffert", "Obdam", "Oisterwijk", "Oldenzaal", "Olst", "Ommen", "Oosterbeek", "Hoevelaken", "Opheusden", "Oss", "Oss West", "Sliedrecht Baanhoek", "Oudenbosch", "Purmerend Weidevenne", "Overveen", "Rijswijk", "Purmerend Overwhere", "Rotterdam Alexander", "Purmerend", "Putten", "Raalte", "Ravenstein", "Rhenen", "Reuver", "Rheden", "Almelo de Riet", "Rilland-Bath", "Roermond", "Rosmalen", "Roodeschool", "Roosendaal", "Rotterdam Blaak", "Rotterdam Centraal", "Rotterdam Noord", "Rotterdam Zuid", "Rotterdam Stadion", "Ruurlo", "Rijssen", "Rotterdam Lombardijen", "Santpoort Noord", "Santpoort Zuid", "Sauwerd", "Landgraaf", "Schagen", "Scheemda", "Schiedam Centrum", "Schinnen", "Schin op Geul", "Schiphol Airport", "Sittard", "Sliedrecht", "Sneek", "Soest", "Soestdijk", "Hilversum Sportpark", "Soest Zuid", "Spaubeek", "Sneek Noord", "Stavoren", "Stedum", "Steenwijk", "Susteren", "Swalmen", "Tegelen", "Terborg", "Tilburg Universiteit", "Tiel", "Tilburg", "Tilburg Reeshof", "Twello", "Tiel Passewaaij", "Sassenheim", "Utrecht Vaartsche Rijn", "Utrecht Leidsche Rijn", "Utrecht Zuilen", "Utrecht Terwijde", "Uitgeest", "Uithuizen", "Uithuizermeeden", "Usquert", "Utrecht Overvecht", "Utrecht Centraal", "Utrecht Lunetten", "Utrecht Maliebaan", "Veenendaal Centrum", "Veenendaal West", "Valkenburg", "Varsseveld", "Veendam", "Veenendaal-De Klomp", "Feanwâlden", "Velp", "Venlo", "Venray", "Vierlingsbeek", "Vleuten", "Vlissingen", "Vlissingen Souburg", "Voorhout", "Voerendaal", "Voorburg", "Voorschoten", "Vorden", "Vriezenveen", "Vroomshoop", "Vught", "Voorst-Empe", "Waddinxveen Noord", "Waddinxveen", "Waddinxveen Triangel", "Warffum", "Weert", "Weesp", "Wehl", "Westervoort", "Wezep", "Wierden", "Winschoten", "Winsum", "Winterswijk", "Winterswijk West", "Woerden", "Wolfheze", "Wolvega", "Workum", "Wormerveer", "Wijchen", "Wijhe", "IJlst", "Zaandam Kogerveld", "Zaandam", "Zaltbommel", "Zandvoort aan Zee", "Zetten-Andelst", "Zevenaar", "Zevenbergen", "Zoetermeer Oost", "Zoetermeer", "Zuidbroek", "Zuidhorn", "Zutphen", "De Westereen", "Zwolle Stadshagen", "Zwolle", "Zwijndrecht");
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
