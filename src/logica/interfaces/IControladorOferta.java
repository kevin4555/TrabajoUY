package logica.interfaces;

import java.util.ArrayList;
import java.util.Date;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import logica.DataTypes.DTOfertaLaboral;
import logica.classes.CantidadTipoPublicacion;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;

public interface IControladorOferta {

	public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial, String horarioFinal,
			float remuneracion, String ciudad, String departamento, Date fechaAlta, TipoPublicacion tipoPublicacion,
			Empresa empresa, CompraPaquete compraPaquete) throws Exception;

	public ArrayList<String> listarPostulantes();

	public PaquetePublicacion obtenerPaquetePublicacion(String nombrePaquete)
			throws PaquetePublicacionNoExisteException;

	public ArrayList<String> listarTipoDePublicaciones();

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;

	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete);

	public ArrayList<String> listarPaquetes();

	public void agregarKeywordEnOfertaLaboral(String nomKeyword, String nomOferta)
			throws KeywordNoExisteException, OfertaLaboralNoExisteException, TipoPublicacionNoExisteException;

	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic)
			throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException;

	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException;

	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException;

	public ArrayList<String> listarKeywords();

	public void registrarPostulacion(String cvReducido, String motivacion, Date fechaPostulacion, String nickname,
			String nomOferta);

	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta);

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa);

	public void registrarPaquete(String nombre, String descripcion, int cantidadPublicaciones, int periodoValDias,
			Float descuento, Date fechaAlta, ArrayList<CantidadTipoPublicacion> cantidadTipoPublicacion);

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, Float costo,
			Date fechaPub) throws TipoPublicacionYaExisteException;
}
