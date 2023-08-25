package logica.classes;

public class CantidadTipoPublicacion {
	private PaquetePublicacion paquetePublicacion;
	private int cantidadRestante;
	private TipoPublicacion tipoPublicacion;
	
	public CantidadTipoPublicacion(int cantidadRestante, TipoPublicacion tipoPublicacion) {
		this.paquetePublicacion = null;
		this.cantidadRestante = cantidadRestante;
		this.tipoPublicacion = tipoPublicacion;
	}
	
	
	public void asociarPaquete(PaquetePublicacion paquete) {
		this.paquetePublicacion = paquete;
	}
	
	public int getCantidadRestante() {
		return cantidadRestante;
	}

	public void setCantidadRestante(int cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}

	public PaquetePublicacion getPaquetePublicacion() {
		return paquetePublicacion;
	}

	public void setPaquetePublicacion(PaquetePublicacion paquetePublicacion) {
		this.paquetePublicacion = paquetePublicacion;
	}

	public TipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}
	public void  decrementCantidadRestante() {
		this.cantidadRestante--;
	}
	
	public double obtenerCostoPublicaciones() {
		return this.tipoPublicacion.getCosto() * this.cantidadRestante;
	}

}
