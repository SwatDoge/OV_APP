package com.example.ovapp.controllers;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SidebarController {
    @FXML
    private Pane sidebar;

    @FXML
    private Button loginLabel;

    @FXML
    protected void initialize() {
        //Update text iedere keer dat de sidebar zichtbaar word.
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

    @FXML
    private void onHomeButtonPressed() {
        Page.navigateTo(EPage.HOME);
        sidebar.setVisible(false);
    }

    @FXML
    private void onProfileButtonPressed() {
        if(Users.getInstance().isSomeUserLoggedIn()) {
            Page.navigateTo(EPage.PROFILE);
        }
        else {
            Page.navigateTo(EPage.LOGIN);
        }

        sidebar.setVisible(false);
    }

    @FXML
    private void onLoginButtonPressed() {
        if(Users.getInstance().isSomeUserLoggedIn()) {
            Users.getInstance().logoutCurrentUser();
        }

        Page.navigateTo(EPage.LOGIN);
        sidebar.setVisible(false);
    }

    @FXML
    private void onDutchLanguageButtonPressed() {
        System.out.println("Systeem taal veranderd naar Nederlands");
    }

    @FXML
    private void onEnglishLanguageButtonPressed() {
        System.out.println("Systeem taal veranderd naar Engels");
    }

    @FXML
    private void onFavouriteButtonPressed() {
        System.out.println("Navigeren naar favorieten");
        sidebar.setVisible(false);
    }
}
