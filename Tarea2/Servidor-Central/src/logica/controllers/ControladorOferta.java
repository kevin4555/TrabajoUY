package logica.controllers;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralNoTienePaquete;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.classes.CantidadTotalTipoPublicacion;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.datatypes.DtcantidadTipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.DttipoPublicacion;
import logica.datatypes.EstadoOferta;
import logica.handlers.ManejadorOfertas;
import logica.handlers.ManejadorPaquetes;
import logica.handlers.ManejadorSettings;
import logica.handlers.ManejadorUsuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Clase controlador oferta.
 */

public class ControladorOferta implements IcontroladorOferta {
  
  @Override
  public ArrayList<String> listarTipoDePublicaciones() {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    ArrayList<String> nombreTiposPublicacion = manejadorSettings.listarTipoDePublicaciones();
    return nombreTiposPublicacion;
  }
  
  @Override
  public void altaOfertaLaboral(String nombre, String descripcion, String horarioInicial,
      String horarioFinal, float remuneracion, String ciudad, String departamento,
      LocalDate fechaAlta, String nomTipoPublicacion, String nicknameEmpresa,
      ArrayList<String> listakeywords, BufferedImage imagen, String nombrePaquete)
      throws OfertaLaboralYaExisteException, TipoPublicacionNoExisteException,
      KeywordNoExisteException, UsuarioNoExisteException {
    
    ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    Fabrica fabrica = Fabrica.getInstance();
    IcontroladorUsuario contUsuario = fabrica.obtenerControladorUsuario();
    
    OfertaLaboral ofertaLaboral = new OfertaLaboral(nombre, descripcion, horarioInicial,
        horarioFinal, remuneracion, ciudad, departamento, fechaAlta,
        manejadorSettings.obtenerTipoPublicacion(nomTipoPublicacion), imagen);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    for (int i = 0; i < listakeywords.size(); i++) {
      ofertaLaboral.agregarKeyword(manejadorSettings.obtenerKeyword(listakeywords.get(i)));
    }
    
    Empresa empresa = contUsuario.obtenerEmpresa(nicknameEmpresa);
    empresa.agregarOferta(ofertaLaboral);
    if (nombrePaquete != null) {
      empresa.comprarOfertaPorPaquete(nombrePaquete, nomTipoPublicacion, ofertaLaboral);
      
    }
    
  }
  
  @Override
  public OfertaLaboral obtenerOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
    OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
    return ofertaLaboral;
  }
  
  @Override
  public void agregarTipoPublicacionAlPaquete(int cantIncluida, String nomTipoPublicacion,
      String nomTipoPaquete)
      throws TipoPublicacionNoExisteException, PaquetePublicacionNoExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
    
    TipoPublicacion tipoPublicacion;
    tipoPublicacion = manejadorSettings.obtenerTipoPublicacion(nomTipoPublicacion);
    PaquetePublicacion paquetePublicacion = manejadorPaquetes.obtenerPaquete(nomTipoPaquete);
    
    paquetePublicacion.crearCantidadTipoPublicacion(cantIncluida, tipoPublicacion);
  }
  
  @Override
  public ArrayList<String> listarPaquetes() {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
    return manejadorPaquetes.listarPaquetes();
  }
  
  @Override
  public void altaTipoPublicacion(String nombre, String descripcion, String exposicion,
      int duracion, float costo, LocalDate fechaPub) throws TipoPublicacionYaExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    TipoPublicacion tipoPublicacion = new TipoPublicacion(nombre, descripcion, exposicion,
        duracion, costo, fechaPub);
    manejadorSettings.addTipoPublicacion(tipoPublicacion);
  }
  
  @Override
  public TipoPublicacion obtenerTipoPublicacion(String nomTpoPublic)
      throws TipoPublicacionNoExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    TipoPublicacion tpoPublic = manejadorSettings.obtenerTipoPublicacion(nomTpoPublic);
    return tpoPublic;
  }
  
  @Override
  public void altaKeyword(String nomKeyword) throws KeywordYaExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    Keyword keyword = new Keyword(nomKeyword);
    manejadorSettings.addKeyword(keyword);
  }
  
  @Override
  public Keyword obtenerKeywords(String nomKeyword)
      throws KeywordNoExisteException, TipoPublicacionNoExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    Keyword keyword = manejadorSettings.obtenerKeyword(nomKeyword);
    return keyword;
  }
  
  @Override
  public ArrayList<String> listarKeywords() {
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    ArrayList<String> listKeywords = manejadorSettings.listarKeywords();
    return listKeywords;
  }
  
  @Override
  public DtOfertaLaboral obtenerDtOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
    
    OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOferta);
    DtOfertaLaboral dtOfertaLaboral = ofertaLaboral.obtenerDtofertaLaboral();
    return dtOfertaLaboral;
  }
  
  @Override
  public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
    
    Empresa empresa = manejadorUsuario.obtenerEmpresa(nicknameEmpresa);
    ArrayList<OfertaLaboral> ofertas = empresa.getOfertasLaborales();
    
    ArrayList<String> nombreOfertas = new ArrayList<String>();
    for (OfertaLaboral ofertaLaboral : ofertas) {
      nombreOfertas.add(ofertaLaboral.getNombre());
    }
    return nombreOfertas;
  }
  
  @Override
  public void registrarPaquete(String nombre, String descripcion, int periodoValDias,
      float descuento, BufferedImage imagen, LocalDate fechaAlta,
      ArrayList<DtcantidadTipoPublicacion> cantidadTipoPublicacion)
      throws PaquetePublicacionYaExisteException, TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
    ManejadorSettings manejadorSettings = ManejadorSettings.getInstance();
    ArrayList<CantidadTotalTipoPublicacion> arrayCantidad = 
        new ArrayList<CantidadTotalTipoPublicacion>();
    
    if (cantidadTipoPublicacion != null) {
      for (DtcantidadTipoPublicacion dtCantidad : cantidadTipoPublicacion) {
        TipoPublicacion publicacionParticularPublicacion = manejadorSettings
            .obtenerTipoPublicacion(dtCantidad.getNombreTipoPublicacion());
        CantidadTotalTipoPublicacion nuevoTipo = new CantidadTotalTipoPublicacion(
            dtCantidad.getCantidad(), publicacionParticularPublicacion);
        arrayCantidad.add(nuevoTipo);
      }
    }
    
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion(nombre, descripcion,
        periodoValDias, descuento, imagen, arrayCantidad, fechaAlta);
    
    manejadorPaquetes.agregarPaquete(paquetePublicacion);
  }
  
  @Override
  public PaquetePublicacion obtenerPaquetePublicacion(String nombre)
      throws PaquetePublicacionNoExisteException {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes.getInstance();
    PaquetePublicacion paquetePublicacion = manejadorPaquetes.obtenerPaquete(nombre);
    return paquetePublicacion;
  }
  
  @Override
  public boolean estaPostulado(String postulante, String nomOfertaLaboral)
      throws UsuarioNoExisteException, OfertaLaboralNoExisteException {
    IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    Postulante post = controladorUsuario.obtenerPostulante(postulante);
    return post.listarOfertasUsuario().contains(nomOfertaLaboral);
  }
  
  @Override
  public ArrayList<String> obtenerKeywordsDeOfertaLaboral(String nomOfertaLab)
      throws OfertaLaboralNoExisteException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas.getInstance();
    OfertaLaboral ofertaLaboral = manejadorOfertas.obtenerOfertaLaboral(nomOfertaLab);
    ArrayList<String> listaKeywords = new ArrayList<String>();
    for (int i = 0; i < ofertaLaboral.getKeywords().size(); i++) {
      listaKeywords.add(ofertaLaboral.getKeywords().get(i).getNombre());
    }
    return listaKeywords;
  }
  
  @Override
  public void aceptarRechazarOfertaLaboral(String nombreOferta, EstadoOferta estadoOferta,
      LocalDate fechaResolucion) throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance().obtenerOfertaLaboral(nombreOferta);
    oferta.resolucionOferta(estadoOferta, fechaResolucion);
  }
  
  @Override
  public ArrayList<DtOfertaLaboral> obtenerDtOfertasConfirmadas() {
    return ManejadorOfertas.getInstance().obtenerDtofertasConfirmadas();
  }
  
  @Override
  public ArrayList<DtOfertaLaboral> obtenerDtofertasPorKeyword(String keyword) {
    return ManejadorOfertas.getInstance().obtenerDtofertasPorKeyword(keyword);
  }
  
  @Override
  public ArrayList<Dtpostulacion> obtenerDtPostulacionesDeOferta(String nombreOferta)
      throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance().obtenerOfertaLaboral(nombreOferta);
    return oferta.obtenerDTPostulacion();
    
  }
  
  @Override
  public boolean estaCompradoPorPaqueteOferta(String nombreOferta)
      throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance().obtenerOfertaLaboral(nombreOferta);
    return oferta.estaCompradaPorPaquete();
  }
  
  @Override
  public DtpaquetePublicacion obtenerDtPaquetePublicacion(String nombreOferta)
      throws OfertaLaboralNoExisteException, OfertaLaboralNoTienePaquete {
    OfertaLaboral oferta = ManejadorOfertas.getInstance().obtenerOfertaLaboral(nombreOferta);
    if (oferta.estaCompradaPorPaquete()) {
      return oferta.obtenerDtpaquete();
    } else {
      throw new OfertaLaboralNoTienePaquete(
          "La oferta:" + nombreOferta + "no fue comprada por paquete");
    }
  }
  
  @Override
  public ArrayList<String> listarTipoPublicacionDePaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    PaquetePublicacion paquete = ManejadorPaquetes.getInstance().obtenerPaquete(nombrePaquete);
    return paquete.obtenerNombresTipoPublicaciones();
  }
  
  @Override
  public DtpaquetePublicacion obtenerDtpaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    PaquetePublicacion paquete = ManejadorPaquetes.getInstance().obtenerPaquete(nombrePaquete);
    return paquete.obtenerDTPaquete();
  }
  
  @Override
  public DttipoPublicacion obtenerDttipoPublicacion(String nombreTipo)
      throws TipoPublicacionNoExisteException {
    TipoPublicacion tipoPublicacion = ManejadorSettings.getInstance()
        .obtenerTipoPublicacion(nombreTipo);
    return tipoPublicacion.obtenerDttipoPublicacion();
  }
  
  @Override
  public ArrayList<String> listarPaquetesNoComprados() {
    return ManejadorPaquetes.getInstance().listarPaquetesNoComprados();
  }
  
  @Override
  public ArrayList<DtpaquetePublicacion> listarDtpaquetes() {
    return ManejadorPaquetes.getInstance().listarDtpaquetes();
  }
}
