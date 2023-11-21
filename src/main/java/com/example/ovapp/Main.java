package com.example.ovapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
System.out.println(getClass().getResource("Login.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root2 = loader.load();

        Image icon = new Image("file:src/main/resources/images/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Hollandse Baan");

        stage.setScene(new Scene(root2));

        stage.show();
    }
}