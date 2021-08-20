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
public class Detalle {
    public int IdDetalle;
    public int Producto;//clave foranea tabla producto
    public int cantidad;
    public int precio;

    public Detalle() {
    }

    public Detalle(int IdDetalle, int Producto, int cantidad, int precio) {
        this.IdDetalle = IdDetalle;
        this.Producto = Producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(int IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    public int getProducto() {
        return Producto;
    }

    public void setProducto(int Producto) {
        this.Producto = Producto;
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
        return "Detillo{" + "IdDetalle=" + IdDetalle + ", Producto=" + Producto + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    
}
