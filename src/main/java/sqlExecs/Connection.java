package sqlExecs;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to create a connection with the database.
 * It has two methods to create a connection, one of them is authomatical and the other one is not.
 * The authomatical method is used to create a connection with the cloud database.
 * The non authomatical method is used to create a connection with a local database.
 *
 * @version 1.0
 * @since 2021-04-11
 */
public class Connection {

    private static java.sql.Connection connection;

    /**
     * Connection attribute getter
     *
     * @return connection
     */
    public static java.sql.Connection getConnection() {
        return connection;
    }

    /**
     * This method is used to create a connection by an authomatical way.
     * Users must select the database radiobutton.
     */
    public static void execCloudConnection(){
        Map<String, String> mp = retriveCloudData();
        createConnection(mp, true);
    }

    /**
     * This method is used to create a connection by a non authomatical way.
     * Users must enter all credentials by themselves.
     *
     * @param mp Map of strings which was created in previous method.
     */
    public static void execLocalConnection(Map<String, String> mp){
        createConnection(mp,false);
    }

    /**
     *
     * This method is used to create a map of strings with the credentials
     *
     * @return Map of strings (type of date : value)
     */
    private static Map<String, String> retriveCloudData(){
        Map<String, String> mp = new HashMap<>();
        //Fill this with your cloud credentials.
        mp.put("host","localhost"); // Your DDBB server hostName (localhost by default)
        mp.put("port","3306"); //BBDD port (3306 for Mysql)
        mp.put("database","DatabaseName"); //BBDD name (Ex. test)
        mp.put("username","root"); //Root user from database (Ex. root)
        mp.put("password","root"); //Database password (Ex. root)
        return mp;
    }

    /**
     * This method creates a connection with the database.
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
