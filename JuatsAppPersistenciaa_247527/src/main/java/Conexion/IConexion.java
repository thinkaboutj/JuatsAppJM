/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jesus
 */
public interface IConexion {
     /**
     * Crea una nueva conexión a la base de datos.
     * 
     * @return Una instancia de Connection representando la nueva conexión.
     */
    public Connection crearConexion();
    
    /**
     * Cierra la conexión actual a la base de datos.
     * 
     * Este método debe ser llamado para liberar recursos y cerrar la conexión
     * después de que ya no sea necesaria.
     */
    public void cerrarConexion();
    
}
