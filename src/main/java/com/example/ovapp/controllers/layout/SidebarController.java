package com.example.ovapp.controllers.layout;

import java.util.Locale;
import java.util.ResourceBundle;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SidebarController {

    @FXML
    private HBox sidebarContainer;

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
    private TitledPane languageTitledPane;

    private ResourceBundle resourceBundle;

    @FXML
    protected void initialize() {
        // Initialize with the default language (e.g., Dutch)
        setLanguage("nl");

        // Update text every time the sidebar becomes visible.
        sidebar.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    loginLabel.setText("Inloggen");
                    if (Users.getInstance().isSomeUserLoggedIn()) {
                        loginLabel.setText("Uitloggen");
                    }
                }
            }
        });
    }

    public void show() {
        sidebarContainer.setVisible(true);
    }

    public void hide() {
        sidebarContainer.setVisible(false);
    }

    @FXML
    private void onClickedOutOfContext() {
        hide();
    }

    private void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        resourceBundle = ResourceBundle.getBundle("messages", locale);

        // Set text for buttons based on the current language
        loginLabel.setText(resourceBundle.getString("loginLabelText"));
        homeButton.setText(resourceBundle.getString("homeButton"));
        profileButton.setText(resourceBundle.getString("profileButton"));
        favouriteButton.setText(resourceBundle.getString("favouriteButton"));

        // Set text for elements inside TitledPane

        // Print a message for debugging (you can remove this in production)
        System.out.println("System language changed to " + languageCode);
    }

    @FXML
    private void onDutchLanguageButtonPressed() {
        setLanguage("nl");
    }

    @FXML
    private void onEnglishLanguageButtonPressed() {
        setLanguage("en");
    }

    @FXML
    private void onHomeButtonPressed() {
        // Handle Home button press
        System.out.println("Home button pressed");
        Page.navigateTo(EPage.HOME);
    }

    @FXML
    private void onProfileButtonPressed() {
        // Handle Profile button press
        System.out.println("Profile button pressed");
        if(Users.getInstance().isSomeUserLoggedIn()) {
            Page.navigateTo(EPage.PROFILE);
        }
        else {
            Page.navigateTo(EPage.LOGIN);
        }
    }

    @FXML
    private void onLoginButtonPressed() {
        // Handle Login button press
        if(Users.getInstance().isSomeUserLoggedIn()) {
            Users.getInstance().logoutCurrentUser();
        }

        Page.navigateTo(EPage.LOGIN);
    }

    @FXML
    private void onFavouriteButtonPressed() {
        // Handle Favourite button press
        System.out.println("Home button pressed");
        Page.navigateTo(EPage.FAVORIET);
    }
}
