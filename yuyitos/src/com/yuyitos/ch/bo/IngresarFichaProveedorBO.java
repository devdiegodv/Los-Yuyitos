/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.bo;


import com.yuyitos.ch.dao.IngresarFichaProveedorDAO;
import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.Empleado;


import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author tavo-
 */
public class IngresarFichaProveedorBO {
    
        private IngresarFichaProveedorDAO ifpdao = new IngresarFichaProveedorDAO();
       public void ListarFichaProveedor(JTable tabla){
        Connection conn = Conexion.getConnection();
        ifpdao.ListarFichaEmpresa(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
