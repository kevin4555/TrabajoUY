package logica.DataTypes;

import java.util.ArrayList;

public class DTEmpresa extends DTUsuario{
	private String descripcion;
	private String sitioWeb;
	private ArrayList<DTOfertaLaboral> ofertasLaborales;
	
	public DTEmpresa(String nickname, String nombre, String apellido, String email, String imagen, String contrasenia,
			ArrayList<DTOfertaLaboral> ofertasColeccion, String descripcion, String sitioWeb, ArrayList<DTOfertaLaboral> ofertasLaborales) {
		super(nickname, nombre, apellido, email, imagen, contrasenia, ofertasColeccion);
		this.descripcion = descripcion;
		this.sitioWeb = sitioWeb;
		this.ofertasLaborales = ofertasLaborales;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public ArrayList<DTOfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}
}
