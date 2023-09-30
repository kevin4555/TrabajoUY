package logica.classes;

import logica.datatypes.DtcantidadTipoPublicacion;
/**
 * Clase CantidadTotalTipoPublicacion.
 */

public class CantidadTotalTipoPublicacion {
  private int cantidadTotal;
  private TipoPublicacion tipoPublicacion;
  
  public CantidadTotalTipoPublicacion(int cantidadTotal, TipoPublicacion tipoPublicacion) {
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
    return this.tipoPublicacion.getCosto() * this.cantidadTotal;
  }
  
  public DtcantidadTipoPublicacion obtenerDtcantidadTipoPublicacion() {
    return new DtcantidadTipoPublicacion(this.tipoPublicacion.getNombre(), this.cantidadTotal);
    
  }
  
}
