package logica.DataTypes;

public class DTCantidadTipoPublicacion {
	String nombreTipoPublicacion;
	int cantidad;

	public DTCantidadTipoPublicacion(String nombre, int cantidad) {
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
