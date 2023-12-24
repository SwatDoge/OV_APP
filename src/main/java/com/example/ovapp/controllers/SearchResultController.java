package com.example.ovapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

public class SearchResultController implements Initializable{
    @FXML
    private ScrollPane scrollPaneMain;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void onBackButtonPressed() {
        Page.navigateTo(EPage.HOME);
    }
}
