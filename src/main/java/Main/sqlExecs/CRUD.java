package Main.sqlExecs;

import Main.controllers.applicationController;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class executes all CRUD methods
 */
public class CRUD {

    private Map<String, String> nameType = new HashMap<>();

    /**
     * This method retrieve tables types from metadata contained in a selected table
     *
     * @param table Selected table
     */
    private void extractMetadata(String table) {
        try {

            ResultSet resultSet = sqlExecs.Connection.getConnection().createStatement().executeQuery(
                    "SELECT * FROM " + table + ";");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

                String columnName = resultSetMetaData.getColumnName(i);
                String columnTypeName = resultSetMetaData.getColumnTypeName(i);
                nameType.put(columnName, columnTypeName);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert data into a selected table
     *
     * @param insertData            data contained on input textfields
     * @param table                 Selected table
     * @param applicationController instance of applicationController class
     */
    public void insert(Map<String, String> insertData, String table, applicationController applicationController) {

        try {

            extractMetadata(table);

            StringBuilder query = new StringBuilder("INSERT INTO " + table + " VALUES (");

            for (int i = 0; i < insertData.size(); i++) {
                query.append((i != insertData.size() - 1) ? "?, " : "?)");
            }

            PreparedStatement statement = sqlExecs.Connection.getConnection().prepareStatement(query.toString());

            int i = 1;
            for (Map.Entry<String, String> input : insertData.entrySet()) {

                if (nameType.get(input.getKey()).equals("INT")) {
                    statement.setInt(i, Integer.parseInt(input.getValue()));
                } else if (nameType.get(input.getKey()).equals("VARCHAR") || nameType.get(input.getKey()).equals("CHAR")) {
                    statement.setString(i, input.getValue());
                } else if (nameType.get(input.getKey()).equals("DECIMAL")) {
                    statement.setDouble(i, Double.parseDouble(input.getValue()));
                }
                i++;
            }

            // Execute query
            statement.executeUpdate();
            // Close statement
            statement.close();

            applicationController.selectTables(table);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datos introducidos no validos, porfavor.");
        }

        for (Map.Entry<String, String> input : insertData.entrySet()) {

            System.out.println("Nombre columna: " + input.getKey()); // Column name
            System.out.println("Valor introducido  por el usuario: " + input.getValue()); // Value entered by user
            System.out.println("Tipo de dato: " + nameType.get(input.getKey()));

        }
    }

    /**
     * Delete data into a selected table
     *
     * @param insertData            data contained on input textfields
     * @param table                 Selected table
     * @param applicationController instance of applicationController class
     */
    public void delete(Map<String, String> insertData, String table, applicationController applicationController) {

        try {
            extractMetadata(table);

            StringBuilder sql = new StringBuilder("DELETE FROM " + table + " WHERE ");

            int i = 0;
            for (Map.Entry<String, String> input : insertData.entrySet()) {
                if (input.getValue().length() != 0) {
                    if (i != 0) {
                        sql.append(" AND ").append(input.getKey()).append(" = ?");
                    } else {
                        sql.append(input.getKey()).append(" = ?");
                    }
                    i++;
                }
            }

            i = 1;

            PreparedStatement statement = sqlExecs.Connection.getConnection().prepareStatement(sql.toString());

            for (Map.Entry<String, String> input : insertData.entrySet()) {
                if (input.getValue().length() != 0) {
                    if (nameType.get(input.getKey()).equals("INT")) {
                        statement.setInt(i, Integer.parseInt(input.getValue()));
                    } else if (nameType.get(input.getKey()).equals("VARCHAR") || nameType.get(input.getKey()).equals("CHAR")) {
                        statement.setString(i, input.getValue());
                    } else if (nameType.get(input.getKey()).equals("DECIMAL")) {
                        statement.setDouble(i, Double.parseDouble(input.getValue()));
                    }
                }
            }

            // Execute query
            statement.executeUpdate();
            //Close statement
            statement.close();

            applicationController.selectTables(table);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datos introducidos no validos, porfavor.");

            e.printStackTrace();
        }

    }


    /**
     * Update data from a selected table
     *
     * @param insertData data contained on input textfields
     * @param table Selected table
     * @param applicationController instance of applicationController class
     */
    public void update(Map<String, String> insertData, String table, applicationController applicationController) {

        try {

            extractMetadata(table);

            StringBuilder sql = new StringBuilder(
                    "UPDATE " + table + " SET "
            );

            int i = 0;
            for (Map.Entry<String, String> input : insertData.entrySet()) {
                if (input.getValue().length() != 0) {
                    if (i != 0) {
                        sql.append(" WHERE ").append(input.getKey()).append(" = ?");
                    } else {
                        sql.append(input.getKey()).append(" = ?");
                    }
                    i++;
                }
            }


            i = 1;

            PreparedStatement statement = sqlExecs.Connection.getConnection().prepareStatement(sql.toString());

            for (Map.Entry<String, String> input : insertData.entrySet()) {
                if (input.getValue().length() != 0) {
                    if (nameType.get(input.getKey()).equals("INT")) {
                        statement.setInt(i, Integer.parseInt(input.getValue()));
                    } else if (nameType.get(input.getKey()).equals("VARCHAR") || nameType.get(input.getKey()).equals("CHAR")) {
                        statement.setString(i, input.getValue());
                    } else if (nameType.get(input.getKey()).equals("DECIMAL")) {
                        statement.setDouble(i, Double.parseDouble(input.getValue()));
                    }
                }

                i++;
            }

            // Execute query
            statement.executeUpdate();
            // Close statement
            statement.close();

            applicationController.selectTables(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos introducidos no validos, porfavor.");

            e.printStackTrace();
        }

    }

}


