/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 041AlikinOS
 */
public class Spr {

    private List<Record> records;
    private SprType type;
    private ClientDAO clientDAO;

    public Spr(SprType type) {
        this.type = type;
        clientDAO = new ClientDAO();
    }

    public List<Record> getSpr() {
        try {
            records = clientDAO.findAllRecords(type.getTitle());
        } catch (IOException ex) {
            Logger.getLogger(Spr.class.getName()).log(Level.SEVERE, null, ex);
        }

        return records;
    }
    
    
    public void addRecord() {
        Record record = records.get(records.size() - 1);
        try {
            clientDAO.addRecord(record, this.getType().getTitle());
        } catch (IOException ex) {
            Logger.getLogger(Spr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delRecord(int id) {
        
        try {
            clientDAO.deleteRecord(id, this.getType().getTitle());
        } catch (IOException ex) {
            Logger.getLogger(Spr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SprType getType() {
        return type;
    }

    public List<Record> getRecords() {
        return records;
    }
    
    
    public void importSpr(String[][] prepareContent) {
        try {
            clientDAO.importRecords(prepareContent, this.getType().getTitle());
        } catch (IOException ex) {
            Logger.getLogger(Spr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eraseSpr() {
        try {
            clientDAO.eraseSpr(this.getType().getTitle());
        } catch (IOException ex) {
            Logger.getLogger(Spr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editRecord(Record record) {
        try {
            clientDAO.editRecord(record, this.getType().getTitle());
        } catch (IOException ex) {
            Logger.getLogger(Spr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
