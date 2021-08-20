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
public class TipoProducto {
    private int CodTipoProducto;
    private String NombreTipoProducto;
    private int ProductoIdProducto;
    private int ProductoCodProducto;
    private int ProductoCodProducto1;

    public TipoProducto() {
    }

    public TipoProducto(int CodTipoProducto, String NombreTipoProducto, int ProductoIdProducto, int ProductoCodProducto, int ProductoCodProducto1) {
        this.CodTipoProducto = CodTipoProducto;
        this.NombreTipoProducto = NombreTipoProducto;
        this.ProductoIdProducto = ProductoIdProducto;
        this.ProductoCodProducto = ProductoCodProducto;
        this.ProductoCodProducto1 = ProductoCodProducto1;
    }

    public int getCodTipoProducto() {
        return CodTipoProducto;
    }

    public void setCodTipoProducto(int CodTipoProducto) {
        this.CodTipoProducto = CodTipoProducto;
    }

    public String getNombreTipoProducto() {
        return NombreTipoProducto;
    }

    public void setNombreTipoProducto(String NombreTipoProducto) {
        this.NombreTipoProducto = NombreTipoProducto;
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

    public int getProductoCodProducto1() {
        return ProductoCodProducto1;
    }

    public void setProductoCodProducto1(int ProductoCodProducto1) {
        this.ProductoCodProducto1 = ProductoCodProducto1;
    }

    @Override
    public String toString() {
        return "TipoProducto{" + "CodTipoProducto=" + CodTipoProducto + ", NombreTipoProducto=" + NombreTipoProducto + ", ProductoIdProducto=" + ProductoIdProducto + ", ProductoCodProducto=" + ProductoCodProducto + ", ProductoCodProducto1=" + ProductoCodProducto1 + '}';
    }
    
    
}
