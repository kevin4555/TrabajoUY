package logica.classes;

import java.util.ArrayList;
import java.util.Map;

import excepciones.CompraPaqueteYaExisteException;
import logica.DataTypes.DTPaquetePublicacion;

public class PaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicaciones;
	private int periodoValidez;
	private float descuento;
	private float costo;
	private ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones;
	private Map<String, CompraPaquete> compraPaquetes;

	public PaquetePublicacion(String nombre, String descripcion, int periodoValidez,
			float descuento, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		if (this.cantidadTipoPublicaciones == null) {
			this.cantidadTipoPublicaciones = new ArrayList<CantidadTipoPublicacion>();
		}
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
		for (CantidadTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			this.cantidadPublicaciones = this.cantidadPublicaciones + cantidad.getCantidadRestante();
		}
		this.setCosto();
	}

	public String getNombre() {
		return nombre;
	}

	private void setCosto() {
		float costoTotal = 0;
		if (cantidadTipoPublicaciones != null) {
			for (CantidadTipoPublicacion cantidadTipoPublicacion : cantidadTipoPublicaciones) {
				costoTotal += cantidadTipoPublicacion.obtenerCostoPublicaciones();
			}
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

	/**
	 * Aumenta la cantidad en uno
	 */
	public void aumentarCantidadPublicaciones() {
		this.cantidadPublicaciones += 1;
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

	public void setCantidadTipoPublicaciones(ArrayList<CantidadTipoPublicacion> cantidadTipoPublicaciones) {
		this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
	}
	public ArrayList<CantidadTipoPublicacion> getCantidadTipoPublicaciones() {
		return this.cantidadTipoPublicaciones;
	}

	public DTPaquetePublicacion obtenerDTPaquete() {
		return new DTPaquetePublicacion(nombre, descripcion, cantidadPublicaciones, periodoValidez, descuento, costo);
	}

	public void addCantidadTipoPublicacion(CantidadTipoPublicacion cantidadTipoPublicacion) {
		if (this.cantidadTipoPublicaciones == null) {
			this.cantidadTipoPublicaciones = new ArrayList<CantidadTipoPublicacion>();
		}
		this.cantidadTipoPublicaciones.add(cantidadTipoPublicacion);
		for (CantidadTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			this.cantidadPublicaciones = this.cantidadPublicaciones + cantidad.getCantidadRestante();
		}
		setCosto();
	}

	public Map<String, CompraPaquete> getCompraPaquetes() {
		return compraPaquetes;
	}

	public void addCompraPaquete(CompraPaquete compraPaquete) throws CompraPaqueteYaExisteException {
		if (!this.compraPaquetes.containsKey(compraPaquete.getEmpresa().getNombre())) {
			this.compraPaquetes.put(compraPaquete.getEmpresa().getNombre(), compraPaquete);
		} else {
			throw new CompraPaqueteYaExisteException(
					"CompraPaquete de la Empresa " + compraPaquete.getEmpresa().getNombre() + " ya existe");
		}
	}

	public void crearCantidadTipoPublicacion(int cantIncluida, TipoPublicacion tipoPublicacion) {
		CantidadTipoPublicacion cantidadTipoPublicacion = new CantidadTipoPublicacion(cantIncluida, tipoPublicacion);
		this.addCantidadTipoPublicacion(cantidadTipoPublicacion);
		
	}

}
