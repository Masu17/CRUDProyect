package login.loginController;

import login.executables.ExecutableActions;
import login.interfaces.Login;
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
     *
     * This method retrive the inputed data from the user
     *
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

    public void LogOut() {
        goToLogIn();
    }

    public void goToLogIn(){

        Stage logOut = (Stage) btnLogOut.getScene().getWindow();
        logOut.close();

        Login log = new Login();
        log.setLogingStage(btnLogOut);
    }
}
