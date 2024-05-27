
package interfaces;

import dominio.Chat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jairo G. Rodriguez Hernandez 00000213248
 */
public interface IChatDAO {
    
     void insertar(Chat chat) throws SQLException;

    Chat obtenerPorId(Long id) throws SQLException;

    List<Chat> obtenerTodos() throws SQLException;

    void actualizar(Chat chat) throws SQLException;

    void eliminar(Long id) throws SQLException;
}
