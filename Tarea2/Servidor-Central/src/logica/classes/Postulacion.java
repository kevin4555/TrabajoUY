package logica.classes;

import java.time.LocalDate;
import java.util.Date;

import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;

public class Postulacion {
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;
	private String descrpMotivacion;
	private LocalDate fechaPostulacion;
	private String cvReducido;
	
	public Postulacion(String descrpMotivacion, LocalDate fechaPostulacion, String cvReducido, Postulante postulante, OfertaLaboral ofertaLaboral) {
		super();
		setDescrpMotivacion(descrpMotivacion);
		setFechaPostulacion(fechaPostulacion);
		setCvReducido(cvReducido);
		this.postulante = postulante;
		this.ofertaLaboral = ofertaLaboral;
	}
	
	public String getDescrpMotivacion() {
		return descrpMotivacion;
	}
	
	public void setDescrpMotivacion(String descrpMotivacion) {
		this.descrpMotivacion = descrpMotivacion;
	}
	
	public LocalDate getFechaPostulacion() {
		return fechaPostulacion;
	}
	
	public void setFechaPostulacion(LocalDate fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}
	
	public String getCvReducido() {
		return cvReducido;
	}
	
	public void setCvReducido(String cvReducido) {
		this.cvReducido = cvReducido;
	}

	public OfertaLaboral getOfertaLaboral() {
		return ofertaLaboral;
	}

	public Postulante getPostulante() {
		return postulante;
	}
	
	public DTPostulacion obtenerDTPostulacion()
	{
						
		
		DTPostulacion dtPostulacion = new DTPostulacion(this.postulante.getNickname(), this.getDescrpMotivacion(), this.getFechaPostulacion(), this.getCvReducido());
		return dtPostulacion;
	}
	
	public String getNombreOfertaLaboral() {
		return this.ofertaLaboral.getNombre();
	}
}
