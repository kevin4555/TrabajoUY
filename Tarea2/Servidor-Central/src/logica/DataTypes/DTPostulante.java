package logica.DataTypes;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;


public class DTPostulante extends DTUsuario {
	private LocalDate fechaNacimiento;
	private String nacionalidad;

	public DTPostulante(String nickname, String nombre, String apellido, String email, BufferedImage imagen,
			String contrasenia, ArrayList<DTOfertaLaboral> ofertasColeccion, LocalDate fechaNacimiento,
			String nacionalidad) {
		super(nickname, nombre, apellido, email, imagen, contrasenia, ofertasColeccion);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
}
