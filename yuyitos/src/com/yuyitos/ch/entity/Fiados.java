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
public class Fiados {
    private int CodFiado;
    private int MontoFiado;
    private String FechaCompra;
    private String FechaPlazo;
    private int Abonos;
    private int EmpleadoIdEmpleado;

    public Fiados() {
    }

    public Fiados(int CodFiado, int MontoFiado, String FechaCompra, String FechaPlazo, int Abonos, int EmpleadoIdEmpleado) {
        this.CodFiado = CodFiado;
        this.MontoFiado = MontoFiado;
        this.FechaCompra = FechaCompra;
        this.FechaPlazo = FechaPlazo;
        this.Abonos = Abonos;
        this.EmpleadoIdEmpleado = EmpleadoIdEmpleado;
    }

    public int getCodFiado() {
        return CodFiado;
    }

    public void setCodFiado(int CodFiado) {
        this.CodFiado = CodFiado;
    }

    public int getMontoFiado() {
        return MontoFiado;
    }

    public void setMontoFiado(int MontoFiado) {
        this.MontoFiado = MontoFiado;
    }

    public String getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(String FechaCompra) {
        this.FechaCompra = FechaCompra;
    }

    public String getFechaPlazo() {
        return FechaPlazo;
    }

    public void setFechaPlazo(String FechaPlazo) {
        this.FechaPlazo = FechaPlazo;
    }

    public int getAbonos() {
        return Abonos;
    }

    public void setAbonos(int Abonos) {
        this.Abonos = Abonos;
    }

    public int getEmpleadoIdEmpleado() {
        return EmpleadoIdEmpleado;
    }

    public void setEmpleadoIdEmpleado(int EmpleadoIdEmpleado) {
        this.EmpleadoIdEmpleado = EmpleadoIdEmpleado;
    }

    @Override
    public String toString() {
        return "Fiados{" + "CodFiado=" + CodFiado + ", MontoFiado=" + MontoFiado + ", FechaCompra=" + FechaCompra + ", FechaPlazo=" + FechaPlazo + ", Abonos=" + Abonos + ", EmpleadoIdEmpleado=" + EmpleadoIdEmpleado + '}';
    }
    
    
}
