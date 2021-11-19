package com.nja.modelos;

public class Producto {

    private int id;
    private String nombre;
    private String marca;
    private float precio;
    private String categoria;
    private int cantidad;
    private String imagen;
    private String activo;

    public Producto() {
    }

    public Producto(int id, String nombre, String marca, float precio, String categoria, int cantidad, String imagen, String activo) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.imagen = imagen;
        this.activo = activo;
    }
    
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
    
    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public float getPrecio() {return precio;}

    public void setPrecio(float precio) {this.precio = precio;}
    
    public String getCategoria() {return categoria;}

    public void setCategoria(String categoria) {this.categoria = categoria;}
    
    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    
    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public String getActivo() {return activo;}

    public void setActivo(String activo) {this.activo = activo;}

}
