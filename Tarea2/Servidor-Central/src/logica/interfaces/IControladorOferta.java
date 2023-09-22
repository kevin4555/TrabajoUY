package logica.interfaces;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralNoTienePaquete;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.EstadoOferta;
import logica.classes.CantidadTotalTipoPublicacion;
import logica.classes.CompraPaquete;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.TipoPublicacion;

public interface IControladorOferta {

	public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial, String horarioFinal,
			float remuneracion, String ciudad, String departamento, LocalDate fechaAlta, String nomTipoPublicacion,
			String nicknameEmpresa, ArrayList<String> listakeywords, BufferedImage imagen, String nombrePaquete)
			throws OfertaLaboralYaExisteException, TipoPublicacionNoExisteException, KeywordNoExisteException,
			UsuarioNoExisteException;

	public ArrayList<String> listarTipoDePublicaciones();

	public OfertaLaboral obtenerOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;

	public ArrayList<String> listarPaquetes();

	public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic)
			throws TipoPublicacionNoExisteException, TipoPublicacionYaExisteException;

	public Keyword obtenerKeywords(String nomKeyword) throws KeywordNoExisteException, TipoPublicacionNoExisteException;

	public void altaKeyword(String nomKeyword) throws KeywordYaExisteException;

	public ArrayList<String> listarKeywords();

	public DTOfertaLaboral obtenerDtOfertaLaboral(String nomOferta) throws OfertaLaboralNoExisteException;

	public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException;

	public void registrarPaquete(String nombre, String descripcion, int periodoValDias, float descuento,BufferedImage imagen, LocalDate fechaAlta,
			ArrayList<DTCantidadTipoPublicacion> cantidadTipoPublicacion) throws PaquetePublicacionYaExisteException,
			TipoPublicacionYaExisteException, TipoPublicacionNoExisteException;

	public void altaTipoPublicacion(String nombre, String descripcion, String exposicion, int duracion, float costo,
			LocalDate fechaPub) throws TipoPublicacionYaExisteException;

	public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion, String nomTipoPaquete)
			throws TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException;

	public PaquetePublicacion obtenerPaquetePublicacion(String string) throws PaquetePublicacionNoExisteException;

	public boolean estaPostulado(String postulante, String nomOfertaLaboral)
			throws UsuarioNoExisteException, OfertaLaboralNoExisteException;

	void aceptarRechazarOfertaLaboral(String nombreOferta, EstadoOferta estadoOferta, LocalDate fechaResolucion)	throws OfertaLaboralNoExisteException;

	public ArrayList<DTOfertaLaboral> obtenerDtOfertasConfirmadas();

	ArrayList<DTOfertaLaboral> obtenerDTOfertasPorKeyword(String keyword);
	
	public ArrayList<DTPostulacion> obtenerDtPostulacionesDeOferta(String nombreOferta) throws OfertaLaboralNoExisteException;
	
	public boolean estaCompradoPorPaqueteOferta (String nombreOferta) throws OfertaLaboralNoExisteException;
	
	public DTPaquetePublicacion obtenerDtPaquetePublicacion(String nombreOferta) throws OfertaLaboralNoExisteException, OfertaLaboralNoTienePaquete;
		
	public ArrayList<String> listarTipoPublicacionDePaquete(String nombrePaquete) throws PaquetePublicacionNoExisteException;

	ArrayList<String> obtenerKeywordsDeOfertaLaboral(String nomOfertaLab) throws OfertaLaboralNoExisteException;

	

	
	
	
	

}
