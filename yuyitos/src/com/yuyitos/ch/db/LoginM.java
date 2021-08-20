/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.db;

/**
 *
 * @author tavo-
 */
public class LoginM {
    private int Id;
    private String Correo;
    private String Pass;

    public LoginM() {
    }

    public LoginM(int Id, String Correo, String Pass) {
        this.Id = Id;
        this.Correo = Correo;
        this.Pass = Pass;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    
}
