package logica.DataTypes;

import java.util.ArrayList;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	private String imagen;
	private String contrasenia;
	private ArrayList<DTOfertaLaboral> ofertasColeccion;

	public DTUsuario(String nickname, String nombre, String apellido, String email, String imagen, String contrasenia,
			ArrayList<DTOfertaLaboral> ofertasColeccion) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.imagen = imagen;
		this.contrasenia = contrasenia;
		this.ofertasColeccion = ofertasColeccion;
	}

	public String getImagen() {
		return imagen;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public ArrayList<DTOfertaLaboral> getOfertasColeccion() {
		return ofertasColeccion;
	}

	public String getEmail() {
		return email;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNickname() {
		return nickname;
	}
	
}
