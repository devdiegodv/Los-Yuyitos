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
public class Ente {
    private int IdEnte;
    private String Nombre;
    private int RutEnte;
    private char DVRutEnte;
    private int AreaTelefono;
    private int NumeroTelefono;
    private String Direccion;

    public Ente() {
    }

    public Ente(int IdEnte, String Nombre, int RutEnte, char DVRutEnte, int AreaTelefono, int NumeroTelefono, String Direccion) {
        this.IdEnte = IdEnte;
        this.Nombre = Nombre;
        this.RutEnte = RutEnte;
        this.DVRutEnte = DVRutEnte;
        this.AreaTelefono = AreaTelefono;
        this.NumeroTelefono = NumeroTelefono;
        this.Direccion = Direccion;
    }

    public int getIdEnte() {
        return IdEnte;
    }

    public void setIdEnte(int IdEnte) {
        this.IdEnte = IdEnte;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getRutEnte() {
        return RutEnte;
    }

    public void setRutEnte(int RutEnte) {
        this.RutEnte = RutEnte;
    }

    public char getDVRutEnte() {
        return DVRutEnte;
    }

    public void setDVRutEnte(char DVRutEnte) {
        this.DVRutEnte = DVRutEnte;
    }

    public int getAreaTelefono() {
        return AreaTelefono;
    }

    public void setAreaTelefono(int AreaTelefono) {
        this.AreaTelefono = AreaTelefono;
    }

    public int getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(int NumeroTelefono) {
        this.NumeroTelefono = NumeroTelefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    @Override
    public String toString() {
        return "Ente{" + "IdEnte=" + IdEnte + ", Nombre=" + Nombre + ", RutEnte=" + RutEnte + ", DVRutEnte=" + DVRutEnte + ", AreaTelefono=" + AreaTelefono + ", NumeroTelefono=" + NumeroTelefono + ", Direccion=" + Direccion + '}';
    }
    
    
}
