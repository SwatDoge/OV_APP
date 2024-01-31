package com.example.ovapp.controllers.layout;

import com.example.ovapp.tools.PageInfo;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class LayoutController {
    @FXML
    private VBox content;
    @FXML
    private HBox navbar;
    @FXML
    private HBox sidebar;

    //Set the content window of the layout
    public void setContent(Parent parent) {
        sidebar.setVisible(false);

        // Waarom werkt dit?!!?
        javafx.collections.ObservableList<javafx.scene.Node> contentChildren = content.getChildren();
        VBox.setVgrow(parent, Priority.ALWAYS);
        contentChildren.removeFirst();
        contentChildren.addFirst(parent);
        contentChildren.removeFirst();
        contentChildren.addFirst(parent);
    }

    @FXML
    protected void initialize() {
        sidebar.setVisible(false);
        navbar.getChildren().getFirst().onMouseClickedProperty().set((EventHandler<? super MouseEvent>) (MouseEvent t) -> {
            sidebar.setVisible(!sidebar.isVisible());
        });
    }
}
