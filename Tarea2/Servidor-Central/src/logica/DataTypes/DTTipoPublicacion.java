package logica.DataTypes;

import java.time.LocalDate;


public class DTTipoPublicacion {
	private String nombre;
	private String descripcion;
	private String exposicion;
	private int duracionDia;
	private float costo;
	private LocalDate fechaAlta;
	
	public DTTipoPublicacion(String nombre, String descripcion, String exposicion, int duracionDia, float costo,
			LocalDate fechaAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracionDia = duracionDia;
		this.costo = costo;
		this.fechaAlta = fechaAlta;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getExposicion() {
		return exposicion;
	}
	
	public int getDuracionDia() {
		return duracionDia;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	} 
	
	
}
