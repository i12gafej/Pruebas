package TiempoEnAndalucia;
public class Usuario {
    private String usuario;
    private String correo;
    private String contrasena;
    private Integer valoracion;
    private String comentario;

    public Usuario(String usuario, String correo, String contrasena, Integer valoracion, String comentario) {
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }
    public Usuario(String usuario, String correo, Integer valoracion, String comentario) {
        this.usuario = usuario;
        this.correo = correo;
        this.valoracion = valoracion;
        this.comentario = comentario;
    }
    // Constructor sin valoracion y comentario
    public Usuario(String usuario, String correo, String contrasena) {
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    // Getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

