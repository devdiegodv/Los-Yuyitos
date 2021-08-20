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
public class RegistroFiadoCliente {
    private int ClienteIdCliente;
    private int FiadosCodFiado;

    public RegistroFiadoCliente() {
    }

    public RegistroFiadoCliente(int ClienteIdCliente, int FiadosCodFiado) {
        this.ClienteIdCliente = ClienteIdCliente;
        this.FiadosCodFiado = FiadosCodFiado;
    }

    public int getClienteIdCliente() {
        return ClienteIdCliente;
    }

    public void setClienteIdCliente(int ClienteIdCliente) {
        this.ClienteIdCliente = ClienteIdCliente;
    }

    public int getFiadosCodFiado() {
        return FiadosCodFiado;
    }

    public void setFiadosCodFiado(int FiadosCodFiado) {
        this.FiadosCodFiado = FiadosCodFiado;
    }

    @Override
    public String toString() {
        return "RegistroFiadoCliente{" + "ClienteIdCliente=" + ClienteIdCliente + ", FiadosCodFiado=" + FiadosCodFiado + '}';
    }
    
    
}
