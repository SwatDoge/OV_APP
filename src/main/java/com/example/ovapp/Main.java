package com.example.ovapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setResizable(false);

        primaryStage.setTitle("Hollandse Baan");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
