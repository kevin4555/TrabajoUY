package logica.DataTypes;

public class DTPaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez;
	private float descuento;
	private float costo;
	
	public DTPaquetePublicacion(String nombre, String descripcion, int cantidadPublicaciones,
			int periodoValidez, float descuento, float costo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.costo = costo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}
	
	public int getPeriodoValidez() {
		return periodoValidez;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public float getCosto() {
		return costo;
	}
}
