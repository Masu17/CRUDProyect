package sqlExecs;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class creates the connection with a mysql database
 */
public class Connection {

    private static java.sql.Connection connection;

    /**
     *
     * Connection attribute getter
     *
     * @return connection
     */
    public static java.sql.Connection getConnection() {
        return connection;
    }

    /**
     *  This method executes all method that create a connection with the cloud database.
     */
    public static void execCloudConnection(){
        Map<String, String> mp = retriveCloudData();
        createConnection(mp, true);
    }

    /**
     * This method is used to create a connection by a non authomatical way.
     * Users must enter all credentials by themselves.
     *
     * @param mp Map of strings created by the values entered by the user
     */
    public static void execLocalConnection(Map<String, String> mp){
        createConnection(mp,false);
    }

    /**
     *
     * This method creates a Map with all necessary data to create a connection with cloud.
     *
     * @return Map of strings (type of date : value)
     */
    private static Map<String, String> retriveCloudData(){
        Map<String, String> mp = new HashMap<>();
        //Fill this with your cloud credentials.
        mp.put("host","b1razub1r7yjyqcjk9wl-mysql.services.clever-cloud.com");
        mp.put("port","3306");
        mp.put("database","b1razub1r7yjyqcjk9wl");
        mp.put("username","u6ltzdzhvtrb9ke3");
        mp.put("password","Ujujltq5hhZe3sFcLPwV");
        return mp;
    }

    /**
     *
     * @param mp Map of strings which was created in previous method.
     * @param connectionType True = Cloud, false = local connection
     */
    public static void createConnection(Map<String, String> mp, boolean connectionType){

        String url = "";

        if (connectionType){
            url = "jdbc:mysql://" + mp.get("host") + ":" + mp.get("port") + "/" + mp.get("database");
        }else {
            url = "jdbc:mysql://" + mp.get("host") + ":" + mp.get("port");
        }

        try {

            System.out.println("[i] Estableciendo conexion");                       //root              //123456
            connection = DriverManager.getConnection(url, mp.get("username"), mp.get("password"));

            if (connection == null){
                throw new SQLException();
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] conexion no valida");
            JOptionPane.showMessageDialog(null, "[ERROR] Conexion fallida, intentandolo de nuevo.");
        }
    }
}
