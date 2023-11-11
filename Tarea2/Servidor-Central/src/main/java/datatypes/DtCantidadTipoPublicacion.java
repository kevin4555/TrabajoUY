package main.java.datatypes;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * Clase DTCantidadTipoPublicacion.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCantidadTipoPublicacion
      implements
      Serializable {

  private static final long serialVersionUID =
        -7434270955531327533L;
  String nombreTipoPublicacion;
  int cantidad;

  public DtCantidadTipoPublicacion(
        String nombre,
          int cantidad) {
    this.nombreTipoPublicacion = nombre;
    this.cantidad = cantidad;
  }

  public DtCantidadTipoPublicacion() {
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
