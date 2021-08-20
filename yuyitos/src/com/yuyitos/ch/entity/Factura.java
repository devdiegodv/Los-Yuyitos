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
public class Factura {
    public int NumFactura;
    public int idDetalle;
    public String fecha;
    public int costo;
    public int iva;
    public int total;

    public Factura() {
    }

    public Factura(int NumFactura, int idDetalle, String fecha, int costo, int iva, int total) {
        this.NumFactura = NumFactura;
        this.idDetalle = idDetalle;
        this.fecha = fecha;
        this.costo = costo;
        this.iva = iva;
        this.total = total;
    }

    public int getNumFactura() {
        return NumFactura;
    }

    public void setNumFactura(int NumFactura) {
        this.NumFactura = NumFactura;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" + "NumFactura=" + NumFactura + ", idDetalle=" + idDetalle + ", fecha=" + fecha + ", costo=" + costo + ", iva=" + iva + ", total=" + total + '}';
    }
    
    
}
