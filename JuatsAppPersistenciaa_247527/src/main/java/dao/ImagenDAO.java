/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author jesus
 */
import Conexion.ConexionBD;
import dominio.Imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImagenDAO {

    private ConexionBD conexionBD;

    public ImagenDAO() {
    }

    public ImagenDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public void insertarImagen(Imagen imagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "INSERT INTO imagen (nombre, datos_imagen) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, imagen.getNombre());
            pstmt.setBytes(2, imagen.getDatosImagen());
            pstmt.executeUpdate();

            // Obtener el ID generado
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    imagen.setIdImagen(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
            }
        }
    }

    public Imagen obtenerImagenPorId(Long idImagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "SELECT nombre, datos_imagen FROM imagenes WHERE id_imagen = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, idImagen);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    byte[] datosImagen = rs.getBytes("datos_imagen");
                    return new Imagen(idImagen, nombre, datosImagen);
                }
            }
        }
        return null;
    }

    public void actualizarImagen(Imagen imagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "UPDATE imagen SET nombre = ?, datos_imagen = ? WHERE id_imagen = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, imagen.getNombre());
            pstmt.setBytes(2, imagen.getDatosImagen());
            pstmt.setLong(3, imagen.getIdImagen());
            pstmt.executeUpdate();
        }
    }

    public void eliminarImagen(Long idImagen) throws SQLException {
        Connection conn = conexionBD.crearConexion();

        String sql = "DELETE FROM imagen WHERE id_imagen = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, idImagen);
            pstmt.executeUpdate();
        }
    }
}
