package com.nja.modelos;

/**
 *
 * @author Camilo
 */
public class UsuarioN {
 
    private int id;
    private String usuario;
    private String password;
    private String correo;
    private String telefono;
    private int rol;
    private String activo;

    public UsuarioN() {
    }

    public UsuarioN(int id, String usuario, String password, String correo, String telefono, int rol, String activo) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
}
