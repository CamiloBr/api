package com.nja.controladores;

import com.nja.dao.AdministradorDao;
import com.nja.modelos.Administrador;
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
@Path("/administradores")
public class AdministradorControlador {

    private AdministradorDao administradorDao = new AdministradorDao();

    //get es para obtener datos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Administrador> getAdministradores() {
        return this.administradorDao.getAdministradores();
    }

    //post es para insertar datos
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Administrador addAdministrador(Administrador administrador) {
        return this.administradorDao.addAdministrador(administrador);
    }

    //solicitar datos de un solo recurso GET
    @GET
    @Path("/consultar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdministrador(@PathParam("id") int id) {
        Administrador p = this.administradorDao.getAdministrador(id);
        if (p.getId() != 0) {
            return Response.ok(p).status(Response.Status.CREATED).build();
        } else {
            Mensajes mensaje = new Mensajes("ERROR");
            return Response.ok(mensaje).status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes editAdministrador(Administrador administrador) {
        Mensajes mensaje = new Mensajes("ERROR");

        boolean resultado = this.administradorDao.editAdministrador(administrador);

        if (resultado) {
            mensaje.setTexto("OK");
        }

        return mensaje;
    }

    //eliminar un recurso DELETE
    @DELETE
    @Path("/eliminar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensajes deleteAdministrador(@PathParam("id") int id) {
        Mensajes mensaje = new Mensajes("ERROR");

        boolean resultado = this.administradorDao.deleteAdministrador(id);

        if (resultado) {
            mensaje.setTexto("OK");
        }

        return mensaje;
    }

}
