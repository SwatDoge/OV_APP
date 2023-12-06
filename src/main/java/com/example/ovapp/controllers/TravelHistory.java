package com.example.ovapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

public class TravelHistory implements Initializable{
    @FXML
    private ScrollPane scrollPaneMain;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPaneMain.setStyle("-fx-background: #252422; -fx-border-color: #252422;");
    }

}
