module com.example.aulasinterface {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports interfaces.view;
    opens interfaces.view to javafx.fxml;
    exports interfaces.controllers;
    opens interfaces.controllers to javafx.fxml;
    exports interfaces.model;
    opens interfaces.model to javafx.fxml;
}