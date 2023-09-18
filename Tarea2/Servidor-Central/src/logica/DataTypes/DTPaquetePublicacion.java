package logica.DataTypes;

import java.util.ArrayList;



public class DTPaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez	;
	private float descuento;
	private float costo;
	private String imagen; 
	private ArrayList<DTCantidadTipoPublicacion> cantidadPublicacionesColeccion;
	
	public DTPaquetePublicacion(String nombre, String descripcion, int cantidadPublicaciones, int periodoValidez, float descuento, float costo, String imagen, ArrayList<DTCantidadTipoPublicacion> cantidadPublicacionesColeccion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadPublicaciones = cantidadPublicaciones;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.costo = costo;
		this.imagen = imagen;
		this.cantidadPublicacionesColeccion = cantidadPublicacionesColeccion;
	}
	
	public String getImagen() {
		return imagen;
	}

	public ArrayList<DTCantidadTipoPublicacion> getCantidadPublicacionesColeccion() {
		return cantidadPublicacionesColeccion;
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
