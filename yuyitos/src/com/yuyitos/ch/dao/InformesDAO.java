/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.dao;

import com.yuyitos.ch.db.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author tavo-
 */
public class InformesDAO {
    Conexion cn = new Conexion();
    Connection con;
    
    PreparedStatement pst;
    PreparedStatement pst2;
    
         public void PDFFiadosTotales(){
                try {
            String sql ="select concat(cli.nombre,' ',cli.apaterno,' ',cli.amaterno), fi.monto, bol.fecha from fiado as fi inner join cliente as cli on cli.fiado_idfiado=fi.idfiado inner join venta as ven on ven.idventa=fi.idventa \n" +
"inner join boleta as bol on ven.numboleta=bol.numboleta where fi.monto>0 order by bol.fecha";
            con=cn.getConnection();
            
                String nombre = "";
                String monto = "";  
                String fecha = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
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
         
                contenido.showText("Lista de fiados");
                contenido.endText();
                int i=10;//10 serian para el salto de linea, el cual seria un salto de linea ideal para que este abajo de numboleta 
                while(rs1.next()){
                    
                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    nombre=(rs1.getString(1));
                    monto=(rs1.getString(2));
                    fecha=(rs1.getString(3));
                    contenido.showText("| Nombre del deudor "+nombre+" | monto: "+monto+" | Fecha de fiado: "+fecha);
                    contenido.endText();
                }

            contenido.close();
            documento.save("pdfFiados.pdf");
            
            JOptionPane.showMessageDialog(null, "pdf creado");
            try {
                    File path = new File ("pdfFiados.pdf");
                    Desktop.getDesktop().open(path);
               }catch (IOException ex) {
                    ex.printStackTrace();
               }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error pdf"+e);
        }
     }
         
        public void PDFVentaBoletaMes(){
                try {
            String sql ="select prod.descripcion,sum(det.cantidad),sum(bol.total) from producto as prod inner join detalleboleta as det on det.producto=prod.codproducto inner join boleta as bol on bol.numboleta=det.numboleta\n" +
"where bol.total>0 and month(fecha)=month(current_date()) group by prod.descripcion order by sum(bol.total)desc, sum(det.cantidad) desc ";
            con=cn.getConnection();
            
                String descripcion = "";
                String cantidad = "";  
                String total = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
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
            
            contenido.newLineAtOffset(150, pagina.getMediaBox().getHeight()-52);
         
                contenido.showText("Lista de ventas ultimo mes");
                contenido.endText();
                int i=10;//10 serian para el salto de linea, el cual seria un salto de linea ideal para que este abajo de numboleta 
                while(rs1.next()){
                    
                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    descripcion=(rs1.getString(1));
                    cantidad=(rs1.getString(2));
                    total=(rs1.getString(3));
                    contenido.showText("| Nombre producto "+descripcion+" | Cantidad vendida: "+cantidad+" | Total: "+total);
                    contenido.endText();
                }

            contenido.close();
            documento.save("pdfVenta.pdf");
            
            JOptionPane.showMessageDialog(null, "pdf creado");
            try {
                    File path = new File ("pdfVenta.pdf");
                    Desktop.getDesktop().open(path);
               }catch (IOException ex) {
                    ex.printStackTrace();
               }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error pdf"+e);
        }
     }
        
                public void PDFProveedores(){
                try {
            String sql ="select nombre, rut, rubro, direccion, telefono, email from empresa order by nombre";
            con=cn.getConnection();
            
                String nombre = "";
                String rut = "";  
                String rubro = "";
                String direccion = "";
                String telefono = "";
                String email = "";
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
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
            
            contenido.newLineAtOffset(170, pagina.getMediaBox().getHeight()-52);
         
                contenido.showText("Lista de Proveedores");
                contenido.endText();
                int i=10;//10 serian para el salto de linea, el cual seria un salto de linea ideal para que este abajo de numboleta 
                while(rs1.next()){

                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    nombre=(rs1.getString(1));
                    rut=(rs1.getString(2));
                    rubro=(rs1.getString(3));
                    direccion=(rs1.getString(4));
                    telefono=(rs1.getString(5));
                    email=(rs1.getString(6));
                    contenido.showText("| Nombre proveedor "+nombre+" | Rut: "+rut+" | rubro: "+rubro);
                    contenido.endText();
                    
                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    contenido.showText("| direccion: "+direccion+" | telefono: "+telefono+" | email: "+email);
                    contenido.endText();
                    i=i+15;//esto sumado al pequeño cambio de linea hará que exista un espacio mas grande entre cada informacion proveedor
                }
                
            contenido.close();
            documento.save("pdfProveedores.pdf");
            
            JOptionPane.showMessageDialog(null, "pdf creado");
            try {
                    File path = new File ("pdfProveedores.pdf");
                    Desktop.getDesktop().open(path);
               }catch (IOException ex) {
                    ex.printStackTrace();
               }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error pdf"+e);
        }
     }
                
                
       public void PDFEmpleados(){
                try {
            String sql ="select concat(nombre,' ',apaterno,' ',amaterno), rut, direccion, telefono, concat(fechacontrato,' - ',fechatermino),cargo,sueldo from empleado order by apaterno ";
            con=cn.getConnection();
            
                String nombre= "";
                String rut = "";  
                String direccion = "";
                String telefono = "";
                String fecha = "";
                String cargo = "";
                String sueldo = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
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
            
            contenido.newLineAtOffset(180, pagina.getMediaBox().getHeight()-52);
         
                contenido.showText("Lista de Empleados");
                contenido.endText();
                int i=10;//10 serian para el salto de linea, el cual seria un salto de linea ideal para que este abajo de numboleta 
                while(rs1.next()){

                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    nombre=(rs1.getString(1));
                    rut=(rs1.getString(2));
                    direccion=(rs1.getString(3));
                    telefono=(rs1.getString(4));
                    fecha=(rs1.getString(5));
                    cargo=(rs1.getString(6));
                    sueldo=(rs1.getString(7));
                    
                    contenido.showText("| Nombre: "+nombre+" | rut: "+rut+" | dirección: "+direccion+" | sueldo: "+sueldo);
                    contenido.endText();
                    
                    
                    
                    contenido.beginText();        
                    contenido.setFont(PDType1Font.TIMES_ROMAN, 7);
                    contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-(104+i));
                    i=i+10;//para que por cada producto comprado exista un pequeño salto de linea idoneo
                    contenido.showText("| telefono: "+telefono+" | fecha: "+fecha+" | cargo: "+cargo);
                    contenido.endText();
                    i=i+15;//esto sumado al pequeño cambio de linea hará que exista un espacio mas grande entre cada informacion proveedor
                    
                }

            contenido.close();
            documento.save("pdfEmpleado.pdf");
            
            JOptionPane.showMessageDialog(null, "pdf creado");
            try {
                    File path = new File ("pdfEmpleado.pdf");
                    Desktop.getDesktop().open(path);
               }catch (IOException ex) {
                    ex.printStackTrace();
               }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error pdf"+e);
        }
     }
              
   public void GraficoVentas(){
        
        
        String sql2 = "select prod.descripcion,sum(bol.total) from producto as prod inner join detalleboleta as det on det.producto=prod.codproducto inner join boleta as bol on bol.numboleta=det.numboleta\n" +
    "where bol.total>0 and month(fecha)=month(current_date()) group by prod.descripcion order by sum(bol.total)desc, sum(det.cantidad) desc ";

        try {
            con=cn.getConnection();
            
             pst2 = con.prepareStatement(sql2);

            //---------------hacer factura--------------------
            String nombre="";
            ResultSet rs1 = pst2.executeQuery();
            DefaultCategoryDataset ds=new DefaultCategoryDataset();
            while(rs1.next()){
                int cantidad=Integer.parseInt(rs1.getString(2));
                nombre=rs1.getString(1);
                   ds.addValue(cantidad,nombre,Integer.toString(cantidad)+" "+nombre);// uno es el valor para la tabla int , dos es el nombre que saldra abajo string , tres es el string nombre que saldra justo abajo de cada dato
                }

            JFreeChart jf=ChartFactory.createBarChart3D("Ventas (Mes actual)","Productos","Ventas total $",ds,PlotOrientation.VERTICAL, true, true, true);//1 es el titulo del grafico,
            //2 seria el nombre en horizontal (-) y 3 seria el nombre en vertical (|) 4 es donde se guardo los datos en este caso ds. 5 la posicion de la tabla, lo demas no se xD
            ChartFrame f=new ChartFrame("ventas",jf);//cantidad de usuario seriá el nombre del panel que se desplegará
            f.setSize(1000,600);//tamaño
            f.setLocationRelativeTo(null);//
            f.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
            }
   }
   
    public void GraficoFiados(){
        
        
        String sql2 = "select fi.monto,concat(cli.nombre,' ',cli.apaterno) from cliente as cli inner join fiado as fi\n" +
"on cli.fiado_idfiado=fi.idfiado where fi.monto>0 ";

        try {
            con=cn.getConnection();
            
             pst2 = con.prepareStatement(sql2);

            //---------------hacer factura--------------------
            String nombre="";
            ResultSet rs1 = pst2.executeQuery();
            DefaultCategoryDataset ds=new DefaultCategoryDataset();
            while(rs1.next()){
                int cantidad=Integer.parseInt(rs1.getString(1));
                nombre=rs1.getString(2);
                   ds.addValue(cantidad,nombre,'$'+Integer.toString(cantidad)+"-"+nombre);// uno es el valor para la tabla int , dos es el nombre que saldra abajo string , tres es el string nombre que saldra justo abajo de cada dato
                }

            JFreeChart jf=ChartFactory.createBarChart3D("Dinero en fiado","Clientes","Fiados por  cliente",ds,PlotOrientation.VERTICAL, true, true, true);//1 es el titulo del grafico,
            //2 seria el nombre en horizontal (-) y 3 seria el nombre en vertical (|) 4 es donde se guardo los datos en este caso ds. 5 la posicion de la tabla, lo demas no se xD
            ChartFrame f=new ChartFrame("Fiados",jf);//cantidad de usuario seriá el nombre del panel que se desplegará
            f.setSize(1000,600);//tamaño
            f.setLocationRelativeTo(null);//
            f.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
            }
   }
   
            
    public void word(String asd){
        File archivo;
        PrintWriter Escritura;

        archivo = new File(asd);
        if(!archivo.exists()){//si el archivo no existe
            try {
                   archivo.createNewFile();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                }
            }else{
                    try {
                        
                        String sql ="select prod.descripcion,sum(det.cantidad),sum(bol.total) from producto as prod inner join detalleboleta as det on det.producto=prod.codproducto inner join boleta as bol on bol.numboleta=det.numboleta\n" +
                         "where bol.total>0 and month(fecha)=month(current_date()) group by prod.descripcion order by sum(bol.total)desc, sum(det.cantidad) desc ";
                         con=cn.getConnection();

                         String descripcion = "";
                         String cantidad = "";  
                         String total = "";

                         PreparedStatement pst;
                         pst = con.prepareStatement(sql);
                         ResultSet rs1 = pst.executeQuery();
                         Escritura = new PrintWriter(archivo,"utf-8");
                          Escritura.println("     Lista de ventas mes actual            Los Yuyitos");
                         Escritura.println("");
                         Escritura.println("");
                         while(rs1.next()){

                             descripcion=(rs1.getString(1));
                             cantidad=(rs1.getString(2));
                             total=(rs1.getString(3));
                             Escritura.println("| Nombre producto: "+descripcion+" | Cantidad vendida: "+cantidad+" | Total: "+total);
                             Escritura.println("");
                        }
                         Escritura.close();
                        File path = new File (asd);
                        Desktop.getDesktop().open(path);
                    } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                            System.out.println(e.toString());
                         }
                    }

        }
    
        public void WordProveedores(String asd){
            
            File archivo;
            PrintWriter Escritura;

            archivo = new File(asd);
            if(!archivo.exists()){//si el archivo no existe
            try {
                   archivo.createNewFile();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                }
            }else{
                try {
                    String sql ="select nombre, rut, rubro, direccion, telefono, email from empresa order by nombre";
                    con=cn.getConnection();
                    Escritura = new PrintWriter(archivo,"utf-8");

                    String nombre = "";
                    String rut = "";  
                    String rubro = "";
                    String direccion = "";
                    String telefono = "";
                    String email = "";
                    PreparedStatement pst;
                    pst = con.prepareStatement(sql);
                ResultSet rs1 = pst.executeQuery();
                        Escritura.println("     Lista de proveedores         Los Yuyitos");
                       Escritura.println("");
                       Escritura.println("");

                    while(rs1.next()){

                       

                        nombre=(rs1.getString(1));
                        rut=(rs1.getString(2));
                        rubro=(rs1.getString(3));
                        direccion=(rs1.getString(4));
                        telefono=(rs1.getString(5));
                        email=(rs1.getString(6));
                        Escritura.println("| Nombre proveedor: "+nombre+" | Rut: "+rut+" | rubro: "+rubro);
                        Escritura.println("| direccion: "+direccion+" | telefono: "+telefono+" | email: "+email);
                        Escritura.println("");
                    }

                Escritura.close();
                    File path = new File (asd);
                    Desktop.getDesktop().open(path);
               } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                            System.out.println(e.toString());
                }
           }
}

    
        
    public void WordEmpleados(String asd){
            
            File archivo;
            PrintWriter Escritura;

            archivo = new File(asd);
            if(!archivo.exists()){//si el archivo no existe
            try {
                   archivo.createNewFile();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                }
            }else{
                try {
               String sql ="select concat(nombre,' ',apaterno,' ',amaterno), rut, direccion, telefono, concat(fechacontrato,' - ',fechatermino),cargo,sueldo from empleado order by apaterno ";
               con=cn.getConnection();
            
                String nombre= "";
                String rut = "";  
                String direccion = "";
                String telefono = "";
                String fecha = "";
                String cargo = "";
                String sueldo = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
                 
                    Escritura = new PrintWriter(archivo,"utf-8");

         
                        Escritura.println("     Lista de empleados           Los Yuyitos");
                       Escritura.println("");
                       Escritura.println("");
                       while(rs1.next()){
                            nombre=(rs1.getString(1));
                        rut=(rs1.getString(2));
                        direccion=(rs1.getString(3));
                        telefono=(rs1.getString(4));
                        fecha=(rs1.getString(5));
                        cargo=(rs1.getString(6));
                        sueldo=(rs1.getString(7));
                        Escritura.println("| Nombre: "+nombre+" | rut: "+rut+" | dirección: "+direccion+" | sueldo: "+sueldo);
                        Escritura.println("| telefono: "+telefono+" | fecha: "+fecha+" | cargo: "+cargo);
                        Escritura.println("");
                       }

                Escritura.close();
                    File path = new File (asd);
                    Desktop.getDesktop().open(path);
               } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                            System.out.println(e.toString());
                }
           }
}
    
    public void WordFiados(String asd){
            
            File archivo;
            PrintWriter Escritura;
            
            archivo = new File(asd);
            if(!archivo.exists()){//si el archivo no existe
            try {
                   archivo.createNewFile();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                }
            }else{
                try {
                  String sql ="select concat(cli.nombre,' ',cli.apaterno,' ',cli.amaterno), fi.monto, bol.fecha from fiado as fi inner join cliente as cli on cli.fiado_idfiado=fi.idfiado inner join venta as ven on ven.idventa=fi.idventa \n" +
                                "inner join boleta as bol on ven.numboleta=bol.numboleta where fi.monto>0 order by bol.fecha";
                con=cn.getConnection();
            
                String nombre = "";
                String monto = "";  
                String fecha = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
                 
                    Escritura = new PrintWriter(archivo,"utf-8");

         
                        Escritura.println("     Lista de fiados         Los Yuyitos");
                       Escritura.println("");
                       Escritura.println("");
                       while(rs1.next()){
                                nombre=(rs1.getString(1));
                                monto=(rs1.getString(2));
                                fecha=(rs1.getString(3));
                                Escritura.println("| Nombre del deudor "+nombre+" | monto: "+monto+" | Fecha de fiado: "+fecha);
                                Escritura.println("");
                            }

                Escritura.close();
                    File path = new File (asd);
                    Desktop.getDesktop().open(path);
               } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                            System.out.println(e.toString());
                }
           }
}
    
    public void Excel(){
        XSSFWorkbook libro = new XSSFWorkbook();//se crea el lbiro de excel
        XSSFSheet hoja = libro.createSheet("Fiados");//se crea una hoja con nombre excel ejemplo
        try {
                String sql ="select concat(cli.nombre,' ',cli.apaterno,' ',cli.amaterno), fi.monto, bol.fecha from fiado as fi inner join cliente as cli on cli.fiado_idfiado=fi.idfiado inner join venta as ven on ven.idventa=fi.idventa \n" +
              "inner join boleta as bol on ven.numboleta=bol.numboleta where fi.monto>0 order by bol.fecha";
                con=cn.getConnection();
         
                String nombre = "";
                int monto ;  
                String fecha = "";
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
                 //ESTILO AZUL----------------------
                 XSSFCellStyle style=libro.createCellStyle();
	style.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderTop(BorderStyle.MEDIUM);
                style.setBorderBottom(BorderStyle.MEDIUM);
                style.setBorderLeft(BorderStyle.MEDIUM);
                style.setBorderRight(BorderStyle.MEDIUM);
                //ESTILO AZUL----------------------
                     XSSFRow row1 =hoja.createRow(0);
                    XSSFCell cell= row1.createCell(0);
                    cell.setCellValue("Lista de fiados");
                    cell.setCellStyle(style);
                    cell= row1.createCell(1);
                    cell.setCellValue("Los yuyitos");
                    cell.setCellStyle(style);
                    
                    //ESTILO GRIS----------------------
                    XSSFCellStyle style2=libro.createCellStyle();
                    style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style2.setBorderTop(BorderStyle.MEDIUM);
                    style2.setBorderBottom(BorderStyle.MEDIUM);
                    style2.setBorderLeft(BorderStyle.MEDIUM);
                    style2.setBorderRight(BorderStyle.MEDIUM);
                   //ESTILO GRIS----------------------
                   
                    row1 =hoja.createRow(1);
                    
                    cell= row1.createCell(0);
                    cell.setCellValue("Nombre Deudor");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(1);
                    cell.setCellValue("Monto Deuda");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(2);
                    cell.setCellValue("Fecha fiado");
                    cell.setCellStyle(style2);
                    
                     //ESTILO celeste con lineas delgadas----------------------
                    XSSFCellStyle style3=libro.createCellStyle();
                    style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
                    style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style3.setBorderTop(BorderStyle.THIN);
                    style3.setBorderBottom(BorderStyle.THIN);
                    style3.setBorderLeft(BorderStyle.THIN);
                    style3.setBorderRight(BorderStyle.THIN);
                   //ESTILO celeste con lineas delgadas----------------------
                    
                    int i=0;
                       while(rs1.next()){
                           i=i+1;
                           int numeroHoja=1+i;
                           nombre=(rs1.getString(1));
                           monto=(rs1.getInt(2));
                           fecha=(rs1.getString(3));
                            row1 =hoja.createRow(numeroHoja);
                            cell= row1.createCell(0);
                            cell.setCellValue(nombre);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(1);
                            cell.setCellValue(monto);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(2);
                            cell.setCellValue(fecha);
                            cell.setCellStyle(style3);
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       int numero = hoja.getLastRowNum();
                       while(numero>=0){
                           hoja.autoSizeColumn(numero);
                           numero=numero-1;
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       

                       
                        FileOutputStream archivo = new FileOutputStream(new File("Excel Fiados.xlsx"));//se crea el archivo excel con nombre reporte
                        libro.write(archivo);
                        archivo.close();
                        
                        File path = new File ("Excel Fiados.xlsx");
                        Desktop.getDesktop().open(path);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error excel: "+e.toString());
        }
        
    }
    
    public void ExcelVentasMes(){
        XSSFWorkbook libro = new XSSFWorkbook();//se crea el lbiro de excel
        XSSFSheet hoja = libro.createSheet("Ventas");//se crea una hoja con nombre excel ejemplo
        try {
                String sql ="select prod.descripcion,sum(det.cantidad),sum(bol.total) from producto as prod inner join detalleboleta as det on det.producto=prod.codproducto inner join boleta as bol on bol.numboleta=det.numboleta\n" +
"where bol.total>0 and month(fecha)=month(current_date()) group by prod.descripcion order by sum(bol.total)desc, sum(det.cantidad) desc ";
            con=cn.getConnection();
            
                String descripcion = "";
                int cantidad ;  
                int total ;
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
                 //ESTILO AZUL----------------------
                 XSSFCellStyle style=libro.createCellStyle();
	style.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderTop(BorderStyle.MEDIUM);
                style.setBorderBottom(BorderStyle.MEDIUM);
                style.setBorderLeft(BorderStyle.MEDIUM);
                style.setBorderRight(BorderStyle.MEDIUM);
                //ESTILO AZUL----------------------
                     XSSFRow row1 =hoja.createRow(0);
                    XSSFCell cell= row1.createCell(0);
                    cell.setCellValue("Lista de ventas ultimo mes");
                    cell.setCellStyle(style);
                    cell= row1.createCell(1);
                    cell.setCellValue("Los yuyitos");
                    cell.setCellStyle(style);
                    
                    //ESTILO GRIS----------------------
                    XSSFCellStyle style2=libro.createCellStyle();
                    style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style2.setBorderTop(BorderStyle.MEDIUM);
                    style2.setBorderBottom(BorderStyle.MEDIUM);
                    style2.setBorderLeft(BorderStyle.MEDIUM);
                    style2.setBorderRight(BorderStyle.MEDIUM);
                   //ESTILO GRIS----------------------
                   
                    row1 =hoja.createRow(1);
                    
                    cell= row1.createCell(0);
                    cell.setCellValue("Nombre producto");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(1);
                    cell.setCellValue("Cantidad");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(2);
                    cell.setCellValue("Total");
                    cell.setCellStyle(style2);
                    
                     //ESTILO celeste con lineas delgadas----------------------
                    XSSFCellStyle style3=libro.createCellStyle();
                    style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
                    style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style3.setBorderTop(BorderStyle.THIN);
                    style3.setBorderBottom(BorderStyle.THIN);
                    style3.setBorderLeft(BorderStyle.THIN);
                    style3.setBorderRight(BorderStyle.THIN);
                   //ESTILO celeste con lineas delgadas----------------------
                    
                    int i=0;
                       while(rs1.next()){
                           i=i+1;
                           int numeroHoja=1+i;
                            descripcion=(rs1.getString(1));
                            cantidad=(rs1.getInt(2));
                            total=(rs1.getInt(3));
                            row1 =hoja.createRow(numeroHoja);
                            cell= row1.createCell(0);
                            cell.setCellValue(descripcion);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(1);
                            cell.setCellValue(cantidad);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(2);
                            cell.setCellValue(total);
                            cell.setCellStyle(style3);
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       int numero = hoja.getLastRowNum();
                       while(numero>=0){
                           hoja.autoSizeColumn(numero);
                           numero=numero-1;
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       

                       
                        FileOutputStream archivo = new FileOutputStream(new File("Excel Ventas.xlsx"));//se crea el archivo excel con nombre reporte
                        libro.write(archivo);
                        archivo.close();
                        
                        File path = new File ("Excel Ventas.xlsx");
                        Desktop.getDesktop().open(path);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error excel: "+e.toString());
        }
        
    }
    
        public void ExcelProveedores(){
        XSSFWorkbook libro = new XSSFWorkbook();//se crea el lbiro de excel
        XSSFSheet hoja = libro.createSheet("Proveedores");//se crea una hoja con nombre excel ejemplo
        try {
                String sql ="select nombre, rut, rubro, direccion, telefono, email from empresa order by nombre";
                con=cn.getConnection();
            
                String nombre = "";
                String rut = "";  
                String rubro = "";
                String direccion = "";
                String telefono = "";
                String email = "";
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
                 //ESTILO AZUL----------------------
                 XSSFCellStyle style=libro.createCellStyle();
	style.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderTop(BorderStyle.MEDIUM);
                style.setBorderBottom(BorderStyle.MEDIUM);
                style.setBorderLeft(BorderStyle.MEDIUM);
                style.setBorderRight(BorderStyle.MEDIUM);
                //ESTILO AZUL----------------------
                     XSSFRow row1 =hoja.createRow(0);
                    XSSFCell cell= row1.createCell(0);
                    cell.setCellValue("Lista de proveedores");
                    cell.setCellStyle(style);
                    cell= row1.createCell(1);
                    cell.setCellValue("Los yuyitos");
                    cell.setCellStyle(style);
                    
                    //ESTILO GRIS----------------------
                    XSSFCellStyle style2=libro.createCellStyle();
                    style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style2.setBorderTop(BorderStyle.MEDIUM);
                    style2.setBorderBottom(BorderStyle.MEDIUM);
                    style2.setBorderLeft(BorderStyle.MEDIUM);
                    style2.setBorderRight(BorderStyle.MEDIUM);
                   //ESTILO GRIS----------------------
                   
                    row1 =hoja.createRow(1);
                    
                    cell= row1.createCell(0);
                    cell.setCellValue("Nombre proveedor");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(1);
                    cell.setCellValue("Rut");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(2);
                    cell.setCellValue("Rubro");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(3);
                    cell.setCellValue("Dirección");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(4);
                    cell.setCellValue("Telefono");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(5);
                    cell.setCellValue("Email");
                    cell.setCellStyle(style2);
                     //ESTILO celeste con lineas delgadas----------------------
                    XSSFCellStyle style3=libro.createCellStyle();
                    style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
                    style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style3.setBorderTop(BorderStyle.THIN);
                    style3.setBorderBottom(BorderStyle.THIN);
                    style3.setBorderLeft(BorderStyle.THIN);
                    style3.setBorderRight(BorderStyle.THIN);
                   //ESTILO celeste con lineas delgadas----------------------
                    
                    int i=0;
                       while(rs1.next()){
                           i=i+1;
                           int numeroHoja=1+i;
                           
                    nombre=(rs1.getString(1));
                    rut=(rs1.getString(2));
                    rubro=(rs1.getString(3));
                    direccion=(rs1.getString(4));
                    telefono=(rs1.getString(5));
                    email=(rs1.getString(6));
                 
                            row1 =hoja.createRow(numeroHoja);
                            cell= row1.createCell(0);
                            cell.setCellValue(nombre);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(1);
                            cell.setCellValue(rut);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(2);
                            cell.setCellValue(rubro);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(3);
                            cell.setCellValue(direccion);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(4);
                            cell.setCellValue(telefono);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(5);
                            cell.setCellValue(email);
                            cell.setCellStyle(style3);
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       int numero = hoja.getLastRowNum();
                       while(numero>=0){
                           hoja.autoSizeColumn(numero);
                           numero=numero-1;
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       

                       
                        FileOutputStream archivo = new FileOutputStream(new File("Excel proveedores.xlsx"));//se crea el archivo excel con nombre reporte
                        libro.write(archivo);
                        archivo.close();
                        
                        File path = new File ("Excel proveedores.xlsx");
                        Desktop.getDesktop().open(path);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error excel: "+e.toString());
        }
        
    }
    
        public void ExcelEmpleados(){
        XSSFWorkbook libro = new XSSFWorkbook();//se crea el lbiro de excel
        XSSFSheet hoja = libro.createSheet("Empleados");//se crea una hoja con nombre excel ejemplo
        try {
                String sql ="select concat(nombre,' ',apaterno,' ',amaterno), rut, direccion, telefono, concat(fechacontrato,' - ',fechatermino),cargo,sueldo from empleado order by apaterno ";
            con=cn.getConnection();
            
                String nombre= "";
                String rut = "";  
                String direccion = "";
                String telefono = "";
                String fecha = "";
                String cargo = "";
                int sueldo;
                
                PreparedStatement pst;
                pst = con.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery();
                 //ESTILO AZUL----------------------
                 XSSFCellStyle style=libro.createCellStyle();
	style.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setBorderTop(BorderStyle.MEDIUM);
                style.setBorderBottom(BorderStyle.MEDIUM);
                style.setBorderLeft(BorderStyle.MEDIUM);
                style.setBorderRight(BorderStyle.MEDIUM);
                //ESTILO AZUL----------------------
                     XSSFRow row1 =hoja.createRow(0);
                    XSSFCell cell= row1.createCell(0);
                    cell.setCellValue("Lista de empleados");
                    cell.setCellStyle(style);
                    cell= row1.createCell(1);
                    cell.setCellValue("Los yuyitos");
                    cell.setCellStyle(style);
                    
                    //ESTILO GRIS----------------------
                    XSSFCellStyle style2=libro.createCellStyle();
                    style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style2.setBorderTop(BorderStyle.MEDIUM);
                    style2.setBorderBottom(BorderStyle.MEDIUM);
                    style2.setBorderLeft(BorderStyle.MEDIUM);
                    style2.setBorderRight(BorderStyle.MEDIUM);
                   //ESTILO GRIS----------------------
                   
                    row1 =hoja.createRow(1);
                    
                    cell= row1.createCell(0);
                    cell.setCellValue("Nombre");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(1);
                    cell.setCellValue("Rut");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(2);
                    cell.setCellValue("Dirección");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(3);
                    cell.setCellValue("Sueldo");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(4);
                    cell.setCellValue("Telefono");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(5);
                    cell.setCellValue("Fecha");
                    cell.setCellStyle(style2);
                    cell= row1.createCell(6);
                    cell.setCellValue("Cargo");
                    cell.setCellStyle(style2);
                     //ESTILO celeste con lineas delgadas----------------------
                    XSSFCellStyle style3=libro.createCellStyle();
                    style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
                    style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    style3.setBorderTop(BorderStyle.THIN);
                    style3.setBorderBottom(BorderStyle.THIN);
                    style3.setBorderLeft(BorderStyle.THIN);
                    style3.setBorderRight(BorderStyle.THIN);
                   //ESTILO celeste con lineas delgadas----------------------
                    
                    int i=0;
                       while(rs1.next()){
                           i=i+1;
                           int numeroHoja=1+i;
                           
                        nombre=(rs1.getString(1));
                         rut=(rs1.getString(2));
                         direccion=(rs1.getString(3));
                         telefono=(rs1.getString(4));
                         fecha=(rs1.getString(5));
                         cargo=(rs1.getString(6));
                         sueldo=(rs1.getInt(7));
                 
                            row1 =hoja.createRow(numeroHoja);
                            cell= row1.createCell(0);
                            cell.setCellValue(nombre);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(1);
                            cell.setCellValue(rut);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(2);
                            cell.setCellValue(direccion);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(3);
                            cell.setCellValue(sueldo);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(4);
                            cell.setCellValue(telefono);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(5);
                            cell.setCellValue(fecha);
                            cell.setCellStyle(style3);
                            cell= row1.createCell(6);
                            cell.setCellValue(cargo);
                            cell.setCellStyle(style3);
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       int numero = hoja.getLastRowNum();
                       
                       while(numero>=0){
                           hoja.autoSizeColumn(numero);
                           numero=numero-1;
                       }
                       //AJUSTAR TAMAÑO DE HOJAS (PARECE QUE EN EXCEL GRANDES TARDA MUCHO EN HACERLO)
                       

                       
                        FileOutputStream archivo = new FileOutputStream(new File("Excel Empleados.xlsx"));//se crea el archivo excel con nombre reporte
                        libro.write(archivo);
                        archivo.close();
                        
                        File path = new File ("Excel Empleados.xlsx");
                        Desktop.getDesktop().open(path);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error excel: "+e.toString());
        }
        
    }
//    ERROR NULLPOINTEXCEPTION CON ESTE METODO
//   public void ImprimirPantallaExcel(){
//            //PARA LEER EL EXCEL NO ABRIENDO EXCEL, PARA IMPRIMIRLO EN PANTALLA-------------------------
//
//        try {
//            FileInputStream leer = new FileInputStream("C:\\Users\\tavo-\\OneDrive\\Escritorio\\portafolio\\Carta gantt Portafolio.xlsx");
//            XSSFWorkbook libro2 = new XSSFWorkbook(leer);
//            XSSFSheet hoja2 = libro2.getSheetAt(0);//solo extraeria una hoja
//            
//            int numero_filas = hoja2.getLastRowNum();
//            
//            for(int i = 0; i<=numero_filas; i++){
//                Row fila = hoja2.getRow(i); //aqui obtiene todas las filas
//                int numero_columnas = fila.getLastCellNum();
//                
//                for(int j =0;j<numero_columnas;j++){
//                    
//                    Cell celda = fila.getCell(j);// obtendra las celdas 
//
//                        
//                    
//                    switch(celda.getCellTypeEnum().toString()){//obtendra el tipo de numero de la celda
//                        case "NUMERIC":
//                            System.out.println(celda.getNumericCellValue()+"");
//                            break;
//                        case "STRING":
//                            System.out.println(celda.getStringCellValue()+"");
//                            
//                            break;
//                        case "FORMULA":
//                            System.out.println(celda.getCellFormula()+"");
//                            break;
//                        
//                            
//                    }
//                  
//                }
//                 System.out.println("");
//                
//            }
//            //PARA LEER EL EXCEL NO ABRIENDO EXCEL, PARA IMPRIMIRLO EN PANTALLA------------------------------
//   }catch(Exception e){
//       JOptionPane.showMessageDialog(null, e.toString());
//   }
//}
}
