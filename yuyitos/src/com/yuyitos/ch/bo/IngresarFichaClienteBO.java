/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.bo;

import com.yuyitos.ch.dao.IngresarFichaClienteDAO;
import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author tavo-
 */
public class IngresarFichaClienteBO {
//    private String mensaje = "";
    private IngresarFichaClienteDAO ifcdao = new IngresarFichaClienteDAO();
//    s
//    public String agregarFichaCliente(Cliente cli){ // Cliente se refiere a el objeto del paquete entidad(entity) creado anteriormente
//       Connection conn = Conexion.getConnection();
//       try{
//           mensaje = ifcdao.agregarFichaCliente(conn, cli);
//           //conn.rollback();//el rollback detiene los errores, por lo que si hay un error en un dato no se guardará en la base de datos
//       }catch (Exception e){
//           mensaje = mensaje + ""+ e.getMessage();
//       }finally{
//           try {
//               if (conn != null){
//                   conn.close();
//               }
//           } catch (Exception e) {
//               mensaje = mensaje + "" + e.getMessage();
//           }
//       }
//       return mensaje;
//    }
//    public String ModificarFichaCliente(Cliente cli){
//        Connection conn = Conexion.getConnection();
//       try{
//           mensaje = ifcdao.ModificarFichaCliente(conn, cli);
//           //conn.rollback();//el rollback detiene los errores, por lo que si hay un error en un dato no se guardará en la base de datos
//       }catch (Exception e){
//           mensaje = mensaje + ""+ e.getMessage();
//       }finally{
//           try {
//               if (conn != null){
//                   conn.close();
//               }
//           } catch (Exception e) {
//               mensaje = mensaje + "" + e.getMessage();
//           }
//       }
//       return mensaje;
//    }
//    public String EliminarFichaCliente(int IdCliente){
//        Connection conn = Conexion.getConnection();
//       try{
//           mensaje = ifcdao.EliminarFichaCliente(conn, IdCliente);
//           //conn.rollback();//el rollback detiene los errores, por lo que si hay un error en un dato no se guardará en la base de datos
//       }catch (Exception e){
//           mensaje = mensaje + ""+ e.getMessage();
//       }finally{
//           try {
//               if (conn != null){
//                   conn.close();
//               }
//           } catch (Exception e) {
//               mensaje = mensaje + "" + e.getMessage();
//           }
//       }
//       return mensaje;
//    }
    public void ListarFichaCliente(JTable tabla){
        Connection conn = Conexion.getConnection();
        ifcdao.ListarFichaCliente(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
