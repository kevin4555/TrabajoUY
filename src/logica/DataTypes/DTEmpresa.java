package logica.DataTypes;

import java.util.ArrayList;

import logica.DTOferta;

public class DTEmpresa extends DTUsuario{
	private String descripcion;
	private String sitioWeb;
	private ArrayList<DTOferta> ofertasLaborales;
	
	public DTEmpresa(String nickname, String nombre, String apellido, String email, 
			String descripcion, String sitioWeb, ArrayList<DTOferta> ofertasLaborales) {
		super(nickname, nombre, apellido, email);
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

	public ArrayList<DTOferta> getOfertasLaborales() {
		return ofertasLaborales;
	}
}
