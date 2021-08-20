/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;

import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.DetalleBoleta;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author tavo-
 */
public class IngresarVentaDAO {
      private String mensaje = "";
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst2;
    PreparedStatement pst3;
    ResultSet rs;
    public boolean ListarProductosEmpresaPedidoCBO(JComboBox cb,JTextField txt, JTextField txt2, JTextField txt3){
        try {
                String sql="select emp.nombre,inv.stock,prod.precioprodunitario from producto as prod\n" +
                        "inner join empresa as emp\n" +
                        "	on	prod.idempresa=emp.idempresa\n" +
                        "inner join inventario as inv\n" +
                        "	on inv.producto_codproducto=prod.codproducto\n" +
                        "where prod.descripcion=?";
                con=cn.getConnection();
                PreparedStatement pst;
                 pst = con.prepareStatement(sql);  
                ResultSet rs;
                
                pst.setString(1, (String)cb.getSelectedItem());

                rs = pst.executeQuery();
               txt.setText("");
               txt2.setText("");
               txt3.setText("");
                if(rs.next()){
                txt.setText(rs.getString(1));//aqui se agregará todos los productos asociados según la empresa que elegiste en el combobox
                txt2.setText(rs.getString(2));
                txt3.setText(rs.getString(3));
                }
                return true;
                
                } catch (SQLException ex) {
                    
                    JOptionPane.showMessageDialog(null, ex.toString());
                    return false;
                }
                
        }
    
     public boolean agregarBoleta(){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        String sql2 = "insert into boleta(fecha,total,deuda)"
                        +"values(?,?,?)";

        try {
            con=cn.getConnection();
            
             pst2 = con.prepareStatement(sql2);
           


            
            pst2.setString(1, dtf.format(LocalDateTime.now()));
            pst2.setInt(2, 0);
            pst2.setInt(3, 0);
            pst2.executeUpdate();
            pst2.close();

            
            
            
            
           
           
            return true;
        }catch(Exception e){
            
           JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
            System.out.println("incorrecto boleta");
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean agregardetalleboleta(DetalleBoleta detb){
        String sql = "insert into detalleboleta(numboleta,producto,cantidad,precio) "
                + "values(?,?,?,?)" ;
        try {
            con = cn.getConnection();
                        //-----------------select num factura------------------------
            String sql5="select max(numboleta) from boleta";
                
                pst2 = con.prepareStatement(sql5);
                 pst = con.prepareStatement(sql);//recordar hacer esto antes de cualquier pst.setint o similar porque da nullpointer exception
               
                ResultSet rs1 = pst2.executeQuery();
               
                if (rs1.next()){
                    pst.setInt(1,Integer.parseInt(rs1.getString(1)));
                }
                
                
            
          
            //agregar info a tabla detalle-------         
            pst.setInt(2, detb.getProducto());
            pst.setInt(3, detb.getCantidad());
            pst.setInt(4, detb.getPrecio());
            pst.executeUpdate();
            
            
           JOptionPane.showMessageDialog(null,"detalle realizado con exito");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
             System.out.println("incorrecto detalle Boleta");
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
    
     public boolean agregarventa(JTextField txt){
        String sql = "insert into venta(idempleado,numboleta) values (1,?) " ;
        try {
            con = cn.getConnection();
               
                 pst = con.prepareStatement(sql);//recordar hacer esto antes de cualquier pst.setint o similar porque da nullpointer exception
               
                
                    pst.setInt(1,Integer.parseInt(txt.getText()));
                

            pst.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"Venta realizada con exito");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
             System.out.println("incorrecto venta");
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
    
    public boolean agregarFiado(JTextField txt2,JComboBox cb){
        String sql = "insert into fiado (monto,idventa) values (?,(select max(idventa) from venta)) " ;
        try {
            con = cn.getConnection();
               
                 pst = con.prepareStatement(sql);//recordar hacer esto antes de cualquier pst.setint o similar porque da nullpointer exception
               
                    pst.setInt(1,Integer.parseInt(txt2.getText()));
                

            pst.executeUpdate();
            
            String sql2 = "update cliente set fiado_idfiado=?,deuda='s' where idcliente=?";
            String sql3 ="select max(idfiado) from fiado";
            String sql4 = "select rut from cliente where rut=?";
            pst=con.prepareStatement(sql2);
            pst2=con.prepareStatement(sql3);
            pst3=con.prepareStatement(sql4);
             ResultSet rs1 = pst2.executeQuery();
               
                if (rs1.next()){
                    pst.setInt(1,Integer.parseInt(rs1.getString(1)));
                }
                pst3.setInt(1,Integer.parseInt((String)cb.getSelectedItem()));
                 ResultSet rs2 = pst3.executeQuery();
               
                if (rs2.next()){
                    pst.setInt(2,Integer.parseInt(rs2.getString(1)));
                }

            pst.executeUpdate();
           JOptionPane.showMessageDialog(null, "Fiado usuario creado con exito.");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
             System.out.println("incorrecto fiado");
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
    public int NumBoletaImprimir(){
       
        try {
            con=cn.getConnection();
            String sql5="select max(numboleta) from boleta";
            PreparedStatement pst5;
            pst5 = con.prepareStatement(sql5);
            
            
            ResultSet rs1 = pst5.executeQuery();
            int numboleta=0;
            if (rs1.next()){
                    numboleta=Integer.parseInt(rs1.getString(1));
                     
            }
                return numboleta;
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            System.out.println("Error imprimir num boleta");
            return 0;
        }
    }
    public void ListarVenta(Connection con, JTable tabla,JTextField txt){ //crear metodo de lista

        DefaultTableModel model; //llamamos al objeto de nuestra tabla
        String [] columnas = {"Id detalle","Nombre prod","Fecha","Cantidad","precio"};//agregamos parametros a la columna
        model = new DefaultTableModel(null, columnas);//se los agragamos a la tabla 
        
        String sql = "select det.iddetalle,prod.descripcion,bol.fecha,det.cantidad,det.precio from detalleboleta as det\n" +
                                "inner join Boleta as bol\n" +
                                "	on det.numboleta=bol.numboleta\n" +
                                "inner join producto as prod\n" +
                                "	on prod.codProducto=det.producto\n" +
                                "    where det.numboleta=?"; 

        
        String [] filas = new String [5];//creamos las filas
        PreparedStatement st = null;// statement ejecuta la query
        ResultSet rs = null;// obtendrá los resultados del sql
        
        try {
            con = cn.getConnection();
            
            st = con.prepareStatement(sql);//se crea el select
           
            st.setInt(1, Integer.parseInt(txt.getText()));
            rs = st.executeQuery();//obtiene el resultado del select
            while (rs.next()) {           // aqui hará un recorrido del select     
                for (int i = 0; i < 5; i++) { //aqui con el for se limita el recorrido a la cantidad de filas (6)
                    filas[i] = rs.getString(i+1);//se guardará el resultado del rs en el dato filas
                }
                model.addRow(filas);//addrow significa agregar filas
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede listar la tabla Venta: "+e.toString());
            System.out.println("tabla venta "+e.toString());
        }
    }
    
    public boolean CompararProductoDetalleBolleta(JComboBox cb, JTextField txt){
        
        String sql = "select prod.descripcion from producto as prod\n" +
                "inner join detalleboleta as det\n" +
                     "on det.producto=prod.codProducto\n" +
                "where det.numboleta=? and prod.descripcion=?";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            
            
            pst.setString(1, txt.getText());
            pst.setString(2,(String)cb.getSelectedItem());
            rs = pst.executeQuery();
            
            if(rs.next()){
                return false;//Aqui false porque queremos verificar que un producto no exista dentro de detalle para evitar clonación de producto en un mismo detalle :)
            }else{
                return true;
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("Error metodo comparaproductodetalleboleta");
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
      
    }
    
    public boolean ModificaBoleta(JTextField txt,JTextField txt2 ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sql = "update boleta set fecha=?,total=? "
                + "where numboleta=?";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            
            
            pst.setString(1, dtf.format(LocalDateTime.now()));
            pst.setInt(2, Integer.parseInt(txt.getText()));
            pst.setInt(3, Integer.parseInt(txt2.getText()));
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
    public boolean EliminarDetalleBoleta(JTextField txt){
        String sql = "Delete from detalleboleta where iddetalle = ?";
        try {
            con=cn.getConnection();
            
            
                 pst= con.prepareStatement(sql);
               
              
              
                    pst.setInt(1,Integer.parseInt(txt.getText()));
              
           
            
            pst.execute();
            pst.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("error eliminar detalleboleta"+e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString() );
            }
        }
    }
    
    public boolean ModificarFiadoCliente(JComboBox cb){
        String sql = "update cliente as cli inner join fiado as fi on fi.idfiado=cli.fiado_idfiado\n" +
                            "set cli.fiado_idfiado=(select max(idfiado) from fiado), deuda='s' where cli.rut=?";
        try {
            con=cn.getConnection();
            
            
                 pst= con.prepareStatement(sql);
               
              
              
                    pst.setInt(1,Integer.parseInt((String) cb.getSelectedItem()));
              
           
            
            pst.execute();
            pst.close();
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
    
     public boolean ActualizarStockVenta(JTextField txt ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sql = "update boleta as bol\n" +
                        "inner join detalleboleta as det on det.numboleta=bol.numBoleta inner join producto as prod on prod.codProducto=det.producto\n" +
                         "inner join inventario as inv on inv.producto_codproducto=prod.codProducto \n" +
                        " set inv.stock=inv.stock-det.cantidad where bol.numBoleta=? ";
        try {
            con=cn.getConnection();
            pst= con.prepareStatement(sql);
            
            pst.setInt(1,  Integer.parseInt(txt.getText()));
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Stock modificado con exito");
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("Error al actualizar STOCK BOLETA");
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
     
     public void PDFBoleta(JTextField txt){
                try {
            String sql ="select numboleta, fecha, total from boleta where numBoleta=?";
            String sql2="select prod.descripcion, det.cantidad from detalleboleta as det \n" +
                            "inner join producto as prod on prod.codproducto=det.producto\n" +
                            "where det.numboleta=?";
            con=cn.getConnection();
            
                String numboleta = "";
                String fecha = "";  
                String total = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txt.getText()));
            ResultSet rs1 = pst.executeQuery();
           
            if (rs1.next()){
                    numboleta=(rs1.getString(1));
                    fecha=(rs1.getString(2));
                    total=(rs1.getString(3));
            }
            
            PDDocument documento = new PDDocument ();
            PDPage pagina = new PDPage(PDRectangle.A6);//nueva pagina a6 igual tipo de pagina
            
            documento.addPage(pagina);
            PDPageContentStream contenido=new PDPageContentStream(documento,pagina);
            
            contenido.beginText();
            contenido.setFont(PDType1Font.TIMES_BOLD, 12);
            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52);
           
                contenido.showText("Los Yuyitos ");
                contenido.endText();
                
                contenido.beginText();
            contenido.setFont(PDType1Font.TIMES_BOLD, 12);
            
            contenido.newLineAtOffset(200, pagina.getMediaBox().getHeight()-52);
           
                contenido.showText("Boleta n°"+numboleta);
                contenido.endText();
                
                
                
                
            contenido.beginText();        
            contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52*2);

                contenido.showText("| Numero de boleta: "+numboleta+" | Fecha de compra: "+fecha+" | total: "+total+" |");
                contenido.endText();
                
                PreparedStatement pst2;
                pst2 = con.prepareStatement(sql2);
                pst2.setInt(1, Integer.parseInt(txt.getText()));
            ResultSet rs2 = pst2.executeQuery();
                int i=10;//10 serian para el salto de linea, el cual seria un salto de linea ideal para que este abajo de numboleta 
                while(rs2.next()){
                    
                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    String producto;
                    String cantidad;
                    producto=(rs2.getString(1));
                    cantidad=(rs2.getString(2));
                    contenido.showText("| Producto: "+producto+" | cantidad: "+cantidad);
                    contenido.endText();
                }
//                
//                contenido.beginText();    
//                contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
//                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-54*3);
//                contenido.showText("-----------------------------------------------------------------------");
//                contenido.endText();
            contenido.close();
            documento.save("C:\\Users\\Diego\\Desktop\\YUYITOS\\pdfprueba pdf.pdf");
            
            JOptionPane.showMessageDialog(null, "pdf creado");
            try {
                    File path = new File("C:\\Users\\Diego\\Desktop\\YUYITOS\\pdfprueba pdf.pdf");
                    Desktop.getDesktop().open(path);
               }catch (IOException ex) {
                    ex.printStackTrace();
               }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error pdf"+e);
        }
     }
}
