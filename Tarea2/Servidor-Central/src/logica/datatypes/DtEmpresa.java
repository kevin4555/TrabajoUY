package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Clase DTEmpresa.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtEmpresa extends DtUsuario implements Serializable {
	
  private static final long serialVersionUID = -8154138493877341107L;
  private String descripcion;
  private String sitioWeb;
  
  /**
   * Contructor.
   */
  
  public DtEmpresa(String nickname, String nombre,
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
  
  public DtEmpresa(String nickname, String nombre,
      String apellido, String email, BufferedImage imagen,
      String contrasenia,
      List<DtOfertaLaboral> ofertasColeccion)
      throws IOException {
    super(nickname, nombre, apellido, email, imagen,
        contrasenia, ofertasColeccion);
  }
  
  public DtEmpresa() {
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
