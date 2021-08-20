/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.db;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
/**
 *
 * @author tavo-
 */
public class Conexion 
{
        
        
        public static Connection getConnection(){
            Connection conn;
            String usuario = "root";
            String clave = "root12345";
            String url = "jdbc:mysql://localhost:3306/yuyitosbd";
            try{
//                try {
//                    Class.forName("com.mysql.cj.jdbc.Driver");
//                } catch (ClassNotFoundException ex) {
//                    java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//                }
                conn = DriverManager.getConnection(url,usuario,clave);
                return conn;
                
            }catch(SQLException e){
                System.out.println(e.toString());
                
                
            }
            return null;
        }
        
//        private static Connection conn = null;      /*static para que no sean llamados por otro paquete*/
//            private static String login = "yuyitos"; /*nombre de usuario que se conecta a sqldeveloper*/
//            private static String clave = "YUy1T0SP4sS";    /*contraseña*/
//            private static String url = "jdbc:mysql://localhost:3306/yuyitos";
//            
//        
//        public static Connection getConnection(){
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                conn = DriverManager.getConnection(url,login,clave); /* url login y clave para utilizar la conexion de base de datos */
//                conn.setAutoCommit(false);
//                if (conn != null){ /*si los datos son correctos saltara el print*/
//                    System.out.println("Conexion Exitosa.");
//                }else{
//                    System.out.println("Conexion Erronea. ");
//                }
//            } catch (ClassNotFoundException | SQLException e){
//                JOptionPane.showMessageDialog(null, "Conexion erronea "+ e.getMessage()); //e.getmessage para indicar el error en el print
//            }
//            return conn;
//        }
//        public void desconexion(){
//            try{
//                conn.close();
//            } catch (Exception e){
//                System.out.println("Error al desconectar "+ e.getMessage()); /*e.getmessage para indicar el error en el print*/ 
//            }
//        }
   public static void main(String[] args){
//            
//            Conexion c = new Conexion();
//            c.getConnection();
//            private static Connection conn = null; /*static para que no sean llamados por otro paquete*/
//            private static String login = "yuyitos"; /*nombre de usuario que se conecta a sqldeveloper*/
//            private static String clave = "YUy1T0SP4sS";    /*contraseña*/
//            private static String url = "jdbc:mysql://localhost:3306/yuyitos";
//            Statement stmt;
//            ResultSet rs;


//              String usuario = "yuyitos";
//              String clave = "YUy1T0SP4sS";
//              String url = "jdbc:mysql://localhost:3306/yuyitos";
//              Connection con;
//              Statement stmt;
//              ResultSet rs;
//        
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//            } catch (ClassNotFoundException ex) {
//                java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            try {
//                con =DriverManager.getConnection(url,usuario,clave);
//                
//            } catch (SQLException ex) {
//                java.util.logging.Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//            }
  }
            
}
