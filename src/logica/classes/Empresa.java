package logica.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import excepciones.OfertaLaboralYaExisteException;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTUsuario;

public class Empresa extends Usuario {
	private String descripcion;
	private String sitioWeb;
	private ArrayList<OfertaLaboral> ofertasLaborales;
	
	public Empresa(String nickname, String nombre, String apellido, String email, String descripcion, String sitioWeb) {
		super(nickname, nombre, apellido, email);
		setDescripcion(descripcion);
		setSitioWeb(sitioWeb);
		this.ofertasLaborales = new ArrayList<OfertaLaboral>();
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getSitioWeb() {
		return sitioWeb;
	}
	
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	
	
	public void agregarOferta(OfertaLaboral ol) throws OfertaLaboralYaExisteException {
		if (ofertasLaborales.indexOf(ol) != -1) {
			throw new OfertaLaboralYaExisteException("La Oferta Laboral " + ol.getNombre() + " ya esta asociada a la Empresa " + this.nickname);
		}
		this.ofertasLaborales.add(ol);
	}
	
	public ArrayList<String> obtenerNombresOfertas(){
		ArrayList<String> ofertas = new ArrayList<String>();
		if(!ofertasLaborales.isEmpty())
		{
			for (OfertaLaboral oferta : this.ofertasLaborales) {
				ofertas.add(oferta.getNombre());
			}
		}
		return ofertas;
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}
	
	public DTEmpresa obtenerDTEmpresa() {
		ArrayList<DTOfertaLaboral> listaDTOfertas = new ArrayList<DTOfertaLaboral>();
		for(OfertaLaboral oferta : ofertasLaborales) {
			listaDTOfertas.add(oferta.obtenerDTOfertaLaboral());
		}
		DTEmpresa resultado = new DTEmpresa(this.nickname, this.nombre, this.apellido, this.email, this.descripcion, this.sitioWeb, listaDTOfertas);
		return resultado;
	}
	
	@Override
	public ArrayList<String> listarOfertasUsuario(){
		ArrayList<String> listaOfertas = new ArrayList<String>();
		for(OfertaLaboral oferta : ofertasLaborales) {
			listaOfertas.add(oferta.getNombre());
		}
		return listaOfertas;
	}

	@Override
	public DTUsuario obtenerDTUsuario() {
		return this.obtenerDTEmpresa();
	}
}
