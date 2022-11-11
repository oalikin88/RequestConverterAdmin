/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author 041AlikinOS
 */
public class Request {
    private int id;
    private String name;
    private String shortName;
    
    private IntegerProperty nameSizeProperty = new SimpleIntegerProperty();
    
    public final Integer getNameSizeProperty() {
        return name.length();
    }
    

    public Request() {
    }
    
    public Request(String name) {
        this.name = name;
    }
    
    public Request(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
     @Override
    public String toString()  {
        return this.name;
    }
    
}
