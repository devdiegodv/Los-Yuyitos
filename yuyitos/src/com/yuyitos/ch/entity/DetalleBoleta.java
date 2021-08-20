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
public class DetalleBoleta {
        public int iddetalle;
        public int numboleta;
        public int producto;
        public int cantidad;
        public int precio;

    public DetalleBoleta() {
    }

    public DetalleBoleta(int iddetalle, int numboleta, int producto, int cantidad, int precio) {
        this.iddetalle = iddetalle;
        this.numboleta = numboleta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getNumboleta() {
        return numboleta;
    }

    public void setNumboleta(int numboleta) {
        this.numboleta = numboleta;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetalleBoleta{" + "iddetalle=" + iddetalle + ", numboleta=" + numboleta + ", producto=" + producto + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
        
}
