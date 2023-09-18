package logica.classes;

import java.util.Date;

import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;

public class Postulacion {
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;
	private String descrpMotivacion;
	private Date fechaPostulacion;
	private String cvReducido;
	
	public Postulacion(String descrpMotivacion, Date fechaPostulacion, String cvReducido, Postulante postulante, OfertaLaboral ofertaLaboral) {
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
	
	public Date getFechaPostulacion() {
		return fechaPostulacion;
	}
	
	public void setFechaPostulacion(Date fechaPostulacion) {
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
		
		DTPostulante dtPostulante = new DTPostulante(this.postulante.getNickname(), this.postulante.getNombre(), this.postulante.getApellido(), this.postulante.getEmail(),
				this.postulante.getImagen(), this.postulante.getContrasenia(), this.postulante.obtenerDTOfertas(), this.postulante.getFechaNacimiento(), this.postulante.getNacionalidad() );
		
				
		
		DTPostulacion dtPostulacion = new DTPostulacion(dtPostulante, this.getDescrpMotivacion(), this.getFechaPostulacion(), this.getCvReducido());
		return dtPostulacion;
	}
	
	public String getNombreOfertaLaboral() {
		return this.ofertaLaboral.getNombre();
	}
}
