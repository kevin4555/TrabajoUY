package logica.classes;

import java.util.ArrayList;

import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTPaquetePublicacion;

public class PaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez;
	private float descuento;
	private float costo;
	private String imagen;
	private ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones;

	public PaquetePublicacion(String nombre, String descripcion, int periodoValidez, float descuento, String imagen,
			ArrayList<CantidadTipoPublicacion> cantidadTipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadPublicaciones = 0;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.imagen = imagen;
		this.cantidadTipoPublicaciones = cantidadTipo;
		for (CantidadTipoPublicacion cantidad : cantidadTipo) {
			this.cantidadPublicaciones = this.cantidadPublicaciones + cantidad.getCantidadRestante();
		}
		this.setCosto();
	}

	public String getNombre() {
		return nombre;
	}

	private void setCosto() {
		float costoTotal = 0;
		for (CantidadTipoPublicacion cantidadTipoPublicacion : cantidadTipoPublicaciones) {
			costoTotal += cantidadTipoPublicacion.obtenerCostoPublicaciones();
		}
		this.costo = costoTotal * ((100 - descuento) / 100);
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Establece el descuento y recalcula el costo.
	 * 
	 * @param descuento Descripción del primer parámetro.
	 */
	public void setDescuento(float descuento) {
		this.descuento = descuento;
		setCosto();
	}

	public float getCosto() {
		return costo;
	}

	public void setCantidadTipoPublicaciones(ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones) {
		this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
	}

	public DTPaquetePublicacion obtenerDTPaquete() {
		ArrayList<DTCantidadTipoPublicacion> listDTcantidad = new ArrayList<DTCantidadTipoPublicacion>();
		for(CantidadTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			listDTcantidad.add(cantidad.obtenerDTCantidadTipoPublicacion());
		}
		return new DTPaquetePublicacion(nombre, descripcion, cantidadPublicaciones, periodoValidez, descuento, costo, imagen, listDTcantidad );
	}

	public void crearCantidadTipoPublicacion(int cantIncluida, TipoPublicacion tipoPublicacion) {
		CantidadTipoPublicacion nuevoCantidad = new CantidadTipoPublicacion(cantIncluida, tipoPublicacion);
		nuevoCantidad.asociarPaquete(this);
		cantidadTipoPublicaciones.add(nuevoCantidad);

	}

	public ArrayList<CantidadTipoPublicacion> getCantidadTipoPublicaciones() {
		return cantidadTipoPublicaciones;
	}

}
