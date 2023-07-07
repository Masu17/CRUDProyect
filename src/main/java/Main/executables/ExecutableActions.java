package Main.executables;

import Main.interfaces.Application;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */

public class ExecutableActions {

    private static File drag;

    public static String connectionWay;

    private Map<String, ArrayList<Object>> columnConditions = new HashMap<>();

    public static void setConnectionWay(String connectionWay) {
        ExecutableActions.connectionWay = connectionWay;
    }

    public static void setDrag(File drag) {
        ExecutableActions.drag = drag;
    }

    public Map<String, ArrayList<Object>> getColumnConditions() {
        return columnConditions;
    }

    public void uploadFile() {
        try (Scanner output = new Scanner(drag)) {

            JOptionPane.showMessageDialog(null, "Iniciando la carga de archivos, por favor, " +
                    "cierre esta pestaña y espere hasta que se muestre un nuevo mensaje. " +
                    "Esta acción puede llevar unos segundos");

            String query = "";

            while (output.hasNextLine()) {

                Statement statement = sqlExecs.Connection.getConnection().createStatement();

                query += output.nextLine();

                if (query.contains(";")) {

                    //This sentence is used to avoid the creation of the database in the cloud when has been
                    //already created by default
                    if (connectionWay.equals("Cloud")) {
                        if (!query.contains("CREATE DATABASE") && !query.contains("USE")) {
                            Application.reloadText(query);
                            statement.executeUpdate(query);
                            query = "";
                        } else if (query.contains("CREATE DATABASE") || query.contains("USE") || query.contains("SET")) {
                            Application.reloadText(query);
                            query = "";
                        }
                    } else {
                        Application.reloadText(query);
                        statement.executeUpdate(query);
                        query = "";
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Carga de datos finalizada con éxito");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[i] Error en la subida del archivo");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "[i] No encuentra el archivo");
        }
    }

    public static Map<Integer, String> createDynamicButtons(java.sql.Connection connection) {
        Map<Integer, String> buttons = new HashMap<>();
        try {

            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SHOW TABLES;");

            for (int i = 0; result.next(); i++) {
                buttons.put(i, result.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buttons;
    }

    /**
     * Retrieve tables y generate a map with the name of the table and the columns
     *
     * @param connectionDB connection to the database
     */

    public void showTables(java.sql.Connection connectionDB) {

        try {

            Statement st = connectionDB.createStatement();
            ResultSet resultSetTableNames = st.executeQuery("SHOW TABLES");

            while (resultSetTableNames.next()) {
                String tableName = resultSetTableNames.getString(1);
                selectColumnsTables(tableName, connectionDB);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void selectColumnsTables(String table, java.sql.Connection connectionDB) {

        try {
            String columName;

            Statement st = connectionDB.createStatement();
            ResultSet resultSetTableData = st.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData metaDataTable = resultSetTableData.getMetaData();


            for (int i = 1; i <= metaDataTable.getColumnCount(); i++) {

                resultSetTableData = st.executeQuery("SELECT * FROM " + table);
                columName = metaDataTable.getColumnName(i) + ":" + table;
                ArrayList<String> columnValues = new ArrayList<>();
                ArrayList<Object> values = new ArrayList<>();
                System.out.println(metaDataTable.getColumnName(i));

                while (resultSetTableData.next()) {
                    System.out.println(resultSetTableData.getString(i));
                    columnValues.add(resultSetTableData.getString(i));
                }

                testData(values, columnValues);

                columnConditions.put(columName, values);
            }

        }catch (IndexOutOfBoundsException e){
            System.out.println("Array vacio, no hay insersiones validas.");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Las tablas introducidas están vacías");
        }
    }

    private ArrayList<Object> testData(ArrayList<Object> values, ArrayList<String> columnValues) throws ArrayIndexOutOfBoundsException {

        boolean haveNumber = false;
        boolean haveText = false;
        boolean haveSpecial = false;
        boolean variableLength = false;
        int length = columnValues.get(1).length();

        //regex:
        String havenum = "[0-9]";
        String haveTxt = "[a-zA-Z]";
        String haveSpe = "\\W";
        Pattern ptNum = Pattern.compile(havenum);
        Pattern ptLet = Pattern.compile(haveTxt);
        Pattern ptPut = Pattern.compile(haveSpe);

        for (int i = 0; i < columnValues.size(); i++) {

            Matcher mtNum = ptNum.matcher(columnValues.get(i));
            Matcher mtLet = ptLet.matcher(columnValues.get(i));
            Matcher mtPut = ptPut.matcher(columnValues.get(i));

            haveNumber = mtNum.find();
            haveText = mtLet.find();
            haveSpecial = mtPut.find();

            if (columnValues.get(i).length() > length) {
                variableLength = true;
                length = columnValues.get(i).length();
            }

        }

        values.add(haveNumber);
        values.add(haveText);
        values.add(haveSpecial);
        values.add(variableLength);
        values.add(length);
        return values;
    }
}
