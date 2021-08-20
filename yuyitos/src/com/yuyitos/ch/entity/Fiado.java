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
public class Fiado {
    private int IdFiado;
    private String Monto;
    private String Plazo;
    private int IdVenta;//clave foranea
    private int NumBoleta;//clave foranea
    private int IdDetalle;//clave foranea
    private int IdCliente;//clave foranea
    private int VentaIdEmpleado;//clave foranea

    public Fiado() {
    }

    public Fiado(int IdFiado, String Monto, String Plazo, int IdVenta, int NumBoleta, int IdDetalle, int IdCliente, int VentaIdEmpleado) {
        this.IdFiado = IdFiado;
        this.Monto = Monto;
        this.Plazo = Plazo;
        this.IdVenta = IdVenta;
        this.NumBoleta = NumBoleta;
        this.IdDetalle = IdDetalle;
        this.IdCliente = IdCliente;
        this.VentaIdEmpleado = VentaIdEmpleado;
    }

    public int getIdFiado() {
        return IdFiado;
    }

    public void setIdFiado(int IdFiado) {
        this.IdFiado = IdFiado;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getPlazo() {
        return Plazo;
    }

    public void setPlazo(String Plazo) {
        this.Plazo = Plazo;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public int getNumBoleta() {
        return NumBoleta;
    }

    public void setNumBoleta(int NumBoleta) {
        this.NumBoleta = NumBoleta;
    }

    public int getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(int IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getVentaIdEmpleado() {
        return VentaIdEmpleado;
    }

    public void setVentaIdEmpleado(int VentaIdEmpleado) {
        this.VentaIdEmpleado = VentaIdEmpleado;
    }

    @Override
    public String toString() {
        return "Fiado{" + "IdFiado=" + IdFiado + ", Monto=" + Monto + ", Plazo=" + Plazo + ", IdVenta=" + IdVenta + ", NumBoleta=" + NumBoleta + ", IdDetalle=" + IdDetalle + ", IdCliente=" + IdCliente + ", VentaIdEmpleado=" + VentaIdEmpleado + '}';
    }
    
    
}
