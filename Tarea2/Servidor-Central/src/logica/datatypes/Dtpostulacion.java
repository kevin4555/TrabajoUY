package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Dtpostulacion.
 */

@XmlAccessorType
public class Dtpostulacion implements Serializable {
  
  private String nicknamePostulante;
  
  private String descripMotivacion;
  
  private LocalDate fechaPostulacion;
  
  private String cvReducido;
  
  private String nombreOferta;
  
  private String linkVideo;
  
  /**
   * Contructor.
   */
  
  public Dtpostulacion(String postulante,
      String descripMotivacion, LocalDate fechaPostulacion,
      String cvReducido, String nombreOferta, String linkVideo) {
    this.nicknamePostulante = postulante;
    this.descripMotivacion = descripMotivacion;
    this.fechaPostulacion = fechaPostulacion;
    this.cvReducido = cvReducido;
    this.nombreOferta = nombreOferta;
    this.linkVideo = linkVideo;
  }
  
  public Dtpostulacion() {
  }
  
  public String getNicknamePostulante() {
    return nicknamePostulante;
  }
  
  public void setNicknamePostulante(
      String nicknamePostulante) {
    this.nicknamePostulante = nicknamePostulante;
  }
  
  public void setDescripMotivacion(
      String descripMotivacion) {
    this.descripMotivacion = descripMotivacion;
  }
  
  public void setFechaPostulacion(
      LocalDate fechaPostulacion) {
    this.fechaPostulacion = fechaPostulacion;
  }
  
  public void setCvReducido(String cvReducido) {
    this.cvReducido = cvReducido;
  }
  
  public void setNombreOferta(String nombreOferta) {
    this.nombreOferta = nombreOferta;
  }
  
  public String getnicknamePostulante() {
    return nicknamePostulante;
  }
  
  public String getDescripMotivacion() {
    return descripMotivacion;
  }
  
  public LocalDate getFechaPostulacion() {
    return fechaPostulacion;
  }
  
  public String getCvReducido() {
    return cvReducido;
  }
  
  public String getNombreOferta() {
    return nombreOferta;
  }
  
}
