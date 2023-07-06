package Main.controllers;


import login.interfaces.Login;
import Main.executables.ExecutableActions;
import Main.sqlExecs.CRUD;
import Main.interfaces.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class AplicativoController {
    public Button loadFile;
    public TextArea textarea;
    public Group antes;
    public Group despues;
    public Button unloadFile;
    public AnchorPane left;
    public TableView mostrarTablas;
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
    public ChoiceBox pruebaDomingo;

    private Map<Integer, String> tables;
    private String tabla;


    public void loadFileToBBD() {

        ExecutableActions exec = new ExecutableActions();
        JOptionPane.showMessageDialog(null, "Se van a leer los datos del fichero, " +
                "cierre la ventana para continuar. Esta operacion puede tardar unos segundos, por favor, espere");
        exec.uploadFile();
        Application.collectData();
        tables = ExecutableActions.mostrarTablas(sqlExecs.Connection.getConnection());
        showButtons();
    }


    public void onDragOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();

        // Verificar si la fuente del gesto de arrastre no es el mismo textArea y si el Dragboard contiene archivos
        if (dragboard.hasFiles() && dragboard.getFiles().get(0).getName().toLowerCase().endsWith(".sql")) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume(); //onDragOver Consumir el evento
    }

    public void onDragDropped(DragEvent event) {

        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        // Verificar si el Dragboard contiene archivos
        if (dragboard.hasFiles()) {
            // Asignar el primer archivo de la lista de archivos arrastrados a la variable drag
            ExecutableActions.setDrag(dragboard.getFiles().get(0));
            antes.setVisible(false);
            despues.setVisible(true);
            loadFile.setDisable(false);
            unloadFile.setDisable(false);
            success = true;
        }

        // Configurar el estado de finalización de la operación de soltar
        event.setDropCompleted(success);
        event.consume(); // Consumir el evento
    }

    public void unloadFileToBBD(ActionEvent actionEvent) {
        ExecutableActions.setDrag(null); // Restablecer la variable drag a null o a un valor inicial válido
        antes.setVisible(true); // Restablecer la visibilidad de los componentes antes y despues según corresponda
        despues.setVisible(false);
        loadFile.setDisable(true); // Deshabilitar el botón loadFile si es necesario
        unloadFile.setDisable(true);
    }


    @FXML
    private void showButtons() {

        int numButtons = tables.size(); // Número de botones a crear

        for (int i = 0; i < numButtons; i++) {
            Button button = new Button("btnTabla " + (i + 1));
            buttonStyle(button, i);
            int finalI = i;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    home.setVisible(false);
                    showTables.setVisible(true);
                    showTables.setId(tables.get(finalI));
                    inserting.getChildren().clear();
                    selectTables(button.getText());
                }
            });

            left.getChildren().add(button);
        }
    }

    public void selectTables(String tableName) {
        try {

            // Borrar las columnas existentes en el TableView
            mostrarTablas.getColumns().clear();

            // Borrar las filas existentes en el TableView
            mostrarTablas.getItems().clear();

            // Crear una declaración para ejecutar consultas SQL en la conexión establecida
            Statement statement = sqlExecs.Connection.getConnection().createStatement();

            // Ejecutar consulta SQL para obtener los datos de la tabla
            String sqlQuery = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Obtener metadatos de la tabla
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Crear columnas en el TableView
            for (int i = 1; i <= columnCount; i++) {
                int columnIndex = i;
                // Crear una columna en el TableView con el nombre obtenido de los metadatos
                TableColumn<Object[], Object> column = new TableColumn<>(resultSet.getMetaData().getColumnName(i));

                // Configurar el valor de la celda utilizando una expresión lambda
                column.setCellValueFactory(cellData -> {
                    Object[] row = cellData.getValue();
                    return (row != null && columnIndex - 1 < row.length) ?
                            new SimpleObjectProperty<>(row[columnIndex - 1]) :
                            new SimpleObjectProperty<>(null);
                });

                // Agregar la columna al TableView
                mostrarTablas.getColumns().add(column);
            }

            // Agregar filas de datos al TableView
            while (resultSet.next()) {
                // Crear un arreglo para almacenar los datos de una fila
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    // Obtener el valor de cada columna en la fila actual y agregarlo al arreglo
                    row[i - 1] = resultSet.getString(i);
                }
                // Agregar la fila al TableView
                mostrarTablas.getItems().add(row);
            }
        } catch (Exception e) {
            // Manejo de excepciones en caso de errores
            System.out.println("Algo salió mal");
        }
    }

    public void buttonStyle(Button button, int i) {
        button.setLayoutX(0);
        button.setLayoutY((i + 1) * 80);
        button.setText(tables.get(i));
        button.setPrefHeight(80);
        button.setPrefWidth(221.0);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: #bd427d; -fx-border-width: 0px 0px 2px 0px;");
        button.setFont(Font.font("Ubuntu Bold"));

    }

    public void comeBack() {
        home.setVisible(true);
        showTables.setVisible(false);
    }

    public void LogOut() {
        goToLogIn();
    }

    public void goToLogIn() {

        Stage logOut = (Stage) btnLogOut.getScene().getWindow();
        logOut.close();

        Login log = new Login();
        log.setLogingStage(btnLogOut);
    }


    public void insertData() {

        /*Definimos estas variables para luego poder dirigirnos al método del insert, delete o update
          Esto sirve en el método EnvioColumnas
         */
        insertBolean = true;
        deleteBolean = false;
        updateBolean = false;

        inserting.setVisible(true);
        inserting.getChildren().clear();
        try {
            ArrayList<TextField> textFieldOperations = new ArrayList<>();
            Statement statement = sqlExecs.Connection.getConnection().createStatement();
            String tableName = showTables.getId();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();

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

            Application.test.clear();
            Application.execListener(textFieldOperations);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha perdido la conexión a la base de datos");
        }

    }

    public void deleteData() {

        /*Definimos estas variables para luego poder dirigirnos al método del insert, delete o update
          Esto sirve en el método EnvioColumnas
         */
        insertBolean = false;
        deleteBolean = true;
        updateBolean = false;

        inserting.setVisible(true);
        inserting.getChildren().clear();
        btnColumnSubmission.setDisable(false);

        try {
            ArrayList<TextField> textFieldOperations = new ArrayList<>();
            Statement statement = sqlExecs.Connection.getConnection().createStatement();
            String tableName = showTables.getId();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();

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

            Application.test.clear();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se ha perdido la conexión a la base de datos");
        }

    }

    private void groupStyle(Label lb, TextField tf, int i, Group gr) {
        gr.getChildren().add(lb);
        gr.getChildren().add(tf);
        gr.setLayoutX(40);
        gr.setLayoutY(80 * (i - 1));
    }

    private void labelStyle(Label label, String columName) {
        label.setLayoutY(7);
        label.setFont(Font.font("Ubuntu", 17));
        label.setText(columName);
    }

    private void textfieldStyle(TextField textField, String id, int i) {
        textField.setLayoutY(25);
        textField.setId(id + ":" + i);
        textField.setPromptText(id.substring(0, id.indexOf(":")));
        textField.setFont(Font.font("Ubuntu", 17));
    }

    public void EnvioColumnas() {

        Map<String, String> datosFinales = recogerDatos(extraerTextfields());
        CRUD crud = new CRUD();

        if (insertBolean) {
            crud.insert(datosFinales, tabla, this);
        } else if (deleteBolean) {
            crud.delete(datosFinales, tabla, this);
        } else if (updateBolean) {
            crud.update(datosFinales, tabla, this);
        }
    }


    public ArrayList<TextField> extraerTextfields() {

        ArrayList<TextField> textFields = new ArrayList<>();

        for (int i = 0; i < inserting.getChildren().size(); i++) {

            if (inserting.getChildren().get(i) instanceof Group) {

                for (javafx.scene.Node nodo : ((Group) inserting.getChildren().get(i)).getChildren()) {
                    if (nodo instanceof TextField) {
                        textFields.add((TextField) nodo);
                    }

                }

            }
        }

        return textFields;
    }

    //esto es generico para cualquier consulta
    public Map<String, String> recogerDatos(ArrayList<TextField> textFields) {

        Map<String, String> datosFinales = new LinkedHashMap<>();

        for (TextField textData :
                textFields) {

            //columna:tabla:id
            //tabla:id
            //tabla

            tabla = textData.getId().substring(textData.getId().indexOf(":") + 1);
            tabla = tabla.substring(0, tabla.indexOf(":"));
            datosFinales.put(textData.getId().substring(0, textData.getId().indexOf(":")), textData.getText());

        }

        return datosFinales;
    }


    public void updateData(ActionEvent actionEvent) throws SQLException {
        /*Definimos estas variables para luego poder dirigirnos al método del insert, delete o update
          Esto sirve en el método EnvioColumnas
         */
        insertBolean = false;
        deleteBolean = false;
        updateBolean = true;

        Application.test.clear();


        Statement statement = sqlExecs.Connection.getConnection().createStatement();
        String tableName = showTables.getId();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = resultSet.getMetaData();

        inserting.setVisible(true);
        inserting.getChildren().clear();
        btnColumnSubmission.setDisable(false);

        for (int i = 0; i < 2; i++) {

            //se crea la choice box
            ChoiceBox choiceBox = new ChoiceBox();
            ArrayList<String> tempColum = new ArrayList<>();


            //se rellena con el nombre de las tablas
            for (int j = 1; j <= metaData.getColumnCount(); j++) {
                System.out.println(metaData.getColumnName(j));
                tempColum.add(metaData.getColumnName(j));
            }

            choiceBox.getItems().addAll(tempColum);
            System.out.println(choiceBox.getItems());

            //se le añade el evento onAction a la choiceBox
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

            Text titulo = new Text();
            titulo.setText((i == 0) ? "Introducir dato (SET)" : "Tupla a modificar (WHERE)");
            titulo.setFont(Font.font("Ubuntu", 17));

            Label label = new Label();
            label.setLayoutY(65);

            TextField textField = new TextField();
            textField.setLayoutY(90);

            choiceBox.setLayoutY(25);

            Group group = new Group();
            group.getChildren().addAll(titulo, choiceBox, label, textField);
            group.setId("group:" + i);
            group.setLayoutY(200 * i);

            inserting.getChildren().add(group);
        }

    }


}
