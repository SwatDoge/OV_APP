module com.example.ovapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens com.example.ovapp to javafx.fxml;
    exports com.example.ovapp;
    exports com.example.ovapp.controllers;
    exports com.example.ovapp.models.nsapi;
    opens com.example.ovapp.controllers to javafx.fxml;
    exports com.example.ovapp.components;
    opens com.example.ovapp.components to javafx.fxml;
    exports com.example.ovapp.tools;
    opens com.example.ovapp.tools to javafx.fxml;
}