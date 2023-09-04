package logica.classes;

import java.util.Date;

public class CompraPaquete {
	private Date fehcaNacimiento;
	private String nacionalidad;
	
	public CompraPaquete(Date fechaNacimiento, String nacionalidad)
	{
		this.setFehcaNacimiento(fechaNacimiento);
		this.setNacionalidad(nacionalidad);
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFehcaNacimiento() {
		return fehcaNacimiento;
	}

	public void setFehcaNacimiento(Date fehcaNacimiento) {
		this.fehcaNacimiento = fehcaNacimiento;
	};
	
	
}
