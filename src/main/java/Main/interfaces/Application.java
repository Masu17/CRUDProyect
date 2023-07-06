package Main.interfaces;

import Main.controllers.AplicativoController;
import Main.executables.ExecutableActions;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This is the main menu of the application
 * This stage is loaded after the user validates the credentials for the application
 */

public class Application {

    private static AplicativoController controller;
    public static Map<Integer, Boolean> test = new HashMap<>();
    private static ExecutableActions exec = new ExecutableActions();

    /**
     * This method collect data from showTables
     */
    public static void collectData() {
        exec.showTables(sqlExecs.Connection.getConnection());
    }

    /**
     * This method loads application's stage
     */
    public static void showApplicationWindow() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Application.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            controller = fxmlLoader.getController();
            stage.setTitle("Aplicativo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("[ERROR] No se encontro el fichero FXML correspondiente");
        }
    }

    /**
     * This method shows the result of .sql document loaded
     *
     * @param query Query contained in .sql document
     */
    public static void reloadText(String query) {

        String temp = "";

        if (query.contains("CREATE DATABASE")) {
            temp = controller.console.getText();
            temp += "\n [OK]Base de datos creada correctamente.";
            controller.console.setText(temp);
        } else if (query.contains("USE")) {
            temp = controller.console.getText();
            temp += "\n [OK]Base de datos cambiada correctamente.";
            controller.console.setText(temp);
        } else if (query.contains("CREATE TABLE")) {
            temp = controller.console.getText();
            temp += "\n [OK]Tabla creada correctamente.";
            controller.console.setText(temp);
        } else if (query.contains("INSERT")) {
            temp = controller.console.getText();
            temp += "\n [OK]datos insertados correctamente.";
            controller.console.setText(temp);
        }

    }

    /**
     * Each time that a character is being introduced, this method is executed
     *
     * @param textField Text contained in a textfield
     */
    public static void execListener(ArrayList<TextField> textField) {

        for (TextField text : textField) {
            text.textProperty().addListener((observable, oldValue, newValue) ->
            {
                if (createRegex(text.getText(), text.getId().substring(0, text.getId().lastIndexOf(":")))) {
                    test.put(Integer.parseInt(text.getId().substring(text.getId().lastIndexOf(":") + 1)), true);
                    activateButton(textField);
                } else if (test.get(Integer.parseInt(text.getId().substring(text.getId().lastIndexOf(":") + 1))) != null
                        && !test.get(Integer.parseInt(text.getId().substring(text.getId().lastIndexOf(":") + 1)))) {
                    test.remove(Integer.parseInt(text.getId().substring(text.getId().lastIndexOf(":") + 1)));
                } else {
                    controller.btnColumnSubmission.setDisable(true);
                }
            });
        }
    }

    /**
     * This method check if all textfields have been validated and enables
     * the execution button
     *
     * @param textField
     */

    public static void activateButton(ArrayList<TextField> textField) {

        boolean isDisabled = true;

        if (textField.size() == test.size()) {
            for (int i = 1; i <= test.size(); i++) {
                if (!test.get(i)) {
                    isDisabled = true;
                    break;
                } else {
                    isDisabled = false;
                }
            }
            controller.btnColumnSubmission.setDisable(isDisabled);
        }
    }

    //NO TOCAR BAJO NINGUN CONCEPTO

    /**
     *
     * This method creates a regex dynamically and check if the textfield content matches with it
     *
     * @param userInput
     * @param columnTableName
     * @return True if the text contained in textfield fullfils all the conditions
     */

    public static boolean createRegex(String userInput, String columnTableName) {

        ArrayList<Object> arrayList = exec.getCondicionesColumnas().get(columnTableName);
        boolean properLength = false;

        // Creates a regex dinamically
        StringBuilder regexBuilder = new StringBuilder();
        StringBuilder regexSpecialChars = new StringBuilder();
        boolean containsNumbers = (boolean) arrayList.get(0);
        boolean containsLetters = (boolean) arrayList.get(1);
        boolean containsSpecialChars = (boolean) arrayList.get(2);

        if (containsNumbers && !containsLetters) {
            regexBuilder.append("\\d+");
        } else if (containsLetters && !containsNumbers) {
            regexBuilder.append("[a-zA-Z]+");
        } else {
            regexBuilder.append("(?=.*\\d)(?=.*[a-zA-Z]).*");
        }

        Pattern patternRegexSpecial = Pattern.compile("a");
        if (containsSpecialChars) {
            regexSpecialChars.append("|[.,\\\\-]");
            patternRegexSpecial = Pattern.compile(regexBuilder.toString());
        }

        // Pattern to allow decimal numbers
        regexBuilder.append("|\\d+\\.\\d+");

        // Obtain final regex
        Pattern patternRegex = Pattern.compile(regexBuilder.toString());

        if ((boolean) arrayList.get(3)) {
            // Check word's length that should be less or equal to the length set on the map
            if ((int) arrayList.get(4) >= userInput.length()) {
                properLength = true;
            }
        } else {
            // Check that the word's length is the same length as the one set on the map
            if ((int) arrayList.get(4) == userInput.length()) {
                properLength = true;
            }
        }

        // Check if string matches with regex pattern
        boolean matches = patternRegex.matcher(userInput).matches();
        if (containsSpecialChars) {
            boolean matchesSpecial = patternRegexSpecial.matcher(userInput).find();
            return matches && matchesSpecial && userInput.length() != 0;
        }

        // check if the text contained int textfield matches with the regex, and the lenght is correct and
        // length of the text is not 0 and return true or false

        return matches && properLength && userInput.length() != 0;
    }
}
