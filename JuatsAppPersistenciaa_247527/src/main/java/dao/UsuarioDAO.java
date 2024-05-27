package dao;

import Conexion.ConexionBD;
import Conexion.IConexion;
import dominio.Usuario;
import interfaces.IUsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jairo G. Rodriguez Hernandez 00000213248
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexionBD;

    public UsuarioDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "INSERT INTO Usuarios (telefono, genero, perfil, fechaNacimiento, contrasenya, domicilio_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getGenero().toString());
            ps.setBytes(3, usuario.getPerfil().getDatosImagen());
            ps.setTimestamp(4, new Timestamp(usuario.getFechaNacimiento().getTimeInMillis()));
            ps.setString(5, usuario.getContrasenya());
            ps.setInt(6, usuario.getDomicilio().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }
    
    public Usuario createUsuarioSimagen(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "INSERT INTO Usuarios (telefono, generoUsuario, fechaNacimiento, contrasenya, id_domicilio) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getGenero().toString());
            ps.setTimestamp(3, new Timestamp(usuario.getFechaNacimiento().getTimeInMillis()));
            ps.setString(4, usuario.getContrasenya());
            ps.setInt(5, usuario.getDomicilio().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            System.out.println("Error al crear el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }


    @Override
    public Usuario readUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "SELECT * FROM Usuarios WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId(rs.getInt("id"));
                usuarioEncontrado.setTelefono(rs.getString("telefono"));
                // Resto de la asignación de atributos
                return usuarioEncontrado;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "UPDATE Usuarios SET telefono = ?, genero = ?, perfil = ?, fechaNacimiento = ?, contrasenya = ?, domicilio_id = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getTelefono());
            ps.setString(2, usuario.getGenero().toString());
            ps.setBytes(3, usuario.getPerfil().getDatosImagen());
            ps.setTimestamp(4, new Timestamp(usuario.getFechaNacimiento().getTimeInMillis()));
            ps.setString(5, usuario.getContrasenya());
            ps.setInt(6, usuario.getDomicilio().getId());
            ps.setInt(7, usuario.getId());
            ps.executeUpdate();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "DELETE FROM Usuarios WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
    }

    @Override
    public List<Usuario> readAllUsuario() {
        Connection conn = null;
        try {
            conn = conexionBD.crearConexion();
            String sql = "SELECT * FROM Usuarios";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setTelefono(rs.getString("telefono"));
                // Resto de la asignación de atributos
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
            return usuarios;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener todos los usuarios: " + ex.getMessage());
        } finally {
            if (conn != null) {
                conexionBD.cerrarConexion();
            }
        }
        return null;
    }
}
