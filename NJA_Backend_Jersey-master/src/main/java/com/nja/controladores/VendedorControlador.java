/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nja.controladores;

import com.nja.dao.VendedorDao;
import com.nja.modelos.Vendedor;
import com.nja.utilidades.Mensajes;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Camilo
 */

@Path("/vendedores")
public class VendedorControlador {
    
    private VendedorDao vendedorDao = new VendedorDao();
    
    //get es para obtener datos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vendedor> getVendedores(){        
        return this.vendedorDao.getVendedores();
    }
    
    //post es para insertar datos
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Vendedor addVendedor(Vendedor vendedor){
        return this.vendedorDao.addVendedor(vendedor);
    }
    
    //solicitar datos de un solo recurso GET
    @GET
    @Path("/consultar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVendedor(@PathParam("id") int id){
        Vendedor p = this.vendedorDao.getVendedor(id);        
        if(p.getId()!=0){
            return Response.ok(p).status(Response.Status.CREATED).build();
        }
        else{
            Mensajes mensaje = new Mensajes("ERROR");
            return Response.ok(mensaje).status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes editVendedor(Vendedor vendedor){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.vendedorDao.editVendedor(vendedor);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
    
    
    //eliminar un recurso DELETE
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes deleteVendedor(@PathParam("id") int id){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.vendedorDao.deleteVendedor(id);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
    
}
