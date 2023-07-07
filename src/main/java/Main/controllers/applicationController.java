package Main.controllers;


import Loggin.interfaces.Loggin;
import Main.executables.ExecutableActions;
import Main.sqlExecs.CRUD;
import Main.interfaces.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class applicationController {
    public Button loadFile;
    public TextArea textarea;
    public Group before;
    public Group after;
    public Button unloadFile;
    public AnchorPane left;
    public TableView tableviewShowTables;
    public AnchorPane home;
    public AnchorPane showTables;
    public Button btnTabla;
    public Button btnLogOut;
    public Text console;
    public Button insert;
    public AnchorPane inserting;
    public Button btnColumnSubmission;
    public boolean insertBolean;
    public boolean deleteBolean;
    public boolean updateBolean;

    private Map<Integer, String> tables;
    private String tabla;


    /**
     * This method creates the connection and gives feedback
     * to the user, also it's the method that executes the recollection of data.
     */

    public void loadNewScene() {
        // Create the instance of the class that contains the methods to execute the connection
        ExecutableActions exec = new ExecutableActions();
        // Show feedback to the user.
        JOptionPane.showMessageDialog(null, "Se van a leer los datos del fichero, " +
                "cierre la ventana para continuar. Esta operacion puede tardar unos segundos, por favor, espere");
        // Execute the connection, and upload the sql query to the database.
        exec.uploadFile();
        // Collect the data of the database and store it in a map.
        Application.collectData();
        // create the dynamic buttons. (one for each table)
        tables = ExecutableActions.createDynamicButtons(sqlExecs.Connection.getConnection());
        // Show the buttons
        showButtons();
    }

    /**
     * This method checks if the drag and drop have stored any file
     * also verify that the extension is a .sql
     *
     * @param event this is the action event execution.
     */

    public void onDragOver(DragEvent event) {
        // Get the dragboard from the event
        Dragboard dragboard = event.getDragboard();
        // Check if the dragboard has files and its extension is .sql
        if (dragboard.hasFiles() && dragboard.getFiles().get(0).getName().toLowerCase().endsWith(".sql")) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        // Close the event
        event.consume();
    }

    /**
     * This method executes the drag and drop of the file,
     * also it gives feedback to the user.
     * @param event
     */

    public void onDragDropped(DragEvent event) {
        // Get the dragboard from the event
        Dragboard dragboard = event.getDragboard();
        // Set the success of the droped file to false
        boolean success = false;
        // Check if the Drag-Board has files
        if (dragboard.hasFiles()) {
            // Assign the file to the variable drag and reset the interface of the drag and drop menu.
            ExecutableActions.setDrag(dragboard.getFiles().get(0));
            before.setVisible(false);
            after.setVisible(true);
            loadFile.setDisable(false);
            unloadFile.setDisable(false);
            success = true;
        }
        // Set the new state of the droped file
        event.setDropCompleted(success);
        // Close the event
        event.consume();
    }

    /**
     * This method enables the button that execute the .sql query
     * also this method changes the interface of the drag and drop menu
     * to give feedback to the user
     */

    public void unloadFileToBBD() {
        // Set the drag to null
        ExecutableActions.setDrag(null);
        // Show feedback to the user
        before.setVisible(true);
        after.setVisible(false);
        // Disable the button that execute the .sql query
        loadFile.setDisable(true);
        unloadFile.setDisable(true);
    }

    /**
     * This method creates dynamic buttons depending on the
     * tables of the database.
     */

    @FXML
    private void showButtons() {
        // Get the number of tables
        int numButtons = tables.size();
        // Create the buttons depending on the number of tables
        for (int i = 0; i < numButtons; i++) {
            // Create the button
            Button button = new Button("btnTabla " + (i + 1));
            buttonStyle(button, i);
            int finalI = i;
            // This lambda creates all the buttons, and assign them an action
            // depending on the table that they are going to show.
            // Also, it hides the home panel and shows the panel that contains the table.
            button.setOnAction(event -> {
                home.setVisible(false);
                showTables.setVisible(true);
                showTables.setId(tables.get(finalI));
                inserting.getChildren().clear();
                selectTables(button.getText());
            });
            // Add the button to the left panel of the interface.
            left.getChildren().add(button);
        }
    }

    /**
     * This method resets the dynamic table container, retrive all the table data
     * and creates the table.
     *
     * @param tableName this is the name of the table that is going to be shown.
     */

    public void selectTables(String tableName) {
        try {

            // Delete existing columns in the TableView
            tableviewShowTables.getColumns().clear();

            // Delete existing rows in the TableView
            tableviewShowTables.getItems().clear();

            // Create a statement to execute queries
            Statement statement = sqlExecs.Connection.getConnection().createStatement();

            // Execute query to get table data
            String sqlQuery = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Get table metadata
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Create columns on in the Tableview
            for (int i = 1; i <= columnCount; i++) {
                int columnIndex = i;
                // Create a column in the TableView with the name retrieved from metadata
                TableColumn<Object[], Object> column = new TableColumn<>(resultSet.getMetaData().getColumnName(i));

                // Setting cell value using a lambda expression
                column.setCellValueFactory(cellData -> {
                    Object[] row = cellData.getValue();
                    return (row != null && columnIndex - 1 < row.length) ?
                            new SimpleObjectProperty<>(row[columnIndex - 1]) :
                            new SimpleObjectProperty<>(null);
                });

                // Add column to the TableView
                tableviewShowTables.getColumns().add(column);
            }

            // Add data rows to the TableView
            while (resultSet.next()) {
                // Create an array to store data of a row
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    // Obtain each column value from current row and add it to array
                    row[i - 1] = resultSet.getString(i);
                }
                // Add row to the TableView
                tableviewShowTables.getItems().add(row);
            }
        } catch (Exception e) {
            System.out.println("Algo salió mal");
        }
    }

    /**
     * This method sets the style of the dynamic buttons
     *
     * @param button this is the button that is going to be styled
     * @param i      this is the index of the button
     */

    public void buttonStyle(Button button, int i) {
        button.setLayoutX(0);
        button.setLayoutY((i + 1) * 80);
        button.setText(tables.get(i));
        button.setPrefHeight(80);
        button.setPrefWidth(221.0);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: #bd427d; -fx-border-width: 0px 0px 2px 0px;");
        button.setFont(Font.font("Ubuntu Bold"));

    }

    /**
     * This method is used to go back to the main menu of the application
     */

    public void comeBack() {
        home.setVisible(true);
        showTables.setVisible(false);
    }

    /**
     * This method is used to go back to the login menu
     */

    public void LogOut() {
        Stage logOut = (Stage) btnLogOut.getScene().getWindow();
        logOut.close();
        Loggin log = new Loggin();
        log.setLogingStage(btnLogOut);
    }

    /**
     * This method is used to execute the action on the insert button,
     * after all the texfields are filled successfully
     */

    public void insertData() {

        // These booleans statement are used to know which button is pressed,
        // so we can later execute the correct query
        insertBolean = true;
        deleteBolean = false;
        updateBolean = false;

        inserting.setVisible(true);
        inserting.getChildren().clear();
        try {
            // Create a statement to execute queries
            ArrayList<TextField> textFieldOperations = new ArrayList<>();
            Statement statement = sqlExecs.Connection.getConnection().createStatement();
            String tableName = showTables.getId();
            // Execute query to get table data and metadata.
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();
            // using the metadata we can get the number of columns of the table and
            // create the labels, textfields and groups that are going to be shown dynamically
            for (int i = 1; i <= metaData.getColumnCount(); i++) {

                Label label = new Label();
                labelStyle(label, metaData.getColumnName(i));

                TextField textField = new TextField();
                textfieldStyle(textField, metaData.getColumnName(i) + ":" + tableName, i);
                textFieldOperations.add(textField);

                Group group = new Group();
                groupStyle(label, textField, i, group);

                inserting.getChildren().add(group);
            }
            // Clean the arraylist that contains the textfields
            Application.textFields.clear();
            Application.execListener(textFieldOperations);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha perdido la conexión a la base de datos");
        }

    }

    /**
     * This method is used to execute the action on the delete button,
     * this action have no need to get all the texfields fulfilled
     */

    public void deleteData() {

        // These booleans statement are used to know which button is pressed,
        // so we can later execute the correct query
        insertBolean = false;
        deleteBolean = true;
        updateBolean = false;

        inserting.setVisible(true);
        inserting.getChildren().clear();
        btnColumnSubmission.setDisable(false);

        try {
            // Create a statement to execute queries
            ArrayList<TextField> textFieldOperations = new ArrayList<>();
            Statement statement = sqlExecs.Connection.getConnection().createStatement();
            String tableName = showTables.getId();
            // Execute query to get table data and metadata.
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();
            // using the metadata we can get the number of columns of the table and
            // create the labels, textfields and groups that are going to be shown dynamically
            for (int i = 1; i <= metaData.getColumnCount(); i++) {

                Label label = new Label();
                labelStyle(label, metaData.getColumnName(i));

                TextField textField = new TextField();
                textfieldStyle(textField, metaData.getColumnName(i) + ":" + tableName, i);
                textFieldOperations.add(textField);

                Group group = new Group();
                groupStyle(label, textField, i, group);

                inserting.getChildren().add(group);
            }
            // Clean the arraylist that contains the textfields
            Application.textFields.clear();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha perdido la conexión a la base de datos");
        }

    }

    /**
     * this method is used to execute the action on the update button,
     * this action have no need to get all the texfields fulfilled,
     * in this case we use a choicebox to select the column that we want to update
     * @param actionEvent event
     * @throws SQLException exception
     */

    public void updateData(ActionEvent actionEvent) throws SQLException {

        // These booleans statement are used to know which button is pressed,
        // so we can later execute the correct query
        insertBolean = false;
        deleteBolean = false;
        updateBolean = true;
        // Clean the arraylist that contains the textfields
        Application.textFields.clear();
        // Create a statement to execute queries
        Statement statement = sqlExecs.Connection.getConnection().createStatement();
        String tableName = showTables.getId();
        //Execute query to get table data and metadata.
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = resultSet.getMetaData();
        // using the metadata we can get the number of columns of the table and
        // create the labels, textfields and groups that are going to be shown dynamically
        inserting.setVisible(true);
        inserting.getChildren().clear();
        btnColumnSubmission.setDisable(false);

        for (int i = 0; i < 2; i++) {

            //the choicebox is created and filled with the name of the columns
            ChoiceBox choiceBox = new ChoiceBox();
            ArrayList<String> tempColum = new ArrayList<>();
            for (int j = 1; j <= metaData.getColumnCount(); j++) {
                System.out.println(metaData.getColumnName(j));
                tempColum.add(metaData.getColumnName(j));
            }
            choiceBox.getItems().addAll(tempColum);
            System.out.println(choiceBox.getItems());

            //adding the event to the choicebox
            //to change the label and the prompt text of the textfield
            //depending on the column selected.
            int finalI = i;
            choiceBox.setOnAction(event -> {

                Group grp = (Group) choiceBox.getParent();
                for (javafx.scene.Node nodo : grp.getChildren()) {
                    if (nodo instanceof Label) {
                        ((Label) nodo).setText((String) choiceBox.getValue());
                    } else if (nodo instanceof TextField) {
                        ((TextField) nodo).setPromptText((String) choiceBox.getValue());
                        nodo.setId(choiceBox.getValue() + ":" + tableName + ":" + finalI);
                    }
                }

            });
            //the label and the textfield are created
            // and added to the group
            Text tittle = new Text();
            tittle.setText((i == 0) ? "Introducir dato (SET)" : "Tupla a modificar (WHERE)");
            tittle.setFont(Font.font("Ubuntu", 17));

            Label label = new Label();
            label.setLayoutY(65);

            TextField textField = new TextField();
            textField.setLayoutY(90);

            choiceBox.setLayoutY(25);

            Group group = new Group();
            group.getChildren().addAll(tittle, choiceBox, label, textField);
            group.setId("group:" + i);
            group.setLayoutY(200 * i);

            inserting.getChildren().add(group);
        }

    }

    /**
     * this method gives style to the labels and textfields
     * that are created dynamically
     * @param lb label
     * @param tf textfield
     * @param i index
     * @param gr group
     */

    private void groupStyle(Label lb, TextField tf, int i, Group gr) {
        gr.getChildren().add(lb);
        gr.getChildren().add(tf);
        gr.setLayoutX(40);
        gr.setLayoutY(80 * (i - 1));
    }

    /**
     * this method is used to give style to the labels
     * @param label label
     * @param columName name of the column, so we can set it as the label text
     */

    private void labelStyle(Label label, String columName) {
        label.setLayoutY(7);
        label.setFont(Font.font("Ubuntu", 17));
        label.setText(columName);
    }

    /**
     * this method is used to give style to the textfields
     * @param textField textfield
     * @param id id of the textfield, this id is created with special needs
     *           so we can later extract the name of the column and the name of the table
     * @param i index of the textfield
     */

    private void textfieldStyle(TextField textField, String id, int i) {
        textField.setLayoutY(25);
        textField.setId(id + ":" + i);
        textField.setPromptText(id.substring(0, id.indexOf(":")));
        textField.setFont(Font.font("Ubuntu", 17));
    }

    /**
     * This method is in charge of defining what action is going to be executed
     * depending on the button that is pressed (insert, delete or update).
     */

    public void EnvioColumnas() {
        //we get the final data from the textfields
        Map<String, String> finalUserData = recogerDatos(extraerTextfields());
        //instance of the CRUD class
        CRUD crud = new CRUD();
        //now using the booleans we can execute the correct query
        //depending on the button that was previously pressed.
        if (insertBolean) {
            crud.insert(finalUserData, tabla, this);
        } else if (deleteBolean) {
            crud.delete(finalUserData, tabla, this);
        } else if (updateBolean) {
            crud.update(finalUserData, tabla, this);
        }
    }

    /**
     * This method is used to extract all the textfields that are created dynamically
     * @return an arraylist of all the textfields present in the inserting pane.
     */

    public ArrayList<TextField> extraerTextfields() {
        //we create an arraylist of textfields
        ArrayList<TextField> textFields = new ArrayList<>();
        //we iterate through the inserting pane to get all the textfields
        for (int i = 0; i < inserting.getChildren().size(); i++) {
            //we check if the node is an instace of a group,
            // because the textfields are inside groups
            if (inserting.getChildren().get(i) instanceof Group) {
                //we iterate through the group to get the textfields
                for (javafx.scene.Node nodo : ((Group) inserting.getChildren().get(i)).getChildren()) {
                    if (nodo instanceof TextField) {
                        //we add the textfields to the arraylist
                        textFields.add((TextField) nodo);
                    }

                }

            }
        }

        return textFields;
    }

    /**
     * this is a generic method that is used to extract the data from the textfields
     * and put it in a map, so we can later use it in the CRUD class.
     * @param textFields arraylist of textfields.
     * @return a map with the data of the textfields
     */
    public Map<String, String> recogerDatos(ArrayList<TextField> textFields) {
        //we create a linkedhashmap so we can keep the order of the data
        Map<String, String> datosFinales = new LinkedHashMap<>();
        // we iterate through the textfields array previusly created
        // to get the data and put it in the map
        for (TextField textData :
                textFields) {
            // we get the name of the column and the name of the table
            tabla = textData.getId().substring(textData.getId().indexOf(":") + 1);
            tabla = tabla.substring(0, tabla.indexOf(":"));
            // we put the data in the map with
            // the name of the column as the key and the data as the value

            datosFinales.put(textData.getId().substring(0, textData.getId().indexOf(":")), textData.getText());

        }

        return datosFinales;
    }


}
