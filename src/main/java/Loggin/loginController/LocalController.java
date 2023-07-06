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
    public TextField hostLocal;
    public PasswordField passwordLocal;
    public TextField puertoLocal;
    public TextField usuarioLocal;
    public Button LocalDBConnect;
    public Button btnLogOut;

    /**
     * This method retrive the input data from the user
     * and sends the information to the Connection method
     * */

    @FXML
    protected void createConnection(){
        Map<String, String > mp = new HashMap<>();
        mp.put("host",hostLocal.getText());
        mp.put("port",puertoLocal.getText());
        mp.put("username",usuarioLocal.getText());
        mp.put("password",passwordLocal.getText());

        Connection.execLocalConnection(mp);

        if(Connection.getConnection() != null){
            ExecutableActions.nextStage(LocalDBConnect);
        }
    }

    /**
     *
     * This method executes a button OnAction
     * that makes the stage change to Loging interface
     *
     */

    public void LogOut() {
        goToLogIn();
    }

    /**
     * This method sends changes the Local Stage
     * to the Login Stage
     */

    public void goToLogIn(){
        //recovers the LocalStage and closes it
        Stage logOut = (Stage) btnLogOut.getScene().getWindow();
        logOut.close();
        //executes the new Logging Stage.
        Loggin log = new Loggin();
        log.setLogingStage(btnLogOut);
    }
}
