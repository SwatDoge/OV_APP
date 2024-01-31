package com.example.ovapp.controllers;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label errorLabel;

    @FXML
    private void onLoginButtonPressed() {
        try {
            Users.getInstance().logIntoUser(usernameField.getText(), passwordField.getText());
            showLoginSuccessAlert();
            Page.navigateTo(EPage.HOME);
            clearFields();
        }
        catch (RuntimeException e) {
            passwordField.setText("");
            errorLabel.setText(e.getMessage());
        }
    }

    private void showLoginSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inloggen geslaagd");
        alert.setHeaderText(null);
        alert.setContentText("Welkom, " + usernameField.getText() + "! Inloggen is geslaagd.");
        alert.showAndWait();
    }

    @FXML
    private void onRequestRegisterButtonClick() {
        Page.navigateTo(EPage.REGISTER);
    }

    @FXML
    private void onBackButtonPressed() {
        clearFields();
        Page.navigateTo(EPage.HOME);
    }

    private void clearFields() {
        passwordField.setText("");
        errorLabel.setText("");
    }
}
