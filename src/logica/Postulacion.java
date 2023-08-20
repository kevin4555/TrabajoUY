package logica;

import java.sql.Date;
import java.util.Map;

public class Postulacion {
	private String descripMotivacion;
	private Date fechaPostulacion;
	private String cvReducido;
	private Map<String, OfertaLaboral> postulaciones;
	
	public String getDescripMotivacion() {
		return descripMotivacion;
	}
	public void setDescripMotivacion(String descripMotivacion) {
		this.descripMotivacion = descripMotivacion;
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
	public Map<String, OfertaLaboral> getPostulaciones() {
		return postulaciones;
	}
	public void setPostulaciones(Map<String, OfertaLaboral> postulaciones) {
		this.postulaciones = postulaciones;
	}
}
