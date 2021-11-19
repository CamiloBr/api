package com.nja.controladores;

import com.nja.dao.UsuarioNDao;
import com.nja.modelos.UsuarioN;
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

@Path("/usuariosN")
public class UsuarioNControlador {
    
    private UsuarioNDao usuarioNDao = new UsuarioNDao();
    
    //get es para obtener datos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioN> getUsuariosN(){        
        return this.usuarioNDao.getUsuariosN();
    }
    
    //post es para insertar datos
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioN addUsuarioN(UsuarioN usuarioN){
        return this.usuarioNDao.addUsuarioN(usuarioN);
    }
    
    //solicitar datos de un solo recurso GET
    @GET
    @Path("/consultar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioN(@PathParam("id") int id){
        UsuarioN p = this.usuarioNDao.getUsuarioN(id);        
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
    public Mensajes editUsuarioN(UsuarioN usuarioN){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.usuarioNDao.editUsuarioN(usuarioN);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
    
    
    //eliminar un recurso DELETE
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes deleteUsuarioN(@PathParam("id") int id){
        Mensajes mensaje = new Mensajes("ERROR");
        
        boolean resultado = this.usuarioNDao.deleteUsuarioN(id);
        
        if(resultado){
            mensaje.setTexto("OK");
        }
        
        return mensaje;
    }
    
}
