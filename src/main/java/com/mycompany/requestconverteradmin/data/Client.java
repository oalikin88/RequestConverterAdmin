/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.requestconverteradmin.data;

/**
 *
 * @author 041AlikinOS
 */
public class Client {
    
    private String subject;
    private String opfr;
    private String upfr;
    private String name;
    
    public Client() {}
    
    public Client(String name) {
        this.name = name;
    }
    
    public Client(String subject, String opfr, String upfr, String name) {
        this.name = subject;
        this.name = opfr;
        this.name = upfr;
        this.name = name;
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
}
