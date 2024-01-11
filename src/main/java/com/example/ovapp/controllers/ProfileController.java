package com.example.ovapp.controllers;

import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfileController {

    @FXML
    private Pane sidebar;

    @FXML
    private void toggleSideBar() {
        sidebar.setVisible(true);
    }

    @FXML
    private Button back_button;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button deleteAccountButton;

    // Event handler voor de terugknop
    @FXML
    private void back() {
    }

    // Event handler voor het wijzigen van het wachtwoord
    @FXML
    private void changePassword() {
    }

    // Event handler voor het verwijderen van het account
    @FXML
    private void deleteAccount() {
    }

    @FXML
    private void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
