package com.example.ovapp;

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
    public static Stage currentStage;

    @Override
    public void start(Stage stage) throws Exception {
        currentStage = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/ovapp/home-view.fxml")));


        Scene scene = new Scene(root, 600, 400);

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/assets/brand/logo.png")));
        stage.setResizable(false);
        stage.setTitle("Hollandse Baan");

        stage.setScene(scene);
        stage.show();
    }
}
