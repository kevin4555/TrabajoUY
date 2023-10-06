package logica.datatypes;

/**
 * Clase DtCantidadTipoPublicacionRestante.
 */

public class DtCantidadTipoPublicacionRestante {
  private int cantidad;
  private DttipoPublicacion tipoPublicacion;
  
  public DtCantidadTipoPublicacionRestante(int cantidad, DttipoPublicacion tipoPublicacion) {
    this.cantidad = cantidad;
    this.tipoPublicacion = tipoPublicacion;
  }
  
  public int getCantidad() {
    return cantidad;
  }
  
  public DttipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }
  
}
