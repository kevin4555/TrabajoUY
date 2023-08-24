package logica.interfaces;

import java.util.Date;
import java.util.ArrayList;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;

public interface IControladorOferta {
	
	public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial, String horarioFinal, float remuneracion, String ciudad, String departamento, Date fechaAlta, TipoPublicacion tipoPublicacion) throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException;

	public ArrayList<String> listarPostulantes();
	
	public ArrayList<String> listarTipoDePublicaciones();
	
	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;
	
	public void agregarTipoPublicacionAlPaquete(int cantIncluida);
	
	public ArrayList<String> listarPaquetes();
	
	public void confirmarAltaPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo, Date fechaPub);
	
	public void agregarKeywordEnOfertaLaboral(String nomKeyword, String nomOferta) throws KeywordNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException;
	
	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic) throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException;
	
	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException, TipoPublicacionNoExisteException;
	
	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException, TipoPublicacionYaExisteException;
	
	public ArrayList<String> listarKeywords();
	
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname, String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException;
	
	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;
	
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;
	
	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias, Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion) throws PaquetePublicacionYaExisteException;

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo,
			Date fechaPub) throws TipoPublicacionYaExisteException;
}
