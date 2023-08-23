package logica.interfaces;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import excepciones.ColeccionTipoPublicacionEsVaciaException;
import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExiste;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;

public interface IControladorOferta {
	
	public void altaOfertaLaboral(String nombre, String descripcion, String horario, float remuneracion,
			String ciudad, String departamento, Date fechaAlta,
			TipoPublicacion tipoPublicacion) throws OfertaLaboralYaExisteException;

	public ArrayList<String> listarPostulantes();
	
	public ArrayList<String> listarTipoDePublicaciones();
	
	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;
	
	public void agregarTipoPublicacionAlPaquete(int cantIncluida);
	
	public ArrayList<String> listarPaquetes();
	
	public void confirmarAltaPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo, Date fechaPub);
	
	public void agregarKeywordEnOfertaLaboral(String nomKeyword, String nomOferta) throws KeywordNoExisteException, OfertaLaboralNoExisteException;
	
	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic) throws TipoPublicacionNoExiste;
	
	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException;
	
	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException;
	
	public ArrayList<String> listarKeywords();
	
	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname, String nomOferta);
	
	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta);
	
	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa);
	
	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias, Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion);

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo,
			Date fechaPub) throws TipoPublicacionYaExisteException;
}
