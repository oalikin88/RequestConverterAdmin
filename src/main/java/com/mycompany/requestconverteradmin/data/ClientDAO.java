/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import com.mycompany.requestconverteradmin.connection.DBConnection;
import com.mycompany.requestconverteradmin.exceptions.DaoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class ClientDAO {

    private static final String FIND_ALL_SQL = "SELECT id, subject, opfr, upfr, name FROM spr";
    private static final String UPDATE_RECORD = "UPDATE spr set subject = ?, opfr = ?, upfr = ? where id = ?";
    private static final String ADD_RECORD = "INSERT INTO spr (subject, opfr, upfr, name) VALUES(?,?,?,?)";
    private static final String DELETE_RECORD = "DELETE FROM spr WHERE id = ?";

    public List<Record> findAll() {
        try ( var connection = DBConnection.getInstance().getConnection();
                var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Record> clients = new ArrayList<>();
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

    private Record buildClient(ResultSet resultSet) throws SQLException {

        Record client = new Record();
        client.setId(resultSet.getInt("id"));
        client.setSubject(resultSet.getString("subject"));
        client.setOpfr(resultSet.getString("opfr"));
        client.setUpfr(resultSet.getString("upfr"));
        client.setName(resultSet.getString("name"));
        return client;
    }

    public void editRecord(int id, String subject, String opfr, String upfr) {
        try ( var connection = DBConnection.getInstance().getConnection();
                var preparedStatement = connection.prepareStatement(UPDATE_RECORD)) {
            preparedStatement.setString(1, subject);
            preparedStatement.setString(2, opfr);
            preparedStatement.setString(3, upfr);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }

    public void addRecord(String subject, String opfr, String upfr, String name) {
        try ( var connection = DBConnection.getInstance().getConnection();
                var preparedStatement = connection.prepareStatement(ADD_RECORD)) {

            preparedStatement.setString(1, subject);
            preparedStatement.setString(2, opfr);
            preparedStatement.setString(3, upfr);
            preparedStatement.setString(4, name);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }

    }

    public void deleteRecord(int id) {
        try ( var connection = DBConnection.getInstance().getConnection();
                var preparedStatement = connection.prepareStatement(DELETE_RECORD)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }

}
