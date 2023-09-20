package logica.DataTypes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

public class DTPostulante extends DTUsuario {
	private Date fechaNacimiento;
	private String nacionalidad;

	public DTPostulante(String nickname, String nombre, String apellido, String email, BufferedImage imagen,
			String contrasenia, ArrayList<DTOfertaLaboral> ofertasColeccion, Date fechaNacimiento,
			String nacionalidad) {
		super(nickname, nombre, apellido, email, imagen, contrasenia, ofertasColeccion);
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
