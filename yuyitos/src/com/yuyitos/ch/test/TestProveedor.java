/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuyitos.ch.test;



import com.yuyitos.ch.dao.IngresarFichaProveedorDAO;
import com.yuyitos.ch.entity.Empresa;


/**
 *
 * @author tavo-
 */
public class TestProveedor {
   //IngresarFichaClienteBO ifcbo = new IngresarFichaClienteBO();
    Empresa emp = new Empresa();

    IngresarFichaProveedorDAO ingresarficha  = new IngresarFichaProveedorDAO();
    String mensaje = "";

    
    public void insertar(){
        

        
        emp.setNombre("Papeleria");
        emp.setRut(20379209);
        emp.setDV('k');
        emp.setRubro("PapeleriaXD");
        emp.setDireccion("avenida siempre viva");
        emp.setTelefono(934813738);
        emp.setEmail("Papeleriavienvellaka@gmail.com");
        
        ingresarficha.agregarFichaEmpresa(emp);


        
       
        //mensaje = ifcbo.agregarFichaCliente(cli);
        System.out.println("correcto");
    }
    public void modificar(){

        emp.setNombre("Papl");
        emp.setRut(211209);
        emp.setDV('k');
        emp.setRubro("PapelsdfaXD");
        emp.setDireccion("avenidgfd viva");
        emp.setTelefono(9111);
        emp.setEmail("Papdfgaka@gmail.com");
        
        emp.setIdEmpresa(1);//id para el where
        
        ingresarficha.ModificarFichaEmpresa(emp);

        System.out.println("Correcto");
       
    }
    
    public void eliminar(){
        ingresarficha.EliminarEmpresa(1);//solo requiere el id
        System.out.println(mensaje);
    }
    public static void main (String[] args){
        TestProveedor test = new TestProveedor();
        //test.insertar();
        //test.modificar();
        test.eliminar();
    }
}
