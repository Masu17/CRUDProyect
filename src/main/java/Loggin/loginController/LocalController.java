package Loggin.loginController;

import Loggin.executables.ExecutableActions;
import Loggin.interfaces.Loggin;
import sqlExecs.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the main controller class of the local connection interface
 * */

public class LocalController {
    //These are the components that define the fxml.
    @FXML
    public TextField localHost;
    public PasswordField localPassword;
    public TextField localPort;
    public TextField localUser;
    public Button localDBConnect;
    public Button logOutButton;

    /**
     * This method retrive the input data from the user
     * and sends the information to the Connection method
     **/
    @FXML
    protected void createConnection(){
        Map<String, String > localMap = new HashMap<>();
        localMap.put("host", localHost.getText());
        localMap.put("port", localPort.getText());
        localMap.put("username", localUser.getText());
        localMap.put("password", localPassword.getText());
        Connection.execLocalConnection(localMap);
        if(Connection.getConnection() != null){
            ExecutableActions.nextStage(localDBConnect);
        }
    }

    /**
     * This method executes a button OnAction
     * that makes the stage change to Loging interface
     */

    public void logOut() {
        goToLogIn();
    }

    /**
     * This method sends changes the Local Stage
     * to the Login Stage
     */
    //Change this method to executableActions
    public void goToLogIn(){
        //recovers the LocalStage and closes it
        Stage logOut = (Stage) logOutButton.getScene().getWindow();
        logOut.close();
        //executes the new Logging Stage.
        Loggin log = new Loggin();
        log.setLogingStage(logOutButton);
    }
}
