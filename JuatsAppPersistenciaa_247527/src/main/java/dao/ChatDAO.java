package dao;

import Conexion.ConexionBD;
import interfaces.IChatDAO;
import Conexion.IConexion;
import dominio.Chat;
import dominio.Imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo G. Rodriguez Hernandez 00000213248
 */
public class ChatDAO implements IChatDAO {

   private final ConexionBD conexionBD;

    public ChatDAO(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public void insertar(Chat chat) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "INSERT INTO Chat (nombre, miniatura) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, chat.getNombre());
        ps.setBytes(2, chat.getMiniatura().getDatosImagen());
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            chat.setIdChat(rs.getLong(1));
        }
        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
    }

    @Override
    public Chat obtenerPorId(Long id) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "SELECT * FROM Chat WHERE idChat = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        
        ResultSet rs = ps.executeQuery();
        Chat chat = null;
        if (rs.next()) {
            chat = new Chat();
            chat.setIdChat(rs.getLong("idChat"));
            chat.setNombre(rs.getString("nombre"));
            Imagen imagen = new Imagen();
            imagen.setDatosImagen(rs.getBytes("miniatura"));
            chat.setMiniatura(imagen);
        }
        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
        return chat;
    }

    @Override
    public List<Chat> obtenerTodos() throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "SELECT * FROM Chat";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        List<Chat> chats = new ArrayList<>();
        while (rs.next()) {
            Chat chat = new Chat();
            chat.setIdChat(rs.getLong("idChat"));
            chat.setNombre(rs.getString("nombre"));
            Imagen imagen = new Imagen();
            imagen.setDatosImagen(rs.getBytes("miniatura"));
            chat.setMiniatura(imagen);
            chats.add(chat);
        }
        rs.close();
        ps.close();
        conexionBD.cerrarConexion();
        return chats;
    }

    @Override
    public void actualizar(Chat chat) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "UPDATE Chat SET nombre = ?, miniatura = ? WHERE idChat = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, chat.getNombre());
        ps.setBytes(2, chat.getMiniatura().getDatosImagen());
        ps.setLong(3, chat.getIdChat());
        ps.executeUpdate();
        ps.close();
        conexionBD.cerrarConexion();
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        Connection conn = conexionBD.crearConexion();
        String sql = "DELETE FROM Chat WHERE idChat = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
        conexionBD.cerrarConexion();
    }
}




