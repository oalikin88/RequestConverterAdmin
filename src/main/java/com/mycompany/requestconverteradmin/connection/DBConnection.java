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
import java.util.Map;

/**
 *
 * @author 041AlikinOS
 */
public class DBConnection {
    
    private Settings settings;

    public Connection getConnection() throws IOException {
        settings = new Settings(); 
        try {
            Map<String, String> prepareSettings = settings.prepareSettings();
            settings.getSettings(prepareSettings);
            return DriverManager.getConnection("jdbc:mysql://" + settings.getUrl() + ":" + settings.getPort() + "/" + settings.getDbName()
                    + "?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true", settings.getUsername(), settings.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
           
        
       
    }
}
