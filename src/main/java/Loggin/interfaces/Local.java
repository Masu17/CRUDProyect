package Loggin.interfaces;

import Loggin.loginController.LocalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the interface of the Local connection form
 * in this class we work on the user input
 * so the introduced data produces a valid connection.
 * */

public class Local {

    //This is a static instace of the local controller.
    private static LocalController controller;

    /**
     * This method creates the new Scene for the application form.
     * All this Scene is created from the SceneBuilder workstation
     * @param stage  current state of the stage
     * */

    public static void setScene(Stage stage){
        try {
            FXMLLoader newFXMLLoader = new FXMLLoader(Loggin.class.getResource("LoginLocalConnection.fxml"));
            Scene scene = new Scene(newFXMLLoader.load());
            controller = newFXMLLoader.getController();
            addListeners();
            stage.setTitle("Loggin");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("[ERROR] No se encontro el fichero FXML");
        }
    }

    /**
     * Given the previous TextFields we can work with listeners,
     * this, so we can  validate the user input data
     * */

    private static void addListeners(){
        controller.hostLocal.textProperty().addListener((observable, oldValue, newValues) -> activateButton() );
        controller.passwordLocal.textProperty().addListener((observable, oldValue, newValues) -> activateButton() );
        controller.puertoLocal.textProperty().addListener((observable, oldValue, newValues) -> activateButton() );
        controller.usuarioLocal.textProperty().addListener((observable, oldValue, newValues) -> activateButton() );
    }

    /**
     * This is the method that's been constantly executed by the listeners
     * it defines the condition that have to be accomplished by the input text
     * */

    protected static void activateButton() {
        boolean fieldIsEmpty = controller.hostLocal.getText().isEmpty() || controller.passwordLocal.getText().isEmpty()
                || controller.puertoLocal.getText().isEmpty() || controller.usuarioLocal.getText().isEmpty();
        controller.LocalDBConnect.setDisable(fieldIsEmpty);
    }
}
