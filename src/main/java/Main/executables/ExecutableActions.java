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

    private Map<String, ArrayList<Object>> condicionesColumnas = new HashMap<>();

    public static void setConnectionWay(String connectionWay) {
        ExecutableActions.connectionWay = connectionWay;
    }

    public static void setDrag(File drag) {
        ExecutableActions.drag = drag;
    }

    public Map<String, ArrayList<Object>> getCondicionesColumnas() {
        return condicionesColumnas;
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

    //recuperar tablas y generar mapa:

    public void showTables(java.sql.Connection connectionDB) {

        try {

            Statement st = connectionDB.createStatement();
            ResultSet resultSetTableNames = st.executeQuery("SHOW TABLES");

            while (resultSetTableNames.next()) {
                String tableName = resultSetTableNames.getString(1);
                seleccionarColumnasTablas(tableName, connectionDB);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void seleccionarColumnasTablas(String tabla, java.sql.Connection connectionDB) {

        try {
            String columname;

            Statement st = connectionDB.createStatement();
            ResultSet resultSetTableData = st.executeQuery("SELECT * FROM " + tabla);
            ResultSetMetaData metaDataTable = resultSetTableData.getMetaData();


            for (int i = 1; i <= metaDataTable.getColumnCount(); i++) {

                resultSetTableData = st.executeQuery("SELECT * FROM " + tabla);
                columname = metaDataTable.getColumnName(i) + ":" + tabla;
                ArrayList<String> valoresColumna = new ArrayList<>();
                ArrayList<Object> values = new ArrayList<>();
                System.out.println(metaDataTable.getColumnName(i));

                while (resultSetTableData.next()) {
                    System.out.println(resultSetTableData.getString(i));
                    valoresColumna.add(resultSetTableData.getString(i));
                }

                testData(values, valoresColumna);

                condicionesColumnas.put(columname, values);
            }

        }catch (IndexOutOfBoundsException e){
            System.out.println("Array vacio, no hay insersiones validas.");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Las tablas introducidas están vacías");
        }
    }

    private ArrayList<Object> testData(ArrayList<Object> values, ArrayList<String> valoresColumna) throws ArrayIndexOutOfBoundsException {

        boolean haveNumero = false;
        boolean haveLetra = false;
        boolean havePunt = false;
        boolean longVariable = false;
        int length = valoresColumna.get(1).length();

        //regex:
        String havenum = "[0-9]";
        String haveLet = "[a-zA-Z]";
        String havePut = "\\W";
        Pattern ptNum = Pattern.compile(havenum);
        Pattern ptLet = Pattern.compile(haveLet);
        Pattern ptPut = Pattern.compile(havePut);

        for (int i = 0; i < valoresColumna.size(); i++) {

            Matcher mtNum = ptNum.matcher(valoresColumna.get(i));
            Matcher mtLet = ptLet.matcher(valoresColumna.get(i));
            Matcher mtPut = ptPut.matcher(valoresColumna.get(i));

            haveNumero = mtNum.find();
            haveLetra = mtLet.find();
            havePunt = mtPut.find();

            if (valoresColumna.get(i).length() > length) {
                longVariable = true;
                length = valoresColumna.get(i).length();
            }

        }

        values.add(haveNumero);
        values.add(haveLetra);
        values.add(havePunt);
        values.add(longVariable);
        values.add(length);
        return values;
    }
}
