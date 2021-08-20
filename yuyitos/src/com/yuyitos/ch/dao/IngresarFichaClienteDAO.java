/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;

import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.Cliente;
import com.yuyitos.ch.entity.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tavo-
 */
public class IngresarFichaClienteDAO {
    private String mensaje = "";
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs;
    
    public boolean agregarFichaCliente(Cliente cli){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sql = "insert into cliente(fechaRegistro,nombre,apaterno,amaterno,rut,dv,direccion,deuda,fiado_idFiado)"
                +"values(?,?,?,?,?,?,?,?,?)" ;
        
        
        
        try {
            con=cn.getConnection();
          
            pst = con.prepareStatement(sql);         
            
            pst.setString(1, dtf.format(LocalDateTime.now()));
            pst.setString(2, cli.getNombre());
            pst.setString(3, cli.getApellidoPaterno());
            pst.setString(4, cli.getApellidoMaterno());
            pst.setInt(5, cli.getRut());
            pst.setString(6, cli.getDVRut()+"");//para no convertirlo a char, de otra manera setCharacterStream se debe utilizar  
            pst.setString(7, cli.getDireccion());
            pst.setString(8, cli.getDeuda()+"");
            pst.setInt(9, cli.getIdFiado());
            
            
            pst.executeUpdate();
            pst.close();
            JOptionPane.showMessageDialog(null, "cliente agregado");
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

    public boolean ModificarFichaCliente( Cliente cli){
        String sql = "update cliente set nombre=?, apaterno=?, amaterno=?, rut=?, dv=?, direccion=?, deuda=?, fiado_idFiado=fiado_idFiado "
                    +"where idcliente=?";//me salio un error java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'idcliente=1' at line 1
                                        //solo porque se me olvido poner espacio luego de terminar el ultimo set  (fiado_idFiado=? )
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            
            pst.setString(1, cli.getNombre());
            pst.setString(2, cli.getApellidoPaterno());
            pst.setString(3, cli.getApellidoMaterno());
            pst.setInt(4, cli.getRut());
            pst.setString(5, cli.getDVRut()+"");
            pst.setString(6, cli.getDireccion());
            pst.setString(7, cli.getDeuda()+"");
            
            pst.setInt(8, cli.getIdCliente());
            pst.execute();
            JOptionPane.showMessageDialog(null, "cliente modificado");
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
    public boolean EliminarCliente(int id){
        String sql = "Delete from cliente where idcliente = ?";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "cliente eliminado");
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
    public void ListarFichaCliente(Connection con, JTable tabla){ //crear metodo de lista
//        List<Cliente> ListaCL = new ArrayList();
//        String sql = "SELECT * FROM cliente";
//        try {
//            con = cn.getConnection();
//            pst = con.prepareStatement(sql);
//            rs = pst.executeQuery();
//            while(rs.next()){
//                Cliente cli =new Cliente();
//                cli.setIdCliente(rs.getInt("id cliente"));
//                cli.setRut(rs.getInt("Rut"));
//                cli.setDVRut(rs.getString("DVRut"));
//                c
//                
//            }
//            
//        } catch (Exception e) {
//        }
        
        
        DefaultTableModel model; //llamamos al objeto de nuestra tabla
        String [] columnas = {"IDCliente","fechaRegistro","nombre","Apellido paterno","Apellido materno","rut","DVRut","direccion","Deuda","IdFiado"};//agregamos parametros a la columna
        model = new DefaultTableModel(null, columnas);//se los agragamos a la tabla 
        
        String sql = "select * from cliente order by idcliente"; //el select que hará la consulta de datos
        
        String [] filas = new String [10];//creamos las filas
        Statement st = null;// statement ejecuta la query
        ResultSet rs = null;// obtendrá los resultados del sql
        
        try {
            con = cn.getConnection();
            
            st = con.createStatement();//se crea el select
            rs = st.executeQuery(sql);//obtiene el resultado del select
            while (rs.next()) {           // aqui hará un recorrido del select     
                for (int i = 0; i < 10; i++) { //aqui con el for se limita el recorrido a la cantidad de filas (10)
                    filas[i] = rs.getString(i+1);//se guardará el resultado del rs en el dato filas
                }
                model.addRow(filas);//addrow significa agregar filas
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede listar la tabla ");
        }
    }
    
    public boolean ModificarFiadoCliente(JTextField txt,JTextField txt2){
        String sql = "update cliente as cli inner join fiado as fi on fi.idfiado=cli.fiado_idfiado\n" +
                        "set fi.monto=fi.monto-? where cli.idcliente=?";
        try {
            con=cn.getConnection();
            
            
                 pst= con.prepareStatement(sql);
               
              
              
                    pst.setInt(1,Integer.parseInt(txt.getText()));
                    pst.setInt(2,Integer.parseInt(txt2.getText()));
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Fiado Ingresado con exito.");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("error modificar fiado cliente"+e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString() );
            }
        }
    }
    
        public boolean ModificarCHARFiado(JTextField txt){
        String sql = "update cliente as cli inner join fiado as fi on fi.idfiado=cli.fiado_idfiado\n" +
                        "set cli.deuda='n' where cli.idcliente=?";
        try {
            con=cn.getConnection();
            
            
                 pst= con.prepareStatement(sql);
               
              
              
                    pst.setInt(1,Integer.parseInt(txt.getText()));
                    
            pst.execute();
            pst.close();
           
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("error modificar char fiado"+e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString() );
            }
        }
    }
    
    public boolean CalcularDeuda(JTextField txt,JTextField txt2 ){
        String sql = "select fi.monto from cliente as cli inner join fiado as fi\n" +
                        "on cli.fiado_idfiado=fi.idfiado\n" +//ME DABA ERROR porque borre los \n entonces habia un error el cual no decia acerca de \n
                        "where cli.idcliente=?";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            
            
            
            pst.setInt(1, Integer.parseInt(txt.getText()));
             rs = pst.executeQuery();
             txt2.setText("0");
            if(rs.next()){
                txt2.setText(rs.getString(1));
            }
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
      
    }
}
