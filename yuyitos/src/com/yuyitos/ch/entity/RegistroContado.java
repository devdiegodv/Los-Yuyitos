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
public class RegistroContado {
    private int ClienteIdCliente;
    private int ClienteFiadoCodFiado;
    private int VentaCodVenta;

    public RegistroContado() {
    }

    public RegistroContado(int ClienteIdCliente, int ClienteFiadoCodFiado, int VentaCodVenta) {
        this.ClienteIdCliente = ClienteIdCliente;
        this.ClienteFiadoCodFiado = ClienteFiadoCodFiado;
        this.VentaCodVenta = VentaCodVenta;
    }

    public int getClienteIdCliente() {
        return ClienteIdCliente;
    }

    public void setClienteIdCliente(int ClienteIdCliente) {
        this.ClienteIdCliente = ClienteIdCliente;
    }

    public int getClienteFiadoCodFiado() {
        return ClienteFiadoCodFiado;
    }

    public void setClienteFiadoCodFiado(int ClienteFiadoCodFiado) {
        this.ClienteFiadoCodFiado = ClienteFiadoCodFiado;
    }

    public int getVentaCodVenta() {
        return VentaCodVenta;
    }

    public void setVentaCodVenta(int VentaCodVenta) {
        this.VentaCodVenta = VentaCodVenta;
    }

    @Override
    public String toString() {
        return "RegistroContado{" + "ClienteIdCliente=" + ClienteIdCliente + ", ClienteFiadoCodFiado=" + ClienteFiadoCodFiado + ", VentaCodVenta=" + VentaCodVenta + '}';
    }
    
    
}
