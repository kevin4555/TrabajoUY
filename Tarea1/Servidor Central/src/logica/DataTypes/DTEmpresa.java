package logica.DataTypes;

import java.util.ArrayList;

public class DTEmpresa extends DTUsuario{
	private String descripcion;
	private String sitioWeb;
	private ArrayList<DTOfertaLaboral> ofertasLaborales;
	
	public DTEmpresa(String nickname, String nombre, String apellido, String email, 
			String descripcion, String sitioWeb, ArrayList<DTOfertaLaboral> ofertasLaborales) {
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

	public ArrayList<DTOfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}
}
