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
public class DetallePedido {
    private int Cod;
    private String NombreProducto;
    private int Proveedor;
    private int ValorProductoPedido;

    public DetallePedido() {
    }

    public DetallePedido(int Cod, String NombreProducto, int Proveedor, int ValorProductoPedido) {
        this.Cod = Cod;
        this.NombreProducto = NombreProducto;
        this.Proveedor = Proveedor;
        this.ValorProductoPedido = ValorProductoPedido;
    }

    public int getCod() {
        return Cod;
    }

    public void setCod(int Cod) {
        this.Cod = Cod;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public int getProveedor() {
        return Proveedor;
    }

    public void setProveedor(int Proveedor) {
        this.Proveedor = Proveedor;
    }

    public int getValorProductoPedido() {
        return ValorProductoPedido;
    }

    public void setValorProductoPedido(int ValorProductoPedido) {
        this.ValorProductoPedido = ValorProductoPedido;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "Cod=" + Cod + ", NombreProducto=" + NombreProducto + ", Proveedor=" + Proveedor + ", ValorProductoPedido=" + ValorProductoPedido + '}';
    }
    
    
}
