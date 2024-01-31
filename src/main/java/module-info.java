module com.example.ovapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;


    opens com.example.ovapp to javafx.fxml;
    exports com.example.ovapp;
    exports com.example.ovapp.controllers;
    exports com.example.ovapp.models.nsapi;
    opens com.example.ovapp.controllers to javafx.fxml;
    exports com.example.ovapp.components;
    opens com.example.ovapp.components to javafx.fxml;
    exports com.example.ovapp.tools;
    opens com.example.ovapp.tools to javafx.fxml;
    exports com.example.ovapp.models.user;
    opens com.example.ovapp.models.user to javafx.fxml;
    exports com.example.ovapp.controllers.layout;
    opens com.example.ovapp.controllers.layout to javafx.fxml;
}