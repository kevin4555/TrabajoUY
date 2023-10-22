package logica.classes;

import java.time.LocalDate;
import logica.datatypes.Dtpostulacion;

/**
 * Clase Postulación .
 */

public class Postulacion {
  private Postulante postulante;
  private OfertaLaboral ofertaLaboral;
  private String descrpMotivacion;
  private LocalDate fechaPostulacion;
  private String cvReducido;
  private String linkVideo;
  
  /**
   * Constructor .
   */
  
  public Postulacion(String descrpMotivacion, LocalDate fechaPostulacion, String cvReducido,
      Postulante postulante, OfertaLaboral ofertaLaboral, String linkVideo) {
    super();
    setDescrpMotivacion(descrpMotivacion);
    setFechaPostulacion(fechaPostulacion);
    setCvReducido(cvReducido);
    this.postulante = postulante;
    this.ofertaLaboral = ofertaLaboral;
    this.linkVideo = linkVideo;
  }
  
  public String getDescrpMotivacion() {
    return descrpMotivacion;
  }
  
  public void setDescrpMotivacion(String descrpMotivacion) {
    this.descrpMotivacion = descrpMotivacion;
  }
  
  public LocalDate getFechaPostulacion() {
    return fechaPostulacion;
  }
  
  public void setFechaPostulacion(
      LocalDate fechaPostulacion) {
    this.fechaPostulacion = fechaPostulacion;
  }
  
  public String getCvReducido() {
    return cvReducido;
  }
  
  public void setCvReducido(String cvReducido) {
    this.cvReducido = cvReducido;
  }
  
  public OfertaLaboral getOfertaLaboral() {
    return ofertaLaboral;
  }
  
  public Postulante getPostulante() {
    return postulante;
  }
  
  /**
   * Obtener DTPostulacion.
   */
  
  public Dtpostulacion obtenerDtpostulacion() {
    Dtpostulacion dtPostulacion = new Dtpostulacion(
        this.postulante.getNickname(),
        this.getDescrpMotivacion(),
        this.getFechaPostulacion(), this.getCvReducido(),
        this.ofertaLaboral.getNombre(), this.linkVideo);
    return dtPostulacion;
  }
  
  public String getNombreOfertaLaboral() {
    return this.ofertaLaboral.getNombre();
  }
}
