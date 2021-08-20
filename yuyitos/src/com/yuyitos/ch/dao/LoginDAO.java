/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;

import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.db.LoginM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tavo-
 */
public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public LoginM log(String Correo, String Pass){
        
        LoginM l = new LoginM();
        String sql = "SELECT * FROM usuarios where correo = ? and pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Correo);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                l.setId(rs.getInt("idusuario"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return l;
    }
    
}
