package com.example.ovapp.controllers;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label errorLabel;

    @FXML
    private void onRegisterButtonPressed() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Users userList = Users.getInstance();
        try {
            userList.createUser(username, password);
            userList.logIntoUser(username, password);
            clearFields();
            Page.navigateTo(EPage.HOME);
        }
        catch (RuntimeException e) {
            System.out.println(e);
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void onBackButtonPressed() {
        clearFields();
        Page.navigateTo(EPage.HOME);
    }

    @FXML
    private void onRequestLoginButtonClick() {
        Page.navigateTo(EPage.LOGIN);
    }

    private void clearFields() {
        passwordField.setText("");
        usernameField.setText("");
        errorLabel.setText("");
    }
}
