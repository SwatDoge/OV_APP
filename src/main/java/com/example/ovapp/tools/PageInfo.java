package com.example.ovapp.tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class PageInfo<T> {
    public String resourcePath;
    public FXMLLoader loader;
    private Parent parent;
    private Scene scene;
    private T controller;

    public PageInfo(String resourcePath) {
        this.resourcePath = resourcePath;
        this.loader = new FXMLLoader(getClass().getResource(resourcePath));
    }

    public Parent getParent() throws IOException {
        if (parent == null) {
            parent = this.loader.load();
        }

        return parent;
    }

    public Scene getScene() throws IOException {
        if (scene == null) {
            Parent parent = getParent();
            scene = new Scene(parent);
        }

        return scene;
    }

    public T getController() throws IOException {
        if (controller == null) {
            getParent();
        }

        return loader.getController();
    }
}
