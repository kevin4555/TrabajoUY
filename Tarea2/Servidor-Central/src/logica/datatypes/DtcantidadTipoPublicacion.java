package logica.datatypes;

/**
 * Clase DTCantidadTipoPublicacion.
 */

public class DtcantidadTipoPublicacion {
  String nombreTipoPublicacion;
  int cantidad;
  
  public DtcantidadTipoPublicacion(String nombre, int cantidad) {
    this.nombreTipoPublicacion = nombre;
    this.cantidad = cantidad;
  }
  
  public String getNombreTipoPublicacion() {
    return nombreTipoPublicacion;
  }
  
  public int getCantidad() {
    return cantidad;
  }
  
}
