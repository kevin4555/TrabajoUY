package logica.DataTypes;

import java.sql.Date;

public class DTPostulacion {

	//private DTUsuario usuario;

	private String descripMotivacion;

	private Date fechaPostulacion;

	private String cvReducido;

	/*DTPostulacion(DTUsuario usuario, String descripMotivacion, Date fechaPostulacion, String cvReducido) {
		this.usuario = usuario;
		this.descripMotivacion = descripMotivacion;
		this.fechaPostulacion = fechaPostulacion;
		this.cvReducido = cvReducido;
	}*/

	/*public DTUsuario getUsuario() {
		return usuario;
	}*/

	
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
