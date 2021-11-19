package com.nja.controladores;

import com.nja.dao.ProductoDao;
import com.nja.modelos.Producto;
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
import javax.ws.rs.core.Response.Status;

@Path("/productos")
public class ProductoControlador {
    
    private ProductoDao productoDAO = new ProductoDao();
    
    //get es para obtener datos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getProductos(){        
        return this.productoDAO.getProductos();
    }
    
    //post es para insertar datos
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Producto addProducto(Producto producto){
        return this.productoDAO.addProducto(producto);
    }
    
    //solicitar datos de un solo recurso GET
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") int id){
        Producto p = this.productoDAO.getProducto(id);        
        if(p.getId()!=0){
            return Response.ok(p).status(Status.CREATED).build();
        }
        else{
            Mensajes mensaje = new Mensajes("ERROR");
            return Response.ok(mensaje).status(Status.NOT_FOUND).build();
        }
    }
    
    /*@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto getProducto(@PathParam("id") int id){
        return this.productoDAO.getProducto(id);                
    }*/
    
    //actualizar un recurso PUT/PATCH
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes editProducto(Producto producto){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.productoDAO.editProducto(producto);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
    
    
    //eliminar un recurso DELETE
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Mensajes deleteProducto(@PathParam("id") int id){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.productoDAO.deleteProducto(id);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
}
