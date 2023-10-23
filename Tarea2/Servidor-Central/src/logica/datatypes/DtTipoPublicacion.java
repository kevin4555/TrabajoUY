package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase DttipoPublicacion.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtTipoPublicacion implements Serializable {
	
  private static final long serialVersionUID = -5978261198147353774L;
  private String nombre;
  private String descripcion;
  private String exposicion;
  private int duracionDia;
  private float costo;
  private LocalDate fechaAlta;
  
  /**
   * Contructor.
   */
  
  public DtTipoPublicacion(String nombre,
      String descripcion, String exposicion,
      int duracionDia, float costo, LocalDate fechaAlta) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.exposicion = exposicion;
    this.duracionDia = duracionDia;
    this.costo = costo;
    this.fechaAlta = fechaAlta;
  }
  
  public DtTipoPublicacion() {
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public void setExposicion(String exposicion) {
    this.exposicion = exposicion;
  }
  
  public void setDuracionDia(int duracionDia) {
    this.duracionDia = duracionDia;
  }
  
  public void setCosto(float costo) {
    this.costo = costo;
  }
  
  public void setFechaAlta(LocalDate fechaAlta) {
    this.fechaAlta = fechaAlta;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public String getExposicion() {
    return exposicion;
  }
  
  public int getDuracionDia() {
    return duracionDia;
  }
  
  public float getCosto() {
    return costo;
  }
  
  public LocalDate getFechaAlta() {
    return fechaAlta;
  }
  
}
