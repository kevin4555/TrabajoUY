package logica.controllers; 

import java.sql.Date; 
import java.util.ArrayList;

import excepciones.DtOfertaNoExisteException;
import excepciones.KeywordNoExisteException;  
import excepciones.OfertaLaboralNoExisteException;
import logica.DataTypes.DTOferta;
import logica.DataTypes.DTPaquete;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
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

	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion, String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic, String nicknameEmpresa) { 
		ControladorUsuario cusuario = new ControladorUsuario(); 
		Empresa emp = cusuario.obtenerEmpresa(nicknameEmpresa);
		ManejadorSettings ms = ManejadorSettings.getInstance(); 

		TipoPublicacion tpublicacion = ms.obtenerTipoPublicacion(nomTpoPublic); 

		if (tpublicacion != null) { 

			OfertaLaboral olaboral = new OfertaLaboral(nombre, descrip, ciudad, departamento, horaInicio, horaFin, remuneracion, fechaAlta); 

			for (String kw: keywords) { 

				Keyword key = ms.obtenerKeywords(kw); 

				if (key != null) { 

					olaboral.getKw().put(kw, key); 

				}	 

			} 

			olaboral.setTp(tpublicacion); 

			emp.agregarOferta(olaboral); 

			//paso 8 

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
		return null; 
	} 

	public void registrarPaquete(String nombre, String descripcion, int periodoValDias, Float descuento, Date fechaAlta) 
	{ 
		
	} 
} 