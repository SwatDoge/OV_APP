package com.example.ovapp.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.models.user.User;
import com.example.ovapp.tools.Page;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

public class SidebarController {

    @FXML
    private Pane sidebar;

    @FXML
    private Button loginLabel;

    @FXML
    private Button homeButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button favouriteButton;

    @FXML
    private Button historyButton; // Geschiedenisknop toegevoegd


    @FXML
    protected void initialize() {
        // Initialize with the default language (e.g., Dutch)

        loginLabel.setText("Inloggen");
        homeButton.setText("Home");
        profileButton.setText("Profiel");
        favouriteButton.setText("Favorieten");
        historyButton.setText("Reishistorie"); // Tekst voor geschiedenisknop
    }

    // Update text every time the sidebar becomes visible

    @FXML
    private void onHomeButtonPressed() {
        // Handle Home button press
        System.out.println("Home button pressed");
        Page.navigateTo(EPage.HOME);
        sidebar.setVisible(false);
    }

    @FXML
    private void onProfileButtonPressed() {
        // Handle Profile button press
        System.out.println("Profile button pressed");
        if (Users.getInstance().isSomeUserLoggedIn()) {
            Page.navigateTo(EPage.PROFILE);
        } else {
            Page.navigateTo(EPage.LOGIN);
        }
        sidebar.setVisible(false);
    }

    @FXML
    private void onLoginButtonPressed() {
        // Handle Login button press
        if (Users.getInstance().isSomeUserLoggedIn()) {
            Users.getInstance().logoutCurrentUser();
        }

        Page.navigateTo(EPage.LOGIN);
        sidebar.setVisible(false);
    }

    @FXML
    private void onFavouriteButtonPressed() {
        // Handle Favourite button press
        System.out.println("Favorite history button pressed");

        User currentUser = Users.getInstance().currentUser;

        if (currentUser != null && !currentUser.getFavoriteTrips().isEmpty()) {
            Page.navigateTo(EPage.FAVORITE);
        } else if (Users.getInstance().isSomeUserLoggedIn()) {
            showNoRoutesMessage();
            Page.navigateTo(EPage.HOME);
        } else {
            Page.navigateTo(EPage.LOGIN);
        }

        sidebar.setVisible(false);
    }

    @FXML
    private void onHistoryButtonPressed() {
        System.out.println("History history button pressed");

        User currentUser = Users.getInstance().currentUser;

        if (currentUser != null && !currentUser.getTripDetails().isEmpty()) {
            Page.navigateTo(EPage.HISTORY);
        } else if (Users.getInstance().isSomeUserLoggedIn()) {
            showNoRoutesMessage();
            Page.navigateTo(EPage.HOME);
        } else {
            Page.navigateTo(EPage.LOGIN);
        }

        sidebar.setVisible(false);
    }

    private void showNoRoutesMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Geen opgeslagen routes");
        alert.setHeaderText(null);
        alert.setContentText("U heeft geen opgeslagen routes. Ga naar HOME om een reis te plannen.");
        alert.showAndWait();
    }
}