/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.bo;

import com.yuyitos.ch.dao.IngresarEmpleadoDAO;
import com.yuyitos.ch.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author tavo-
 */
public class TablaEmpleado {
     private IngresarEmpleadoDAO iedao= new IngresarEmpleadoDAO();
       public void ListarFichaProveedor(JTable tabla){
        Connection conn = Conexion.getConnection();
        iedao.ListarEmpleado(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
