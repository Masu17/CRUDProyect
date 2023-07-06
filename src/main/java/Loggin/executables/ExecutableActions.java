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
     * @param user     name entered by the user to log in the app
     * @param password Password entered by the user that should get access the application
     * @return True if the credentials are correct
     */
    public static boolean validateCredentials(TextField user, TextField password) {

        if (!user.getText().isEmpty() && !password.getText().isEmpty()) {
            if (!user.getText().contains(" ") && !password.getText().contains(" ")) {
                if (user.getText().trim().equals("admin") || user.getText().trim().equals("admin")) {
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
     * @param btn button which is used to connect with the database
     */
    public static void nextStage(Button btn) {
        Application.showApplicationWindow();

        Stage Login = (Stage) btn.getScene().getWindow();
        Login.close();
    }


}
