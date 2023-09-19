package logica.classes;

import java.util.ArrayList;

import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTPaquetePublicacion;

public class PaquetePublicacion {
	private String nombre;
	private String descripcion;
	private int cantidadPublicacionesPublicadas;
	private int periodoValidez;
	private float descuento;
	private float costo;
	private String imagen;
	private ArrayList<CantidadTotalTipoPublicacion> cantidadTipoPublicaciones;

	public PaquetePublicacion(String nombre, String descripcion, int periodoValidez, float descuento, String imagen,
			ArrayList<CantidadTotalTipoPublicacion> cantidadTipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadPublicacionesPublicadas = 0;
		this.periodoValidez = periodoValidez;
		this.descuento = descuento;
		this.imagen = imagen;
		this.cantidadTipoPublicaciones = cantidadTipo;
		//this.setCosto();
	}

	public String getNombre() {
		return nombre;
	}

	/*private void setCosto() {
		float costoTotal = 0;
		for (CantidadTotalTipoPublicacion cantidadTipoPublicacion : cantidadTipoPublicaciones) {
			costoTotal += cantidadTipoPublicacion.obtenerCostoPublicaciones();
		}
		this.costo = costoTotal * ((100 - descuento) / 100);
	}*/

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
		return cantidadPublicacionesPublicadas;
	}

	public void setCantidadPublicaciones(int cantidadPublicacionesPublicadas) {
		this.cantidadPublicacionesPublicadas = cantidadPublicacionesPublicadas;
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

	/*public void setDescuento(float descuento) {
		this.descuento = descuento;
		setCosto();
	}*/

	public float getCosto() {
		return costo;
	}

	public void setCantidadTotalTipoPublicaciones(ArrayList<CantidadTotalTipoPublicacion> cantidadTipoPublicaciones) {
		this.cantidadTipoPublicaciones = cantidadTipoPublicaciones;
	}

	public DTPaquetePublicacion obtenerDTPaquete() {
		ArrayList<DTCantidadTipoPublicacion> listDTcantidad = new ArrayList<DTCantidadTipoPublicacion>();
		/*for (CantidadTotalTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			listDTcantidad.add(cantidad.obtenerDTCantidadTipoPublicacion());
		}*/
		return new DTPaquetePublicacion(nombre, descripcion, cantidadPublicacionesPublicadas, periodoValidez, descuento,
				costo, imagen, listDTcantidad);
	}

	public ArrayList<CantidadTotalTipoPublicacion> obtenerCantidadTotalTipoPublicaciones() {
		return cantidadTipoPublicaciones;
	}

	public ArrayList<String> obtenerNombresTipoPublicaciones() {
		ArrayList<String> listaResultado = new ArrayList<String>();
		for (CantidadTotalTipoPublicacion cantidad : cantidadTipoPublicaciones) {
			listaResultado.add(cantidad.getTipoPublicacion().getNombre());
		}
		return listaResultado;
	}

	public void agregarTipoPublicacion(TipoPublicacion tipoPublicacion) {
		CantidadTotalTipoPublicacion cantidadTotalTipoPublicacion = tipoPublicacion.getCantidadTipoPublicacion();
		cantidadTipoPublicaciones.add(cantidadTotalTipoPublicacion);
	}

	public ArrayList<String> listarTipoPublicacion() {
		ArrayList<String> resultado = new ArrayList<>();
		for (CantidadTotalTipoPublicacion tipoPublicacion : cantidadTipoPublicaciones) {
			resultado.add(tipoPublicacion.getTipoPublicacion().getNombre());
		}
		return resultado;
	}
	
	

}
