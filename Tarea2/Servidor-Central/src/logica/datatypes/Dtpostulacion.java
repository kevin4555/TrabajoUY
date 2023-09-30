package logica.datatypes;

import java.time.LocalDate;

/**
 * Clase Dtpostulacion.
 */

public class Dtpostulacion {
  
  private String nicknamePostulante;
  
  private String descripMotivacion;
  
  private LocalDate fechaPostulacion;
  
  private String cvReducido;
  
  public Dtpostulacion() {
    
  }
  
  /**
   * Contructor.
   */
  
  public Dtpostulacion(String postulante, String descripMotivacion, 
      LocalDate fechaPostulacion, String cvReducido) {
    this.nicknamePostulante = postulante;
    this.descripMotivacion = descripMotivacion;
    this.fechaPostulacion = fechaPostulacion;
    this.cvReducido = cvReducido;
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
  
}
