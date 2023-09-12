package logica.DataTypes;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String email;
	
	public DTUsuario( String nickname, String nombre, 
			String apellido, String email) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
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
