package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    private Connection conexion;

    public UsuarioDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    public Usuario login(Usuario usuario) {

        try {
            String sql = "SELECT us_id, us_rol, us_activo FROM usuarios WHERE us_usuario = ? AND us_password = ? AND us_activo = 'S'";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getPassword());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                usuario.setId(rs.getInt("us_id"));
                usuario.setRol(rs.getInt("us_tipo"));
                usuario.setActivo(rs.getString("us_activo"));

                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return usuario;
    }

}
