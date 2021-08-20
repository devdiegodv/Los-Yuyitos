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
public class Persona {
    private String Nombre;
    private String ApellidoPaterno;
    private int RutPersona;
    private String ApellidoMaterno;
    private String FechaRegistro;
    private char Deuda;
    private char Moroso;
    private String FechaInicioCon;
    private String FechaTerminoCon;
    private String Cargo;
    private int sueldo;

    public Persona() {
    }

    public Persona(String Nombre, String ApellidoPaterno, int RutPersona, String ApellidoMaterno, String FechaRegistro, char Deuda, char Moroso, String FechaInicioCon, String FechaTerminoCon, String Cargo, int sueldo) {
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.RutPersona = RutPersona;
        this.ApellidoMaterno = ApellidoMaterno;
        this.FechaRegistro = FechaRegistro;
        this.Deuda = Deuda;
        this.Moroso = Moroso;
        this.FechaInicioCon = FechaInicioCon;
        this.FechaTerminoCon = FechaTerminoCon;
        this.Cargo = Cargo;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public int getRutPersona() {
        return RutPersona;
    }

    public void setRutPersona(int RutPersona) {
        this.RutPersona = RutPersona;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public char getDeuda() {
        return Deuda;
    }

    public void setDeuda(char Deuda) {
        this.Deuda = Deuda;
    }

    public char getMoroso() {
        return Moroso;
    }

    public void setMoroso(char Moroso) {
        this.Moroso = Moroso;
    }

    public String getFechaInicioCon() {
        return FechaInicioCon;
    }

    public void setFechaInicioCon(String FechaInicioCon) {
        this.FechaInicioCon = FechaInicioCon;
    }

    public String getFechaTerminoCon() {
        return FechaTerminoCon;
    }

    public void setFechaTerminoCon(String FechaTerminoCon) {
        this.FechaTerminoCon = FechaTerminoCon;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Persona{" + "Nombre=" + Nombre + ", ApellidoPaterno=" + ApellidoPaterno + ", RutPersona=" + RutPersona + ", ApellidoMaterno=" + ApellidoMaterno + ", FechaRegistro=" + FechaRegistro + ", Deuda=" + Deuda + ", Moroso=" + Moroso + ", FechaInicioCon=" + FechaInicioCon + ", FechaTerminoCon=" + FechaTerminoCon + ", Cargo=" + Cargo + ", sueldo=" + sueldo + '}';
    }
    
    
    
}
