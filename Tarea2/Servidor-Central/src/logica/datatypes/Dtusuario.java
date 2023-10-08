package logica.datatypes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Clase Dtusuario.
 */

public class Dtusuario {
  private String nickname;
  private String nombre;
  private String apellido;
  private String email;
  private BufferedImage imagen;
  private String imagenBase64;
  private String contrasenia;
  private List<DtOfertaLaboral> ofertasColeccion;
  
  /**
   * Contructor.
   */
  
  public Dtusuario(String nickname, String nombre, String apellido, String email,
      BufferedImage imagen, String contrasenia, List<DtOfertaLaboral> ofertasColeccion)
      throws IOException {
    this.nickname = nickname;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.imagen = imagen;
    this.imagenBase64 = null;
    if (imagen != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(this.imagen, "png", baos);
      this.imagenBase64 = Base64.getEncoder().encodeToString(baos.toByteArray());
    }
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
  
  public String getImagenBase64() {
    return imagenBase64;
  }
  
}
