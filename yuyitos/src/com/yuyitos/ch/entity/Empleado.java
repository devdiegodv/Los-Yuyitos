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
public class Empleado {
   private int IdEmpleado;
   private String Nombre;
   private String ApellidoPaterno;
   private String ApellidoMaterno;
   private int rut;
   private char dvRut;
   private String Direccion;
   private int telefono;
   private String FechaContrato;
   private String FechaTerminoContrato;
   private String Cargo;
   private int sueldo;

    public Empleado() {
    }

    public Empleado(int IdEmpleado, String Nombre, String ApellidoPaterno, String ApellidoMaterno, int rut, char dvRut, String Direccion, int telefono, String FechaContrato, String FechaTerminoContrato, String Cargo, int sueldo) {
        this.IdEmpleado = IdEmpleado;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.rut = rut;
        this.dvRut = dvRut;
        this.Direccion = Direccion;
        this.telefono = telefono;
        this.FechaContrato = FechaContrato;
        this.FechaTerminoContrato = FechaTerminoContrato;
        this.Cargo = Cargo;
        this.sueldo = sueldo;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
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

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public char getDvRut() {
        return dvRut;
    }

    public void setDvRut(char dvRut) {
        this.dvRut = dvRut;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFechaContrato() {
        return FechaContrato;
    }

    public void setFechaContrato(String FechaContrato) {
        this.FechaContrato = FechaContrato;
    }

    public String getFechaTerminoContrato() {
        return FechaTerminoContrato;
    }

    public void setFechaTerminoContrato(String FechaTerminoContrato) {
        this.FechaTerminoContrato = FechaTerminoContrato;
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
        return "Empleado{" + "IdEmpleado=" + IdEmpleado + ", Nombre=" + Nombre + ", ApellidoPaterno=" + ApellidoPaterno + ", ApellidoMaterno=" + ApellidoMaterno + ", rut=" + rut + ", dvRut=" + dvRut + ", Direccion=" + Direccion + ", telefono=" + telefono + ", FechaContrato=" + FechaContrato + ", FechaTerminoContrato=" + FechaTerminoContrato + ", Cargo=" + Cargo + ", sueldo=" + sueldo + '}';
    }
    
   
    
}
