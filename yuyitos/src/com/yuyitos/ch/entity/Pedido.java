/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.entity;

/**
 *
 * @author tavo-a
 */
public class Pedido {
    public int IdPedido;
    public int IdEmpleado;//clave foranea de  tabla empleado
    public int IdRepartidor;//clave foranea de  tabla repartidor
    public int NumFactura;//clave foranea de tabla factura
    public int IdDetalle;//clave foranea de  tabla detalle

    public Pedido() {
    }

    public Pedido(int IdPedido, int IdEmpleado, int IdRepartidor, int NumFactura, int IdDetalle) {
        this.IdPedido = IdPedido;
        this.IdEmpleado = IdEmpleado;
        this.IdRepartidor = IdRepartidor;
        this.NumFactura = NumFactura;
        this.IdDetalle = IdDetalle;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdRepartidor() {
        return IdRepartidor;
    }

    public void setIdRepartidor(int IdRepartidor) {
        this.IdRepartidor = IdRepartidor;
    }

    public int getNumFactura() {
        return NumFactura;
    }

    public void setNumFactura(int NumFactura) {
        this.NumFactura = NumFactura;
    }

    public int getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(int IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    @Override
    public String toString() {
        return "Pedido{" + "IdPedido=" + IdPedido + ", IdEmpleado=" + IdEmpleado + ", IdRepartidor=" + IdRepartidor + ", NumFactura=" + NumFactura + ", IdDetalle=" + IdDetalle + '}';
    }
    
    
}
