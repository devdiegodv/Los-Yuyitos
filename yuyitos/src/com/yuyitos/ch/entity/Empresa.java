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
public class Empresa {
    private int IdEmpresa;
    private String Nombre;
    private int Rut;
    private char DV;
    private String Rubro;
    private String Direccion;
    private int Telefono;
    private String email;

    public Empresa() {
    }

    public Empresa(int IdEmpresa, String Nombre, int Rut, char DV, String Rubro, String Direccion, int Telefono, String email) {
        this.IdEmpresa = IdEmpresa;
        this.Nombre = Nombre;
        this.Rut = Rut;
        this.DV = DV;
        this.Rubro = Rubro;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.email = email;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getRut() {
        return Rut;
    }

    public void setRut(int Rut) {
        this.Rut = Rut;
    }

    public char getDV() {
        return DV;
    }

    public void setDV(char DV) {
        this.DV = DV;
    }

    public String getRubro() {
        return Rubro;
    }

    public void setRubro(String Rubro) {
        this.Rubro = Rubro;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empresa{" + "IdEmpresa=" + IdEmpresa + ", Nombre=" + Nombre + ", Rut=" + Rut + ", DV=" + DV + ", Rubro=" + Rubro + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", email=" + email + '}';
    }
    
}
