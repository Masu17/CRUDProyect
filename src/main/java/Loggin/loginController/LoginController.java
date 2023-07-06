package Loggin.loginController;

import Loggin.executables.ExecutableActions;
import Loggin.interfaces.Local;
import sqlExecs.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * This class is the controller for the LoggingFxml
 */

public class LoginController {
    //These are the components that define the fxml.
    @FXML
    public Button LoginAction;
    @FXML
    public
    ToggleGroup conexionLogin;
    @FXML
    public TextField userLogin;
    public TextField passwordLogin;

    /**
     * This is a Button OnAction of the Logging
     * It recovers the user information to make a redirection.
     */

    @FXML
    protected void LoginAction() {

        if (ExecutableActions.validateCredentials(userLogin, passwordLogin)) {
            if (ExecutableActions.retriveConnectionData(conexionLogin)) {
                Connection.execCloudConnection();

                if (Connection.getConnection() != null) {
                    Main.executables.ExecutableActions.setConnectionWay("Cloud");
                    ExecutableActions.nextStage(LoginAction);
                }

            } else {
                String newFXML = "LoginLocalConnection.fxml";
                Main.executables.ExecutableActions.setConnectionWay("Local");
                Local.setScene((Stage) LoginAction.getScene().getWindow());
            }
        }
    }


}