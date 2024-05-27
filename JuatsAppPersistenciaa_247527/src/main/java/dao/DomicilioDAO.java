/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Conexion.ConexionBD;
import Conexion.IConexion;
import dominio.Domicilio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jesus
 */
public class DomicilioDAO {

    private IConexion conexionBD;

    public DomicilioDAO() {
    }

    public DomicilioDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    

    public void create(Domicilio domicilio) {
        Connection conn = null;

        String sql = "INSERT INTO domicilio (calle, numero, colonia, codigoPostal) VALUES (?, ?, ?, ?)";
        try {
            conn = conexionBD.crearConexion();

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, domicilio.getCalle());
            pstmt.setString(2, domicilio.getNumero());
            pstmt.setString(3, domicilio.getColonia());
            pstmt.setString(4, domicilio.getCodigoPostal());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        domicilio.setId(generatedKeys.getInt(1));  // Establece el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}
