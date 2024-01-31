package com.example.ovapp.controllers;

import com.example.ovapp.Users;
import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ProfileController {

    @FXML
    private AnchorPane base;

    @FXML
    private HBox sidebarContainer;

    @FXML
    private void toggleSideBar() {
        sidebarContainer.setVisible(true);
    }

    @FXML
    private Label title;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    // Event handler voor de terugknop
    @FXML
    private void back() {
    }

    // Event handler voor het wijzigen van het wachtwoord
    @FXML
    private void saveDetails() {
        Users.getInstance().currentUser.username = usernameField.getText();
        Users.getInstance().currentUser.password = passwordField.getText();
        Users.getInstance().saveUsers();
    }

    // Event handler voor het verwijderen van het account
    @FXML
    private void deleteAccount() {
        Users.getInstance().deleteCurrentUser();
        Page.navigateTo(EPage.REGISTER);
        sidebarContainer.setVisible(false);
    }

    @FXML
    private void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }

    @FXML
    protected void initialize() {
        updateDetails();
    }

    public void onSwitchToPage() {
        updateDetails();
    }

    //Initiele waardes
    void updateDetails() {
        title.setText("Goedendag, " + Users.getInstance().currentUser.username);
        usernameField.setText(Users.getInstance().currentUser.username);
        passwordField.setText(Users.getInstance().currentUser.password);
    }
}
