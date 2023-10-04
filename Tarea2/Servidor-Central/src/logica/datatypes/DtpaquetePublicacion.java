package logica.datatypes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DTPaquetePublicacion.
 */

public class DtpaquetePublicacion {
  private String nombre;
  private String descripcion;
  private int periodoValidez;
  private float descuento;
  private float costo;
  private BufferedImage imagen;
  private List<DtcantidadTipoPublicacion> cantidadTipoPublicaciones;
  private LocalDate fechaAlta;
  
  /**
   * Contructor.
   */
  
  public DtpaquetePublicacion(String nombre, String descripcion, 
      int periodoValidez, float descuento, float costo,
      BufferedImage imagen, 
      List<DtcantidadTipoPublicacion> cantidadTipoPublicaciones, 
      LocalDate fechaAlta) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.periodoValidez = periodoValidez;
    this.descuento = descuento;
    this.costo = costo;
    this.imagen = imagen;
    this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
    this.fechaAlta = fechaAlta;
  }
  
  public BufferedImage getImagen() {
    return imagen;
  }
  
  public List<DtcantidadTipoPublicacion> getCantidadTipoPublicaciones() {
    return cantidadTipoPublicaciones;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }
  
  public int getPeriodoValidez() {
    return periodoValidez;
  }
  
  public float getDescuento() {
    return descuento;
  }
  
  public float getCosto() {
    return costo;
  }
  
  public LocalDate getFechaAlta() {
    return fechaAlta;
  }
  
}
