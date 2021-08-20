/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.test;


import com.yuyitos.ch.dao.IngresarFichaClienteDAO;
import com.yuyitos.ch.entity.Cliente;
import com.yuyitos.ch.entity.Persona;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author tavo-
 */
public class Test {
    //IngresarFichaClienteBO ifcbo = new IngresarFichaClienteBO();
    Cliente cli = new Cliente();
    Persona per = new Persona();
    IngresarFichaClienteDAO ingresarficha  = new IngresarFichaClienteDAO();
    String mensaje = "";
    
    
    public void insertar(){
        

        
        cli.setApellidoMaterno("Gonzalez");//me salio un error ora-12899 value too large, esto pasó porque el metodo ingresar ficha cliente estaba en int
        cli.setApellidoPaterno("Neira");
        cli.setDVRut('3');
        cli.setDeuda('s');
        cli.setDireccion("calle siempre viva #4132");
        
        cli.setNombre("Gustavo");
        cli.setRut(20379209);
        ingresarficha.agregarFichaCliente(cli);


        
       
        //mensaje = ifcbo.agregarFichaCliente(cli);
        System.out.println("correcto");
    }
    public void modificar(){

        cli.setApellidoMaterno("Gonzaz");//me salio un error ora-12899 value too large, esto pasó porque el metodo ingresar ficha cliente estaba en int
        cli.setApellidoPaterno("Nei");
        cli.setDVRut('3');
        cli.setDeuda('s');
        cli.setDireccion("calle siempre viva #4132");
        

        cli.setNombre("Gus");
        cli.setRut(20111222);
        cli.setIdCliente(1);//id para el where
        
        ingresarficha.ModificarFichaCliente(cli);

        System.out.println("Correcto");
       
    }
    
    public void eliminar(){
        ingresarficha.EliminarCliente(1);//solo requiere el id
        System.out.println(mensaje);
    }
    public static void main (String[] args){
        Test test = new Test();
        //test.insertar();
        test.modificar();
        //test.eliminar();
    }
}
