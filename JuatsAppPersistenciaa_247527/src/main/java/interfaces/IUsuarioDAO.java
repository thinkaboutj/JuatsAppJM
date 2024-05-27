/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Usuario;
import java.util.List;

/**
 *
 * @author jesus
 */
public interface IUsuarioDAO {
     public Usuario createUsuario(Usuario usuario);
    
    public Usuario readUsuario(Usuario usuario);
    
    public Usuario updateUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
    
    public List<Usuario> readAllUsuario();
    
}
