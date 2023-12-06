package com.example.ovapp.controllers;

import com.example.ovapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {

    private Stage stage;
    private Scene main;

    public ScreenController(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("login.fxml"));
        main = new Scene(fxmlLoader.load(), 1680, 800);
        activate("login", "login!");
    }

    public ScreenController (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        main = node.getScene();
    }

    protected void activate(String name, String title) throws IOException {
        stage.setTitle(title);
        main.setRoot(FXMLLoader.load(getClass().getResource(name + ".fxml")));
        stage.setScene(main);
        stage.show();
    }
}
