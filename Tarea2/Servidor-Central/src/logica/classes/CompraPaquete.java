package logica.classes;

import java.util.ArrayList;
import java.time.LocalDate;

import logica.DataTypes.DTPaquetePublicacion;

public class CompraPaquete {
	private LocalDate fechaVencimiento;
	private LocalDate fechaCompra;
	private ArrayList<CantidadTipoPublicacionRestante> cantidadesRestantes;
	private PaquetePublicacion paquete;
	

	public CompraPaquete(LocalDate fechaCompra, PaquetePublicacion paquete) {
		this.fechaCompra = fechaCompra;
		this.fechaVencimiento = fechaCompra.withDayOfMonth(fechaCompra.getDayOfMonth() + paquete.getPeriodoValidez());
		this.paquete = paquete;
		
		ArrayList<CantidadTotalTipoPublicacion> listaCantidadtotal = this.paquete.obtenerCantidadTotalTipoPublicaciones();
		this.cantidadesRestantes = new ArrayList<CantidadTipoPublicacionRestante>();
		for(CantidadTotalTipoPublicacion cantidad : listaCantidadtotal) {
			this.cantidadesRestantes.add(new CantidadTipoPublicacionRestante(cantidad.getCantidadTotal(), cantidad.getTipoPublicacion()));
		}
		
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public String obtenerNombrePaquete() {
		return this.paquete.getNombre();
	}
	
	public DTPaquetePublicacion obtenerDTPaquete() {
		return this.paquete.obtenerDTPaquete();
	}
	
	public void gastarTipoPublicacion(String nombreTipoPublicacion) {
		for(CantidadTipoPublicacionRestante cantidad : cantidadesRestantes) {
			if(cantidad.getNombreTipoPublicacion() == nombreTipoPublicacion) {
				cantidad.reducirCantidad();
				break;
			}
		}
	}
}
