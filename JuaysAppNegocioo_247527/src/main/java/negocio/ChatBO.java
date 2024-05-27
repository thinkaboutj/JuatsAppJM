/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.Chat;
import interfaces.IChatDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jesus
 */
public class ChatBO {
    
     IChatDAO chatDAO;

    public ChatBO(IChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    public ChatBO() {
    }
    
    

    public void insertarChat(Chat chat) throws SQLException {
        chatDAO.insertar(chat);
    }

    public Chat obtenerChatPorId(Long id) throws SQLException {
        return chatDAO.obtenerPorId(id);
    }

    public List<Chat> obtenerTodosLosChats() throws SQLException {
        return chatDAO.obtenerTodos();
    }

    public void actualizarChat(Chat chat) throws SQLException {
        chatDAO.actualizar(chat);
    }

    public void eliminarChat(Long id) throws SQLException {
        chatDAO.eliminar(id);
    }
    
}
