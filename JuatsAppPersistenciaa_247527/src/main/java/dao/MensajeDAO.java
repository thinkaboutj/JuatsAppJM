package dao;

import Conexion.ConexionBD;
import dominio.Mensaje;
import dominio.Usuario;
import interfaces.IMensajeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jairo G. Rodriguez Hernandez 00000213248
 */
public class MensajeDAO implements IMensajeDAO {

     private final ConexionBD conexionBD;

    public MensajeDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public Mensaje createMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "INSERT INTO mensaje (texto, fecha_hora_registro, usuario_id, imagen_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, mensaje.getTexto());
            statement.setTimestamp(2, new Timestamp(mensaje.getFechaHoraRegistro().getTimeInMillis()));
            statement.setInt(3, mensaje.getUsuario().getId());
            if (mensaje.getImagen() != null) {
                statement.setLong(4, mensaje.getImagen().getIdImagen());
            } else {
                statement.setNull(4, Types.BIGINT);
            }
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    mensaje.setId(generatedKeys.getInt(1));
                }
                return mensaje;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Mensaje readMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "SELECT * FROM mensaje WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, mensaje.getId());
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                Mensaje mensajeEncontrado = new Mensaje();
                mensajeEncontrado.setId(resultado.getInt("id"));
                mensajeEncontrado.setTexto(resultado.getString("texto"));
                Calendar fechaHoraRegistro = Calendar.getInstance();
                fechaHoraRegistro.setTime(resultado.getTimestamp("fecha_hora_registro"));
                mensajeEncontrado.setFechaHoraRegistro(fechaHoraRegistro);
                // Set the usuario and imagen fields
                // mensajeEncontrado.setUsuario(usuarioDAO.readUsuarioById(resultado.getInt("usuario_id")));
                // mensajeEncontrado.setImagen(imagenDAO.readImagenById(resultado.getLong("imagen_id")));
                return mensajeEncontrado;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Mensaje updateMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "UPDATE mensaje SET texto = ?, fecha_hora_registro = ?, usuario_id = ?, imagen_id = ? WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, mensaje.getTexto());
            statement.setTimestamp(2, new Timestamp(mensaje.getFechaHoraRegistro().getTimeInMillis()));
            statement.setInt(3, mensaje.getUsuario().getId());
            if (mensaje.getImagen() != null) {
                statement.setLong(4, mensaje.getImagen().getIdImagen());
            } else {
                statement.setNull(4, Types.BIGINT);
            }
            statement.setInt(5, mensaje.getId());
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                return mensaje;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void deleteMensaje(Mensaje mensaje) {
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "DELETE FROM mensaje WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, mensaje.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Mensaje> readAllMensaje() {
        List<Mensaje> mensajes = new ArrayList<>();
        try (Connection conexion = conexionBD.crearConexion()) {
            String query = "SELECT * FROM mensaje";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(query);
            while (resultado.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(resultado.getInt("id"));
                mensaje.setTexto(resultado.getString("texto"));
                Calendar fechaHoraRegistro = Calendar.getInstance();
                fechaHoraRegistro.setTime(resultado.getTimestamp("fecha_hora_registro"));
                mensaje.setFechaHoraRegistro(fechaHoraRegistro);
                // Set the usuario and imagen fields
                // mensaje.setUsuario(usuarioDAO.readUsuarioById(resultado.getInt("usuario_id")));
                // mensaje.setImagen(imagenDAO.readImagenById(resultado.getLong("imagen_id")));
                mensajes.add(mensaje);
            }
            return mensajes;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
