package com.example.ovapp;

import com.example.ovapp.controllers.layout.LayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static LayoutController layoutController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/ovapp/layout/layout-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        layoutController = loader.getController();

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/assets/brand/logo.png")));
        stage.setTitle("Hollandse Baan");

        stage.setScene(scene);
        stage.show();
    }
}
