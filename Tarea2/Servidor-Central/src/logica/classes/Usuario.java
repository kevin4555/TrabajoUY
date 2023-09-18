package logica.classes;

import java.util.ArrayList;

import logica.DataTypes.DTUsuario;

public abstract class Usuario {
	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String email;
	private String imagen;
	private String contrasenia;

	public Usuario(String nickname, String nombre, String apellido, String email, String imagen, String contrasenia) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasenia = contrasenia;
		this.imagen = imagen;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	abstract public ArrayList<String> listarOfertasUsuario();

	abstract public DTUsuario obtenerDTUsuario();
}
