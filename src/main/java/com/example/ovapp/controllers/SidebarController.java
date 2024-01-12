package com.example.ovapp.controllers;

import com.example.ovapp.enums.EPage;
import com.example.ovapp.tools.Page;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SidebarController {

    @FXML
    private Pane sidebar;

    @FXML
    private void onHomeButtonPressed() {
        Page.navigateTo(EPage.HOME);
        sidebar.setVisible(false);
    }

    @FXML
    private void onProfileButtonPressed() {
        Page.navigateTo(EPage.PROFILE);
        sidebar.setVisible(false);
    }

    @FXML
    private void onFavouriteButtonPressed() {
        System.out.println("Navigeren naar favorieten");
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
}
