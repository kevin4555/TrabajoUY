package logica.DataTypes;

import java.util.Date;

public class DTTipoPublicacion {
	private String nombre;
	private String descripcion;
	private String exposicion;
	private int duracionDia;
	private double costo;
	private Date fechaAlta;
	
	public DTTipoPublicacion(String nombre, String descripcion, String exposicion, int duracionDia, double costo,
			Date fechaAlta) {
		super();
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
	
	public double getCosto() {
		return costo;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	} 
	
	
}
