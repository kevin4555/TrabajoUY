package logica.classes;

public class CantidadTipoPublicacionRestante {
	
	private int cantidad;
	private TipoPublicacion tipoPublicacion;
	
	public CantidadTipoPublicacionRestante(int cantidad, TipoPublicacion tipoPublicacion) {
		super();
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
	
	public String getNombreTipoPublicacion(){
		return tipoPublicacion.getNombre();
	}
}
