package logica.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.CompraPaqueteYaExisteException;
import excepciones.OfertaLaboralYaExisteException;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTUsuario;

public class Empresa extends Usuario {
	private String descripcion;
	private String sitioWeb;
	private ArrayList<OfertaLaboral> ofertasLaborales;
	private Map<String, CompraPaquete> compraPaquetes;

	public Empresa(String nickname, String nombre, String apellido, String email, String descripcion, String sitioWeb) {
		super(nickname, nombre, apellido, email);
		setDescripcion(descripcion);
		setSitioWeb(sitioWeb);
		this.ofertasLaborales = new ArrayList<OfertaLaboral>();
		this.compraPaquetes = new HashMap<String, CompraPaquete>();
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

	public ArrayList<String> obtenerNombresOfertas() {
		ArrayList<String> ofertas = new ArrayList<String>();
		if (!ofertasLaborales.isEmpty()) {
			for (OfertaLaboral oferta : this.ofertasLaborales) {
				ofertas.add(oferta.getNombre());
				
			}
		}
		return ofertas;
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public void setOfertasLaborales(ArrayList<OfertaLaboral> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}

	public DTEmpresa obtenerDTEmpresa() {
		
		ArrayList<DTOfertaLaboral> listaDTOfertas = new ArrayList<DTOfertaLaboral>();
		for (OfertaLaboral oferta : ofertasLaborales) {
			listaDTOfertas.add(oferta.obtenerDTOfertaLaboral());
		}
		DTEmpresa resultado = new DTEmpresa(this.nickname, this.nombre, this.apellido, this.email, this.descripcion,
				this.sitioWeb, listaDTOfertas);
		return resultado;
	}

	@Override
	public ArrayList<String> listarOfertasUsuario(){
		return this.obtenerNombresOfertas();
	}

	@Override
	public DTUsuario obtenerDTUsuario() {
		return this.obtenerDTEmpresa();
	}

	public Map<String, CompraPaquete> getCompraPaquetes() {
		return compraPaquetes;
	}

	public void addCompraPaquete(CompraPaquete compraPaquete) throws CompraPaqueteYaExisteException {
		if (!this.compraPaquetes.containsKey(compraPaquete.getPaquetePublicacion().getNombre())) {
			this.compraPaquetes.put(compraPaquete.getPaquetePublicacion().getNombre(), compraPaquete);
		}else {
			throw new CompraPaqueteYaExisteException("CompraPaquete del PaquetePublicacion " + compraPaquete.getPaquetePublicacion().getNombre()  + " ya existe");
		}
	}
}
