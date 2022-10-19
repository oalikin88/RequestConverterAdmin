/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import com.mycompany.requestconverteradmin.connection.DBConnection;
import com.mycompany.requestconverteradmin.exceptions.DaoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class ClientDAO {
    
    private static final String FIND_ALL_SQL = "SELECT subject, opfr, upfr, name FROM spr";
    
    public List<Client> findAll() {
        try (var connection = DBConnection.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
             var resultSet = preparedStatement.executeQuery();
             List<Client> clients = new ArrayList<>();
             while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }
    private static final ClientDAO INSTANCE = new ClientDAO();
    public static ClientDAO getInstance() {
        return INSTANCE;
    }
    private Client buildClient(ResultSet resultSet) throws SQLException {
        
      Client client = new Client();
      client.setSubject(resultSet.getString("subject"));
      client.setOpfr(resultSet.getString("opfr"));
      client.setUpfr(resultSet.getString("upfr"));
      client.setName(resultSet.getString("name"));
      return client;
    }
    
}
