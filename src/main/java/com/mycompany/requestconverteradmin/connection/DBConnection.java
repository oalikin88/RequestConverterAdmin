/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.connection;
import com.mycompany.requestconverteradmin.data.Settings;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 041AlikinOS
 */
public class DBConnection {
    
    private static DBConnection instance = null;
    private static String URL;
    private static String PORT;
    private static String DB_NAME;
    private static String USER;
    private static String PASSWORD;

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        DBConnection.URL = URL;
    }

    public static String getDB_NAME() {
        return DB_NAME;
    }

    public static void setDB_NAME(String DB_NAME) {
        DBConnection.DB_NAME = DB_NAME;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        DBConnection.USER = USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        DBConnection.PASSWORD = PASSWORD;
    }

    public static String getPORT() {
        return PORT;
    }

    public static void setPORT(String PORT) {
        DBConnection.PORT = PORT;
    }
    
    
    
    
    
    
    
    public static DBConnection getInstance() throws SQLException {
        if(null == instance)
            instance = new DBConnection();

        return instance;
    }
    public Connection getConnection() throws IOException {
        Connection connection = null;
        try{
            Settings settings = new Settings();
            settings.getSettings(Settings.prepareSettings());
            URL = settings.getUrl();
            PORT = settings.getPort();
            DB_NAME = settings.getDbName();
            USER = settings.getUsername();
            PASSWORD = settings.getPassword();
            connection = DriverManager.getConnection("jdbc:mysql://" + URL + ":" + PORT + "/" + DB_NAME, USER, PASSWORD);
            System.out.println("Connection success!");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
