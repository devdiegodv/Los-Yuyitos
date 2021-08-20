/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.test;

import com.yuyitos.ch.dao.IngresarEmpleadoDAO;
import com.yuyitos.ch.entity.Empleado;

/**
 *
 * @author tavo-
 */
public class TestEmpleado {
    //IngresarFichaClienteBO ifcbo = new IngresarFichaClienteBO();
    Empleado empl = new Empleado();

    IngresarEmpleadoDAO iemp = new IngresarEmpleadoDAO();
    String mensaje = "";

    
    public void insertar(){
        

        empl.setNombre("Gustavo");
        empl.setApellidoMaterno("Gonzalez");
        empl.setApellidoPaterno("Neira");
        empl.setCargo("ElqueMira");
        empl.setDireccion("Avenida siempre viva #2483");
        empl.setDvRut('k');
        empl.setFechaTerminoContrato("2021-10-05");
        empl.setRut(20379209);
        empl.setSueldo(310000);
        empl.setTelefono(93481738);
        
        
        
        iemp.agregarEmpleado(empl);


        
       
        //mensaje = ifcbo.agregarFichaCliente(cli);
        System.out.println("correcto");
    }
    public void modificar(){

        empl.setNombre("Gustavo");
        empl.setApellidoMaterno("Gonzalez");
        empl.setApellidoPaterno("Neira");
        empl.setCargo("ElqueMira");
        empl.setDireccion("Avenida siempre viva #2483");
        empl.setDvRut('k');
        empl.setFechaTerminoContrato("2022-04-02");
        empl.setRut(20379209);
        empl.setSueldo(310000);
        empl.setTelefono(93481738);
        empl.setIdEmpleado(1);
        
        
        
        iemp.ModificarEmpleado(empl);
       
    }
    
    public void eliminar(){
        iemp.EliminarEmpleado(1);//solo requiere el id
        System.out.println(mensaje);
    }
    public static void main (String[] args){
        TestEmpleado test = new TestEmpleado();
        //test.insertar();
        //test.modificar();
        test.eliminar();
    }
}
