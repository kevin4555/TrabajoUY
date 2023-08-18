package logica;

import java.sql.Date;

public class TipoPublicacion {
	
	private String nombre;
	private String descripcion;
	private String exposicion;
	private int duracionDia;
	private double costo;
	private Date fechaAlta;
	
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
	
	

}

/*
 -nombre : String
-descripcion : String
-exposicion : String
-duracionDia : Int
-costo : Float
-fechaAlta : DTFecha
*/ 
