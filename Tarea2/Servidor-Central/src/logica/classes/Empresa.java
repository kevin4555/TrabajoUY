package logica.classes;

import excepciones.OfertaLaboralYaExisteException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTUsuario;
import logica.DataTypes.EstadoOferta;

public class Empresa extends Usuario {
	private String descripcion;
	private String sitioWeb;
	private ArrayList<OfertaLaboral> ofertasLaborales;
	private ArrayList<CompraPaquete> compraPaquetes;

	public Empresa(String nickname, String nombre, String apellido, String email, String descripcion, String sitioWeb,
			BufferedImage imagen, String contrasenia) {
		super(nickname, nombre, apellido, email, imagen, contrasenia);
		setDescripcion(descripcion);
		setSitioWeb(sitioWeb);
		this.ofertasLaborales = new ArrayList<OfertaLaboral>();
		this.compraPaquetes = new ArrayList<CompraPaquete>();
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
			throw new OfertaLaboralYaExisteException(
					"La Oferta Laboral " + ol.getNombre() + " ya esta asociada a la Empresa " + this.nickname);
		}
		this.ofertasLaborales.add(ol);
	}

	public ArrayList<String> obtenerNombresOfertas() {
		ArrayList<String> ofertas = new ArrayList<String>();
		for (OfertaLaboral oferta : this.ofertasLaborales) {
			ofertas.add(oferta.getNombre());
		}
		return ofertas;
	}

	public ArrayList<OfertaLaboral> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public DTEmpresa obtenerDTEmpresa() {

		ArrayList<DTOfertaLaboral> listaDTOfertas = new ArrayList<DTOfertaLaboral>();
		for (OfertaLaboral oferta : ofertasLaborales) {
			listaDTOfertas.add(oferta.obtenerDTOfertaLaboral());
		}
		DTEmpresa resultado = new DTEmpresa(this.nickname, this.nombre, this.apellido, this.email, this.getImagen(),
				this.getContrasenia(), listaDTOfertas, this.descripcion, this.sitioWeb);
		return resultado;
	}

	@Override
	public ArrayList<String> listarOfertasUsuario() {
		return this.obtenerNombresOfertas();
	}

	@Override
	public DTUsuario obtenerDTUsuario() {
		return this.obtenerDTEmpresa();
	}

	public ArrayList<DTOfertaLaboral> obtenerDTOfertasIngresadas() {
		ArrayList<DTOfertaLaboral> listaResultado = new ArrayList<DTOfertaLaboral>();
		for (OfertaLaboral oferta : ofertasLaborales) {
			if (oferta.getEstado() == EstadoOferta.INGRESADA) {
				listaResultado.add(oferta.obtenerDTOfertaLaboral());
			}
		}
		return listaResultado;
	}

	public ArrayList<DTOfertaLaboral> obtenerDTOfertasConfirmadas() {
		ArrayList<DTOfertaLaboral> listaResultado = new ArrayList<DTOfertaLaboral>();
		for (OfertaLaboral oferta : ofertasLaborales) {
			if (oferta.getEstado() == EstadoOferta.CONFIRMADA) {
				listaResultado.add(oferta.obtenerDTOfertaLaboral());
			}
		}
		return listaResultado;
	}

	public ArrayList<DTOfertaLaboral> obtenerDTOfertasRechazadas() {
		ArrayList<DTOfertaLaboral> listaResultado = new ArrayList<DTOfertaLaboral>();
		for (OfertaLaboral oferta : ofertasLaborales) {
			if (oferta.getEstado() == EstadoOferta.RECHAZADA) {
				listaResultado.add(oferta.obtenerDTOfertaLaboral());
			}
		}
		return listaResultado;
	}

	public void comprarPaquete(CompraPaquete compraPaquete) {
		compraPaquetes.add(compraPaquete);
	}

	public Boolean estaCompradoPaquete(String nombrePaquete) {
		Boolean resultado = false;
		for (CompraPaquete compraPaquete : compraPaquetes) {
			if (compraPaquete.obtenerNombrePaquete() == nombrePaquete) {
				resultado = true;
				break;
			}
		}
		return resultado;
	}

	public ArrayList<DTPaquetePublicacion> obtenerDTPaquetes() {
		ArrayList<DTPaquetePublicacion> listaReultado = new ArrayList<DTPaquetePublicacion>();
		for (CompraPaquete compraPaquete : compraPaquetes) {
			listaReultado.add(compraPaquete.obtenerDTPaquete());
		}
		return listaReultado;
	}

	public ArrayList<String> listarPaquetesComprados() {
		ArrayList<String> listaResultado = new ArrayList<String>();
		for (CompraPaquete compraPaquete : compraPaquetes) {
			listaResultado.add(compraPaquete.obtenerNombrePaquete());
		}
		return listaResultado;
	}

	public void comprarOfertaPorPaquete(String nombrePaquete, String nomTipoPublicacion,
			OfertaLaboral oferta) {
		for (CompraPaquete compra : compraPaquetes) {
			if (compra.obtenerNombrePaquete() == nombrePaquete) {
				compra.gastarTipoPublicacion(nomTipoPublicacion);
				oferta.setCompraPaquete(compra);
				break;
			}
		}

	}

}
