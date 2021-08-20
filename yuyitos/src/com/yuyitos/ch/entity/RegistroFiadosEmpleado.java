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
public class RegistroFiadosEmpleado {
    private int FiadosCodFiados;
    private int EmpleadoIdEmpleado;

    public RegistroFiadosEmpleado() {
    }

    public RegistroFiadosEmpleado(int FiadosCodFiados, int EmpleadoIdEmpleado) {
        this.FiadosCodFiados = FiadosCodFiados;
        this.EmpleadoIdEmpleado = EmpleadoIdEmpleado;
    }

    public int getFiadosCodFiados() {
        return FiadosCodFiados;
    }

    public void setFiadosCodFiados(int FiadosCodFiados) {
        this.FiadosCodFiados = FiadosCodFiados;
    }

    public int getEmpleadoIdEmpleado() {
        return EmpleadoIdEmpleado;
    }

    public void setEmpleadoIdEmpleado(int EmpleadoIdEmpleado) {
        this.EmpleadoIdEmpleado = EmpleadoIdEmpleado;
    }

    @Override
    public String toString() {
        return "RegistroFiadosEmpleado{" + "FiadosCodFiados=" + FiadosCodFiados + ", EmpleadoIdEmpleado=" + EmpleadoIdEmpleado + '}';
    }
    
    
}
