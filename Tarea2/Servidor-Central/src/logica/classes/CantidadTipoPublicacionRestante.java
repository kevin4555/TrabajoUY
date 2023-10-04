package logica.classes;

import logica.datatypes.DtCantidadTipoPublicacionRestante;

/**
 * Clase CantidadTipoPublicacionRestante.
 */

public class CantidadTipoPublicacionRestante {
  
  private int cantidad;
  private TipoPublicacion tipoPublicacion;
  
  /**
   * Constructor.
   */
  
  public CantidadTipoPublicacionRestante(int cantidad, TipoPublicacion tipoPublicacion) {
    this.cantidad = cantidad;
    this.tipoPublicacion = tipoPublicacion;
  }
  
  public int getCantidad() {
    return cantidad;
  }
  
  public TipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }
  
  public void reducirCantidad() {
    this.cantidad--;
  }
  
  public String getNombreTipoPublicacion() {
    return tipoPublicacion.getNombre();
  }
  
  public DtCantidadTipoPublicacionRestante obtenerDtTipoCantidadRestante() {
    DtCantidadTipoPublicacionRestante resultado = new DtCantidadTipoPublicacionRestante(
        cantidad, tipoPublicacion.obtenerDttipoPublicacion());
    return resultado;
  }
}
