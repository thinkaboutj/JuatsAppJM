/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.Mensaje;
import interfaces.IMensajeDAO;
import java.util.List;

/**
 *
 * @author jesus
 */
public class MensajeBO {
    
     IMensajeDAO mensajeDAO;

    public MensajeBO(IMensajeDAO mensajeDAO) {
        this.mensajeDAO = mensajeDAO;
    }

    public MensajeBO() {
    }
    
    

    public Mensaje crearMensaje(Mensaje mensaje) {
        return mensajeDAO.createMensaje(mensaje);
    }

    public Mensaje obtenerMensajePorId(Mensaje mensaje) {
        return mensajeDAO.readMensaje(mensaje);
    }

    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeDAO.readAllMensaje();
    }

    public Mensaje actualizarMensaje(Mensaje mensaje) {
        return mensajeDAO.updateMensaje(mensaje);
    }

    public void eliminarMensaje(Mensaje mensaje) {
        mensajeDAO.deleteMensaje(mensaje);
    }
    
}
