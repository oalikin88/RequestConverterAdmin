/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 041AlikinOS
 */
public class DBConnection {
    
    private static DBConnection instance = null;
    public static DBConnection getInstance() throws SQLException {
        if(null == instance)
            instance = new DBConnection();

        return instance;
    }
    public Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/requestconverter","root","qwerty");
            System.out.println("Connection success!");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
