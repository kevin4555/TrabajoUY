package main.java.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import main.java.classes.Keyword;
import main.java.classes.OfertaLaboral;
import main.java.classes.PaquetePublicacion;
import main.java.classes.TipoPublicacion;
import main.java.datatypes.DtCantidadTipoPublicacion;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.DtPaquetePublicacion;
import main.java.datatypes.DtPostulacion;
import main.java.datatypes.DtTipoPublicacion;
import main.java.datatypes.EstadoOferta;
import main.java.excepciones.KeywordNoExisteException;
import main.java.excepciones.KeywordYaExisteException;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.OfertaLaboralNoSePuedeFinalizar;
import main.java.excepciones.OfertaLaboralNoTienePaquete;
import main.java.excepciones.OfertaLaboralYaExisteException;
import main.java.excepciones.PaquetePublicacionNoExisteException;
import main.java.excepciones.PaquetePublicacionYaExisteException;
import main.java.excepciones.PaquetePublicacionYaFueComprado;
import main.java.excepciones.TipoDePublicacionYaFueIngresado;
import main.java.excepciones.TipoPublicacionNoExisteException;
import main.java.excepciones.TipoPublicacionYaExisteException;
import main.java.excepciones.UsuarioNoExisteException;

/**
 * Interface ControladorOferta .
 */

public interface IcontroladorOferta {

  public void altaOfertaLaboral(String nombre,
        String descripcion, String horarioInicial,
        String horarioFinal, float remuneracion,
        String ciudad, String departamento,
        LocalDate fechaAlta, String nomTipoPublicacion,
        String nicknameEmpresa, List<String> listakeywords,
        BufferedImage imagen, String nombrePaquete)
        throws OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException;

  public List<String> listarTipoDePublicaciones();

  public OfertaLaboral obtenerOfertaLaboral(
        String nomOferta)
        throws OfertaLaboralNoExisteException;

  public List<String> listarPaquetes();

  public TipoPublicacion obtenerTipoPublicacion(
        String nomTpoPublic)
        throws TipoPublicacionNoExisteException,
        TipoPublicacionYaExisteException;

  public Keyword obtenerKeywords(String nomKeyword)
        throws KeywordNoExisteException,
        TipoPublicacionNoExisteException;

  public void altaKeyword(String nomKeyword)
        throws KeywordYaExisteException;

  public List<String> listarKeywords();

  public DtOfertaLaboral obtenerDtOfertaLaboral(
        String nomOferta)
        throws OfertaLaboralNoExisteException, IOException;

  public List<String> obtenerOfertasEmpresa(
        String nicknameEmpresa)
        throws UsuarioNoExisteException;

  public void registrarPaquete(String nombre,
        String descripcion, int periodoValDias,
        float descuento, BufferedImage imagen,
        LocalDate fechaAlta,
        List<DtCantidadTipoPublicacion> cantidadTipoPublicacion)
        throws PaquetePublicacionYaExisteException,
        TipoPublicacionYaExisteException,
        TipoPublicacionNoExisteException;

  public void altaTipoPublicacion(String nombre,
        String descripcion, String exposicion, int duracion,
        float costo, LocalDate fechaPub)
        throws TipoPublicacionYaExisteException;

  public void agregarTipoPublicacionAlPaquete(
        int cantIncluida, String nomTipoPublicacion,
        String nomTipoPaquete)
        throws TipoPublicacionNoExisteException,
        PaquetePublicacionNoExisteException,
        PaquetePublicacionYaFueComprado,
        TipoDePublicacionYaFueIngresado;

  public PaquetePublicacion obtenerPaquetePublicacion(
        String string)
        throws PaquetePublicacionNoExisteException;

  public boolean estaPostulado(String postulante,
        String nomOfertaLaboral)
        throws UsuarioNoExisteException,
        OfertaLaboralNoExisteException;

  void aceptarRechazarOfertaLaboral(String nombreOferta,
        EstadoOferta estadoOferta,
        LocalDate fechaResolucion)
        throws OfertaLaboralNoExisteException;

  public List<DtOfertaLaboral> obtenerDtOfertasConfirmadas()
        throws IOException;

  List<DtOfertaLaboral> obtenerDtOfertasPorKeyword(
        String keyword) throws IOException;

  public List<DtPostulacion> obtenerDtPostulacionesDeOferta(
        String nombreOferta)
        throws OfertaLaboralNoExisteException;

  public boolean estaCompradoPorPaqueteOferta(
        String nombreOferta)
        throws OfertaLaboralNoExisteException;

  public DtPaquetePublicacion obtenerDtPaquetePublicacion(
        String nombreOferta)
        throws OfertaLaboralNoExisteException,
        OfertaLaboralNoTienePaquete, IOException;

  public List<String> listarTipoPublicacionDePaquete(
        String nombrePaquete)
        throws PaquetePublicacionNoExisteException;

  List<String> obtenerKeywordsDeOfertaLaboral(
        String nomOfertaLab)
        throws OfertaLaboralNoExisteException;

  DtPaquetePublicacion obtenerDtPaquete(
        String nombrePaquete)
        throws PaquetePublicacionNoExisteException,
        IOException;

  DtTipoPublicacion obtenerDtTipoPublicacion(
        String nombreTipo)
        throws TipoPublicacionNoExisteException;

  List<String> listarPaquetesNoComprados();

  List<DtPaquetePublicacion> listarDtPaquetes()
        throws IOException;

  Boolean estaCompradoPaquete(String nombrePaquete)
        throws PaquetePublicacionNoExisteException;

  Boolean existeOfertaLaboral(String nombreOferta);

  void agregarVisitaOferta(String nombreOferta)
        throws OfertaLaboralNoExisteException;

  List<DtOfertaLaboral> obtenerOfertasMasVisitadas()
        throws IOException;

  List<DtOfertaLaboral> buscarOfertas(String parametro)
        throws IOException;

  void finalizarOferta(String nombreOferta)
        throws OfertaLaboralNoExisteException,
        OfertaLaboralNoSePuedeFinalizar;

  void ordenarPostulaciones(String nombreOferta,
        List<String> nicknamesPostulantes)
        throws OfertaLaboralNoExisteException;

}
