package login.interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {

    /**
     * The main interface of the application. This method works on the new stage.
     * @param stage Current state of the stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("login");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("[ERROR] No se encontró el fichero FXML");
        }
    }

    /**
     * This method is used to return to the logging stage from the rest of the application
     * @param btnLogOut The button that execute the action of login out,
     *                  it's needed to know the current stage of the stage
     */
    public void setLogingStage(Button btnLogOut) {

        Stage currentStage = (Stage) btnLogOut.getScene().getWindow();
        currentStage.close();
        start(currentStage);

    }
    /**
     * This is the main method that execute all the application
     * */
    public static void main(String[] args) {
        launch();
    }

}