package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Empresa extends Usuario {
	private String descripcion;
	private String sitioWeb;
	private ArrayList<OfertaLaboral> ofertasLaborales;
	
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
	
	
	public void agregarOferta(OfertaLaboral ol) {
		for (OfertaLaboral oferta : this.ofertasLaborales) {
			if (ol.getNombre().equals(oferta.getNombre())) {
				return;
			}
		}
		this.ofertasLaborales.add(ol);
	}
	
	public Set<String> obtenerOfertasEmpresa(){
		Set<String> ofertas = new HashSet<String>();
		for (OfertaLaboral oferta : this.ofertasLaborales) {
			ofertas.add(oferta.getNombre());
		}
		return ofertas;
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public void setOfertasLaborales(ArrayList<OfertaLaboral> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}
	
}
