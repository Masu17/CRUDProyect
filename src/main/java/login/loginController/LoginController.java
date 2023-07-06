package login.loginController;

import login.executables.ExecutableActions;
import Main.interfaces.Application;
import login.interfaces.Local;
import sqlExecs.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class LoginController {

    //Login page

    @FXML
    public Button LoginAction;
    @FXML
    public
    ToggleGroup conexionLogin;
    @FXML
    public TextField userLogin;

    public TextField passwordLogin;

    @FXML
    protected void LoginAction() {

        //Application app = new Application();

        if (ExecutableActions.validateCredentials(userLogin, passwordLogin)) {
            if (ExecutableActions.retriveConnectionData(conexionLogin)) {
                Connection.execCloudConnection();

                if(Connection.getConnection() != null){
                    Main.executables.ExecutableActions.setConnectionWay("Cloud");
                    ExecutableActions.nextStage(LoginAction);
                }

            } else {
                String newFXML = "LoginLocalConnection.fxml";
                Main.executables.ExecutableActions.setConnectionWay("Local");
                Local.setScene((Stage)LoginAction.getScene().getWindow());
            }
        }
    }



}