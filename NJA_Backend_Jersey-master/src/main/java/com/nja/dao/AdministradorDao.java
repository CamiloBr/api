package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camilo
 */
public class AdministradorDao {
 
    private Connection conexion;

    public AdministradorDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos CRUD
    public List<Administrador> getAdministradores() {
        List<Administrador> administradores = new ArrayList<Administrador>();

        try {
            String sql = "SELECT us_id, us_usuario, us_correo, us_telefono, us_rol, "
                    + "us_activo FROM usuarios WHERE us_rol = 1;";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Administrador v = new Administrador();

                v.setId(rs.getInt("us_id"));
                v.setUsuario(rs.getString("us_usuario"));
                v.setCorreo(rs.getString("us_correo"));
                v.setTelefono(rs.getString("us_telefono"));
                v.setRol(rs.getInt("us_rol"));
                v.setActivo(rs.getString("us_activo"));

                administradores.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return administradores;
    }

    public Administrador getAdministrador(int id) {
        
        Administrador administrador = new Administrador();

        try {
            String sql = "SELECT us_id, us_usuario, us_correo, us_telefono, us_rol, "
                    + "us_activo FROM usuarios WHERE us_id = ? AND us_rol = 1;";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Administrador v = new Administrador();

                v.setId(rs.getInt("us_id"));
                v.setUsuario(rs.getString("us_usuario"));
                v.setCorreo(rs.getString("us_correo"));
                v.setTelefono(rs.getString("us_telefono"));
                v.setRol(rs.getInt("us_rol"));
                v.setActivo(rs.getString("us_activo"));

                return v;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return administrador;
    }

    public Administrador addAdministrador(Administrador administrador) {

        try {
            String sql = "INSERT INTO usuarios (us_usuario, us_password, us_correo, us_telefono, us_rol) VALUES (?,?,?,?,?)";

            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, administrador.getUsuario());
            pst.setString(2, administrador.getPassword());
            pst.setString(3, administrador.getCorreo());
            pst.setString(4, administrador.getTelefono());
            pst.setInt(5, 1);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    administrador.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return administrador;
    }

    public boolean editAdministrador(Administrador administrador) {
        boolean resultado = false;
        try {
            String sql = "UPDATE usuarios SET us_usuario = ?, us_password = ?, us_correo = ?, "
                    + "us_telefono = ? WHERE us_id = ? AND us_rol = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);

            pst.setString(1, administrador.getUsuario());
            pst.setString(2, administrador.getPassword());
            pst.setString(3, administrador.getCorreo());
            pst.setString(4, administrador.getTelefono());
            pst.setInt(5, administrador.getId());
            pst.setInt(6, 1);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteAdministrador(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM usuarios WHERE us_id = ? and us_rol = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, 1);
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
