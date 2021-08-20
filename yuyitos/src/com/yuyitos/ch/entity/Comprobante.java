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
public class Comprobante {
    private int NC;
    private int IdEmpleado;
    private int CodDetalle;
    private String FechaComprobante;
    private int Total;

    public Comprobante() {
    }

    public Comprobante(int NC, int IdEmpleado, int CodDetalle, String FechaComprobante, int Total) {
        this.NC = NC;
        this.IdEmpleado = IdEmpleado;
        this.CodDetalle = CodDetalle;
        this.FechaComprobante = FechaComprobante;
        this.Total = Total;
    }

    public int getNC() {
        return NC;
    }

    public void setNC(int NC) {
        this.NC = NC;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getCodDetalle() {
        return CodDetalle;
    }

    public void setCodDetalle(int CodDetalle) {
        this.CodDetalle = CodDetalle;
    }

    public String getFechaComprobante() {
        return FechaComprobante;
    }

    public void setFechaComprobante(String FechaComprobante) {
        this.FechaComprobante = FechaComprobante;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    @Override
    public String toString() {
        return "Comprobante{" + "NC=" + NC + ", IdEmpleado=" + IdEmpleado + ", CodDetalle=" + CodDetalle + ", FechaComprobante=" + FechaComprobante + ", Total=" + Total + '}';
    }
    
     
}
