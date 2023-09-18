package logica.DataTypes;

import java.util.ArrayList;

public class DTEmpresa extends DTUsuario{
	private String descripcion;
	private String sitioWeb;
	
	public DTEmpresa(String nickname, String nombre, String apellido, String email, String imagen, String contrasenia,
			ArrayList<DTOfertaLaboral> ofertasColeccion, String descripcion, String sitioWeb) {
		super(nickname, nombre, apellido, email, imagen, contrasenia, ofertasColeccion);
		this.descripcion = descripcion;
		this.sitioWeb = sitioWeb;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public ArrayList<DTOfertaLaboral> getOfertasLaborales() {
		return getOfertasColeccion();
	}
}
