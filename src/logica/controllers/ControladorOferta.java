package logica.controllers; 

import java.sql.Date; 
import java.util.ArrayList;
import java.util.Map;

import excepciones.DtOfertaNoExisteException;
import excepciones.KeywordNoExisteException;  
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.UsuarioNoExisteUsuarioException;
import logica.DataTypes.DTOferta;
import logica.DataTypes.DTPaquete;
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

	public ArrayList<String> listarTipoDePublicaciones(){ 
		ManejadorSettings msetting = ManejadorSettings.getInstance(); 
		ArrayList<String> nomTposPublic = msetting.listarTipoDePublicaciones(); 
		return nomTposPublic; 
	} 

	public void altaOfertaLaboral(String nombre, String descripcion, Date horaInicio, Date horaFin, double remuneracion, String ciudad, String departamento, Date fechaAlta, Map<String,Keyword> keywords, TipoPublicacion tipoPublicacion, CompraPaquete cp) 
	{ 
		ManejadorOfertas mofertas = ManejadorOfertas.getInstance();
		OfertaLaboral oflaboral = new OfertaLaboral(nombre, descripcion, ciudad, departamento, horaInicio, horaFin, remuneracion, fechaAlta, keywords, tipoPublicacion, cp);
		try {
		mofertas.agregarOferta(oflaboral);
		}catch(OfertaLaboralYaExisteException e)
		{
			System.out.println(e.getMessage());
		}
	} 

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) 
	{ 
		ManejadorOfertas mofertas = ManejadorOfertas.getInstance(); 
		try { 
			OfertaLaboral olaboral = mofertas.obtenerOfertaLaboral(nomOferta); 
			return olaboral;
		}catch(OfertaLaboralNoExisteException e) 
		{ 
			System.out.println(e.getMessage()); 
			return null;
		}
	} 

	public void agregarTipoPublicacionAlPaquete(int cantIncluida) 
	{ 
	
	} 

	public ArrayList<String> listarPaquetes() 
	{ 
		ArrayList<String> paquetes = new ArrayList<>();
		ManejadorPaquetes mpaquetes = ManejadorPaquetes.getInstance(); 
		paquetes = mpaquetes.getColeccionPaquetes(); 
		return paquetes; 
	} 

	public void confirmarAltaPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo, Date fechaPub) 
	{ 
		TipoPublicacion publicacionT = new TipoPublicacion(nombre, descripcion, exposicion, duracion, costo, fechaPub);
		
	} 

	public Keyword obtenerKeywords(String nomKeyword) 
	{ 
		try { 
		ManejadorSettings msetting = ManejadorSettings.getInstance(); 
		Keyword key = msetting.obtenerKeywords(nomKeyword); 
		return key; 
		}catch(KeywordNoExisteException e) 
		{ 
			System.out.println(e.getMessage()); 
			return null;
		} 
	} 

	public ArrayList<String> listarKeywords()
	{
		ManejadorSettings msetting = ManejadorSettings.getInstance(); 
		ArrayList<String> lkeyword = msetting.listarKeywords(); 
		return lkeyword; 
	} 
	 
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname, String nomOferta) 
	{
		ManejadorUsuario musuario = ManejadorUsuario.getInstance();
		ManejadorOfertas mofertas = ManejadorOfertas.getInstance();
		
		try {
		Postulante postu = musuario.obtenerPostulante(nickname);
			try {
				OfertaLaboral oferpostu = mofertas.obtenerOfertaLaboral(nomOferta);
				}catch(OfertaLaboralNoExisteException e)
				{
					System.out.println(e.getMessage());
				}
			
				Postulacion postulacion = new Postulacion(motivacion, fechaPostulacion, cvReducido);
				postulacion.agregarOferta(nomOferta);
				postu.agregarPostulacion(postulacion);
		}catch(UsuarioNoExisteUsuarioException e)
		{
			System.out.println(e.getMessage());
		}
	} 
	 
	public ArrayList<String> listarPostulantes() 
	{ 
		ControladorUsuario cusuario = new ControladorUsuario();
		
		ArrayList<String> lpostulantes = cusuario.listarPostulantes();
		return lpostulantes;
	} 

	public DTOferta obtenerDtOfertaLaboral(String nomOferta) 
	{ 
		ManejadorOfertas mofertas = ManejadorOfertas.getInstance(); 
		try { 
			OfertaLaborl olaboral = mofertas.obtenerOfertaLaboral(nomOferta);
			DTOferta dtoferta = olaboral.obtenerDtOfertaLaboral();
			return dtoferta;
		}catch(DtOfertaNoExisteException e) 
		{ 
			System.out.println(e.getMessage()); 
			return null;
		} 
	} 

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) 
	{ 
		ManejadorUsuario musuario = ManejadorUsuario.getInstance();
		Empresa emp = musuario.obtenerEmpresa(nicknameEmpresa);
		ArrayList<OfertaLaboral> ofertas = emp.getOfertasLaborales();
		
		ArrayList<String> nomofertas = null;
		
		for (OfertaLaboral ofer : ofertas)
		{
			nomofertas.add(ofer.getNombre());
		}
		return nomofertas;
	} 

	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias, Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion) 
	{ 
		ManejadorPaquetes mpaquetes = ManejadorPaquetes.getInstance();
		PaquetePublicacion paquete = new PaquetePublicacion(nombre, descripcion, cantidadPublicaciones, periodoValDias, descuento, cantidadTipoPublicacion);
		mpaquetes.agregarElemento(paquete);
	} 
} 