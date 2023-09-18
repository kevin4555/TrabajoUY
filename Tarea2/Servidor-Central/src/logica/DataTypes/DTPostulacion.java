package logica.DataTypes;

import java.util.Date;

public class DTPostulacion {

	private DTPostulante postulante;

	private String descripMotivacion;

	private Date fechaPostulacion;

	private String cvReducido;

	public DTPostulacion(DTPostulante postulante, String descripMotivacion, Date fechaPostulacion, String cvReducido) {
		this.postulante = postulante;
		this.descripMotivacion = descripMotivacion;
		this.fechaPostulacion = fechaPostulacion;
		this.cvReducido = cvReducido;
	}

	public DTPostulante getPostulante() {
		return postulante;
	}

	
	public String getDescripMotivacion() {
		return descripMotivacion;
	}

	public Date getFechaPostulacion() {
		return fechaPostulacion;
	}

	public String getCvReducido() {
		return cvReducido;
	}
	

}
