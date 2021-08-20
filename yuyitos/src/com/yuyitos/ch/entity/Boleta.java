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
public class Boleta {
    private int ProductoIdProducto;
    private int ProductoCodProducto;
    private int VentaCodVenta;
    private int ProductoCodProducto1;

    public Boleta() {
    }

    public Boleta(int ProductoIdProducto, int ProductoCodProducto, int VentaCodVenta, int ProductoCodProducto1) {
        this.ProductoIdProducto = ProductoIdProducto;
        this.ProductoCodProducto = ProductoCodProducto;
        this.VentaCodVenta = VentaCodVenta;
        this.ProductoCodProducto1 = ProductoCodProducto1;
    }

    public int getProductoIdProducto() {
        return ProductoIdProducto;
    }

    public void setProductoIdProducto(int ProductoIdProducto) {
        this.ProductoIdProducto = ProductoIdProducto;
    }

    public int getProductoCodProducto() {
        return ProductoCodProducto;
    }

    public void setProductoCodProducto(int ProductoCodProducto) {
        this.ProductoCodProducto = ProductoCodProducto;
    }

    public int getVentaCodVenta() {
        return VentaCodVenta;
    }

    public void setVentaCodVenta(int VentaCodVenta) {
        this.VentaCodVenta = VentaCodVenta;
    }

    public int getProductoCodProducto1() {
        return ProductoCodProducto1;
    }

    public void setProductoCodProducto1(int ProductoCodProducto1) {
        this.ProductoCodProducto1 = ProductoCodProducto1;
    }

    @Override
    public String toString() {
        return "Boleta{" + "ProductoIdProducto=" + ProductoIdProducto + ", ProductoCodProducto=" + ProductoCodProducto + ", VentaCodVenta=" + VentaCodVenta + ", ProductoCodProducto1=" + ProductoCodProducto1 + '}';
    }
    
    
}
