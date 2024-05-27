
package interfaces;

import dominio.Mensaje;
import java.util.List;

/**
 *
 * @author Jairo G. Rodriguez Hernandez 00000213248
 */
public interface IMensajeDAO {
    
    public Mensaje createMensaje(Mensaje mensaje);
    
    public Mensaje readMensaje(Mensaje mensaje);
    
    public Mensaje updateMensaje(Mensaje mensaje);
    
    public void deleteMensaje(Mensaje mensaje);
    
    public List<Mensaje> readAllMensaje();
    
    
}
