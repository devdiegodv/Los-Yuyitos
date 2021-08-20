/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.view;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.yuyitos.ch.bo.IngresarFichaClienteBO;
import com.yuyitos.ch.bo.IngresarFichaProveedorBO;
import com.yuyitos.ch.bo.TablaEmpleado;
import com.yuyitos.ch.dao.BuscarComboBoxDAO;
import com.yuyitos.ch.dao.InformesDAO;
import com.yuyitos.ch.dao.IngresarEmpleadoDAO;
import com.yuyitos.ch.dao.IngresarFichaClienteDAO;
import com.yuyitos.ch.dao.IngresarFichaProveedorDAO;
import com.yuyitos.ch.dao.IngresarPedidoDAO;
import com.yuyitos.ch.dao.IngresarVentaDAO;
import com.yuyitos.ch.db.Conexion;
import com.yuyitos.ch.entity.Cliente;
import com.yuyitos.ch.entity.Detalle;
import com.yuyitos.ch.entity.DetalleBoleta;
import com.yuyitos.ch.entity.Discrepancias;
import com.yuyitos.ch.entity.Empleado;

import com.yuyitos.ch.entity.Empresa;
import com.yuyitos.ch.entity.Factura;
import com.yuyitos.ch.entity.Repartidor;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author tavo-
 */
public class MenuPrincipal extends javax.swing.JFrame {
    BuscarComboBoxDAO  bcbDAO = new BuscarComboBoxDAO();
    Repartidor rep =new Repartidor();
    IngresarFichaClienteDAO ingresarcliente = new IngresarFichaClienteDAO();
    Cliente cli = new Cliente();
    Empresa emp = new Empresa();
    Detalle det = new Detalle();
    DetalleBoleta detb = new DetalleBoleta();
    Factura fact = new Factura();
    Discrepancias disc = new Discrepancias();
    IngresarVentaDAO ivdao = new IngresarVentaDAO();
    InformesDAO pdf = new InformesDAO();
    IngresarFichaProveedorDAO ingresarempresa = new IngresarFichaProveedorDAO();
    private IngresarFichaClienteBO ifcbo = new IngresarFichaClienteBO();
    private IngresarFichaProveedorBO ifpbo = new IngresarFichaProveedorBO();
    private TablaEmpleado te = new TablaEmpleado();
    Empleado empl = new Empleado();
    IngresarEmpleadoDAO iemp = new IngresarEmpleadoDAO();
    IngresarPedidoDAO ipdao = new IngresarPedidoDAO();
    Conexion cn = new Conexion();
    Connection con;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        listarCliente();
        listarEmpresa();
        listarEmpleado();
        


        
        //jTabbedPane1.setSelectedIndex(0);//para elegir en que panel iniciara (inecesario aqui) 
        datecFechaTermino.setMinSelectableDate(new Date());
        jTabbedPane1.setEnabled(false);
        
        Login Imagen = new Login();//solo para usar metodo modificar imagen
        Imagen.ModificarImagen(lblImagen, "src/com/yuyitos/ch/imagenes/grisclaro.JPG");
        setTitle("Menu Principal Yuyitos");
        setLocationRelativeTo(null);
        setResizable(false);
       
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
        
        //se crea primero por que se necesita el numero de factura para hacer detallefactura 
        //ivdao.agregarBoleta();SE ELIMINO AGREGARBOLETA Y AGREGARFACTURA AL PRINCIPIO DEBIDO A QUE CREABA MUCHOS DATOS EN 0 
        //POR LO QUE ESTOS SE CREAN AL Agregar producto en factura y agregar producto en boleta Y LOS TXT TXT QUE INDICABANSE RELLENAN CON EL MAX+1
        
       txtTotalPedido.setEditable(false);
       txtNumFactura.setEditable(false);
       txtIdProductoPedido.setEditable(false);
       txtEmpresaVenta.setEditable(false);
       txtStockVenta.setEditable(false);
       txtNumeroBoleta.setEditable(false);
       txtTotalVenta.setEditable(false);
       txtCantidadProductoRevisar.setEditable(false);
       txtTotalProductosRevisar.setEditable(false);
       txtEmpresaVenta.setEditable(false);
       txtPrecioUnitarioVenta.setEditable(false);
       txtStockVenta.setEditable(false);
       txtTotalVenta.setEditable(false);
       txtIDetalleVenta.setEditable(false);
       txtIdEmpresaRevisar.setEditable(false);
       txtID.setEditable(false);
       txtIdEmpleado.setEditable(false);
       txtIdEmpresa.setEditable(false);
       
        //txtNumFactura.setText((ipdao.NumFacturaImprimir()+1)+""); YA NO PORQUE BOTON GENERAR BOLETA HACE ESTOOOOOOOOOOOOOOOOO
        //.setText(ivdao.NumBoletaImprimir()+1+""); YA NO PORQUE BOTON GENERAR BOLETA HACE ESTOOOOOOOOOOOOOOOOO
        
        
        //listarProductoPedido2(); Solo se listará cuando le den a un btn
        
        txtTotalVenta.setText(SumaTotalBoleta()+"");
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnVerOrdenPedido = new javax.swing.JButton();
        btnVerCliente = new javax.swing.JButton();
        btnVerProveedor = new javax.swing.JButton();
        btnVerInformes = new javax.swing.JButton();
        btnVerEmpleado = new javax.swing.JButton();
        btnRevisarPedido = new javax.swing.JToggleButton();
        btnVerVenta = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelCliente = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        txtDVRut = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbIngresarFichaCliente = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtAbonarFiado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnAceptarFiadoCliente = new javax.swing.JButton();
        txtDeuda = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        btnCalcularDeuda = new javax.swing.JButton();
        PanelEmpleado = new javax.swing.JPanel();
        txtTelefonoEmpleado = new javax.swing.JTextField();
        txtDVRutEmpleado = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtRutEmpleado = new javax.swing.JTextField();
        txtNombreEmpleado = new javax.swing.JTextField();
        txtIdEmpleado = new javax.swing.JTextField();
        txtDireccionEmpleado = new javax.swing.JTextField();
        btnLimpiarEmpleado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JButton();
        btnModificarEmpleado = new javax.swing.JButton();
        btnAceptarEmpleado = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbIngresarEmpleado = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtSueldoEmpleado = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtApellidoPaternoEmpleado = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtApellidoMaternoEmpleado = new javax.swing.JTextField();
        datecFechaTermino = new com.toedter.calendar.JDateChooser();
        cboCargoEmpleado = new javax.swing.JComboBox<>();
        PanelEstadistica = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        btnPDFFiadosTotales = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        btnPDFBoletaMes = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        btnPDFProveedores = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        btnPDFEmpleados = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        btnGraficoVentas = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        btnWordVentasMes = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        btnWordProveedores = new javax.swing.JButton();
        btnWordEmpleados = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        btnWordFiados = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        btnTXTEmpleados = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        btnTXTProveedores = new javax.swing.JButton();
        btnTXTVentasMes = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        btnTXTFiados = new javax.swing.JButton();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        btnExcelFiados = new javax.swing.JButton();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        btnExcelVentasMes = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        btnExcelProveedores = new javax.swing.JButton();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        btnExcelEmpleados = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        btnGraficoFiados = new javax.swing.JButton();
        PanelProveedor = new javax.swing.JPanel();
        txtRubroProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        txtRutProveedor = new javax.swing.JTextField();
        txtDVRutProveedor = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtCorreoProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbIngresarProveedor = new javax.swing.JTable();
        btnLimpiarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnModificarProveedor = new javax.swing.JButton();
        btnAceptarProveedor = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtIdEmpresa = new javax.swing.JTextField();
        PanelCompra = new javax.swing.JPanel();
        btnAgregarProductos = new javax.swing.JButton();
        cboNombreEmpresa = new javax.swing.JComboBox<>();
        cboProducto = new javax.swing.JComboBox<>();
        txtPrecioPedido = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        btnQuitarCompra = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbProductosPedido = new javax.swing.JTable();
        txtTotalPedido = new javax.swing.JTextField();
        txtIdProductoPedido = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btnAceptarPedido = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        btnGenerarNFactura = new javax.swing.JButton();
        PanelPedido = new javax.swing.JPanel();
        btnAceptarOrden = new javax.swing.JButton();
        btnDiscrepancias = new javax.swing.JButton();
        cboNumFacturaRevisar = new javax.swing.JComboBox<>();
        cboNombreEmpresaRevisar = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbRevisarPedido = new javax.swing.JTable();
        txtEstadoProducto = new javax.swing.JTextField();
        txtCantidadProductosDiscrepancia = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtTotalProductosDiscrepancia = new javax.swing.JTextField();
        txtTotalProductosRevisar = new javax.swing.JTextField();
        txtCantidadProductoRevisar = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btnListarTablaRevisar = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtApellidoPaternoRevisar = new javax.swing.JTextField();
        txtIdEmpresaRevisar = new javax.swing.JTextField();
        txtDVRutRepartidorRevisar = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtNombreRepartidorRevisar = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txtRutRepartidorRevisar = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        PanelVenta = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtMontoAFiarVenta = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        btnAceptarVenta = new javax.swing.JButton();
        btnAgregarCompra = new javax.swing.JButton();
        txtTotalVenta = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cboProductoVenta = new javax.swing.JComboBox<>();
        btnQuitarCompraVenta = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbVentaBoleta = new javax.swing.JTable();
        txtCantidadVenta = new javax.swing.JTextField();
        txtEmpresaVenta = new javax.swing.JTextField();
        txtStockVenta = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtPrecioUnitarioVenta = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        txtNumeroBoleta = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtIDetalleVenta = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        cboRutVenta = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        btnGenerarBoleta = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        btnVerOrdenPedido.setText("Orden de pedido");
        btnVerOrdenPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerOrdenPedidoActionPerformed(evt);
            }
        });

        btnVerCliente.setText("Crear Cliente");
        btnVerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerClienteActionPerformed(evt);
            }
        });

        btnVerProveedor.setText("Crear proveedor");
        btnVerProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerProveedorActionPerformed(evt);
            }
        });

        btnVerInformes.setText("Ver informes");
        btnVerInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerInformesActionPerformed(evt);
            }
        });

        btnVerEmpleado.setText("Crear Empleado");
        btnVerEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEmpleadoActionPerformed(evt);
            }
        });

        btnRevisarPedido.setText("Revisar Orden pedido");
        btnRevisarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarPedidoActionPerformed(evt);
            }
        });

        btnVerVenta.setText("Nueva venta");
        btnVerVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerInformes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRevisarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnVerProveedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                .addComponent(btnVerOrdenPedido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerEmpleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(btnVerCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerVenta)
                .addGap(18, 18, 18)
                .addComponent(btnVerProveedor)
                .addGap(18, 18, 18)
                .addComponent(btnVerOrdenPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRevisarPedido)
                .addGap(12, 12, 12)
                .addComponent(btnVerEmpleado)
                .addGap(18, 18, 18)
                .addComponent(btnVerInformes)
                .addGap(84, 84, 84))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 540));

        PanelCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Ingresar ficha de Cliente");
        PanelCliente.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jLabel1.setText("-");
        PanelCliente.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 10, -1));

        jLabel2.setText("Dirección");
        PanelCliente.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        PanelCliente.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        txtDVRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDVRutKeyTyped(evt);
            }
        });
        PanelCliente.add(txtDVRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 30, -1));

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        PanelCliente.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 150, -1));

        tbIngresarFichaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbIngresarFichaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIngresarFichaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbIngresarFichaCliente);
        if (tbIngresarFichaCliente.getColumnModel().getColumnCount() > 0) {
            tbIngresarFichaCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbIngresarFichaCliente.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        PanelCliente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 760, 250));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        PanelCliente.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, -1, -1));

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        PanelCliente.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, -1, -1));

        jLabel4.setText("ID (eliminar/modificar)");
        PanelCliente.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutActionPerformed(evt);
            }
        });
        txtRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutKeyTyped(evt);
            }
        });
        PanelCliente.add(txtRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 80, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        PanelCliente.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 150, -1));

        jLabel5.setText("2° Apellido");
        PanelCliente.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        PanelCliente.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, -1, -1));

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDKeyTyped(evt);
            }
        });
        PanelCliente.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 80, -1));

        jLabel7.setText("Rut: 20444555");
        PanelCliente.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jLabel17.setText("- - - - - - - - - - - - - - -");
        PanelCliente.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        txtAbonarFiado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbonarFiadoKeyTyped(evt);
            }
        });
        PanelCliente.add(txtAbonarFiado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 60, -1));

        jLabel8.setText("DV Rut");
        PanelCliente.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        txtApellidoPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPaternoKeyTyped(evt);
            }
        });
        PanelCliente.add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 80, -1));

        txtApellidoMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoMaternoKeyTyped(evt);
            }
        });
        PanelCliente.add(txtApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 90, -1));

        jLabel9.setText("Nombre");
        PanelCliente.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        jLabel12.setText("1° Apellido");
        PanelCliente.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, -1, -1));

        btnAceptarFiadoCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptarFiadoCliente.setText("Aceptar fiado");
        btnAceptarFiadoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarFiadoClienteActionPerformed(evt);
            }
        });
        PanelCliente.add(btnAceptarFiadoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, 30));

        txtDeuda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDeudaKeyTyped(evt);
            }
        });
        PanelCliente.add(txtDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 60, -1));

        jLabel75.setText("Abonar fiado");
        PanelCliente.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel76.setText("Deuda");
        PanelCliente.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        btnCalcularDeuda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCalcularDeuda.setText("Calcular deuda");
        btnCalcularDeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularDeudaActionPerformed(evt);
            }
        });
        PanelCliente.add(btnCalcularDeuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 30));

        jTabbedPane1.addTab("Ficha Cliente", PanelCliente);

        PanelEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefonoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtTelefonoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 120, -1));

        txtDVRutEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDVRutEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtDVRutEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 30, -1));

        jLabel22.setFont(new java.awt.Font("Rockwell Extra Bold", 3, 14)); // NOI18N
        jLabel22.setText("-");
        PanelEmpleado.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 17, -1));

        txtRutEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtRutEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 80, -1));

        txtNombreEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 90, -1));

        txtIdEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEmpleadoActionPerformed(evt);
            }
        });
        txtIdEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtIdEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 90, -1));

        txtDireccionEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtDireccionEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 90, -1));

        btnLimpiarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLimpiarEmpleado.setText("Limpiar");
        btnLimpiarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarEmpleadoActionPerformed(evt);
            }
        });
        PanelEmpleado.add(btnLimpiarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        btnEliminarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarEmpleado.setText("Eliminar");
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });
        PanelEmpleado.add(btnEliminarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, -1));

        btnModificarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModificarEmpleado.setText("Modificar");
        btnModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpleadoActionPerformed(evt);
            }
        });
        PanelEmpleado.add(btnModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        btnAceptarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptarEmpleado.setText("Aceptar");
        btnAceptarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarEmpleadoActionPerformed(evt);
            }
        });
        PanelEmpleado.add(btnAceptarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, -1, -1));

        tbIngresarEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbIngresarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIngresarEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbIngresarEmpleado);

        PanelEmpleado.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 890, 230));

        jLabel23.setText("Dirección");
        PanelEmpleado.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel24.setText("Cargo");
        PanelEmpleado.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel25.setText("ID (Modificar/eliminar)");
        PanelEmpleado.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel26.setText("ApellidoMaterno");
        PanelEmpleado.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, -1, -1));

        jLabel27.setText("Rut");
        PanelEmpleado.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        jLabel28.setText("DV Rut");
        PanelEmpleado.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 39, -1));

        jLabel29.setText("Fecha Termino de Contrato");
        PanelEmpleado.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 180, -1));

        jLabel30.setText("Sueldo: 320000");
        PanelEmpleado.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Ingresar Información de Empleado");
        PanelEmpleado.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel32.setText("Telefono: 934813738");
        PanelEmpleado.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, -1, -1));

        txtSueldoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtSueldoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 90, -1));

        jLabel33.setText("Nombre");
        PanelEmpleado.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        txtApellidoPaternoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPaternoEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtApellidoPaternoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 90, -1));

        jLabel34.setText("Apellido Paterno");
        PanelEmpleado.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        txtApellidoMaternoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoMaternoEmpleadoKeyTyped(evt);
            }
        });
        PanelEmpleado.add(txtApellidoMaternoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 90, -1));

        datecFechaTermino.setDateFormatString("yyyy-MM-dd");
        PanelEmpleado.add(datecFechaTermino, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 160, -1));

        cboCargoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Cajero", "Administrador", "Bodeguero" }));
        cboCargoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCargoEmpleadoActionPerformed(evt);
            }
        });
        PanelEmpleado.add(cboCargoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 110, -1));

        jTabbedPane1.addTab("Información Empleado", PanelEmpleado);

        PanelEstadistica.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setText("Informes");
        PanelEstadistica.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, -1));

        jLabel77.setText("Información Empleados");
        PanelEstadistica.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, -1, -1));

        jLabel78.setText("Generar PDF ");
        PanelEstadistica.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));

        btnPDFFiadosTotales.setText("Generar pdf");
        btnPDFFiadosTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFFiadosTotalesActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnPDFFiadosTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        jLabel79.setText("Productos mas vendidos");
        PanelEstadistica.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, 40));

        jLabel80.setText("fiados actuales");
        PanelEstadistica.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        btnPDFBoletaMes.setText("Generar pdf");
        btnPDFBoletaMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFBoletaMesActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnPDFBoletaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, -1));

        jLabel81.setText("Generar PDF ");
        PanelEstadistica.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, 20));

        jLabel82.setText("de Ventas por boleta");
        PanelEstadistica.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, 30));

        btnPDFProveedores.setText("Generar pdf");
        btnPDFProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFProveedoresActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnPDFProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, -1));

        jLabel83.setText("Generar PDF ");
        PanelEstadistica.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        jLabel84.setText("mes actual");
        PanelEstadistica.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, 20));

        btnPDFEmpleados.setText("Generar pdf");
        btnPDFEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFEmpleadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnPDFEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        jLabel85.setText("Proveedores");
        PanelEstadistica.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        btnGraficoVentas.setText("Generar grafico");
        btnGraficoVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoVentasActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnGraficoVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 130, -1));

        jLabel86.setText("Generar PDF ");
        PanelEstadistica.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, 20));

        jLabel87.setText("Generar word");
        PanelEstadistica.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, 40));

        jLabel89.setText("Grafico productos mas vendido");
        PanelEstadistica.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, -1, 40));

        btnWordVentasMes.setText("Generar word");
        btnWordVentasMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWordVentasMesActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnWordVentasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jLabel90.setText("Generar word");
        PanelEstadistica.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, -1));

        jLabel91.setText("Proveedores");
        PanelEstadistica.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        btnWordProveedores.setText("Generar word");
        btnWordProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWordProveedoresActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnWordProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, -1, -1));

        btnWordEmpleados.setText("Generar word");
        btnWordEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWordEmpleadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnWordEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, -1, -1));

        jLabel92.setText("Información Empleados");
        PanelEstadistica.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, -1, -1));

        jLabel93.setText("Generar word ");
        PanelEstadistica.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        btnWordFiados.setText("Generar word");
        btnWordFiados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWordFiadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnWordFiados, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jLabel94.setText("fiados actuales");
        PanelEstadistica.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        jLabel95.setText("Generar PDF ");
        PanelEstadistica.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, 20));

        btnTXTEmpleados.setText("Generar txt");
        btnTXTEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTXTEmpleadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnTXTEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, -1, -1));

        jLabel96.setText("Información Empleados");
        PanelEstadistica.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, -1, -1));

        jLabel97.setText("Generar txt ");
        PanelEstadistica.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        jLabel98.setText("Generar txt");
        PanelEstadistica.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, -1, -1));

        jLabel99.setText("Proveedores");
        PanelEstadistica.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, -1, -1));

        btnTXTProveedores.setText("Generar txt");
        btnTXTProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTXTProveedoresActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnTXTProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, -1, -1));

        btnTXTVentasMes.setText("Generar txt");
        btnTXTVentasMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTXTVentasMesActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnTXTVentasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        jLabel100.setText("Productos mas vendidos");
        PanelEstadistica.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, 40));

        jLabel101.setText("Generar txt");
        PanelEstadistica.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, 40));

        btnTXTFiados.setText("Generar txt");
        btnTXTFiados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTXTFiadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnTXTFiados, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        jLabel102.setText("fiados actuales");
        PanelEstadistica.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        jLabel103.setText("Generar txt ");
        PanelEstadistica.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, 20));

        btnExcelFiados.setText("Generar Excel");
        btnExcelFiados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelFiadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnExcelFiados, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jLabel104.setText("fiados actuales");
        PanelEstadistica.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        jLabel105.setText("Generar Excel ");
        PanelEstadistica.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, 20));

        btnExcelVentasMes.setText("Generar Excel");
        btnExcelVentasMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelVentasMesActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnExcelVentasMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, -1, -1));

        jLabel106.setText("Productos mas vendidos");
        PanelEstadistica.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, -1, 40));

        jLabel107.setText("Generar Excel");
        PanelEstadistica.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, 40));

        btnExcelProveedores.setText("Generar Excel");
        btnExcelProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProveedoresActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnExcelProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, -1, -1));

        jLabel108.setText("Proveedores");
        PanelEstadistica.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, -1, -1));

        jLabel109.setText("Generar Excel");
        PanelEstadistica.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, -1));

        btnExcelEmpleados.setText("Generar Excel");
        btnExcelEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelEmpleadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnExcelEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, -1, -1));

        jLabel110.setText("Información Empleados");
        PanelEstadistica.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, -1, -1));

        jLabel111.setText("Generar Excel");
        PanelEstadistica.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 290, -1, -1));

        jLabel112.setText("Grafico Fiados");
        PanelEstadistica.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, 40));

        btnGraficoFiados.setText("Generar grafico");
        btnGraficoFiados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoFiadosActionPerformed(evt);
            }
        });
        PanelEstadistica.add(btnGraficoFiados, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 130, 30));

        jTabbedPane1.addTab("Informes", PanelEstadistica);

        PanelProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRubroProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRubroProveedorActionPerformed(evt);
            }
        });
        txtRubroProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRubroProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtRubroProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, -1));

        txtDireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 70, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Ingresar ficha de proveedor");
        PanelProveedor.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jLabel3.setText("ID (Modificar/eliminar)");
        PanelProveedor.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel6.setText("Rut");
        PanelProveedor.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        jLabel15.setText("Rubro");
        PanelProveedor.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel16.setText("Dirección");
        PanelProveedor.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 90, -1));

        txtRutProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtRutProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 80, -1));

        txtDVRutProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDVRutProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtDVRutProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 30, -1));

        jLabel18.setText("DV Rut");
        PanelProveedor.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 39, -1));

        jLabel19.setText("-");
        PanelProveedor.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 17, -1));

        jLabel20.setText("Telefono: 934813738");
        PanelProveedor.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, -1));

        jLabel21.setText("Correo");
        PanelProveedor.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 57, -1));

        txtTelefonoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 120, -1));

        txtCorreoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoProveedorKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtCorreoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 140, -1));

        tbIngresarProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbIngresarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIngresarProveedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbIngresarProveedor);

        PanelProveedor.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 830, 230));

        btnLimpiarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLimpiarProveedor.setText("Limpiar");
        btnLimpiarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarProveedorActionPerformed(evt);
            }
        });
        PanelProveedor.add(btnLimpiarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, -1));

        btnEliminarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarProveedor.setText("Eliminar");
        btnEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProveedorActionPerformed(evt);
            }
        });
        PanelProveedor.add(btnEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, -1));

        btnModificarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModificarProveedor.setText("Modificar");
        btnModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProveedorActionPerformed(evt);
            }
        });
        PanelProveedor.add(btnModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, -1));

        btnAceptarProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAceptarProveedor.setText("Aceptar");
        btnAceptarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarProveedorActionPerformed(evt);
            }
        });
        PanelProveedor.add(btnAceptarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, -1, -1));

        jLabel13.setText("Nombre Empresa");
        PanelProveedor.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        txtIdEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdEmpresaKeyTyped(evt);
            }
        });
        PanelProveedor.add(txtIdEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, -1));

        jTabbedPane1.addTab("Ficha Proveedor", PanelProveedor);

        PanelCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarProductos.setText("Agregar productos");
        btnAgregarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductosActionPerformed(evt);
            }
        });
        PanelCompra.add(btnAgregarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        cboNombreEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNombreEmpresaActionPerformed(evt);
            }
        });
        PanelCompra.add(cboNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 220, -1));

        cboProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelCompra.add(cboProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 360, -1));
        cboProducto.getAccessibleContext().setAccessibleDescription("");

        txtPrecioPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioPedidoKeyTyped(evt);
            }
        });
        PanelCompra.add(txtPrecioPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 100, -1));

        jLabel36.setText("Buscar por nombre Empresa");
        PanelCompra.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel38.setText("Precio");
        PanelCompra.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        btnQuitarCompra.setText("Quitar de la compra");
        btnQuitarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarCompraActionPerformed(evt);
            }
        });
        PanelCompra.add(btnQuitarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, -1, -1));

        jLabel40.setText("Productos");
        PanelCompra.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        PanelCompra.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 100, -1));

        jLabel39.setText("N° Factura");
        PanelCompra.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 80, 30));

        tbProductosPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbProductosPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosPedidoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbProductosPedido);

        PanelCompra.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 610, 180));

        txtTotalPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalPedidoKeyTyped(evt);
            }
        });
        PanelCompra.add(txtTotalPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 100, -1));

        txtIdProductoPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdProductoPedidoKeyTyped(evt);
            }
        });
        PanelCompra.add(txtIdProductoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 100, -1));

        jLabel41.setText("Cantidad");
        PanelCompra.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, 20));

        jLabel42.setText("Para eliminar eliga algún producto");
        PanelCompra.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, 20));

        txtNumFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumFacturaKeyTyped(evt);
            }
        });
        PanelCompra.add(txtNumFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 100, -1));

        jLabel43.setText("de la tabla con el mouse.");
        PanelCompra.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, 160, 30));

        btnAceptarPedido.setText("Aceptar pedido");
        btnAceptarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarPedidoActionPerformed(evt);
            }
        });
        PanelCompra.add(btnAceptarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Ingresar Pedido de compra");
        PanelCompra.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        btnGenerarNFactura.setText("Generar numero factura");
        btnGenerarNFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarNFacturaActionPerformed(evt);
            }
        });
        PanelCompra.add(btnGenerarNFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, -1, -1));

        jTabbedPane1.addTab("Pedido Compra", PanelCompra);

        PanelPedido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAceptarOrden.setText("Aceptar");
        btnAceptarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarOrdenActionPerformed(evt);
            }
        });
        PanelPedido.add(btnAceptarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, -1, -1));

        btnDiscrepancias.setText("Generar Discrepancias");
        btnDiscrepancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscrepanciasActionPerformed(evt);
            }
        });
        PanelPedido.add(btnDiscrepancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 390, -1, -1));

        PanelPedido.add(cboNumFacturaRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 140, -1));

        cboNombreEmpresaRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNombreEmpresaRevisarActionPerformed(evt);
            }
        });
        PanelPedido.add(cboNombreEmpresaRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 200, -1));

        jLabel37.setText("Estado Productos");
        PanelPedido.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 110, -1));

        jLabel44.setText("Apellido paterno");
        PanelPedido.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, -1, -1));

        tbRevisarPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRevisarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRevisarPedidoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbRevisarPedido);

        PanelPedido.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 700, 210));

        txtEstadoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoProductoKeyTyped(evt);
            }
        });
        PanelPedido.add(txtEstadoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 350, 110, -1));

        txtCantidadProductosDiscrepancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductosDiscrepanciaKeyTyped(evt);
            }
        });
        PanelPedido.add(txtCantidadProductosDiscrepancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, 110, -1));

        jLabel45.setText("paso 1: Buscar por proveedor");
        PanelPedido.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel46.setText("Cantidad Productos");
        PanelPedido.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, -1, -1));

        jLabel47.setText("Total Productos");
        PanelPedido.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, -1, -1));

        txtTotalProductosDiscrepancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalProductosDiscrepanciaKeyTyped(evt);
            }
        });
        PanelPedido.add(txtTotalProductosDiscrepancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 280, 110, -1));
        PanelPedido.add(txtTotalProductosRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, 110, -1));
        PanelPedido.add(txtCantidadProductoRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 110, -1));

        jLabel48.setText("-------- Opcional ---------");
        PanelPedido.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, -1, -1));

        jLabel49.setText("Total Productos");
        PanelPedido.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, -1, -1));

        btnListarTablaRevisar.setText("Listar tabla");
        btnListarTablaRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTablaRevisarActionPerformed(evt);
            }
        });
        PanelPedido.add(btnListarTablaRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        jLabel50.setText("Cantidad Productos");
        PanelPedido.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, -1, -1));

        jLabel51.setText("Ingrese la información para cada tipo de");
        PanelPedido.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, -1, -1));

        txtApellidoPaternoRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPaternoRevisarActionPerformed(evt);
            }
        });
        txtApellidoPaternoRevisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPaternoRevisarKeyTyped(evt);
            }
        });
        PanelPedido.add(txtApellidoPaternoRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, 110, -1));

        txtIdEmpresaRevisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdEmpresaRevisarKeyTyped(evt);
            }
        });
        PanelPedido.add(txtIdEmpresaRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 70, -1));

        txtDVRutRepartidorRevisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDVRutRepartidorRevisarKeyTyped(evt);
            }
        });
        PanelPedido.add(txtDVRutRepartidorRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 50, -1));

        jLabel52.setText("paso 2: Buscar por N° factura");
        PanelPedido.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jLabel53.setText("Datos Repartidor (obligatorio)");
        PanelPedido.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));

        jLabel54.setText("DV");
        PanelPedido.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel55.setText("Id Empresa");
        PanelPedido.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, -1, -1));

        txtNombreRepartidorRevisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreRepartidorRevisarKeyTyped(evt);
            }
        });
        PanelPedido.add(txtNombreRepartidorRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, 90, -1));

        jLabel56.setText("-");
        PanelPedido.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 20, 20));

        jLabel57.setText("Nombre");
        PanelPedido.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, -1, -1));

        jLabel58.setText("Rut");
        PanelPedido.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        txtRutRepartidorRevisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRutRepartidorRevisarKeyTyped(evt);
            }
        });
        PanelPedido.add(txtRutRepartidorRevisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 70, -1));

        jLabel59.setText("producto que posea discrepancias.");
        PanelPedido.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, -1, -1));

        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel74.setText("Revisar Pedido");
        PanelPedido.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jTabbedPane1.addTab("Revisar Pedido", PanelPedido);

        PanelVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));

        txtMontoAFiarVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoAFiarVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoAFiarVentaKeyTyped(evt);
            }
        });

        jLabel62.setText("Ingresar monto a fiar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMontoAFiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel62)
                .addGap(28, 28, 28)
                .addComponent(txtMontoAFiarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        PanelVenta.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 270, 200));

        btnAceptarVenta.setText("Aceptar");
        btnAceptarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarVentaActionPerformed(evt);
            }
        });
        PanelVenta.add(btnAceptarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, -1, -1));

        btnAgregarCompra.setText("Agregar a la compra");
        btnAgregarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCompraActionPerformed(evt);
            }
        });
        PanelVenta.add(btnAgregarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        txtTotalVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalVentaActionPerformed(evt);
            }
        });
        PanelVenta.add(txtTotalVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 77, -1));

        jLabel63.setText("Precio c/u");
        PanelVenta.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        jLabel64.setText("Buscar Nombre Producto");
        PanelVenta.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("Venta");
        PanelVenta.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 6, -1, -1));

        jLabel66.setText("Contraseña para poder fiar");
        PanelVenta.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, -1, -1));

        cboProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProductoVentaActionPerformed(evt);
            }
        });
        PanelVenta.add(cboProductoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 74, 170, -1));

        btnQuitarCompraVenta.setText("Quitar de la compra");
        btnQuitarCompraVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarCompraVentaActionPerformed(evt);
            }
        });
        PanelVenta.add(btnQuitarCompraVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 150, -1));

        tbVentaBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbVentaBoleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVentaBoletaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbVentaBoleta);

        PanelVenta.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 530, 200));

        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });
        PanelVenta.add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 77, -1));

        txtEmpresaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpresaVentaActionPerformed(evt);
            }
        });
        PanelVenta.add(txtEmpresaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 77, -1));
        PanelVenta.add(txtStockVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 50, -1));

        jLabel67.setText("N° Boleta");
        PanelVenta.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, -1));

        jLabel68.setText("Stock");
        PanelVenta.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));
        PanelVenta.add(txtPrecioUnitarioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 77, -1));

        jLabel69.setText("Empresa");
        PanelVenta.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));
        PanelVenta.add(txtNumeroBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 77, -1));

        jLabel70.setText("Cantidad");
        PanelVenta.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));
        PanelVenta.add(txtIDetalleVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, 77, -1));

        jLabel71.setText("Total boleta:");
        PanelVenta.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, -1, -1));

        jLabel72.setText("ID producto ");
        PanelVenta.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, -1, -1));

        cboRutVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRutVentaActionPerformed(evt);
            }
        });
        PanelVenta.add(cboRutVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 137, -1));

        jLabel60.setText("Buscar por rut Cliente");
        PanelVenta.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, -1));

        jLabel73.setText("Fiar");
        PanelVenta.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, -1, -1));

        btnGenerarBoleta.setText("Generar Boleta");
        btnGenerarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarBoletaActionPerformed(evt);
            }
        });
        PanelVenta.add(btnGenerarBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, -1, -1));
        PanelVenta.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 120, 20));

        jTabbedPane1.addTab("Venta", PanelVenta);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 1010, 460));

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 2, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 51, 255));
        jLabel10.setText("Los YUYITOS");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 155, -1));

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/yuyitos/ch/imagenes/grisclaro.jpg"))); // NOI18N
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 1020, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerProveedorActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnVerProveedorActionPerformed

    public void ocultarCajero(){
        jTabbedPane1.setSelectedIndex(0);
        btnVerEmpleado.setVisible(false);
        btnVerProveedor.setVisible(false);
        btnVerOrdenPedido.setVisible(false);
        btnVerInformes.setVisible(false);
        btnRevisarPedido.setVisible(false);
        PanelEmpleado.setVisible(false);
        PanelProveedor.setVisible(false);
        PanelPedido.setVisible(false);
        PanelCompra.setVisible(false);
        PanelEstadistica.setVisible(false);
    }
public void ocultarbodeguero(){
        jTabbedPane1.setSelectedIndex(4);
        btnVerEmpleado.setVisible(false);
        btnVerInformes.setVisible(false);
        btnVerCliente.setVisible(false);
        btnVerVenta.setVisible(false);
        PanelEmpleado.setVisible(false);
        PanelCliente.setVisible(false);
        PanelEstadistica.setVisible(false);
        PanelVenta.setVisible(false);
    }
  
    
    private void btnVerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerClienteActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnVerClienteActionPerformed

    private void btnVerOrdenPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerOrdenPedidoActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnVerOrdenPedidoActionPerformed

    private void btnVerEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEmpleadoActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnVerEmpleadoActionPerformed

    private void btnRevisarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarPedidoActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnRevisarPedidoActionPerformed

    private void tbVentaBoletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVentaBoletaMouseClicked
        // TODO add your handling code here:
        int seleccion = tbVentaBoleta.rowAtPoint(evt.getPoint());
        
        txtIDetalleVenta.setText(tbVentaBoleta.getValueAt(seleccion, 0)+"");
    
    }//GEN-LAST:event_tbVentaBoletaMouseClicked

    private void btnQuitarCompraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarCompraVentaActionPerformed
        // TODO add your handling code here:
        int count= tbVentaBoleta.getModel().getRowCount();
        System.out.println(count+"");
        if(txtIDetalleVenta.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe seleccionar algún producto."
                    + " en caso de que ya seleccionará algun producto entonces vuelva a dar click hasta que se llene el cuadro id producto.");
        }else if(count==0){
            JOptionPane.showMessageDialog(null, "Para Eliminar un producto Debe existir un producto en la tabla..");//de esta forma no se borrará algún
            //producto por haber seleccionado el producto luego aceptado el pedido y luego eliminado el el producto (Tambien sirve vaciando el txt al aceptar el pedido)
        }else{

            ivdao.EliminarDetalleBoleta(txtIDetalleVenta);
            listarProductoBoleta();
            txtTotalVenta.setText(SumaTotalBoleta()+"");
            txtIDetalleVenta.setText("");
            
            cboNombreEmpresa.removeAllItems();
            cboNombreEmpresaRevisar.removeAllItems();
            cboProductoVenta.removeAllItems();
            cboRutVenta.removeAllItems();
            bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
            bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
            bcbDAO.BuscarProductoVenta(cboProductoVenta);
            bcbDAO.BuscarRutCliente(cboRutVenta);
        }
    }//GEN-LAST:event_btnQuitarCompraVentaActionPerformed

    private void cboProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProductoVentaActionPerformed
        // TODO add your handling code here:
        ivdao.ListarProductosEmpresaPedidoCBO(cboProductoVenta, txtEmpresaVenta,txtStockVenta,txtPrecioUnitarioVenta);
    }//GEN-LAST:event_cboProductoVentaActionPerformed

    private void txtTotalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentaActionPerformed

    private void btnAgregarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCompraActionPerformed
        // TODO add your handling code here:
        if(cboProductoVenta.getSelectedItem()=="" || txtCantidadVenta.getText().isEmpty()||txtPrecioUnitarioVenta.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "Debe llenar algun campo de empresa.");

        }else if(Integer.parseInt(txtCantidadVenta.getText())>Integer.parseInt(txtStockVenta.getText())){
            JOptionPane.showMessageDialog(null, "la cantidad a vender no debe superar el stock de producto.");
        }else if(txtNumeroBoleta.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe generar una boleta para agregar un detalle");
        }else if((ivdao.CompararProductoDetalleBolleta(cboProductoVenta, txtNumeroBoleta))==false){
            JOptionPane.showMessageDialog(null, "No debe existir algún producto repetido");
        }else{
           
            
            //                String producto=cboProducto.getSelectedItem().toString();

            try {
                String sql="select codproducto from producto where descripcion=?";
                con=cn.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, (String)cboProductoVenta.getSelectedItem());

                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    detb.setProducto(Integer.parseInt(rs.getString(1)));
                }
                detb.setCantidad(Integer.parseInt(txtCantidadVenta.getText()));
                int resultado=Integer.parseInt(txtCantidadVenta.getText())*Integer.parseInt(txtPrecioUnitarioVenta.getText());
                detb.setPrecio(resultado);

                ivdao.agregardetalleboleta(detb);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                System.out.println(e.toString());
            }
            
            listarProductoBoleta();
            txtTotalVenta.setText(SumaTotalBoleta()+"");

        }

    }//GEN-LAST:event_btnAgregarCompraActionPerformed

    private void btnAceptarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarVentaActionPerformed
        // TODO add your handling code here:

        
        
        if ("0".equals(txtTotalVenta.getText())||cboRutVenta.getSelectedItem()==""||cboProductoVenta.getSelectedItem()==""){
            JOptionPane.showMessageDialog(null, ": Para aceptar pedido boleta debe agregar algún producto mas el rut usuario.");
        }else {
            if(txtMontoAFiarVenta.getText().isEmpty() && Arrays.equals( "".toCharArray(), txtpassword.getPassword())){
                
                 ivdao.agregarventa(txtNumeroBoleta);
                    ivdao.ModificaBoleta(txtTotalVenta,txtNumeroBoleta);
                    ivdao.ActualizarStockVenta(txtNumeroBoleta);
                    ivdao.PDFBoleta(txtNumeroBoleta);
                   limpiar();
            }
            
            else if(((Arrays.equals( "pass".toCharArray(), txtpassword.getPassword())) && !txtMontoAFiarVenta.getText().isEmpty()) && 
                 Integer.parseInt(txtMontoAFiarVenta.getText())<Integer.parseInt(txtTotalVenta.getText())){
                ivdao.agregarventa(txtNumeroBoleta);
              ivdao.ModificaBoleta(txtTotalVenta,txtNumeroBoleta);
              ivdao.ActualizarStockVenta(txtNumeroBoleta);
                ivdao.agregarFiado(txtMontoAFiarVenta, cboRutVenta);
                ivdao.ModificarFiadoCliente(cboRutVenta);
                 ivdao.PDFBoleta(txtNumeroBoleta);
                   limpiar();
             }
             else{
                 JOptionPane.showMessageDialog(null, "Error, contraseña incorrecta y/o monto fiado Incorrecto");
             }
            
        }
    }//GEN-LAST:event_btnAceptarVentaActionPerformed

    private void txtApellidoPaternoRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPaternoRevisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPaternoRevisarActionPerformed

    private void btnListarTablaRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTablaRevisarActionPerformed
        // TODO add your handling code here:
        listarProductoPedido2();

    }//GEN-LAST:event_btnListarTablaRevisarActionPerformed

    private void tbRevisarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRevisarPedidoMouseClicked
        // TODO add your handling code here:
        int seleccion = tbRevisarPedido.rowAtPoint(evt.getPoint());

        txtCantidadProductoRevisar.setText(tbRevisarPedido.getValueAt(seleccion, 5)+"");
        txtTotalProductosRevisar.setText(tbRevisarPedido.getValueAt(seleccion, 6)+"");
    }//GEN-LAST:event_tbRevisarPedidoMouseClicked

    private void cboNombreEmpresaRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNombreEmpresaRevisarActionPerformed
        // TODO add your handling code here:
        bcbDAO.ListarNFacturaNCBO(cboNombreEmpresaRevisar, cboNumFacturaRevisar);
        ipdao.ListarIdEmpresa(cboNombreEmpresaRevisar,txtIdEmpresaRevisar);
    }//GEN-LAST:event_cboNombreEmpresaRevisarActionPerformed

    private void btnDiscrepanciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscrepanciasActionPerformed
        // TODO add your handling code here
        if(txtCantidadProductoRevisar.getText().isEmpty()||txtCantidadProductosDiscrepancia.getText().isEmpty()||txtTotalProductosRevisar.getText().isEmpty()
            ||txtTotalProductosDiscrepancia.getText().isEmpty()||cboNumFacturaRevisar.getSelectedItem()=="")
        {
            JOptionPane.showMessageDialog(null, "error, rellene los campos correspondientes para realizar la discrepancia");
        }else{

            disc.setCantidad(Integer.parseInt(txtCantidadProductosDiscrepancia.getText()));
            disc.setInformacionAdicional(txtEstadoProducto.getText());
            disc.setPrecio(Integer.parseInt(txtTotalProductosDiscrepancia.getText()));
            disc.setNumfactura(Integer.parseInt((String)cboNumFacturaRevisar.getSelectedItem()));
            ipdao.agregarDiscrepancia(disc);
        }
    }//GEN-LAST:event_btnDiscrepanciasActionPerformed

    private void btnAceptarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarOrdenActionPerformed
        // TODO add your handling code here:
        //         int seleccion = tbIngresarFichaCliente.rowAtPoint(evt.getPoint());
        //
        //        txtIdProductoPedido.setText(tbProductosPedido.getValueAt(seleccion, 2)+"");
        if(txtRutRepartidorRevisar.getText().isEmpty()||txtApellidoPaternoRevisar.getText().isEmpty()||txtNombreRepartidorRevisar.getText().isEmpty()
            ||txtIdEmpresaRevisar.getText().isEmpty()||txtDVRutRepartidorRevisar.getText().isEmpty()||cboNumFacturaRevisar.getSelectedItem()==""||
            cboNombreEmpresaRevisar.getSelectedItem()==""){
            JOptionPane.showMessageDialog(null, "Error, rellene todos los campos correspondientes");
        }else{

            char dvchar=txtDVRutRepartidorRevisar.getText().charAt(0);
            txtIdEmpresaRevisar.setText(ipdao.NombreEmpresaImprimir(cboNombreEmpresaRevisar));
            rep.setApellidoPaterno(txtApellidoPaternoRevisar.getText());
            rep.setNombre(txtNombreRepartidorRevisar.getText());
            rep.setRut(Integer.parseInt(txtRutRepartidorRevisar.getText()));
            rep.setDv(dvchar);
            rep.setIdEmpresa(Integer.parseInt(txtIdEmpresaRevisar.getText()));

            ipdao.agregarRepartidor(rep);
            ipdao.agregarpedido(cboNumFacturaRevisar);
            ipdao.ActualizarStock(cboNumFacturaRevisar);//ESTO DESPUES DE AGREGAR PEDIDO YA QUE REQUIERE QUE ESTE CREADO PEDIDO
            ipdao.PDFFactura(txtNombreRepartidorRevisar, cboNumFacturaRevisar);
            
            limpiar();
        }
    }//GEN-LAST:event_btnAceptarOrdenActionPerformed

    private void btnAceptarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarPedidoActionPerformed
        // TODO add your handling code here:
        int count= tbProductosPedido.getModel().getRowCount();
        System.out.println(count+"");
        if(count==0){
            JOptionPane.showMessageDialog(null, count+": Para aceptar pedido debe elegir algún producto.");
        }else{

           
             fact.setNumFactura(Integer.parseInt(txtNumFactura.getText()));
            fact.setTotal(Integer.parseInt(txtTotalPedido.getText()));
            
            ipdao.ModificarFactura(fact );//ejecutar primero el modificar debido a que el metodo agregar factura crea una nueva factura
            //para que al aceptar la factura se cree otra factura

            JOptionPane.showMessageDialog(null, count+": Pedido aceptado con exito.");
            JTable table;
            DefaultTableModel model = (DefaultTableModel) tbProductosPedido.getModel();
            model.setRowCount(0);

            limpiar();
        }
    }//GEN-LAST:event_btnAceptarPedidoActionPerformed

    private void txtNumFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumFacturaKeyTyped

    private void txtIdProductoPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProductoPedidoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductoPedidoKeyTyped

    private void txtTotalPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalPedidoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalPedidoKeyTyped

    private void tbProductosPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosPedidoMouseClicked
        // TODO add your handling code here:
        int seleccion = tbProductosPedido.rowAtPoint(evt.getPoint());

        txtIdProductoPedido.setText(tbProductosPedido.getValueAt(seleccion, 2)+"");

    }//GEN-LAST:event_tbProductosPedidoMouseClicked

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtCantidad.getText().length() >=4)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnQuitarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarCompraActionPerformed
        // TODO add your handling code here:
        int count= tbProductosPedido.getModel().getRowCount();
        System.out.println(count+"");
        if(txtIdProductoPedido.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe seleccionar algún producto.");
        }if(count==0){
            JOptionPane.showMessageDialog(null, "Para Eliminar un producto Debe existir un producto en la tabla..");//de esta forma no se borrará algún
            //producto por haber seleccionado el producto luego aceptado el pedido y luego eliminado el el producto (Tambien sirve vaciando el txt al aceptar el pedido)
        }else{
                
            det.setIdDetalle(Integer.parseInt(txtIdProductoPedido.getText()));
            ipdao.EliminarDetallePedido(det);
            listarProductoPedido();
            txtTotalPedido.setText(SumaTotalPedido()+"");
            txtIdProductoPedido.setText("");
        }
        
    }//GEN-LAST:event_btnQuitarCompraActionPerformed

    private void txtPrecioPedidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioPedidoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtPrecioPedido.getText().length() >=7)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioPedidoKeyTyped

    private void cboNombreEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNombreEmpresaActionPerformed
        // TODO add your handling code here:
        ipdao.ListarProductosEmpresaPedidoCBO(cboProducto,cboNombreEmpresa);
        
    }//GEN-LAST:event_cboNombreEmpresaActionPerformed

    
    
    private void btnAgregarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductosActionPerformed
        // TODO add your handling code here:
        if (cboNombreEmpresa.getSelectedItem()==""  ){
            JOptionPane.showMessageDialog(null, "Debe llenar algun campo de empresa.");
        }else if(txtPrecioPedido.getText().isEmpty() || txtCantidad.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Escriba una cantidad de productos y/o Precio Total+");
        }else if(txtNumFactura.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe haber elegido generado una factura");
        }else if((ipdao.CompararProductoDetalleFactura(cboProducto, txtNumFactura))==false){
            JOptionPane.showMessageDialog(null, "No debe existir algún producto repetido");
        }else{

            

            try {
                String sql="select codproducto from producto where descripcion=?";
                con=cn.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, (String)cboProducto.getSelectedItem());

                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    det.setProducto(Integer.parseInt(rs.getString(1)));
                }
                det.setCantidad(Integer.parseInt(txtCantidad.getText()));
                det.setPrecio(Integer.parseInt(txtPrecioPedido.getText()));

                ipdao.agregardetallefactura(det);
                listarProductoPedido();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                System.out.println(e.toString());
            }
            
           txtTotalPedido.setText(SumaTotalPedido()+"");
            
        }
    }//GEN-LAST:event_btnAgregarProductosActionPerformed

    private void txtIdEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdEmpresaKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();

        if(c<'0'|| c>'9'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtIdEmpresa.getText().length() >=8)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdEmpresaKeyTyped

    private void btnAceptarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarProveedorActionPerformed
        // TODO add your handling code here:
        if(txtRubroProveedor.getText().isEmpty() || txtNombreProveedor.getText().isEmpty() || txtDireccionProveedor.getText().isEmpty() || txtNombreProveedor.getText().isEmpty()
            || txtDVRutProveedor.getText().isEmpty()|| txtCorreoProveedor.getText().isEmpty()|| txtRutProveedor.getText().isEmpty()|| txtTelefonoProveedor.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLene todos los campos Correspondientes.");
        }else{
            if(txtRutProveedor.getText().length()!=8 || txtTelefonoProveedor.getText().length()!=9){ //Limitando las respuestas de rut a 8 caracteres y telefono a 9 caracteres
                JOptionPane.showMessageDialog(null, "LLene correctamente el rut o telefono");
            }else{
                char dvchar=txtDVRutProveedor.getText().charAt(0);//convierte el string a char (esta limitado a un caracter el string)
                emp.setNombre(txtNombreProveedor.getText());
                emp.setRut(Integer.parseInt(txtRutProveedor.getText()));
                emp.setDV(dvchar);
                emp.setRubro(txtRubroProveedor.getText());
                emp.setDireccion(txtDireccionProveedor.getText());
                emp.setTelefono(Integer.parseInt(txtTelefonoProveedor.getText()));
                emp.setEmail(txtCorreoProveedor.getText());

                ingresarempresa.agregarFichaEmpresa(emp);

                //String mensaje = ifcbo.agregarFichaCliente(cli);
                JOptionPane.showMessageDialog(null, "cliente registrado");
                limpiar();
                listarEmpresa();

            }
        }
    }//GEN-LAST:event_btnAceptarProveedorActionPerformed

    private void btnModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProveedorActionPerformed
        // TODO add your handling code here:
        if(txtRubroProveedor.getText().isEmpty() || txtNombreProveedor.getText().isEmpty() || txtDireccionProveedor.getText().isEmpty()|| txtDVRutProveedor.getText().isEmpty()
            || txtCorreoProveedor.getText().isEmpty()|| txtRutProveedor.getText().isEmpty()|| txtTelefonoProveedor.getText().isEmpty()||txtIdEmpresa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLene todos los campos Correspondientes.");
        }else{
            if(txtRutProveedor.getText().length()!=8 || txtTelefonoProveedor.getText().length()!=9){ //Limitando las respuestas de rut a 8 caracteres y telefono a 9 caracteres
                JOptionPane.showMessageDialog(null, "LLene correctamente el rut o telefono");
            }else{

                char dvchar=txtDVRutProveedor.getText().charAt(0);//convierte el string a char (esta limitado a un caracter el string)
                emp.setNombre(txtNombreProveedor.getText());
                emp.setRut(Integer.parseInt(txtRutProveedor.getText()));
                emp.setDV(dvchar);
                emp.setRubro(txtRubroProveedor.getText());
                emp.setDireccion(txtDireccionProveedor.getText());
                emp.setTelefono(Integer.parseInt(txtTelefonoProveedor.getText()));
                emp.setEmail(txtCorreoProveedor.getText());

                emp.setIdEmpresa(Integer.parseInt(txtIdEmpresa.getText()));//id para el where

                ingresarempresa.ModificarFichaEmpresa(emp);
                JOptionPane.showMessageDialog(null, "cliente modificado");
                limpiar();
                listarEmpresa();

            }
        }
    }//GEN-LAST:event_btnModificarProveedorActionPerformed

    private void btnEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProveedorActionPerformed
        // TODO add your handling code here:
        if(txtIdEmpresa.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "LLene el campo ID para eliminar.");
        }else{
            int pregunta = JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar?");
            if (pregunta == 0){
                //(String mensaje = ifcbo.EliminarFichaCliente(Integer.parseInt(txtID.getText()));
                    //JOptionPane.showMessageDialog(null, mensaje);
                    ingresarempresa.EliminarEmpresa(Integer.parseInt(txtIdEmpresa.getText()));
                    JOptionPane.showMessageDialog(null, "Eliminado con exito");
                    limpiar();
                    listarEmpresa();
                }
            }

    }//GEN-LAST:event_btnEliminarProveedorActionPerformed

    private void btnLimpiarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProveedorActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarProveedorActionPerformed

    private void tbIngresarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIngresarProveedorMouseClicked
        // TODO add your handling code here:
        int seleccion = tbIngresarProveedor.rowAtPoint(evt.getPoint());

        txtCorreoProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 7)+"");
        txtDVRutProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 3)+"");
        txtDireccionProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 5)+"");
        txtIdEmpresa.setText(tbIngresarProveedor.getValueAt(seleccion, 0)+"");
        txtNombreProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 1)+"");
        txtRubroProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 4)+"");
        txtRutProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 2)+"");
        txtTelefonoProveedor.setText(tbIngresarProveedor.getValueAt(seleccion, 6)+"");

    }//GEN-LAST:event_tbIngresarProveedorMouseClicked

    private void txtCorreoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoProveedorKeyTyped
        // TODO add your handling code here:
        
        if(txtCorreoProveedor.getText().length() >=50)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoProveedorKeyTyped

    private void txtTelefonoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtTelefonoProveedor.getText().length() >=9)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoProveedorKeyTyped

    private void txtDVRutProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDVRutProveedorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if(c<'1'|| c>'9' && c!='k'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtDVRutProveedor.getText().length() >=1)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDVRutProveedorKeyTyped

    private void txtRutProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutProveedorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtRutProveedor.getText().length() >=8)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRutProveedorKeyTyped

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtNombreProveedor.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtDireccionProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionProveedorKeyTyped

        if(txtDireccionProveedor.getText().length() >=30)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionProveedorKeyTyped

    private void txtRubroProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRubroProveedorKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtRubroProveedor.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRubroProveedorKeyTyped

    private void txtRubroProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRubroProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRubroProveedorActionPerformed

    private void cboCargoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCargoEmpleadoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboCargoEmpleadoActionPerformed

    private void txtApellidoMaternoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMaternoEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtApellidoMaternoEmpleado.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoMaternoEmpleadoKeyTyped

    private void txtApellidoPaternoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPaternoEmpleadoKeyTyped

        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtApellidoPaternoEmpleado.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoPaternoEmpleadoKeyTyped

    private void txtSueldoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtSueldoEmpleado.getText().length() >=7)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtSueldoEmpleadoKeyTyped

    private void tbIngresarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIngresarEmpleadoMouseClicked
        // TODO add your handling code here:

        try {//Poner
            int seleccion2 = tbIngresarEmpleado.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tbIngresarEmpleado.getValueAt(seleccion2, 9));
            datecFechaTermino.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        int seleccion = tbIngresarEmpleado.rowAtPoint(evt.getPoint());

        txtApellidoMaternoEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 3)+"");
        txtApellidoPaternoEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion,2)+"");
        txtNombreEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 1)+"");

        cboCargoEmpleado.setSelectedItem(tbIngresarEmpleado.getValueAt(seleccion,10));
        txtDireccionEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 6)+"");
        txtDVRutEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 5)+"");
        txtIdEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 0)+"");
        txtRutEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 4)+"");
        txtSueldoEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 11)+"");
        txtTelefonoEmpleado.setText(tbIngresarEmpleado.getValueAt(seleccion, 7)+"");
        //"idempleado","nombre","apaterno","amaterno","rut","dv","direccion","telefono","fechacontrato","fechatermino","cargo","sueldo"

    }//GEN-LAST:event_tbIngresarEmpleadoMouseClicked

    private void btnAceptarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarEmpleadoActionPerformed
        // TODO add your handling code here:
        if(txtApellidoMaternoEmpleado.getText().isEmpty() || txtApellidoPaternoEmpleado.getText().isEmpty() || cboCargoEmpleado.getSelectedItem()=="" || txtDVRutEmpleado.getText().isEmpty()
            || txtDireccionEmpleado.getText().isEmpty() || datecFechaTermino.getDate()==null ||  txtNombreEmpleado.getText().isEmpty()
            || txtRutEmpleado.getText().isEmpty() || txtSueldoEmpleado.getText().isEmpty() || txtTelefonoEmpleado.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "LLene todos los campos Correspondientes. ");
        }else{
            if(txtRutEmpleado.getText().length()!=8 || txtTelefonoEmpleado.getText().length()!=9 || txtSueldoEmpleado.getText().length()<6){ //Limitando las respuestas de rut a 8 caracteres y telefono a 9 caracteres
                JOptionPane.showMessageDialog(null, "LLene correctamente el rut, telefono o Sueldo");
            }else{
                char dvchar=txtDVRutEmpleado.getText().charAt(0);//convierte el string a char (esta limitado a un caracter el string)
                String fecha=sdf.format(datecFechaTermino.getDate());
                String cargo=cboCargoEmpleado.getSelectedItem().toString();

                empl.setNombre(txtNombreEmpleado.getText());
                empl.setApellidoMaterno(txtApellidoMaternoEmpleado.getText());
                empl.setApellidoPaterno(txtApellidoPaternoEmpleado.getText());
                empl.setCargo(cargo);
                empl.setDireccion(txtDireccionEmpleado.getText());
                empl.setDvRut(dvchar);
                empl.setFechaTerminoContrato(fecha);
                empl.setRut(Integer.parseInt(txtRutEmpleado.getText()));
                empl.setSueldo(Integer.parseInt(txtSueldoEmpleado.getText()));
                empl.setTelefono(Integer.parseInt(txtTelefonoEmpleado.getText()));

                iemp.agregarEmpleado(empl);

                //String mensaje = ifcbo.agregarFichaCliente(cli);

                limpiar();
                listarEmpleado();

            }
        }
    }//GEN-LAST:event_btnAceptarEmpleadoActionPerformed

    private void btnModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpleadoActionPerformed
        // TODO add your handling code here:
        if(txtApellidoMaternoEmpleado.getText().isEmpty() || txtApellidoPaternoEmpleado.getText().isEmpty() || cboCargoEmpleado.getSelectedItem()=="" || txtDVRutEmpleado.getText().isEmpty()
            || txtDireccionEmpleado.getText().isEmpty() || datecFechaTermino.getDate()==null || txtNombreEmpleado.getText().isEmpty()
            || txtRutEmpleado.getText().isEmpty() || txtSueldoEmpleado.getText().isEmpty() || txtTelefonoEmpleado.getText().isEmpty()|| txtIdEmpleado.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLene todos los campos Correspondientes.");
        }else{

            if(txtRutEmpleado.getText().length()!=8 || txtTelefonoEmpleado.getText().length()!=9 || txtSueldoEmpleado.getText().length()<6){ //Limitando las respuestas de rut a 8 caracteres y telefono a 9 caracteres
                JOptionPane.showMessageDialog(null, "LLene correctamente el rut, telefono o Sueldo");
            }else{

                char dvchar=txtDVRutEmpleado.getText().charAt(0);//convierte el string a char (esta limitado a un caracter el string)
                String fecha=sdf.format(datecFechaTermino.getDate());
                String cargo=cboCargoEmpleado.getSelectedItem().toString();

                empl.setNombre(txtNombreEmpleado.getText());
                empl.setApellidoMaterno(txtApellidoMaternoEmpleado.getText());
                empl.setApellidoPaterno(txtApellidoPaternoEmpleado.getText());
                empl.setCargo(cargo);
                empl.setDireccion(txtDireccionEmpleado.getText());
                empl.setDvRut(dvchar);
                empl.setFechaTerminoContrato(fecha);
                empl.setRut(Integer.parseInt(txtRutEmpleado.getText()));
                empl.setSueldo(Integer.parseInt(txtSueldoEmpleado.getText()));
                empl.setTelefono(Integer.parseInt(txtTelefonoEmpleado.getText()));

                empl.setIdEmpleado(Integer.parseInt(txtIdEmpleado.getText()));
                iemp.ModificarEmpleado(empl);

                ingresarempresa.ModificarFichaEmpresa(emp);

                limpiar();
                listarEmpleado();

            }
        }
    }//GEN-LAST:event_btnModificarEmpleadoActionPerformed

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed
        // TODO add your handling code here:
        if(txtIdEmpleado.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "LLene el campo ID para eliminar.");
        }else{
            int pregunta = JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar?");
            if (pregunta == 0){
                //(String mensaje = ifcbo.EliminarFichaCliente(Integer.parseInt(txtID.getText()));
                    //JOptionPane.showMessageDialog(null, mensaje);
                    iemp.EliminarEmpleado(Integer.parseInt(txtIdEmpleado.getText()));

                    limpiar();
                    listarEmpleado();
                }
            }
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed

    private void btnLimpiarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarEmpleadoActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        limpiar();

    }//GEN-LAST:event_btnLimpiarEmpleadoActionPerformed

    private void txtDireccionEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && (c<'0'|| c>'9') && c!='#' && c!=' '){
            evt.consume();
        }
        if(txtDireccionEmpleado.getText().length() >=30)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionEmpleadoKeyTyped

    private void txtIdEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if(c<'0'|| c>'9'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtIdEmpleado.getText().length() >=8)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdEmpleadoKeyTyped

    private void txtIdEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEmpleadoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdEmpleadoActionPerformed

    private void txtNombreEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtNombreEmpleado.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreEmpleadoKeyTyped

    private void txtRutEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtRutEmpleado.getText().length() >=8)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRutEmpleadoKeyTyped

    private void txtDVRutEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDVRutEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if(c<'1'|| c>'9' && c!='k'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtDVRutEmpleado.getText().length() >=1)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDVRutEmpleadoKeyTyped

    private void txtTelefonoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoEmpleadoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtTelefonoEmpleado.getText().length() >=9)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoEmpleadoKeyTyped

    private void txtIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if(c<'0'|| c>'9'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtDVRut.getText().length() >=8)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtIDKeyTyped

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(txtID.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "LLene el campo ID para eliminar.");
        }else{
            int pregunta = JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar?");
            if (pregunta == 0){
                //(String mensaje = ifcbo.EliminarFichaCliente(Integer.parseInt(txtID.getText()));

                    ingresarcliente.EliminarCliente(Integer.parseInt(txtID.getText()));

                    limpiar();
                    listarCliente();
                }
            }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtNombre.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutKeyTyped
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtRut.getText().length() >=8)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRutKeyTyped

    private void txtRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if(txtDireccion.getText().isEmpty() || txtDVRut.getText().isEmpty() || txtApellidoMaterno.getText().isEmpty() || txtNombre.getText().isEmpty() || txtRut.getText().isEmpty()|| txtApellidoPaterno.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLene todos los campos Correspondientes.");
        }else{
            if(txtRut.getText().length()!=8 ){ //Limitando las respuestas de rut a 8 caracteres y telefono a 9 caracteres
                JOptionPane.showMessageDialog(null, "LLene correctamente el rut o telefono");
            }else{
                char dvchar=txtDVRut.getText().charAt(0);//convierte el string a char (esta limitado a un caracter el string)

                cli.setApellidoMaterno(txtApellidoMaterno.getText());//me salio un error ora-12899 value too large, esto pasó porque el metodo ingresar ficha cliente estaba en int
                cli.setApellidoPaterno(txtApellidoPaterno.getText());
                cli.setDVRut(dvchar);
                cli.setDeuda('n');
                cli.setDireccion(txtDireccion.getText());
                cli.setIdFiado(1);
                cli.setNombre(txtNombre.getText());
                cli.setRut(Integer.parseInt(txtRut.getText()));//AQUI DA ERROR PORQUE INT NO ACEPTA NUMEROS DE 11 DE LARGO (ES EN 11G NO EN MYSQL)
                ingresarcliente.agregarFichaCliente(cli);

                //String mensaje = ifcbo.agregarFichaCliente(cli);

                limpiar();
                listarCliente();
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void tbIngresarFichaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIngresarFichaClienteMouseClicked
        // TODO add your handling code here:

        //asi no se podrá modificar el contenido del jtextfield idfiado (se podia borrar haciendo click r apretar una tecla rapidamente)
        int seleccion = tbIngresarFichaCliente.rowAtPoint(evt.getPoint());

        txtDireccion.setText(tbIngresarFichaCliente.getValueAt(seleccion, 7)+"");
        txtDVRut.setText(tbIngresarFichaCliente.getValueAt(seleccion, 6)+"");
        txtRut.setText(tbIngresarFichaCliente.getValueAt(seleccion, 5)+"");
        txtID.setText(tbIngresarFichaCliente.getValueAt(seleccion, 0)+"");
        txtApellidoMaterno.setText(tbIngresarFichaCliente.getValueAt(seleccion, 4)+"");
        txtApellidoPaterno.setText(tbIngresarFichaCliente.getValueAt(seleccion, 3)+"");
        txtNombre.setText(tbIngresarFichaCliente.getValueAt(seleccion, 2)+"");

    }//GEN-LAST:event_tbIngresarFichaClienteMouseClicked

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && (c<'0'|| c>'9') && c!='#' && c!=' '){
            evt.consume();
        }
        if(txtDireccion.getText().length() >=30)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtDVRutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDVRutKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if(c<'1'|| c>'9' && c!='k'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtDVRut.getText().length() >=1)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDVRutKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if(txtDireccion.getText().isEmpty() || txtDVRut.getText().isEmpty() || txtApellidoMaterno.getText().isEmpty() || txtNombre.getText().isEmpty() || txtRut.getText().isEmpty()|| txtApellidoPaterno.getText().isEmpty()||txtID.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLene todos los campos.");
        }else{
            if(txtRut.getText().length()!=8 ){ //Limitando las respuestas de rut a 8 caracteres y telefono a 9 caracteres
                JOptionPane.showMessageDialog(null, "LLene correctamente el rut o telefono");
            }else{

                char dvchar=txtDVRut.getText().charAt(0);//convierte el string a char (esta limitado a un caracter el string)

                cli.setApellidoMaterno(txtApellidoMaterno.getText());//me salio un error ora-12899 value too large, esto pasó porque el metodo ingresar ficha cliente estaba en int
                cli.setApellidoPaterno(txtApellidoPaterno.getText());
                cli.setDVRut(dvchar);
                cli.setDeuda('n');
                cli.setDireccion(txtDireccion.getText());
                cli.setNombre(txtNombre.getText());
                cli.setRut(Integer.parseInt(txtRut.getText()));//AQUI DABÁ ERROR PORQUE INT NO ACEPTA NUMEROS DE 11 DE LARGo
                cli.setIdCliente(Integer.parseInt(txtID.getText()));
                ingresarcliente.ModificarFichaCliente(cli);
                limpiar();
                listarCliente();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtApellidoPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPaternoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtApellidoPaterno.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoPaternoKeyTyped

    private void txtApellidoMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMaternoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtApellidoMaterno.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoMaternoKeyTyped

    private void txtAbonarFiadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonarFiadoKeyTyped
        // TODO add your handling code here:
              char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtAbonarFiado.getText().length() >=8)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtAbonarFiadoKeyTyped

    private void txtIdEmpresaRevisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdEmpresaRevisarKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();

        if(c<'0'|| c>'9'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtIdEmpresaRevisar.getText().length() >=8)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdEmpresaRevisarKeyTyped

    private void txtRutRepartidorRevisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRutRepartidorRevisarKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();

        if(c<'0'|| c>'9'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtRutRepartidorRevisar.getText().length() >=8)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtRutRepartidorRevisarKeyTyped

    private void txtDVRutRepartidorRevisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDVRutRepartidorRevisarKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();

        if(c<'1'|| c>'9' && c!='k'){//limitar a numeros 0-9 y letra k
            evt.consume();
        }
        if(txtDVRutRepartidorRevisar.getText().length() >=1)//limita la cantidad de caracteres en el cuadro a 1
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtDVRutRepartidorRevisarKeyTyped

    private void txtNombreRepartidorRevisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreRepartidorRevisarKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtNombreRepartidorRevisar.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreRepartidorRevisarKeyTyped

    private void txtApellidoPaternoRevisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPaternoRevisarKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' '){
            evt.consume();
        }
        if(txtApellidoPaternoRevisar.getText().length() >=15)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidoPaternoRevisarKeyTyped

    private void txtCantidadProductosDiscrepanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductosDiscrepanciaKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtCantidadProductosDiscrepancia.getText().length() >=4)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadProductosDiscrepanciaKeyTyped

    private void txtTotalProductosDiscrepanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalProductosDiscrepanciaKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtTotalProductosDiscrepancia.getText().length() >=7)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalProductosDiscrepanciaKeyTyped

    private void txtEstadoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoProductoKeyTyped
        // TODO add your handling code here:
                  char c = evt.getKeyChar();
        if((c<'a'|| c>'z') && (c<'A')|c>'Z' && c!=' ' && c!='-' && c!='+'){
            evt.consume();
        }
        if(txtEstadoProducto.getText().length() >=60)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtEstadoProductoKeyTyped

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtCantidadVenta.getText().length() >=4)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadVentaKeyTyped

    private void btnAceptarFiadoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarFiadoClienteActionPerformed
        // TODO add your handling code here:
        if(txtAbonarFiado.getText().isEmpty()||txtID.getText().isEmpty()||txtDeuda.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe llenar Id, monto de fiado o tener el monto de deuda visible.");
        }else if(Integer.parseInt(txtDeuda.getText())<Integer.parseInt(txtAbonarFiado.getText())){
            JOptionPane.showMessageDialog(null, "El monto a abonar no puede ser superior a la deuda");
        }else if(txtDeuda.getText()=="-1"){
            JOptionPane.showMessageDialog(null, "No se puede abonar un fiado con monto -1 (por defecto es -1)");
        }else if(Integer.parseInt(txtDeuda.getText())==Integer.parseInt(txtAbonarFiado.getText())){

            ingresarcliente.ModificarFiadoCliente(txtAbonarFiado,txtID);//AQUI SIRVE PARA VERIFICAR SI SE PAGARÁ TOTALMENTE LA DEUDA, ENTONCES HACER EL CAMBIO EN EL CHAR DEUDA DE TABLA CLIENTE
            ingresarcliente.ModificarCHARFiado(txtID);
            ingresarcliente.CalcularDeuda(txtID,txtDeuda);
        }else{
            ingresarcliente.ModificarFiadoCliente(txtAbonarFiado,txtID);
            ingresarcliente.CalcularDeuda(txtID,txtDeuda);  
        }
    }//GEN-LAST:event_btnAceptarFiadoClienteActionPerformed

    private void btnVerInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerInformesActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnVerInformesActionPerformed

    private void btnVerVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVentaActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnVerVentaActionPerformed

    private void btnGenerarNFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarNFacturaActionPerformed
        // TODO add your handling code here:
         ipdao.agregarfactura();       
        txtNumFactura.setText(ipdao.NumFacturaImprimir()+"");
        
        cboNombreEmpresa.removeAllItems();
        cboNombreEmpresaRevisar.removeAllItems();
        cboProductoVenta.removeAllItems();
        cboRutVenta.removeAllItems();
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresa);
        bcbDAO.BuscarNombreEmpresaCBO(cboNombreEmpresaRevisar);
        bcbDAO.BuscarProductoVenta(cboProductoVenta);
        bcbDAO.BuscarRutCliente(cboRutVenta);
    }//GEN-LAST:event_btnGenerarNFacturaActionPerformed

    private void btnGenerarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarBoletaActionPerformed

         ivdao.agregarBoleta();
        txtNumeroBoleta.setText((ivdao.NumBoletaImprimir())+"");
        
        
    }//GEN-LAST:event_btnGenerarBoletaActionPerformed

    private void txtDeudaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeudaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDeudaKeyTyped

    private void btnCalcularDeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularDeudaActionPerformed
        // TODO add your handling code here:
        if(txtID.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Debe existir un id en el cuadro id");
        }else{
        ingresarcliente.CalcularDeuda(txtID,txtDeuda);
        }
    }//GEN-LAST:event_btnCalcularDeudaActionPerformed

    private void txtMontoAFiarVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAFiarVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoAFiarVentaKeyPressed

    private void txtMontoAFiarVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoAFiarVentaKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if(c<'0'|| c>'9'){
            evt.consume();
        }
        if(txtMontoAFiarVenta.getText().length() >=7)//limita la cantidad de caracteres en el cuadro
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoAFiarVentaKeyTyped

    private void btnPDFFiadosTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFFiadosTotalesActionPerformed
        // TODO add your handling code here:
        pdf.PDFFiadosTotales();
    }//GEN-LAST:event_btnPDFFiadosTotalesActionPerformed

    private void btnPDFBoletaMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFBoletaMesActionPerformed

        // TODO add your handling code here:
        pdf.PDFVentaBoletaMes();
    }//GEN-LAST:event_btnPDFBoletaMesActionPerformed

    private void btnPDFProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFProveedoresActionPerformed
        // TODO add your handling code here:
        pdf.PDFProveedores();
    }//GEN-LAST:event_btnPDFProveedoresActionPerformed

    private void btnPDFEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFEmpleadosActionPerformed
        // TODO add your handling code here:
        pdf.PDFEmpleados();
    }//GEN-LAST:event_btnPDFEmpleadosActionPerformed

    private void btnGraficoVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoVentasActionPerformed
        // TODO add your handling code here:
        pdf.GraficoVentas();
    }//GEN-LAST:event_btnGraficoVentasActionPerformed

    private void btnWordVentasMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWordVentasMesActionPerformed
        // TODO add your handling code here:
        pdf.word("VentasMesTemp.doc");
    }//GEN-LAST:event_btnWordVentasMesActionPerformed

    private void btnWordProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWordProveedoresActionPerformed
        // TODO add your handling code here:
        pdf.WordProveedores("ListaProveedores.doc");
    }//GEN-LAST:event_btnWordProveedoresActionPerformed

    private void btnWordEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWordEmpleadosActionPerformed
        // TODO add your handling code here:
        pdf.WordEmpleados("ListaEmpleados.doc");
    }//GEN-LAST:event_btnWordEmpleadosActionPerformed

    private void btnWordFiadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWordFiadosActionPerformed
        // TODO add your handling code here:
        
        pdf.WordFiados("ListaFiados.doc");
    }//GEN-LAST:event_btnWordFiadosActionPerformed

    private void btnTXTEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTXTEmpleadosActionPerformed
        // TODO add your handling code here:
        pdf.WordEmpleados("ListaEmpleados.txt");
    }//GEN-LAST:event_btnTXTEmpleadosActionPerformed

    private void btnTXTProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTXTProveedoresActionPerformed
        // TODO add your handling code here:
        pdf.WordProveedores("ListaProveedores.txt");
    }//GEN-LAST:event_btnTXTProveedoresActionPerformed

    private void btnTXTVentasMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTXTVentasMesActionPerformed
        // TODO add your handling code here:
        pdf.word("VentasMesTemp.txt");
    }//GEN-LAST:event_btnTXTVentasMesActionPerformed

    private void btnTXTFiadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTXTFiadosActionPerformed
        // TODO add your handling code here:
          pdf.WordFiados("ListaFiados.txt");
    }//GEN-LAST:event_btnTXTFiadosActionPerformed

    private void btnExcelFiadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelFiadosActionPerformed
        // TODO add your handling code here:
        pdf.Excel();
    }//GEN-LAST:event_btnExcelFiadosActionPerformed

    private void btnExcelVentasMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelVentasMesActionPerformed
        // TODO add your handling code here:
        pdf.ExcelVentasMes();
    }//GEN-LAST:event_btnExcelVentasMesActionPerformed

    private void btnExcelProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProveedoresActionPerformed
        // TODO add your handling code here:
        pdf.ExcelProveedores();
    }//GEN-LAST:event_btnExcelProveedoresActionPerformed

    private void btnExcelEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelEmpleadosActionPerformed
        // TODO add your handling code here:
        pdf.ExcelEmpleados();
    }//GEN-LAST:event_btnExcelEmpleadosActionPerformed

    private void btnGraficoFiadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoFiadosActionPerformed
        // TODO add your handling code here:
        pdf.GraficoFiados();
    }//GEN-LAST:event_btnGraficoFiadosActionPerformed

    private void txtEmpresaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpresaVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpresaVentaActionPerformed

    private void cboRutVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRutVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRutVentaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
    public int SumaTotalPedido(){
        int contar=tbProductosPedido.getRowCount();
        int suma=0;
        for (int i = 0; i<contar; i++){
            suma=suma+Integer.parseInt(tbProductosPedido.getValueAt(i,5).toString());
        }
        return suma;
    } 
     public int SumaTotalBoleta(){
        int contar=tbVentaBoleta.getRowCount();
        int suma=0;
        for (int i = 0; i<contar; i++){
            suma=suma+Integer.parseInt(tbVentaBoleta.getValueAt(i,4).toString());
        }
        return suma;
    } 
   
    public void limpiar(){
        txtCorreoProveedor.setText("");
        txtDVRutProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtIdEmpresa.setText("");
        txtNombreProveedor.setText("");
        txtRubroProveedor.setText("");
        txtRutProveedor.setText("");
        txtTelefonoProveedor.setText("");
        
        txtDireccion.setText("");
        txtDVRut.setText("");
        txtRut.setText("");
        txtAbonarFiado.setText("");
        txtID.setText("");
        txtApellidoMaterno.setText("");
        txtApellidoPaterno.setText("");
        txtNombre.setText("");
        
        txtApellidoMaternoEmpleado.setText("");
        txtDireccionEmpleado.setText("");
        txtDVRutEmpleado.setText("");
        txtApellidoPaternoEmpleado.setText("");
        txtIdEmpleado.setText("");
        txtNombreEmpleado.setText("");
        txtRutEmpleado.setText("");
        txtSueldoEmpleado.setText("");
        txtTelefonoEmpleado.setText("");
        cboCargoEmpleado.setSelectedIndex(0);
        datecFechaTermino.setDate(null);
        
        txtCantidad.setText("");
        txtPrecioPedido.setText("");
        txtIdProductoPedido.setText("");
        txtTotalPedido.setText("");
        tbProductosPedido.setModel(new DefaultTableModel());
        cboProducto.setSelectedItem(0);
        cboNombreEmpresa.setSelectedItem(0);
        txtNumFactura.setText("");
        
        cboNombreEmpresaRevisar.setSelectedItem(0);
        cboNumFacturaRevisar.setSelectedItem(0);
        txtIdEmpresaRevisar.setText("");
        txtRutRepartidorRevisar.setText("");
        txtDVRutRepartidorRevisar.setText("");
        txtNombreRepartidorRevisar.setText("");
        txtApellidoPaternoRevisar.setText("");
        txtCantidadProductoRevisar.setText("");
        txtTotalProductosRevisar.setText("");
        txtTotalProductosDiscrepancia.setText("");
        txtCantidadProductosDiscrepancia.setText("");
        txtEstadoProducto.setText("");
        tbRevisarPedido.setModel(new DefaultTableModel());
        
        cboProductoVenta.setSelectedItem(0);
        cboRutVenta.setSelectedItem(0);
        txtStockVenta.setText("");
        txtPrecioUnitarioVenta.setText("");
        txtEmpresaVenta.setText("");
        txtCantidadVenta.setText("");
        txtIDetalleVenta.setText("");
        txtTotalVenta.setText("");
        txtMontoAFiarVenta.setText("");
        txtpassword.setText("");
        
        tbVentaBoleta.setModel(new DefaultTableModel());
        txtNumeroBoleta.setText("");
    }
    
    public void listarCliente(){
        ifcbo.ListarFichaCliente(tbIngresarFichaCliente);
    }
    
    
    public void listarEmpresa(){
        ifpbo.ListarFichaProveedor(tbIngresarProveedor);
    }
    
    public void listarEmpleado(){
        te.ListarFichaProveedor(tbIngresarEmpleado);
    }
    
    public void listarProductoPedido(){
        fact.setNumFactura(Integer.parseInt(txtNumFactura.getText()));
        ipdao.ListarProductosPedido(con, tbProductosPedido, fact);
    }
    
    public void listarProductoPedido2(){
        //fact.setNumFactura(Integer.parseInt(cboNumFacturaRevisar.getText()));//modificar para poder lsitar de pana
        ipdao.ListarProductosPedido2(con, tbRevisarPedido, cboNumFacturaRevisar);
    }
    
    public void listarProductoBoleta(){
       
        ivdao.ListarVenta(con, tbVentaBoleta, txtNumeroBoleta);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel PanelCliente;
    public static javax.swing.JPanel PanelCompra;
    public static javax.swing.JPanel PanelEmpleado;
    public static javax.swing.JPanel PanelEstadistica;
    public static javax.swing.JPanel PanelPedido;
    public static javax.swing.JPanel PanelProveedor;
    public static javax.swing.JPanel PanelVenta;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarEmpleado;
    private javax.swing.JButton btnAceptarFiadoCliente;
    private javax.swing.JButton btnAceptarOrden;
    private javax.swing.JButton btnAceptarPedido;
    private javax.swing.JButton btnAceptarProveedor;
    private javax.swing.JButton btnAceptarVenta;
    private javax.swing.JButton btnAgregarCompra;
    private javax.swing.JButton btnAgregarProductos;
    private javax.swing.JButton btnCalcularDeuda;
    private javax.swing.JButton btnDiscrepancias;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarEmpleado;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnExcelEmpleados;
    private javax.swing.JButton btnExcelFiados;
    private javax.swing.JButton btnExcelProveedores;
    private javax.swing.JButton btnExcelVentasMes;
    private javax.swing.JButton btnGenerarBoleta;
    private javax.swing.JButton btnGenerarNFactura;
    private javax.swing.JButton btnGraficoFiados;
    private javax.swing.JButton btnGraficoVentas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarEmpleado;
    private javax.swing.JButton btnLimpiarProveedor;
    private javax.swing.JButton btnListarTablaRevisar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarEmpleado;
    private javax.swing.JButton btnModificarProveedor;
    private javax.swing.JButton btnPDFBoletaMes;
    private javax.swing.JButton btnPDFEmpleados;
    private javax.swing.JButton btnPDFFiadosTotales;
    private javax.swing.JButton btnPDFProveedores;
    private javax.swing.JButton btnQuitarCompra;
    private javax.swing.JButton btnQuitarCompraVenta;
    private javax.swing.JToggleButton btnRevisarPedido;
    private javax.swing.JButton btnTXTEmpleados;
    private javax.swing.JButton btnTXTFiados;
    private javax.swing.JButton btnTXTProveedores;
    private javax.swing.JButton btnTXTVentasMes;
    private javax.swing.JButton btnVerCliente;
    private javax.swing.JButton btnVerEmpleado;
    private javax.swing.JButton btnVerInformes;
    private javax.swing.JButton btnVerOrdenPedido;
    private javax.swing.JButton btnVerProveedor;
    public static javax.swing.JButton btnVerVenta;
    private javax.swing.JButton btnWordEmpleados;
    private javax.swing.JButton btnWordFiados;
    private javax.swing.JButton btnWordProveedores;
    private javax.swing.JButton btnWordVentasMes;
    private javax.swing.JComboBox<String> cboCargoEmpleado;
    private javax.swing.JComboBox<String> cboNombreEmpresa;
    private javax.swing.JComboBox<String> cboNombreEmpresaRevisar;
    private javax.swing.JComboBox<String> cboNumFacturaRevisar;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JComboBox<String> cboProductoVenta;
    private javax.swing.JComboBox<String> cboRutVenta;
    private com.toedter.calendar.JDateChooser datecFechaTermino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTable tbIngresarEmpleado;
    private javax.swing.JTable tbIngresarFichaCliente;
    private javax.swing.JTable tbIngresarProveedor;
    private javax.swing.JTable tbProductosPedido;
    private javax.swing.JTable tbRevisarPedido;
    private javax.swing.JTable tbVentaBoleta;
    private javax.swing.JTextField txtAbonarFiado;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoMaternoEmpleado;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtApellidoPaternoEmpleado;
    private javax.swing.JTextField txtApellidoPaternoRevisar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadProductoRevisar;
    private javax.swing.JTextField txtCantidadProductosDiscrepancia;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtDVRut;
    private javax.swing.JTextField txtDVRutEmpleado;
    private javax.swing.JTextField txtDVRutProveedor;
    private javax.swing.JTextField txtDVRutRepartidorRevisar;
    private javax.swing.JTextField txtDeuda;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEmpleado;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtEmpresaVenta;
    private javax.swing.JTextField txtEstadoProducto;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDetalleVenta;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdEmpresa;
    private javax.swing.JTextField txtIdEmpresaRevisar;
    private javax.swing.JTextField txtIdProductoPedido;
    private javax.swing.JTextField txtMontoAFiarVenta;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreRepartidorRevisar;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JTextField txtNumeroBoleta;
    private javax.swing.JTextField txtPrecioPedido;
    private javax.swing.JTextField txtPrecioUnitarioVenta;
    private javax.swing.JTextField txtRubroProveedor;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtRutEmpleado;
    private javax.swing.JTextField txtRutProveedor;
    private javax.swing.JTextField txtRutRepartidorRevisar;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtSueldoEmpleado;
    private javax.swing.JTextField txtTelefonoEmpleado;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTotalPedido;
    private javax.swing.JTextField txtTotalProductosDiscrepancia;
    private javax.swing.JTextField txtTotalProductosRevisar;
    private javax.swing.JTextField txtTotalVenta;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
