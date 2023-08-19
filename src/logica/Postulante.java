package logica;

import java.util.Date;

public class Postulante extends Usuario{
	private Date fechaNacimiento;
	private String nacionalidad;
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	//obtenerDTPostulante()
	//agregarPostulacion(post: Postulacion)
}
