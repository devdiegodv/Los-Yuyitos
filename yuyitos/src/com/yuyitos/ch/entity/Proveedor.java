/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.entity;

/**
 *
 * @author tavo-
 */
public class Proveedor {
    private int IdProveedor;
    private String NombreProveedor;
    private int RutProveedor;
    private char DVProveedor;
    private int DetallePedidoCod;

    public Proveedor() {
    }

    public Proveedor(int IdProveedor, String NombreProveedor, int RutProveedor, char DVProveedor, int DetallePedidoCod) {
        this.IdProveedor = IdProveedor;
        this.NombreProveedor = NombreProveedor;
        this.RutProveedor = RutProveedor;
        this.DVProveedor = DVProveedor;
        this.DetallePedidoCod = DetallePedidoCod;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getNombreProveedor() {
        return NombreProveedor;
    }

    public void setNombreProveedor(String NombreProveedor) {
        this.NombreProveedor = NombreProveedor;
    }

    public int getRutProveedor() {
        return RutProveedor;
    }

    public void setRutProveedor(int RutProveedor) {
        this.RutProveedor = RutProveedor;
    }

    public char getDVProveedor() {
        return DVProveedor;
    }

    public void setDVProveedor(char DVProveedor) {
        this.DVProveedor = DVProveedor;
    }

    public int getDetallePedidoCod() {
        return DetallePedidoCod;
    }

    public void setDetallePedidoCod(int DetallePedidoCod) {
        this.DetallePedidoCod = DetallePedidoCod;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "IdProveedor=" + IdProveedor + ", NombreProveedor=" + NombreProveedor + ", RutProveedor=" + RutProveedor + ", DVProveedor=" + DVProveedor + ", DetallePedidoCod=" + DetallePedidoCod + '}';
    }
    
    
}
