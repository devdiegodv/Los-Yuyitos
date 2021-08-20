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
public class Cliente {
    private int IdCliente;
    private String FechaRegistro;
    private String Nombre;
    private String ApellidoMaterno;
    private String ApellidoPaterno;
    private int Rut;
    private char DVRut;
    private String Direccion;
    private char deuda;
    private int IdFiado;

    public Cliente() {
    }

    public Cliente(int IdCliente, String FechaRegistro, String Nombre, String ApellidoMaterno, String ApellidoPaterno, int Rut, char DVRut, String Direccion, char deuda, int IdFiado) {
        this.IdCliente = IdCliente;
        this.FechaRegistro = FechaRegistro;
        this.Nombre = Nombre;
        this.ApellidoMaterno = ApellidoMaterno;
        this.ApellidoPaterno = ApellidoPaterno;
        this.Rut = Rut;
        this.DVRut = DVRut;
        this.Direccion = Direccion;
        this.deuda = deuda;
        this.IdFiado = IdFiado;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public int getRut() {
        return Rut;
    }

    public void setRut(int Rut) {
        this.Rut = Rut;
    }

    public char getDVRut() {
        return DVRut;
    }

    public void setDVRut(char DVRut) {
        this.DVRut = DVRut;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public char getDeuda() {
        return deuda;
    }

    public void setDeuda(char deuda) {
        this.deuda = deuda;
    }

    public int getIdFiado() {
        return IdFiado;
    }

    public void setIdFiado(int IdFiado) {
        this.IdFiado = IdFiado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "IdCliente=" + IdCliente + ", FechaRegistro=" + FechaRegistro + ", Nombre=" + Nombre + ", ApellidoMaterno=" + ApellidoMaterno + ", ApellidoPaterno=" + ApellidoPaterno + ", Rut=" + Rut + ", DVRut=" + DVRut + ", Direccion=" + Direccion + ", deuda=" + deuda + ", IdFiado=" + IdFiado + '}';
    }
    
    
    
    
    
}
