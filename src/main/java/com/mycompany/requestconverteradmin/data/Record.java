/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

/**
 *
 * @author 041AlikinOS
 */
public class Record {
    private int id;
    private String subject;
    private String opfr;
    private String upfr;
    private String name;
    
    public Record() {}
    
    public Record(String name) {
        this.name = name;
    }
    
    public Record(int id, String subject, String opfr, String upfr, String name) {
        this.id = id;
        this.name = subject;
        this.name = opfr;
        this.name = upfr;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setOpfr(String opfr) {
        this.opfr = opfr;
    }

    public void setUpfr(String upfr) {
        this.upfr = upfr;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    
    }

    public String getSubject() {
        return subject;
    }

    public String getOpfr() {
        return opfr;
    }

    public String getUpfr() {
        return upfr;
    }
    
    
    @Override
    public String toString()  {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(obj == null) {
            return false;
        }
        
        if(obj.getClass() != this.getClass()) {
            return false;
        }
        
        Record other = (Record) obj;
        
        if((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;  
        }
        
        if((this.opfr == null) ? (other.opfr != null) : !this.opfr.equals(other.opfr)) {
            return false;
        }
        
        if((this.upfr == null) ? (other.upfr != null) : !this.upfr.equals(other.upfr)) {
            return false;
        }
        
        if((this.subject == null) ? (other.subject != null) : !this.equals(other.subject)) {
            return false;
        }
        
        return true; 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.opfr != null ? this.opfr.hashCode() : 0);
        hash = 53 * hash + (this.upfr != null ? this.upfr.hashCode() : 0);
        hash = 53 * hash + (this.subject != null ? this.subject.hashCode() : 0);
        return hash;
    }
    
    
}
