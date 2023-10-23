package logica.classes;

import logica.datatypes.DtCantidadTipoPublicacion;

/**
 * Clase CantidadTotalTipoPublicacion.
 */

public class CantidadTotalTipoPublicacion {
  private int cantidadTotal;
  private TipoPublicacion tipoPublicacion;
  
  public CantidadTotalTipoPublicacion(int cantidadTotal,
      TipoPublicacion tipoPublicacion) {
    this.cantidadTotal = cantidadTotal;
    this.tipoPublicacion = tipoPublicacion;
  }
  
  public int getCantidadTotal() {
    return cantidadTotal;
  }
  
  public TipoPublicacion getTipoPublicacion() {
    return tipoPublicacion;
  }
  
  public double obtenerCostoTotalPublicaciones() {
    return this.tipoPublicacion.getCosto()
        * this.cantidadTotal;
  }
  
  /**
   * Metodo obtenerDtCantidadTipoPublicacion.
   */
  
  public DtCantidadTipoPublicacion obtenerDtcantidadTipoPublicacion() {
    return new DtCantidadTipoPublicacion(
        this.tipoPublicacion.getNombre(),
        this.cantidadTotal);
    
  }
  
}
