/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import Conexion.ConexionBD;
import dao.UsuarioDAO;
import dominio.Usuario;
import interfaces.IUsuarioDAO;
import java.util.List;

/**
 *
 * @author jesus
 */
public class UsuarioBO {
    
      private UsuarioDAO usuarioDAO;

    public UsuarioBO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO(new ConexionBD());
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioDAO.createUsuario(usuario);
    }
    
     public Usuario crearUsuariosinImagen(Usuario usuario) {
        return usuarioDAO.createUsuarioSimagen(usuario);
    }

    public Usuario obtenerUsuarioPorId(Usuario usuario) {
        return usuarioDAO.readUsuario(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.readAllUsuario();
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.updateUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioDAO.deleteUsuario(usuario);
    }
}
