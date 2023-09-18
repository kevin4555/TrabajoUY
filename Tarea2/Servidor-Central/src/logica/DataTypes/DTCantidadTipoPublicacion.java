package logica.DataTypes;

public class DTCantidadTipoPublicacion {
	String nombreTipoPublicacion;
	int cantidadRestante;

	public DTCantidadTipoPublicacion(String nombre, int cantidad) {
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
