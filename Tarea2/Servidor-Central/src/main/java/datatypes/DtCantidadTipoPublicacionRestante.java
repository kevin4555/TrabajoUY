package main.java.datatypes;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * Clase DtCantidadTipoPublicacionRestante.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCantidadTipoPublicacionRestante
      implements
      Serializable {

  private static final long serialVersionUID =
        -8355208635138695346L;
  private int cantidad;
  private DtTipoPublicacion tipoPublicacion;

  public DtCantidadTipoPublicacionRestante(
        int cantidad,
          DtTipoPublicacion tipoPublicacion) {
    this.cantidad = cantidad;
    this.tipoPublicacion = tipoPublicacion;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public DtCantidadTipoPublicacionRestante() {
  }

  public void setTipoPublicacion(
        DtTipoPublicacion tipoPublicacion) {
    this.tipoPublicacion = tipoPublicacion;
  }

  public int getCantidad() {
    return cantidad;
  }

  public DtTipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }

}
