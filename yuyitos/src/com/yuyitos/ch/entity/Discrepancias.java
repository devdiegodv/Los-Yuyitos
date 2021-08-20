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
public class Discrepancias {
    public int IdDscrepancia;
    public int numfactura;
    public int Cantidad;
    public int Precio;
    public String InformacionAdicional;

    public Discrepancias() {
    }

    public Discrepancias(int IdDscrepancia, int numfactura, int Cantidad, int Precio, String InformacionAdicional) {
        this.IdDscrepancia = IdDscrepancia;
        this.numfactura = numfactura;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.InformacionAdicional = InformacionAdicional;
    }

    
    
    public int getIdDscrepancia() {
        return IdDscrepancia;
    }

    public void setIdDscrepancia(int IdDscrepancia) {
        this.IdDscrepancia = IdDscrepancia;
    }

    public int getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(int numfactura) {
        this.numfactura = numfactura;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public String getInformacionAdicional() {
        return InformacionAdicional;
    }

    public void setInformacionAdicional(String InformacionAdicional) {
        this.InformacionAdicional = InformacionAdicional;
    }

    @Override
    public String toString() {
        return "Discrepancias{" + "IdDscrepancia=" + IdDscrepancia + ", numfactura=" + numfactura + ", Cantidad=" + Cantidad + ", Precio=" + Precio + ", InformacionAdicional=" + InformacionAdicional + '}';
    }
    
    
}
