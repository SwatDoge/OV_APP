module com.example.ovapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ovapp to javafx.fxml;
    exports com.example.ovapp;
    exports com.example.ovapp.controllers;
    opens com.example.ovapp.controllers to javafx.fxml;
    exports com.example.ovapp.models;
    opens com.example.ovapp.models to javafx.fxml;
}