package logica.controllers;

import java.sql.Date;
import java.util.ArrayList;

import logica.interfaces.IControladorOferta;


public class ControladorOferta implements IControladorOferta {

	@Override
	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion,
			String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic,
			String nicknameEmpresa) {
	}
	

/*
	public ArrayList<String> listarTipoDePublicaciones() throws TipoPublicacionNoExisteException {
		ManejadorSettings ms = ManejadorSettings.getinstance();
		ArrayList<String> nomTposPublic = ms.listarTipoDePublicaciones();
		if (nomTposPublic != null) {
			return nomTposPublic;
		}
		else {
			throw new TipoPublicacionNoExisteException("No existen tipos de publicaciones registrados");
		}
	}/*

	public void altaOfertaLaboral(String nombre, String descrip, Date horaInicio, Date horaFin, double remuneracion, String ciudad, String departamento, Date fechaAlta, ArrayList<String> keywords, String nomTpoPublic, String nicknameEmpresa) {
		ControladorUsuario cu = new ControladorUsuario();
		Empresa emp = cu.obtenerEmpresa(nicknameEmpresa);
		
		ManejadorSettings ms = ManejadorSettings.getInstance();
		TipoPublicacion tp = ms.obtenerTipoPublicacion(nomTpoPublic);
		if (tp != null) {
			OfertaLaboral ol = new OfertaLaboral(nombre, descrip, ciudad, departamento, horaInicio, horaFin, remuneracion, fechaAlta);
			for (String kw: keywords) {
				Keyword key = ms.obtenerKeywords(kw);
				if (key != null) {
					ol.getKw().put(kw, key);
				}	
			}
			ol.setTp(tp);
			emp.agregarOferta(ol);
			//paso 8
		}
	}
	
	public OfertaLaboral obtenerOfertaLaboral(String nomOferta)
	{
		ManejadorOfertas mo = ManejadorOfertas.getInstance();
		try {
			OfertaLaboral ol = mo.obtenerOfertaLaboral(nomOferta);
			return ol;
		}catch(OfertalLaboralNoExisteException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public DTOferta obtenerDTOfertaLaboral(String nomOferta)
	{
		ManejadorOfertas mo = ManejadorOfertas.getInstance();
		try {
			OfertaLaboral ol = mo.find(nomOferta);
		}catch(DtOfertaNoExisteException e)
		{
			System.out.println(e.getMessage());
		}
		DTOferta dtof = new DTOferta(ol.getNombre(), ol.getDescripcion(), ol.getCiudad(), ol.getDepartamento(), ol.getHoraInicio(), ol.getHoraFin(), ol.getRemunaracion(), ol.getFechaAlta());

	}
	
	public void agregarTipoPublicacionAlPaquete(int cantIncluida)
	{
		
	}
	
	public ArrayList<String> listarPaquetes()
	{
		ArrayList<String> paquetes = new ArrayList<>();
		ManejadorPaquetes mp = ManejadorPaquetes.getInstance();
		paquetes = mp.obtenerPaquetes();
		return paquetes;
	}

	
	public void confirmarAltaPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo, Date fechaPub)
	{
		
	}
	
	public Keyword obtenerKeyword(String nomKeyword)
	{
		ManejadorSetting ms = ManejadorSetting.getInstance();
		try {
		Keyword k = ms.find(nomKeyword);
		return k;
		}catch()
		{
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<String> listarKeywords()
	{
		ManejadorSetting ms = ManejadorSetting.getInstance();
		ArrayList<String> lkeyword = ms.listarKeywords();
		if(lkeyword != null)
		{
			return lkeyword;
		}
		else
		{
			throw new KeywordsNoExistenKeywords("No hay keyword para listar");
		}
	}
	
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname, String nomOferta)
	{
	
	}
	
	public ArrayList<String> listarPostulantes()
	{
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		ArrayList<String> lpostulantes = mu.listarPostulantes();
		if(lpostulantes != null)
		{
			return lpostulantes;
		}
		else
		{
			throw new PostulantesNoExistenPostulantes("No existen postulantes a listar");
		}
	}
	
	public DTOferta obtenerDTofertaLaboral(String nomOferta)
	{
		return null;
	}
	
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa)
	{
		return null;
	}
	
	public void registrarPaquete(String nombre, String descripcion, int periodoValDias, Float descuento, Date fechaAlta)
	{
		*/
	}
