package logica.classes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import logica.datatypes.Dtusuario;

/**
 * Clase Usuario.
 */
public abstract class Usuario {
  protected String nickname;
  protected String nombre;
  protected String apellido;
  protected String email;
  private BufferedImage imagen;
  private String contrasenia;
  
  /**
   * Constuctor clase Usuario.
   */
  public Usuario(String nickname, String nombre, String apellido, 
      String email, BufferedImage imagen,
      String contrasenia) {
    super();
    this.nickname = nickname;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.contrasenia = contrasenia;
    this.imagen = imagen;
  }
  
  public String getNickname() {
    return this.nickname;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getApellido() {
    return apellido;
  }
  
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  
  public BufferedImage getImagen() {
    return imagen;
  }
  
  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  public String getContrasenia() {
    return contrasenia;
  }
  
  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }
  
  public abstract List<String> listarOfertasUsuario();
  
  public abstract  Dtusuario obtenerDtusuario();
}
