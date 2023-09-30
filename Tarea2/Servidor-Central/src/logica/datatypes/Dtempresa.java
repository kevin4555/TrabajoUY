package logica.datatypes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * Clase DTEmpresa.
 */

public class Dtempresa extends Dtusuario {
  private String descripcion;
  private String sitioWeb;
  
  /**
   * Contructor.
   */
  
  public Dtempresa(String nickname, String nombre, String apellido, 
      String email, BufferedImage imagen, String contrasenia, 
      ArrayList<DtOfertaLaboral> ofertasColeccion, String descripcion, String sitioWeb) {
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
  
  public ArrayList<DtOfertaLaboral> getOfertasLaborales() {
    return getOfertasColeccion();
  }
}
