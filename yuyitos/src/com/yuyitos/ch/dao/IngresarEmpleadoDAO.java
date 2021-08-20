/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;

import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tavo-
 */
public class IngresarEmpleadoDAO {
    private String mensaje = "";
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs;
    
    public boolean agregarEmpleado(Empleado empl){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sql = "insert into empleado(nombre,apaterno,amaterno,rut,dv,direccion,telefono,fechacontrato,fechatermino,cargo,sueldo)"
                +"values(?,?,?,?,?,?,?,?,?,?,?)" ;
        
        
        
        try {
            con=cn.getConnection();
          
            pst = con.prepareStatement(sql);         
            
            pst.setString(1, empl.getNombre());
            pst.setString(2, empl.getApellidoPaterno());
            pst.setString(3, empl.getApellidoMaterno());
            pst.setInt(4, empl.getRut());
            pst.setString(5, empl.getDvRut()+"");
            pst.setString(6, empl.getDireccion());
            pst.setInt(7, empl.getTelefono());
            pst.setString(8, dtf.format(LocalDateTime.now()));
            pst.setString(9, empl.getFechaTerminoContrato());
            pst.setString(10, empl.getCargo());
            pst.setInt(11, empl.getSueldo());
            
            
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "Empleado Agregado");
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

    public boolean ModificarEmpleado( Empleado empl){
        String sql = "update empleado set nombre = ? ,apaterno = ? ,amaterno = ? ,rut = ? ,dv = ? ,direccion = ? ,telefono = ?  ,fechatermino = ? ,cargo = ? ,sueldo = ? "
                    +"where idempleado = ?";//me salio un error java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'idcliente=1' at line 1
                                        //solo porque se me olvido poner espacio luego de terminar el ultimo set  (fiado_idFiado=? )
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            pst.setString(1, empl.getNombre());
            pst.setString(2, empl.getApellidoPaterno());
            pst.setString(3, empl.getApellidoMaterno());
            pst.setInt(4, empl.getRut());
            pst.setString(5, empl.getDvRut()+"");
            pst.setString(6, empl.getDireccion());
            pst.setInt(7, empl.getTelefono());
            
            pst.setString(8, empl.getFechaTerminoContrato());
            pst.setString(9, empl.getCargo());
            pst.setInt(10, empl.getSueldo());
            pst.setInt(11, empl.getIdEmpleado());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Empleado modificado");
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
    public boolean EliminarEmpleado(int id){
        String sql = "Delete from empleado where idempleado = ?";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "empleado Eliminado");
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
    public void ListarEmpleado(Connection con, JTable tabla){ //crear metodo de lista

        DefaultTableModel model; //llamamos al objeto de nuestra tabla
        String [] columnas = {"idempleado","nombre","apaterno","amaterno","rut","dv","direccion","telefono","fechacontrato","fechatermino","cargo","sueldo"};//agregamos parametros a la columna
        model = new DefaultTableModel(null, columnas);//se los agragamos a la tabla 
        
        String sql = "select * from empleado  order by idempleado"; //el select que hará la consulta de datos
        
        String [] filas = new String [12];//creamos las filas
        Statement st = null;// statement ejecuta la query
        ResultSet rs = null;// obtendrá los resultados del sql
        
        try {
            con = cn.getConnection();
            
            st = con.createStatement();//se crea el select
            rs = st.executeQuery(sql);//obtiene el resultado del select
            while (rs.next()) {           // aqui hará un recorrido del select     
                for (int i = 0; i < 12; i++) { //aqui con el for se limita el recorrido a la cantidad de filas (10)
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
