module com.example.pruebados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Main.controllers to javafx.fxml;
    exports Main.controllers;

    opens Loggin.loginController to javafx.fxml;
    exports Loggin.loginController;

    exports Loggin.interfaces;
    opens Loggin.interfaces to javafx.fxml;

    opens Main.interfaces to javafx.fxml;
    exports Main.interfaces;
}