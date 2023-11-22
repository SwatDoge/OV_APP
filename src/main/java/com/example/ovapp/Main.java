package com.example.ovapp;

import java.awt.Dimension;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        Group root = new Group();
        Scene scene = new Scene(root, Color.rgb(191, 20, 65));

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        Image icon = new Image("file:src/main/resources/images/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Hollandse Baan");
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(false);
        //stage.setFullScreen(true);
        //stage.setFullScreenExitHint("Press esc to exit Fullscreen");

        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Inconsolata&display=swap");

        Text text1 = new Text();
        text1.setText("Start");
        text1.setX(165);
        text1.setY(311);
        text1.setStyle("-fx-font-family: Inconsolata; -fx-font-size: 48");
        text1.setFill(Color.rgb(37,36,34));

        Text text2 = new Text();
        text2.setText("Eind");
        text2.setX(940);
        text2.setY(311);
        text2.setStyle("-fx-font-family: Inconsolata; -fx-font-size: 48");
        text2.setFill(Color.rgb(37,36,34));

        Line line = new Line();
        line.setStartX(0);
        line.setStartY(200);
        line.setEndX(width);
        line.setEndY(200);


        root.getChildren().addAll(text1, text2);
        root.getChildren().add(line);
        stage.setScene(scene);

        stage.show();
    }
}