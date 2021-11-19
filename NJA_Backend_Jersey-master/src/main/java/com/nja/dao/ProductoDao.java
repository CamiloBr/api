package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class ProductoDao {

    private Connection conexion;

    public ProductoDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos CRUD
    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<Producto>();

        try {
            String sql = "SELECT po_id, po_nombre, po_marca, po_precio, po_categoria, po_cantidad, po_imagen, po_activo FROM productos WHERE po_activo = 'S' AND po_cantidad > 0";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                
                p.setId(rs.getInt("po_id"));
                p.setNombre(rs.getString("po_nombre"));
                p.setMarca(rs.getString("po_marca"));
                p.setPrecio(rs.getFloat("po_precio"));
                p.setCategoria(rs.getString("po_categoria"));
                p.setCantidad(rs.getInt("po_cantidad"));
                p.setImagen(rs.getString("po_imagen"));
                p.setActivo(rs.getString("po_activo"));

                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return productos;
    }

    public Producto getProducto(int id) {
        Producto producto = new Producto();

        try {
            String sql = "SELECT po_id, po_nombre, po_marca, po_precio, po_categoria, po_cantidad, po_imagen, po_activo FROM productos WHERE po_activo = 'S' AND po_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                
                p.setId(rs.getInt("po_id"));
                p.setNombre(rs.getString("po_nombre"));
                p.setMarca(rs.getString("po_marca"));
                p.setPrecio(rs.getFloat("po_precio"));
                p.setCategoria(rs.getString("po_categoria"));
                p.setCantidad(rs.getInt("po_cantidad"));
                p.setImagen(rs.getString("po_imagen"));
                p.setActivo(rs.getString("po_activo"));

                return p;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return producto;
    }

    public Producto addProducto(Producto producto) {

        try {
            String sql = "INSERT INTO productos VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setInt(1, 0);
            pst.setString(2, producto.getNombre());
            pst.setString(3, producto.getMarca());
            pst.setFloat(4, producto.getPrecio());
            pst.setString(5, producto.getCategoria());
            pst.setInt(6, producto.getCantidad());
            pst.setString(7, producto.getImagen());
            pst.setString(8, producto.getActivo());
            
            int filas = pst.executeUpdate();

            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    producto.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return producto;
    }

    public boolean editProducto(Producto producto) {
        boolean resultado = false;
        try {
            String sql = "UPDATE productos SET po_nombre = ?, po_marca = ?, po_precio = ?, po_categoria = ?, po_cantidad = ?, po_imagen = ?, po_activo = ? WHERE po_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getMarca());
            pst.setFloat(3, producto.getPrecio());
            pst.setString(4, producto.getCategoria());
            pst.setInt(5, producto.getCantidad());
            pst.setString(6, producto.getImagen());
            pst.setString(7, producto.getActivo());
            pst.setInt(8, producto.getId());
            
            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteProducto(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM productos WHERE po_id = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

}
