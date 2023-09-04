package logica.DataTypes;

import java.util.Date;

public class DTPostulante extends DTUsuario{
	private Date fechaNacimiento;
	private String nacionalidad;
	
	public DTPostulante( String nickname, String nombre, String apellido,
			String email, Date fechaNacimiento, String nacionalidad) {
		super(nickname,nombre, apellido, email);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
}
