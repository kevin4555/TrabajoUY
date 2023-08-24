package logica.controllers;

import java.util.Date;
import java.util.ArrayList;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
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

	public ArrayList<String> listarTipoDePublicaciones()
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ArrayList<String> nombreTiposPublicacion = manejadorSettings.listarTipoDePublicaciones();
		return nombreTiposPublicacion;
	}

	public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial, String horarioFinal, float remuneracion, String ciudad, String departamento, Date fechaAlta, TipoPublicacion tipoPublicacion) throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException
	{
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		OfertaLaboral ofertaLaboral = new OfertaLaboral(nombre, descripcion, horarioInicial, horarioFinal, remuneracion,ciudad, departamento, fechaAlta, tipoPublicacion);
		manejadorOfertas.agregarOferta(ofertaLaboral);
	}

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException
	{
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		return ofertaLaboral;
	}

	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete) throws TipoPublicacionYaExisteException, PaquetePublicacionNoExisteException 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();

		TipoPublicacion tipoPublicacion;
		tipoPublicacion = manejadorSettings.obtenerTipoPublicacion(nomTipoPublicacion);
		PaquetePublicacion paquetePublicacion = manejadorPaquetes.obtenerPaquete(nomTipoPaquete);

		paquetePublicacion.crearCantidadTipoPublicacion(paquetePublicacion, cantIncluida, tipoPublicacion);
	}

	public ArrayList<String> listarPaquetes() 
	{
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
		return manejadorPaquetes.listarPaquetes();
	}

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo, Date fechaPub) throws TipoPublicacionYaExisteException 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		TipoPublicacion tipoPublicacion = new TipoPublicacion(nombre, descripcion, exposicion, duracion, costo, fechaPub);
		manejadorSettings.addTipoPublicacion(tipoPublicacion);
	}
	
	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic) throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		TipoPublicacion tpoPublic = manejadorSettings.obtenerTipoPublicacion(nomTpoPublic);
		return tpoPublic;
	}
	
	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException, TipoPublicacionYaExisteException 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		Keyword keyword = new Keyword(nomKeyword);
		manejadorSettings.addKeyword(keyword);	
	}
	
	

	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException, TipoPublicacionNoExisteException 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		Keyword keyword = manejadorSettings.obtenerKeyword(nomKeyword);
		return keyword;
	}

	public ArrayList<String> listarKeywords() 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ArrayList<String> listKeywords = manejadorSettings.listarKeywords();
		return listKeywords;
	}

	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname, String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException 
	{
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();

		Postulante postulante = manejadorUsuario.obtenerPostulante(nickname);
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		Postulacion postulacion = new Postulacion(motivacion, fechaPostulacion, cvReducido, postulante, ofertaLaboral);
		ofertaLaboral.agregarPostulacion(postulacion);
		postulante.agregarPostulacion(postulacion);
	}

	public ArrayList<String> listarPostulantes() 
	{
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		ArrayList<String> lpostulantes = controladorUsuario.listarPostulantes();
		return lpostulantes;
	}

	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException 
	{
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();

		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		DTOfertaLaboral dtOfertaLaboral = ofertaLaboral.obtenerDTOfertaLaboral();
		return dtOfertaLaboral;
	}

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException 
	{
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
			
		Empresa empresa = manejadorUsuario.obtenerEmpresa(nicknameEmpresa);
		ArrayList<OfertaLaboral> ofertas = empresa.getOfertasLaborales();

		ArrayList<String> nombreOfertas = new ArrayList<String>();
		for (OfertaLaboral ofertaLaboral : ofertas) 
		{
			nombreOfertas.add(ofertaLaboral.getNombre());
		}
		return nombreOfertas;
	}

	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias, Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion) throws PaquetePublicacionYaExisteException 
	{
		ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
		PaquetePublicacion paquetePublicacion = new PaquetePublicacion(nombre, descripcion, cantidadPublicaciones, periodoValDias, descuento, cantidadTipoPublicacion);
		manejadorPaquetes.agregarPaquete(paquetePublicacion);
	}
	
	public void agregarKeywordEnOfertaLaboral(String nomKeyword, String nomOferta) throws KeywordNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException 
	{
		ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
		ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
		Keyword keyword = manejadorSettings.obtenerKeyword(nomKeyword);
		OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
		ofertaLaboral.agregarKeyword(keyword);
		
	}



	@Override
	public void agregarTipoPublicacionAlPaquete(int cantIncluida) {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmarAltaPublicacion(String nombre, String descripcion, String exposicion, int duracion,
			Float costo, Date fechaPub) {
		// TODO Auto-generated method stub

	}
}