module com.example.pruebados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Main.controllers to javafx.fxml;
    exports Main.controllers;

    opens login.loginController to javafx.fxml;
    exports login.loginController;

    exports login.interfaces;
    opens login.interfaces to javafx.fxml;

    opens Main.interfaces to javafx.fxml;
    exports Main.interfaces;
}