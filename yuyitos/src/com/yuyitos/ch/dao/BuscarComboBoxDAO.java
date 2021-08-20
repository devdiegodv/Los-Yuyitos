/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;


import com.yuyitos.ch.db.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author tavo-
 */
public class BuscarComboBoxDAO {
    Conexion cn = new Conexion();
    Connection con;
    
    public void ListarPorRutEmpresa(JComboBox cb){//listar items desde una seleccion de un combobox***
        try {
            String sql="select * from empresa where idempresa = ?";
            
            con=cn.getConnection();
            PreparedStatement pst;
             pst = con.prepareStatement(sql);  
            pst.setString(1, (String)cb.getSelectedItem());//cb es Jcombobox
            ResultSet rs;
            rs = pst.executeQuery();
            while(rs.next()){
                cb.addItem(rs.getString(sql));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString() );
        }
    }
    
    public boolean BuscarRutEmpresaCBO(JComboBox cb){
        try {
                String sql="select rut from empresa order by idempresa";
                con=cn.getConnection();
                PreparedStatement pst;
                 pst = con.prepareStatement(sql);  
                ResultSet rs;
                rs = pst.executeQuery(sql);
                cb.addItem("");//para dejar un espacio en blanco en el combo box
                while(rs.next()){
                    cb.addItem(rs.getString(1));//aqui se agregará los ruts en el combobox
                   
                }
                return true;
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"buscarrutempresacombobox " +ex.toString());
                    return false;
                }
                
        }
    
    public boolean BuscarNombreEmpresaCBO(JComboBox cb){
        try {
                String sql="select nombre from empresa order by idempresa";
                con=cn.getConnection();
                PreparedStatement pst;
                 pst = con.prepareStatement(sql);  
                ResultSet rs;
                rs = pst.executeQuery(sql);
                cb.addItem("");//para dejar un espacio en blanco en el combo box
                while(rs.next()){
                    cb.addItem(rs.getString(1));//aqui se agregará los ruts en el combobox
                   
                }
                return true;
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"buscarNombreempresacombobox " +ex.toString());
                    return false;
                }
                
        }
    
    public boolean BuscarProductoEmpresaCBO(JComboBox cb){
        try {
                String sql="select producto.descripcion from producto left join familia on producto.familia = familia.idfamilia\n" +
                               "union\n" +
                               "select producto.descripcion from producto right join familia on producto.familia = familia.idfamilia";
//                        "select concat(producto.descripcion) from producto left join familia on producto.familia = familia.idfamilia\n" +
//                               "union\n" +
//                               "select concat(producto.descripcion) from producto right join familia on producto.familia = familia.idfamilia;";
                con=cn.getConnection();
                PreparedStatement pst;
                 pst = con.prepareStatement(sql);  
                ResultSet rs;
                rs = pst.executeQuery(sql);
                cb.addItem("");//para dejar un espacio en blanco en el combo box
                while(rs.next()){
                    cb.addItem(rs.getString(1));//aqui se agregará los ruts en el combobox
                }
                return true;
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"buscarNombreempresacombobox " +ex.toString());
                    return false;
                }
                
        }
    
    public boolean ListarNFacturaNCBO(JComboBox cb,JComboBox cb2){
        try {
                String sql="select fac.numfactura from factura as fac\n" +
                                    "	inner join detallefactura as det\n" +
                                    "on fac.numfactura=det.numfactura\n" +
                                    "	inner join producto as prod\n" +
                                    "on det.producto=prod.codproducto\n" +
                                    "	inner join empresa as emp\n" +
                                    "on emp.idempresa=prod.idempresa\n" +
                                    "	where emp.nombre=? order by fac.numfactura";
                con=cn.getConnection();
                PreparedStatement pst;
                 pst = con.prepareStatement(sql);  
                ResultSet rs;
                
                pst.setString(1, (String)cb.getSelectedItem());
                rs = pst.executeQuery();

                //cb.addItem("");//para dejar un espacio en blanco en el combo box
               
                cb2.removeAllItems();//para dejar vacio en caso de que la empresa no tenga productos
                while(rs.next()){
                cb2.addItem(rs.getString(1));//aqui se agregará todos los productos asociados según la empresa que elegiste en el combobox
                }
                return true;
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                    return false;
                }
                
        }
    
    public void BuscarProductoVenta(JComboBox cb){//listar items desde una seleccion de un combobox***
        try {
            String sql="select descripcion from producto order by descripcion";
            
            con=cn.getConnection();
            PreparedStatement pst;
             pst = con.prepareStatement(sql);  
            ResultSet rs;
            rs = pst.executeQuery();
            cb.addItem("");
            while(rs.next()){
                cb.addItem(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString() );
        }
    }
    public void BuscarRutCliente(JComboBox cb){//listar items desde una seleccion de un combobox***
        try {
            String sql="select rut from cliente order by rut ";
            
            con=cn.getConnection();
            PreparedStatement pst;
             pst = con.prepareStatement(sql);  
            ResultSet rs;
            rs = pst.executeQuery();
            cb.addItem("");
            while(rs.next()){
                cb.addItem(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString() );
        }
    }
}
