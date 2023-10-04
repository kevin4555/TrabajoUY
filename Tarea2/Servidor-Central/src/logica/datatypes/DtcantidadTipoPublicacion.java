package logica.datatypes;

/**
 * Clase DTCantidadTipoPublicacion.
 */

public class DtcantidadTipoPublicacion {
  String nombreTipoPublicacion;
  int cantidadRestante;
  
  public DtcantidadTipoPublicacion(String nombre, int cantidad) {
    this.nombreTipoPublicacion = nombre;
    this.cantidadRestante = cantidad;
  }
  
  public String getNombreTipoPublicacion() {
    return nombreTipoPublicacion;
  }
  
  public int getCantidad() {
    return cantidadRestante;
  }
  
}
