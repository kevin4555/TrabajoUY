package logica.datatypes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase DTEmpresa.
 */

public class Dtempresa extends Dtusuario {
  private String descripcion;
  private String sitioWeb;
  
  /**
   * Contructor.
 * @throws IOException 
   */
  
  public Dtempresa(String nickname, String nombre, String apellido, 
      String email, BufferedImage imagen, String contrasenia, 
      List<DtOfertaLaboral> ofertasColeccion, String descripcion, String sitioWeb) throws IOException {
    super(nickname, nombre, apellido, email, imagen, contrasenia, ofertasColeccion);
    this.descripcion = descripcion;
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
