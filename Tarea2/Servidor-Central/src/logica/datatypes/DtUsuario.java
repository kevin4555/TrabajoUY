package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Clase Dtusuario.
 */

@XmlSeeAlso({
DtEmpresa.class,
DtPostulante.class
})
@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario implements Serializable {
	
  private static final long serialVersionUID = 6770805630368299744L;
  private String nickname;
  private String nombre;
  private String apellido;
  private String email;
  @XmlTransient
  private BufferedImage imagen;
  private String imagenBase64;
  private String contrasenia;
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<DtOfertaLaboral> ofertasColeccion;
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<String> seguidos;
  @XmlJavaTypeAdapter(ListAdapter.class)
  private List<String> seguidores;
  
  public DtUsuario() {
  }
  
  /**
   * Contructor.
   */
  
  public DtUsuario(String nickname, String nombre, String apellido, String email,
      BufferedImage imagen, String contrasenia, List<DtOfertaLaboral> ofertasColeccion,
      List<String> seguidos, List<String> seguidores) throws IOException {
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
    this.seguidos = seguidos;
    this.seguidores = seguidores;
  }
  
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setImagen(BufferedImage imagen) {
    this.imagen = imagen;
  }
  
  public void setImagenBase64(String imagenBase64) {
    this.imagenBase64 = imagenBase64;
  }
  
  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }
  
  public void setOfertasColeccion(
      List<DtOfertaLaboral> ofertasColeccion) {
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
