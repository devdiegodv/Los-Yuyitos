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
public class Producto {
    public int CodProducto;
    public String Descripcion;
    public int Familia;
    public int IdFamilia;
    public String DescripcionFamilia;
    public int Tipo;
    public int IdTipo;
    public String DescripcionTipo;
    public String fechaElab;
    public String fechaVenc;

    public Producto() {
    }

    public Producto(int CodProducto, String Descripcion, int Familia, int IdFamilia, String DescripcionFamilia, int Tipo, int IdTipo, String DescripcionTipo, String fechaElab, String fechaVenc) {
        this.CodProducto = CodProducto;
        this.Descripcion = Descripcion;
        this.Familia = Familia;
        this.IdFamilia = IdFamilia;//INFO DE LA TABLA FAMILIA DE LA BD
        this.DescripcionFamilia = DescripcionFamilia;//INFO DE LA TABLA FAMILIA DE LA BD
        this.Tipo = Tipo;
        this.IdTipo = IdTipo;//INFO DE LA TABLA TIPO DE LA BD
        this.DescripcionTipo = DescripcionTipo;//INFO DE LA TABLA TIPO DE LA BD
        this.fechaElab = fechaElab;
        this.fechaVenc = fechaVenc;
    }

    public int getCodProducto() {
        return CodProducto;
    }

    public void setCodProducto(int CodProducto) {
        this.CodProducto = CodProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getFamilia() {
        return Familia;
    }

    public void setFamilia(int Familia) {
        this.Familia = Familia;
    }

    public int getIdFamilia() {
        return IdFamilia;
    }

    public void setIdFamilia(int IdFamilia) {
        this.IdFamilia = IdFamilia;
    }

    public String getDescripcionFamilia() {
        return DescripcionFamilia;
    }

    public void setDescripcionFamilia(String DescripcionFamilia) {
        this.DescripcionFamilia = DescripcionFamilia;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    public int getIdTipo() {
        return IdTipo;
    }

    public void setIdTipo(int IdTipo) {
        this.IdTipo = IdTipo;
    }

    public String getDescripcionTipo() {
        return DescripcionTipo;
    }

    public void setDescripcionTipo(String DescripcionTipo) {
        this.DescripcionTipo = DescripcionTipo;
    }

    public String getFechaElab() {
        return fechaElab;
    }

    public void setFechaElab(String fechaElab) {
        this.fechaElab = fechaElab;
    }

    public String getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(String fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    @Override
    public String toString() {
        return "Producto{" + "CodProducto=" + CodProducto + ", Descripcion=" + Descripcion + ", Familia=" + Familia + ", IdFamilia=" + IdFamilia + ", DescripcionFamilia=" + DescripcionFamilia + ", Tipo=" + Tipo + ", IdTipo=" + IdTipo + ", DescripcionTipo=" + DescripcionTipo + ", fechaElab=" + fechaElab + ", fechaVenc=" + fechaVenc + '}';
    }
    
    
}
