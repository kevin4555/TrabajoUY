package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessorType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase DTPostulante.
 */

@XmlAccessorType
public class Dtpostulante extends Dtusuario
    implements Serializable {
  private LocalDate fechaNacimiento;
  private String nacionalidad;
  
  /**
   * Contructor.
   */
  
  public Dtpostulante(String nickname, String nombre,
      String apellido, String email, BufferedImage imagen,
      String contrasenia,
      List<DtOfertaLaboral> ofertasColeccion,
      LocalDate fechaNacimiento, String nacionalidad)
      throws IOException {
    super(nickname, nombre, apellido, email, imagen,
        contrasenia, ofertasColeccion);
    this.fechaNacimiento = fechaNacimiento;
    this.nacionalidad = nacionalidad;
  }
  
  public Dtpostulante() {
  }
  
  public void setFechaNacimiento(
      LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
  
  public String getNacionalidad() {
    return nacionalidad;
  }
  
  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }
}
