package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Clase DTCantidadTipoPublicacion.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtcantidadTipoPublicacion
    implements Serializable {
  
  String nombreTipoPublicacion;
  int cantidad;
  
  public DtcantidadTipoPublicacion(String nombre,
      int cantidad) {
    this.nombreTipoPublicacion = nombre;
    this.cantidad = cantidad;
  }
  
  public DtcantidadTipoPublicacion() {
  }
  
  public String getNombreTipoPublicacion() {
    return nombreTipoPublicacion;
  }
  
  public int getCantidad() {
    return cantidad;
  }
  
  public void setNombreTipoPublicacion(
      String nombreTipoPublicacion) {
    this.nombreTipoPublicacion = nombreTipoPublicacion;
  }
  
  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }
  
}
