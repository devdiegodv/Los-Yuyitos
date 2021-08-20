/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;

import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author tavo-
 */
public class IngresarFichaProveedorDAO {
    private String mensaje = "";
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs;
    
    public boolean agregarFichaEmpresa(Empresa emp){
        
        String sql = "insert into empresa(nombre,rut,dv,rubro,direccion,telefono,email)"
                +"values( ?,?,?,?,?,?,?)" ;
        
        
        
        try {
            con=cn.getConnection();
          
            pst = con.prepareStatement(sql);         
            
            pst.setString(1, emp.getNombre());
            pst.setInt(2, emp.getRut());
            pst.setString(3, emp.getDV()+"");//para no convertirlo a char, de otra manera setCharacterStream se debe utilizar
            pst.setString(4, emp.getRubro());
            pst.setString(5, emp.getDireccion());
            pst.setInt(6, emp.getTelefono());
            pst.setString(7, emp.getEmail());
            
            
            pst.executeUpdate();
            pst.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean ModificarFichaEmpresa( Empresa emp){
        String sql = "update empresa set nombre = ?,rut = ?,dv = ?,rubro = ?,direccion = ?,telefono = ?,email = ? "
                    +"where idempresa=?";//me salio un error java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'idcliente=1' at line 1
                                        //solo porque se me olvido poner espacio luego de terminar el ultimo set  (fiado_idFiado=? )
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            
            pst.setString(1, emp.getNombre());
            pst.setInt(2, emp.getRut());
            pst.setString(3, emp.getDV()+"");//para no convertirlo a char, de otra manera setCharacterStream se debe utilizar
            pst.setString(4, emp.getRubro());
            pst.setString(5, emp.getDireccion());
            pst.setInt(6, emp.getTelefono());
            pst.setString(7, emp.getEmail());
            
            pst.setInt(8, emp.getIdEmpresa());
            pst.execute();
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
      
    }
    public boolean EliminarEmpresa(int id){
        String sql = "Delete from empresa where idempresa = ?";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString() );
            }
        }
    }
    public void ListarFichaEmpresa(Connection con, JTable tabla){ //crear metodo de lista

        DefaultTableModel model; //llamamos al objeto de nuestra tabla
        String [] columnas = {"ID", "Nombre Empresa", "Rut", "Dv Rut", "Rubro", "Direccion", "Telefono", "Email"};//agregamos parametros a la columna
        model = new DefaultTableModel(null, columnas);//se los agragamos a la tabla 
        
        String sql = "select * from empresa  order by idempresa"; //el select que hará la consulta de datos
        
        String [] filas = new String [8];//creamos las filas
        Statement st = null;// statement ejecuta la query
        ResultSet rs = null;// obtendrá los resultados del sql
        
        try {
            con = cn.getConnection();
            
            st = con.createStatement();//se crea el select
            rs = st.executeQuery(sql);//obtiene el resultado del select
            while (rs.next()) {           // aqui hará un recorrido del select     
                for (int i = 0; i < 8; i++) { //aqui con el for se limita el recorrido a la cantidad de filas (10)
                    filas[i] = rs.getString(i+1);//se guardará el resultado del rs en el dato filas
                }
                model.addRow(filas);//addrow significa agregar filas
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede listar la tabla ");
        }
    }
    
}
