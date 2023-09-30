package logica.datatypes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase DTPostulante.
 */

public class Dtpostulante extends Dtusuario {
  private LocalDate fechaNacimiento;
  private String nacionalidad;
  
  /**
   * Contructor.
   */
  
  public Dtpostulante(String nickname, String nombre, String apellido, 
      String email, BufferedImage imagen, String contrasenia, 
      ArrayList<DtOfertaLaboral> ofertasColeccion, LocalDate fechaNacimiento, 
      String nacionalidad) {
    super(nickname, nombre, apellido, email, imagen, contrasenia, ofertasColeccion);
    this.fechaNacimiento = fechaNacimiento;
    this.nacionalidad = nacionalidad;
  }
  
  public String getNacionalidad() {
    return nacionalidad;
  }
  
  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }
}
