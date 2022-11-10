/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

/**
 *
 * @author 041AlikinOS
 */
public class RecordBuilder {
    
    public Record buildRecord(String[] records) {
     Record record = new Record();
     for(int i = 0; i < 4; i++) {
         record.setSubject(records[0]);
         record.setOpfr(records[1]);
         record.setUpfr(records[2]);
         record.setName(records[3]);
     }
        
      return record;  
    }
}
