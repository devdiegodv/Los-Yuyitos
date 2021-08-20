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
public class Venta {
    private int CodVenta;
    private String FechaVenta;
    private int EmpleadoIdEmpleado;
    private int FiadosCodFiado;

    public Venta() {
    }

    public Venta(int CodVenta, String FechaVenta, int EmpleadoIdEmpleado, int FiadosCodFiado) {
        this.CodVenta = CodVenta;
        this.FechaVenta = FechaVenta;
        this.EmpleadoIdEmpleado = EmpleadoIdEmpleado;
        this.FiadosCodFiado = FiadosCodFiado;
    }

    public int getCodVenta() {
        return CodVenta;
    }

    public void setCodVenta(int CodVenta) {
        this.CodVenta = CodVenta;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String FechaVenta) {
        this.FechaVenta = FechaVenta;
    }

    public int getEmpleadoIdEmpleado() {
        return EmpleadoIdEmpleado;
    }

    public void setEmpleadoIdEmpleado(int EmpleadoIdEmpleado) {
        this.EmpleadoIdEmpleado = EmpleadoIdEmpleado;
    }

    public int getFiadosCodFiado() {
        return FiadosCodFiado;
    }

    public void setFiadosCodFiado(int FiadosCodFiado) {
        this.FiadosCodFiado = FiadosCodFiado;
    }

    @Override
    public String toString() {
        return "Venta{" + "CodVenta=" + CodVenta + ", FechaVenta=" + FechaVenta + ", EmpleadoIdEmpleado=" + EmpleadoIdEmpleado + ", FiadosCodFiado=" + FiadosCodFiado + '}';
    }
    
    
}
