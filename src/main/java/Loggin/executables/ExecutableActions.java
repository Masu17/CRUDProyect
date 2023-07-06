package Loggin.executables;

import Main.interfaces.Application;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Objects;

/**
 * This class runs all the functionalities of the database connection
 */

public class ExecutableActions {

    /**
     * Method that validates user credentials
     *
     * @param userName     name entered by the user to log in the app
     * @param userPassword Password entered by the user that should get access the application
     * @return True if the credentials are correct
     */
    public static boolean validateCredentials(TextField userName, TextField userPassword) {
        if (!userName.getText().isEmpty() && !userPassword.getText().isEmpty()) {
            if (!userName.getText().contains(" ") && !userPassword.getText().contains(" ")) {
                if (userName.getText().trim().equals("admin") || userName.getText().trim().equals("admin")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión:" +
                            "\n[ERROR] Identificacion invalida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pueden introducir espacios");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion:" +
                    "\n[ERROR] Campos vacios.");
        }

        return false;
    }


    /**
     * This method indicates if the connection will be with a local database or with a predefined cloud database
     *
     * @param connectionBtn The name of the radiobutton selected before click the log in button
     * @return true if the selected button equals "Cloud"
     */
    public static boolean retriveConnectionData(ToggleGroup connectionBtn) {
        return Objects.equals(((RadioButton) connectionBtn.getSelectedToggle()).getId(), "Cloud");
    }


    /**
     * This method changes the stage of the application. Leaves the user to the first to the second stage.
     *
     * @param button button which is used to connect with the database
     */
    public static void nextStage(Button button) {
        Application.showApplicationWindow();
        Stage Login = (Stage) button.getScene().getWindow();
        Login.close();
    }


}
