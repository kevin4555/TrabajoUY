package logica.controllers;

import java.util.ArrayList;
import java.util.Date;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IControladorOferta;

public class ControladorOferta implements IControladorOferta {

	@Override
	public ArrayList<String> listarTipoDePublicaciones() {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ArrayList<String> nombreTiposPublicacion = manejadorSettings.listarTipoDePublicaciones();
		return nombreTiposPublicacion;
	}

	@Override
	public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial, String horarioFinal,
			float remuneracion, String ciudad, String departamento, Date fechaAlta, TipoPublicacion tipoPublicacion,
			Empresa empresa, CompraPaquete compraPaquete)
			throws Exception {
		if (empresa != null && compraPaquete != null) {
			throw new Exception("No se puede asociar a una Empresa y a un CompraPaquete al mismo tiempo");
		}
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nombre);
		ofertaLaboral = new OfertaLaboral(nombre, descripcion, horarioInicial, horarioFinal, remuneracion, ciudad,
				departamento, fechaAlta, tipoPublicacion , empresa, compraPaquete);
		manejadorOfertas.agregarOferta(ofertaLaboral);
	}

	@Override
	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException {
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		return ofertaLaboral;
	}

	@Override
	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete) {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
		try {
			// Falta ver que hacer si ya hay un cantidadTipoPublicacion con esos dos,
			// Exception o incrementar?
			TipoPublicacion tipoPublicacion = manejadorSettings.obtenerTipoPublicacion(nomTipoPublicacion);
			PaquetePublicacion paquetePublicacion = manejadorPaquetes.obtenerPaquete(nomTipoPaquete);
			CantidadTipoPublicacion cantidadTipoPublicacion = new CantidadTipoPublicacion(tipoPublicacion,
					paquetePublicacion, cantIncluida);
			paquetePublicacion.addCantidadTipoPublicacion(cantidadTipoPublicacion);
			tipoPublicacion.addCantidadTipoPublicacion(cantidadTipoPublicacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> listarPaquetes() {
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
		return manejadorPaquetes.listarPaquetes();
	}

	@Override
	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo,
			Date fechaPub) throws TipoPublicacionYaExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		TipoPublicacion tipoPublicacion = new TipoPublicacion(nombre, descripcion, exposicion, duracion, costo,
				fechaPub);
		manejadorSettings.addTipoPublicacion(tipoPublicacion);
	}

	@Override
	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic)
			throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		TipoPublicacion tpoPublic = manejadorSettings.obtenerTipoPublicacion(nomTpoPublic);
		if (tpoPublic == null) {
			throw new TipoPublicacionNoExisteException("El tipo de publicacion " + nomTpoPublic + " no existe");
		} else {
			return tpoPublic;
		}
	}

	@Override
	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		Keyword keyword = new Keyword(nomKeyword);
		manejadorSettings.addKeyword(keyword);
	}

	@Override
	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		Keyword keyword = manejadorSettings.obtenerKeyword(nomKeyword);
		return keyword;
	}

	@Override
	public ArrayList<String> listarKeywords() {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ArrayList<String> listKeywords = manejadorSettings.listarKeywords();
		return listKeywords;
	}

	@Override
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();

		try {
			Postulante postulante = manejadorUsuario.obtenerPostulante(nickname);
			OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
			Postulacion postulacion = new Postulacion(motivacion, fechaPostulacion, cvReducido, postulante,
					ofertaLaboral);
			ofertaLaboral.agregarPostulacion(postulacion);
			postulante.agregarPostulacion(postulacion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList<String> listarPostulantes() {
		ControladorUsuario controladorUsuario = new ControladorUsuario();

		ArrayList<String> lpostulantes = controladorUsuario.listarPostulantes();
		return lpostulantes;
	}

	@Override
	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) {
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		try {
			OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
			DTOfertaLaboral dtOfertaLaboral = ofertaLaboral.obtenerDTOfertaLaboral();
			return dtOfertaLaboral;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		try {
			Empresa empresa = manejadorUsuario.obtenerEmpresa(nicknameEmpresa);
			ArrayList<OfertaLaboral> ofertas = empresa.getOfertasLaborales();

			ArrayList<String> nombreOfertas = new ArrayList<String>();

			for (OfertaLaboral ofertaLaboral : ofertas) {
				nombreOfertas.add(ofertaLaboral.getNombre());
			}
			return nombreOfertas;

		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias,
			Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion) {
		try {
			ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
			PaquetePublicacion paquetePublicacion = new PaquetePublicacion(nombre, descripcion, cantidadPublicaciones,
					periodoValDias, descuento, cantidadTipoPublicacion);
			manejadorPaquetes.agregarPaquete(paquetePublicacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void agregarKeywordEnOfertaLaboral(String nomKeyword, String nomOferta)
			throws KeywordNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException {
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		Keyword keyword = manejadorSettings.obtenerKeyword(nomKeyword);
		if (keyword == null) {
			throw new KeywordNoExisteException("La Keyword " + nomKeyword + " no existe");
		}
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		if (ofertaLaboral == null) {
			throw new OfertaLaboralNoExisteException("La Oferta laboral " + nomOferta + " no existe");
		}
		ofertaLaboral.agregarKeyword(keyword);

	}

	@Override
	public PaquetePublicacion obtenerPaquetePublicacion(String nombrePaquete)
			throws PaquetePublicacionNoExisteException {
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
		PaquetePublicacion paquetePublicacion = manejadorPaquetes.obtenerPaquete(nombrePaquete);
		return paquetePublicacion;
	}

}