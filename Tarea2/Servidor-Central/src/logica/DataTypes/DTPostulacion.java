package logica.DataTypes;

import java.time.LocalDate;
;

public class DTPostulacion {

	private String nicknamePostulante;

	private String descripMotivacion;

	private LocalDate fechaPostulacion;

	private String cvReducido;
	
	public DTPostulacion() {
		
	}

	public DTPostulacion(String postulante, String descripMotivacion, LocalDate fechaPostulacion, String cvReducido) {
		this.nicknamePostulante = postulante;
		this.descripMotivacion = descripMotivacion;
		this.fechaPostulacion = fechaPostulacion;
		this.cvReducido = cvReducido;
	}

	public String getnicknamePostulante() {
		return nicknamePostulante;
	}

	
	public String getDescripMotivacion() {
		return descripMotivacion;
	}

	public LocalDate getFechaPostulacion() {
		return fechaPostulacion;
	}

	public String getCvReducido() {
		return cvReducido;
	}
	

}
