package com.example.ovapp.controllers.layout;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class LayoutController {
    @FXML
    private VBox content;
    @FXML
    private HBox navbar;
    @FXML
    private HBox sidebar;

    @FXML
    protected void initialize() {
        sidebar.setVisible(false);
        navbar.getChildren().getFirst().onMouseClickedProperty().set((EventHandler<? super MouseEvent>) (MouseEvent t) -> {
            sidebar.setVisible(!sidebar.isVisible());
        });
    }
}
