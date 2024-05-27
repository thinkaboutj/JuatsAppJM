package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author Jairo G. Rodriguez Hernandez 00000213248
 */
public class Chat {
//Atributos
 
    private Long idChat;

    private String nombre;

    private Imagen miniatura;

    
    private List<Usuario> usuarios;

    private List<Mensaje> mensajes;

    //--Constructores--
    public Chat() {
    }

    public Chat(Long idChat, String nombre, Imagen miniatura, List<Usuario> usuarios, List<Mensaje> mensajes) {
        this.idChat = idChat;
        this.nombre = nombre;
        this.miniatura = miniatura;
        this.usuarios = usuarios;
        this.mensajes = mensajes;
    }

    public Chat(String nombre, Imagen miniatura, List<Usuario> usuarios, List<Mensaje> mensajes) {
        this.nombre = nombre;
        this.miniatura = miniatura;
        this.usuarios = usuarios;
        this.mensajes = mensajes;
    }

    public Chat(String nombre, Imagen miniatura) {
        this.nombre = nombre;
        this.miniatura = miniatura;
    }

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    //Getter & setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Imagen getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(Imagen miniatura) {
        this.miniatura = miniatura;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idChat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chat other = (Chat) obj;
        return Objects.equals(this.idChat, other.idChat);
    }

    @Override
    public String toString() {
        return "Chat{" + "idChat=" + idChat + ", nombre=" + nombre + ", miniatura=" + miniatura + ", usuarios=" + usuarios + ", mensajes=" + mensajes + '}';
    }

}
