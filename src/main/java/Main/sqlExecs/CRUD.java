package Main.sqlExecs;

import Main.controllers.AplicativoController;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CRUD {

    private Map<String, String> nameType = new HashMap<>();

    private void sacarMetadatos(String tabla) {
        try {

            ResultSet resultSet = sqlExecs.Connection.getConnection().createStatement().executeQuery(
                    "SELECT * FROM " + tabla + ";");

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

    public void insert(Map<String, String> datosInsert, String tabla, AplicativoController aplicativoController) {

        try {

            sacarMetadatos(tabla);

            StringBuilder consulta = new StringBuilder("INSERT INTO " + tabla + " VALUES (");

            for (int i = 0; i < datosInsert.size(); i++) {
                consulta.append((i != datosInsert.size() - 1) ? "?, " : "?)");
            }

            PreparedStatement declaracion = sqlExecs.Connection.getConnection().prepareStatement(consulta.toString());

            int i = 1;
            for (Map.Entry<String, String> entrada : datosInsert.entrySet()) {

                if (nameType.get(entrada.getKey()).equals("INT")) {
                    declaracion.setInt(i, Integer.parseInt(entrada.getValue()));
                } else if (nameType.get(entrada.getKey()).equals("VARCHAR") || nameType.get(entrada.getKey()).equals("CHAR")) {
                    declaracion.setString(i, entrada.getValue());
                } else if (nameType.get(entrada.getKey()).equals("DECIMAL")) {
                    declaracion.setDouble(i, Double.parseDouble(entrada.getValue()));
                }
                i++;
            }

            // Ejecutar la consulta
            declaracion.executeUpdate();
            //Cerramos declaracion
            declaracion.close();

            aplicativoController.selectTables(tabla);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datos introducidos no validos, porfavor.");
        }

        for (Map.Entry<String, String> entrada : datosInsert.entrySet()) {

            System.out.println("Nombre columna: " + entrada.getKey()); // Nombre columna
            System.out.println("Valor introducido  por el usuario: " + entrada.getValue()); // Valor introducido por usuario
            System.out.println("Tipo de dato: " + nameType.get(entrada.getKey()));

        }
    }

    public void delete(Map<String, String> datosInsert, String tabla, AplicativoController aplicativoController) {

        try {
            sacarMetadatos(tabla);

            StringBuilder sql = new StringBuilder("DELETE FROM " + tabla + " WHERE ");

            int i = 0;
            for (Map.Entry<String, String> entrada : datosInsert.entrySet()) {
                if (entrada.getValue().length() != 0) {
                    if (i != 0) {
                        sql.append(" AND ").append(entrada.getKey()).append(" = ?");
                    } else {
                        sql.append(entrada.getKey()).append(" = ?");
                    }
                    i++;
                }
            }

            i = 1;

            PreparedStatement declaracion = sqlExecs.Connection.getConnection().prepareStatement(sql.toString());

            for (Map.Entry<String, String> entrada : datosInsert.entrySet()) {
                if (entrada.getValue().length() != 0) {
                    if (nameType.get(entrada.getKey()).equals("INT")) {
                        declaracion.setInt(i, Integer.parseInt(entrada.getValue()));
                    } else if (nameType.get(entrada.getKey()).equals("VARCHAR") || nameType.get(entrada.getKey()).equals("CHAR")) {
                        declaracion.setString(i, entrada.getValue());
                    } else if (nameType.get(entrada.getKey()).equals("DECIMAL")) {
                        declaracion.setDouble(i, Double.parseDouble(entrada.getValue()));
                    }
                }
            }

            // Ejecutar la consulta
            declaracion.executeUpdate();
            //Cerramos declaracion
            declaracion.close();

            aplicativoController.selectTables(tabla);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datos introducidos no validos, porfavor.");

            e.printStackTrace();
        }

    }

    public void update(Map<String, String> datosUpdate, String tabla, AplicativoController aplicativoController) {

        try {

//             columna1 --> condicion
//             columnaCondicion --> condicion
//
//            [columna1, condicion, columnaCondion, condicion];

            sacarMetadatos(tabla);

            StringBuilder sql = new StringBuilder(
                    "UPDATE " + tabla + " SET "
            );

            int i = 0;
            for (Map.Entry<String, String> entrada : datosUpdate.entrySet()) {
                if (entrada.getValue().length() != 0) {
                    if (i != 0) {
                        sql.append(" WHERE ").append(entrada.getKey()).append(" = ?");
                    } else {
                        sql.append(entrada.getKey()).append(" = ?");
                    }
                    i++;
                }
            }


            i = 1;

            PreparedStatement declaracion = sqlExecs.Connection.getConnection().prepareStatement(sql.toString());

            for (Map.Entry<String, String> entrada : datosUpdate.entrySet()) {
                if (entrada.getValue().length() != 0) {
                    if (nameType.get(entrada.getKey()).equals("INT")) {
                        declaracion.setInt(i, Integer.parseInt(entrada.getValue()));
                    } else if (nameType.get(entrada.getKey()).equals("VARCHAR") || nameType.get(entrada.getKey()).equals("CHAR")) {
                        declaracion.setString(i, entrada.getValue());
                    } else if (nameType.get(entrada.getKey()).equals("DECIMAL")) {
                        declaracion.setDouble(i, Double.parseDouble(entrada.getValue()));
                    }
                }

                i++;
            }

            //Ejecutar la consulta
            declaracion.executeUpdate();
            //Cerramos declaracion
            declaracion.close();

            aplicativoController.selectTables(tabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos introducidos no validos, porfavor.");

            e.printStackTrace();
        }

    }

}


