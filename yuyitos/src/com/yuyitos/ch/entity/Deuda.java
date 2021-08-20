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
public class Deuda {
    private int codDeuda;
    private int MontoDeuda;
    private String FechaDeuda;
    private int MontoPago;
    private String FechaPago;
    private int TotalDeuda;

    public Deuda() {
    }

    public Deuda(int codDeuda, int MontoDeuda, String FechaDeuda, int MontoPago, String FechaPago, int TotalDeuda) {
        this.codDeuda = codDeuda;
        this.MontoDeuda = MontoDeuda;
        this.FechaDeuda = FechaDeuda;
        this.MontoPago = MontoPago;
        this.FechaPago = FechaPago;
        this.TotalDeuda = TotalDeuda;
    }

    public int getCodDeuda() {
        return codDeuda;
    }

    public void setCodDeuda(int codDeuda) {
        this.codDeuda = codDeuda;
    }

    public int getMontoDeuda() {
        return MontoDeuda;
    }

    public void setMontoDeuda(int MontoDeuda) {
        this.MontoDeuda = MontoDeuda;
    }

    public String getFechaDeuda() {
        return FechaDeuda;
    }

    public void setFechaDeuda(String FechaDeuda) {
        this.FechaDeuda = FechaDeuda;
    }

    public int getMontoPago() {
        return MontoPago;
    }

    public void setMontoPago(int MontoPago) {
        this.MontoPago = MontoPago;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String FechaPago) {
        this.FechaPago = FechaPago;
    }

    public int getTotalDeuda() {
        return TotalDeuda;
    }

    public void setTotalDeuda(int TotalDeuda) {
        this.TotalDeuda = TotalDeuda;
    }

    @Override
    public String toString() {
        return "Deuda{" + "codDeuda=" + codDeuda + ", MontoDeuda=" + MontoDeuda + ", FechaDeuda=" + FechaDeuda + ", MontoPago=" + MontoPago + ", FechaPago=" + FechaPago + ", TotalDeuda=" + TotalDeuda + '}';
    }
    
    
}
