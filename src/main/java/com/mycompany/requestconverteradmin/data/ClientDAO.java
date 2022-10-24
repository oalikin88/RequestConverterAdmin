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

    private static final String FIND_ALL_SPR = "SELECT id, subject, opfr, upfr, name FROM spr";
    private static final String FIND_ALL_REQUEST = "SELECT id, name, short_name FROM request";
    private static final String UPDATE_RECORD = "UPDATE spr set subject = ?, opfr = ?, upfr = ? where id = ?";
    private static final String ADD_RECORD = "INSERT INTO spr (subject, opfr, upfr, name) VALUES(?,?,?,?)";
    private static final String DELETE_RECORD = "DELETE FROM spr WHERE id = ?";
    private static final String ERASE_SPR = "DELETE FROM spr";
    private static final String ADD_REQUEST = "INSERT INTO request (name, short_name) VALUES(?,?)";
    private static final String UPDATE_REQUEST = "UPDATE request set name = ?, short_name = ? where id = ?";
    private static final String DELETE_REQUEST = "DELETE FROM request WHERE id = ?";

    
      public void addRequest(String name, String shortName) {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(ADD_REQUEST)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, shortName);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }

    }
      
       public void editRequest(int id, String name, String shortName) {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, shortName);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }
       
       
          public void deleteRequest(int id) {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(DELETE_REQUEST)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }
    

    public List<Record> findAllRecords() {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(FIND_ALL_SPR)) {
            var resultSet = preparedStatement.executeQuery();
            List<Record> records = new ArrayList<>();
            while (resultSet.next()) {
                records.add(buildRecord(resultSet));
            }
            return records;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }
    
      public List<Request> findAllRequests() {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(FIND_ALL_REQUEST)) {
            var resultSet = preparedStatement.executeQuery();
            List<Request> requests = new ArrayList<>();
            while (resultSet.next()) {
                requests.add(buildRequest(resultSet));
            }
            return requests;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }

    private Record buildRecord(ResultSet resultSet) throws SQLException {

        Record record = new Record();
        record.setId(resultSet.getInt("id"));
        record.setSubject(resultSet.getString("subject"));
        record.setOpfr(resultSet.getString("opfr"));
        record.setUpfr(resultSet.getString("upfr"));
        record.setName(resultSet.getString("name"));
        return record;
    }
    
    private Request buildRequest(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        request.setId(resultSet.getInt("id"));
        request.setName(resultSet.getString("name"));
        request.setShortName(resultSet.getString("short_name"));
        return request;       
    }

    public void editRecord(int id, String subject, String opfr, String upfr) {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(UPDATE_RECORD)) {
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
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(ADD_RECORD)) {

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
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(DELETE_RECORD)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }

    public void importRecords(String[][] input) {
        for (int i = 0; i < input.length; i++) {

            String[] buf = new String[4];
            for (int j = 0; j < 4; j++) {

                buf[j] = input[i][j];
            }

            try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(ADD_RECORD)) {

                preparedStatement.setString(1, buf[0]);
                preparedStatement.setString(2, buf[1]);
                preparedStatement.setString(3, buf[2]);
                preparedStatement.setString(4, buf[3]);
                preparedStatement.executeUpdate();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
                throw new DaoException(throwable);
            }

        }
        System.out.println("Импортирование завершено");
    }

    public void eraseSpr() {
        try ( var connection = DBConnection.getInstance().getConnection();  var preparedStatement = connection.prepareStatement(ERASE_SPR)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new DaoException(throwable);
        }
    }
}
