package logica.DataTypes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class DTPaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int periodoValidez	;
	private float descuento;
	private float costo;
	private BufferedImage imagen; 
	private ArrayList<DTCantidadTipoPublicacion> cantidadPublicacionesColeccion;
	
	public DTPaquetePublicacion(String nombre, String descripcion, int periodoValidez, float descuento, float costo,
			BufferedImage imagen, ArrayList<DTCantidadTipoPublicacion> cantidadPublicacionesColeccion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.costo = costo;
		this.imagen = imagen;
		this.cantidadPublicacionesColeccion = cantidadPublicacionesColeccion;
	}
	
	public BufferedImage getImagen() {
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
