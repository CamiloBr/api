package com.nja.dao;

import com.nja.bd.Conexion;
import com.nja.modelos.Vendedor;
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
public class VendedorDao {

    private Connection conexion;

    public VendedorDao() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    //metodos CRUD
    public List<Vendedor> getVendedores() {
        List<Vendedor> vendedores = new ArrayList<Vendedor>();

        try {
            String sql = "SELECT us_id, us_usuario, us_correo, us_telefono, us_rol, "
                    + "us_activo FROM usuarios WHERE us_rol = 2;";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();

                v.setId(rs.getInt("us_id"));
                v.setUsuario(rs.getString("us_usuario"));
                v.setCorreo(rs.getString("us_correo"));
                v.setTelefono(rs.getString("us_telefono"));
                v.setRol(rs.getInt("us_rol"));
                v.setActivo(rs.getString("us_activo"));

                vendedores.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return vendedores;
    }

    public Vendedor getVendedor(int id) {
        Vendedor vendedor = new Vendedor();

        try {
            String sql = "SELECT us_id, us_usuario, us_correo, us_telefono, us_rol, "
                    + "us_activo FROM usuarios WHERE us_id = ? AND us_rol = 2;";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();

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

        return vendedor;
    }

    public Vendedor addVendedor(Vendedor vendedor) {

        try {
            String sql = "INSERT INTO usuarios (us_usuario, us_password, us_correo, us_telefono, us_rol) VALUES (?,?,?,?,?)";

            PreparedStatement pst = this.conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, vendedor.getUsuario());
            pst.setString(2, vendedor.getPassword());
            pst.setString(3, vendedor.getCorreo());
            pst.setString(4, vendedor.getTelefono());
            pst.setInt(5, 2);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    vendedor.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return vendedor;
    }

    public boolean editVendedor(Vendedor vendedor) {
        boolean resultado = false;
        try {
            String sql = "UPDATE usuarios SET us_usuario = ?, us_password = ?, us_correo = ?, "
                    + "us_telefono = ? WHERE us_id = ? AND us_rol = 2";

            PreparedStatement pst = this.conexion.prepareStatement(sql);

            pst.setString(1, vendedor.getUsuario());
            pst.setString(2, vendedor.getPassword());
            pst.setString(3, vendedor.getCorreo());
            pst.setString(4, vendedor.getTelefono());
            pst.setInt(5, vendedor.getId());
            pst.setInt(6, 2);

            int filas = pst.executeUpdate();

            if (filas > 0) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }

    public boolean deleteVendedor(int id) {
        boolean resultado = false;
        try {
            String sql = "DELETE FROM usuarios WHERE us_id = ? and us_rol = ?";

            PreparedStatement pst = this.conexion.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, 2);
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
