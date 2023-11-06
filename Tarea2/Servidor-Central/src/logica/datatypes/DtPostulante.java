package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase DTPostulante.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPostulante extends DtUsuario
      implements
      Serializable {

  private static final long serialVersionUID =
        3400870199280854013L;
  @XmlTransient
  private LocalDate fechaNacimiento;
  private String nacionalidad;
  private String fechaNacimientoString;
  private List<String> ofertasFavoritas;

  /**
   * Contructor.
   */

  public DtPostulante(
        String nickname,
          String nombre,
          String apellido,
          String email,
          BufferedImage imagen,
          String contrasenia,
          List<DtOfertaLaboral> ofertasColeccion,
          LocalDate fechaNacimiento,
          String nacionalidad,
          List<String> seguidos,
          List<String> seguidores,
          List<String> ofertasFavoritas)
        throws IOException {
    super(nickname, nombre, apellido, email, imagen,
          contrasenia, ofertasColeccion, seguidos,
          seguidores);
    this.fechaNacimiento = fechaNacimiento;
    this.nacionalidad = nacionalidad;
    this.fechaNacimientoString = fechaNacimiento.toString();
    this.ofertasFavoritas = ofertasFavoritas;
  }

  public DtPostulante() {
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

  public String getFechaNacimientoString() {
    return fechaNacimientoString;
  }

  public void setFechaNacimientoString(
        String fechaNacimientoString) {
    this.fechaNacimientoString = fechaNacimientoString;
  }

  /**
   * Metodo getOfertasFavoritas.
   */

  public List<String> getOfertasFavoritas() {
    return ofertasFavoritas;
  }

  /**
   * Metodo setOfertasFavoritas.
   */

  public void
        setOfertasFavoritas(List<String> ofertasFavoritas) {
    this.ofertasFavoritas = ofertasFavoritas;
  }

}
