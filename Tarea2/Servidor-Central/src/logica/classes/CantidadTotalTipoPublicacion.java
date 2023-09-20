package logica.classes;

import logica.DataTypes.DTCantidadTipoPublicacion;

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

	
	  public DTCantidadTipoPublicacion obtenerDTCantidadTipoPublicacion() { return
	  new DTCantidadTipoPublicacion(this.tipoPublicacion.getNombre(),
	  this.cantidadTotal);
	  
	  }

	 
}
