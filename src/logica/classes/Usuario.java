package logica.classes;

import java.util.ArrayList;

import logica.DataTypes.DTUsuario;

public abstract class Usuario {
	protected String nickname;
	protected String nombre;
	protected String apellido;
	protected String email;
	
	public Usuario( String nickname, String nombre, String apellido, String email) {
		super();
		setNickname(nickname);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	abstract public ArrayList<String> listarOfertasUsuario();
	
	abstract public DTUsuario obtenerDTUsuario();
}
