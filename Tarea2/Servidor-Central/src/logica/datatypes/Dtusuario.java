package logica.datatypes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Dtusuario.
 */

public class Dtusuario {
  private String nickname;
  private String nombre;
  private String apellido;
  private String email;
  private BufferedImage imagen;
  private String contrasenia;
  private List<DtOfertaLaboral> ofertasColeccion;
  
  /**
   * Contructor.
   */
  
  public Dtusuario(String nickname, String nombre, String apellido, 
      String email, BufferedImage imagen,
      String contrasenia, List<DtOfertaLaboral> ofertasColeccion) {
    this.nickname = nickname;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.imagen = imagen;
    this.contrasenia = contrasenia;
    this.ofertasColeccion = ofertasColeccion;
  }
  
  public BufferedImage getImagen() {
    return imagen;
  }
  
  public String getContrasenia() {
    return contrasenia;
  }
  
  public List<DtOfertaLaboral> getOfertasColeccion() {
    return ofertasColeccion;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getApellido() {
    return apellido;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public String getNickname() {
    return nickname;
  }
  
}
