package logica.classes;

import java.util.ArrayList;

import logica.DataTypes.DTPaquetePublicacion;

public class PaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez;
	private float descuento;
	private float costo;
	private ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones;
	private ArrayList<CompraPaquete> compraPaquetes;

	public PaquetePublicacion(String nombre, String descripcion, int periodoValidez, float descuento,
			ArrayList<CantidadTipoPublicacion> cantidadTipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadPublicaciones = 0;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.cantidadTipoPublicaciones = cantidadTipo;
		for (CantidadTipoPublicacion cantidad : cantidadTipo) {
			this.cantidadPublicaciones = this.cantidadPublicaciones + cantidad.getCantidadRestante();
		}
		this.compraPaquetes = new ArrayList<CompraPaquete>();
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

	public int getPeriodoValidez() {
		return periodoValidez;
	}

	public void setPeriodoValidez(int periodoValidez) {
		this.periodoValidez = periodoValidez;
	}

	public float getDescuento() {
		return descuento;
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

	public DTPaquetePublicacion obtenerDTPaquete() {
		return new DTPaquetePublicacion(nombre, descripcion, cantidadPublicaciones, periodoValidez, descuento, costo);
	}

	public void agregarCantidadTipoPublicacion(int cantIncluida, TipoPublicacion tipoPublicacion) throws Exception {
		CantidadTipoPublicacion nuevoCantidad = new CantidadTipoPublicacion(cantIncluida, tipoPublicacion, this);
		if (cantidadTipoPublicaciones == null) {
			cantidadTipoPublicaciones = new ArrayList<CantidadTipoPublicacion>();
		}
		if (!estaComprado()) {
			cantidadPublicaciones += cantIncluida;
			cantidadTipoPublicaciones.add(nuevoCantidad);
			setCosto();
		}else {
			throw new Exception("Paquete ya esta comprado");
		}
		
	}

	public ArrayList<CantidadTipoPublicacion> getCantidadTipoPublicaciones() {
		return cantidadTipoPublicaciones;
	}
	
	public boolean estaComprado() {
		return (compraPaquetes != null && !compraPaquetes.isEmpty());
	}

	public ArrayList<CompraPaquete> getCompraPaquetes() {
		return compraPaquetes;
	}

}
