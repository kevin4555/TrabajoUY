package logica.interfaces;

import java.util.ArrayList;
import java.util.Date;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.TipoPublicacion;

public interface IControladorOferta {

	public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial, String horarioFinal,
			float remuneracion, String ciudad, String departamento, Date fechaAlta, TipoPublicacion tipoPublicacion)
			throws OfertaLaboralYaExisteException, OfertaLaboralNoExisteException;

	public ArrayList<String> listarPostulantes();

	public ArrayList<String> listarTipoDePublicaciones();

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;
	public ArrayList<String> listarPaquetes();

	public void agregarKeywordEnOfertaLaboral(ArrayList<String> listaKeyword, String nomOferta)
			throws KeywordNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException;

	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic)
			throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException;

	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException, TipoPublicacionNoExisteException;

	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException, TipoPublicacionYaExisteException;

	public ArrayList<String> listarKeywords();

	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta) throws UsuarioNoExisteException, OfertaLaboralNoExisteException;

	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	public void registrarPaquete(String nombre, String descripcion, int periodoValDias, float descuento, Date fechaAlta,
			ArrayList<DTCantidadTipoPublicacion> cantidadTipoPublicacion) throws PaquetePublicacionYaExisteException,
			TipoPublicacionYaExisteException, TipoPublicacionNoExisteException;

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, float costo,
			Date fechaPub) throws TipoPublicacionYaExisteException;

	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete) throws TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException;

}
