package logica.classes;

import java.util.Date;

public class TipoPublicacion {
	
	private String nombre;
	private String descripcion;
	private String exposicion;
	private int duracionDia;
	private double costo;
	private Date fechaAlta;
	private CantidadTipoPublicacion cantTipo;
	
	public TipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo, Date fechaPub) {
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setExposicion(exposicion);
		this.setDuracionDia(duracion);
		this.setCosto(costo);
		this.setFechaAlta(fechaPub);
		this.cantTipo = null;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getExposicion() {
		return exposicion;
	}
	public void setExposicion(String exposicion) {
		this.exposicion = exposicion;
	}
	public int getDuracionDia() {
		return duracionDia;
	}
	public void setDuracionDia(int duracionDia) {
		this.duracionDia = duracionDia;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public CantidadTipoPublicacion getCantidadTipoPublicacion()
	{
		return cantTipo;
	}
}

