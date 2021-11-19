package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.UsuarioN;
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
public class UsuarioNDao {

    private Connection conexion;

    public UsuarioNDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos CRUD
    public List<UsuarioN> getUsuariosN() {
        List<UsuarioN> usuariosN = new ArrayList<UsuarioN>();

        try {
            String sql = "select us_id, us_usuario, us_correo, us_telefono, us_rol, us_activo from usuarios where us_rol = 3";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                UsuarioN usn = new UsuarioN();

                usn.setId(rs.getInt("us_id"));
                usn.setUsuario(rs.getString("us_usuario"));
                usn.setCorreo(rs.getString("us_correo"));
                usn.setTelefono(rs.getString("us_telefono"));
                usn.setRol(rs.getInt("us_rol"));
                usn.setActivo(rs.getString("us_activo"));

                usuariosN.add(usn);
                
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return usuariosN;
    }

    public UsuarioN getUsuarioN(int id) {
        UsuarioN usuarioN = new UsuarioN();

        try {
            String sql = "select us_id, us_usuario, us_correo, us_telefono, us_rol, "
                    + "us_activo from usuarios where us_id = ? and us_rol = 3";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                UsuarioN usn = new UsuarioN();

                usn.setId(rs.getInt("us_id"));
                usn.setUsuario(rs.getString("us_usuario"));
                usn.setCorreo(rs.getString("us_correo"));
                usn.setTelefono(rs.getString("us_telefono"));
                usn.setRol(rs.getInt("us_rol"));
                usn.setActivo(rs.getString("us_activo"));

                return usn;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return usuarioN;
        
    }

    public UsuarioN addUsuarioN(UsuarioN usuarioN) {

        try {
            String sql = "INSERT INTO usuarios (us_usuario, us_password, us_correo, "
                    + "us_telefono, us_rol) VALUES (?,?,?,?,?)";

            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, usuarioN.getUsuario());
            pst.setString(2, usuarioN.getPassword());
            pst.setString(3, usuarioN.getCorreo());
            pst.setString(4, usuarioN.getTelefono());
            pst.setInt(5, 3);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    usuarioN.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return usuarioN;
    }

    public boolean editUsuarioN(UsuarioN usuarioN) {
        boolean resultado = false;
        try {
            String sql = "Update usuarios set us_usuario = ?, us_password = ?, "
                    + "us_correo = ?, us_telefono = ? where us_id = ? and us_rol = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);

            pst.setString(1, usuarioN.getUsuario());
            pst.setString(2, usuarioN.getPassword());
            pst.setString(3, usuarioN.getCorreo());
            pst.setString(4, usuarioN.getTelefono());
            pst.setInt(5, usuarioN.getId());
            pst.setInt(6, 3);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteUsuarioN(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM usuarios WHERE us_id = ? and us_rol = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, 3);
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
