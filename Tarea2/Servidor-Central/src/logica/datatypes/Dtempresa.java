package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessorType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Clase DTEmpresa.
 */

@XmlAccessorType
public class Dtempresa extends Dtusuario
    implements Serializable {
  
  private String descripcion;
  private String sitioWeb;
  
  /**
   * Contructor.
   */
  
  public Dtempresa(String nickname, String nombre,
      String apellido, String email, BufferedImage imagen,
      String contrasenia,
      List<DtOfertaLaboral> ofertasColeccion,
      String descripcion, String sitioWeb)
      throws IOException {
    super(nickname, nombre, apellido, email, imagen,
        contrasenia, ofertasColeccion);
    this.descripcion = descripcion;
    this.sitioWeb = sitioWeb;
  }
  
  public Dtempresa(String nickname, String nombre,
      String apellido, String email, BufferedImage imagen,
      String contrasenia,
      List<DtOfertaLaboral> ofertasColeccion)
      throws IOException {
    super(nickname, nombre, apellido, email, imagen,
        contrasenia, ofertasColeccion);
  }
  
  public Dtempresa() {
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public void setSitioWeb(String sitioWeb) {
    this.sitioWeb = sitioWeb;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public String getSitioWeb() {
    return sitioWeb;
  }
  
  public List<DtOfertaLaboral> getOfertasLaborales() {
    return getOfertasColeccion();
  }
}
