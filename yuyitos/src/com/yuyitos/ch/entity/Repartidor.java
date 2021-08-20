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
public class Repartidor {
    public int IdRepartidor;
    public String Nombre;
    public String ApellidoMaterno;
    public String ApellidoPaterno;
    public int rut;
    public char dv;
    public int IdEmpresa;//clave foranea de la tabla empresa

    public Repartidor() {
    }

    public Repartidor(int IdRepartidor, String Nombre, String ApellidoMaterno, String ApellidoPaterno, int rut, char dv, int IdEmpresa) {
        this.IdRepartidor = IdRepartidor;
        this.Nombre = Nombre;
        this.ApellidoMaterno = ApellidoMaterno;
        this.ApellidoPaterno = ApellidoPaterno;
        this.rut = rut;
        this.dv = dv;
        this.IdEmpresa = IdEmpresa;
    }

    public int getIdRepartidor() {
        return IdRepartidor;
    }

    public void setIdRepartidor(int IdRepartidor) {
        this.IdRepartidor = IdRepartidor;
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
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public char getDv() {
        return dv;
    }

    public void setDv(char dv) {
        this.dv = dv;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int IdEmpresa) {
        this.IdEmpresa = IdEmpresa;
    }

    @Override
    public String toString() {
        return "Repartidor{" + "IdRepartidor=" + IdRepartidor + ", Nombre=" + Nombre + ", ApellidoMaterno=" + ApellidoMaterno + ", ApellidoPaterno=" + ApellidoPaterno + ", rut=" + rut + ", dv=" + dv + ", IdEmpresa=" + IdEmpresa + '}';
    }
    
    
}
