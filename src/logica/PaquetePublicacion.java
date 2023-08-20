package logica;

import java.util.ArrayList;

public class PaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez;
	private float descuento;
	private float costo;
	private ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones;
	
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
	
	public int getCantidadPublicaciones() {
		return cantidadPublicaciones;
	}
	
	public void setCantidadPublicaciones(int cantidadPublicaciones) {
		this.cantidadPublicaciones = cantidadPublicaciones;
	}
	
	public int getPeriodoValidez() {
		return periodoValidez;
	}
	
	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public ArrayList<CantidadTipoPublicacion> getCantidadTipoPublicaciones() {
		return cantidadTipoPublicaciones;
	}
	
	public void setCantidadTipoPublicaciones(ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones) {
		this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
	}
	
	public DTPaquete obtenerDTPaquete() {
		return new DTPaquete(nombre, descripcion, cantidadPublicaciones,
				periodoValidez, descuento, costo);
	}
}

