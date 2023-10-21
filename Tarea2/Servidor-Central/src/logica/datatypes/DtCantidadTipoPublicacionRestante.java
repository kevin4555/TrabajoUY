package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Clase DtCantidadTipoPublicacionRestante.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCantidadTipoPublicacionRestante
    implements Serializable {
  
  private int cantidad;
  private DttipoPublicacion tipoPublicacion;
  
  public DtCantidadTipoPublicacionRestante(int cantidad,
      DttipoPublicacion tipoPublicacion) {
    this.cantidad = cantidad;
    this.tipoPublicacion = tipoPublicacion;
  }
  
  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }
  
  public DtCantidadTipoPublicacionRestante() {
  }
  
  public void setTipoPublicacion(
      DttipoPublicacion tipoPublicacion) {
    this.tipoPublicacion = tipoPublicacion;
  }
  
  public int getCantidad() {
    return cantidad;
  }
  
  public DttipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }
  
}
