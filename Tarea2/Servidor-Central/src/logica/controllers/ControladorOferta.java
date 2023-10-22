package logica.controllers;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralNoTienePaquete;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.PaquetePublicacionYaFueComprado;
import excepciones.TipoDePublicacionYaFueIngresado;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioNoExisteException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import logica.classes.CantidadTotalTipoPublicacion;
import logica.classes.Empresa;
import logica.classes.Keyword;
import logica.classes.OfertaLaboral;
import logica.classes.PaquetePublicacion;
import logica.classes.Postulante;
import logica.classes.TipoPublicacion;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtcantidadTipoPublicacion;
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

public class ControladorOferta
    implements IcontroladorOferta {
  
  @Override
  public List<String> listarTipoDePublicaciones() {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    List<String> nombreTiposPublicacion = manejadorSettings
        .listarTipoDePublicaciones();
    return nombreTiposPublicacion;
  }
  
  @Override
  public void altaOfertaLaboral(String nombre,
      String descripcion, String horarioInicial,
      String horarioFinal, float remuneracion,
      String ciudad, String departamento,
      LocalDate fechaAlta, String nomTipoPublicacion,
      String nicknameEmpresa, List<String> listakeywords,
      BufferedImage imagen, String nombrePaquete)
      throws OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException,
      KeywordNoExisteException, UsuarioNoExisteException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas
        .getInstance();
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    Fabrica fabrica = Fabrica.getInstance();
    IcontroladorUsuario contUsuario = fabrica
        .obtenerControladorUsuario();
    Empresa empresa = contUsuario
        .obtenerEmpresa(nicknameEmpresa);
    
    OfertaLaboral ofertaLaboral = new OfertaLaboral(nombre,
        descripcion, horarioInicial, horarioFinal,
        remuneracion, ciudad, departamento, fechaAlta,
        manejadorSettings.obtenerTipoPublicacion(
            nomTipoPublicacion),
        imagen, empresa);
    manejadorOfertas.agregarOferta(ofertaLaboral);
    for (int i = 0; i < listakeywords.size(); i++) {
      ofertaLaboral.agregarKeyword(manejadorSettings
          .obtenerKeyword(listakeywords.get(i)));
    }
    empresa.agregarOferta(ofertaLaboral);
    if (nombrePaquete != null) {
      empresa.comprarOfertaPorPaquete(nombrePaquete,
          nomTipoPublicacion, ofertaLaboral);
      
    }
    
  }
  
  @Override
  public OfertaLaboral obtenerOfertaLaboral(
      String nomOferta)
      throws OfertaLaboralNoExisteException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas
        .getInstance();
    OfertaLaboral ofertaLaboral = manejadorOfertas
        .obtenerOfertaLaboral(nomOferta);
    return ofertaLaboral;
  }
  
  @Override
  public void agregarTipoPublicacionAlPaquete(
      int cantIncluida, String nomTipoPublicacion,
      String nomTipoPaquete)
      throws TipoPublicacionNoExisteException,
      PaquetePublicacionNoExisteException,
      PaquetePublicacionYaFueComprado,
      TipoDePublicacionYaFueIngresado {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes
        .getInstance();
    
    TipoPublicacion tipoPublicacion;
    tipoPublicacion = manejadorSettings
        .obtenerTipoPublicacion(nomTipoPublicacion);
    PaquetePublicacion paquetePublicacion = manejadorPaquetes
        .obtenerPaquete(nomTipoPaquete);
    
    paquetePublicacion.agregarTipoPublicacion(
        tipoPublicacion, cantIncluida);
  }
  
  @Override
  public List<String> listarPaquetes() {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes
        .getInstance();
    return manejadorPaquetes.listarPaquetes();
  }
  
  @Override
  public void altaTipoPublicacion(String nombre,
      String descripcion, String exposicion, int duracion,
      float costo, LocalDate fechaPub)
      throws TipoPublicacionYaExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    TipoPublicacion tipoPublicacion = new TipoPublicacion(
        nombre, descripcion, exposicion, duracion, costo,
        fechaPub);
    manejadorSettings.addTipoPublicacion(tipoPublicacion);
  }
  
  @Override
  public TipoPublicacion obtenerTipoPublicacion(
      String nomTpoPublic)
      throws TipoPublicacionNoExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    TipoPublicacion tpoPublic = manejadorSettings
        .obtenerTipoPublicacion(nomTpoPublic);
    return tpoPublic;
  }
  
  @Override
  public void altaKeyword(String nomKeyword)
      throws KeywordYaExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    Keyword keyword = new Keyword(nomKeyword);
    manejadorSettings.addKeyword(keyword);
  }
  
  @Override
  public Keyword obtenerKeywords(String nomKeyword)
      throws KeywordNoExisteException,
      TipoPublicacionNoExisteException {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    Keyword keyword = manejadorSettings
        .obtenerKeyword(nomKeyword);
    return keyword;
  }
  
  @Override
  public List<String> listarKeywords() {
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    List<String> listKeywords = manejadorSettings
        .listarKeywords();
    return listKeywords;
  }
  
  @Override
  public DtOfertaLaboral obtenerDtOfertaLaboral(
      String nomOferta)
      throws OfertaLaboralNoExisteException, IOException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas
        .getInstance();
    
    OfertaLaboral ofertaLaboral = manejadorOfertas
        .obtenerOfertaLaboral(nomOferta);
    DtOfertaLaboral dtOfertaLaboral = ofertaLaboral
        .obtenerDtOfertaLaboral();
    return dtOfertaLaboral;
  }
  
  @Override
  public List<String> obtenerOfertasEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException {
    ManejadorUsuario manejadorUsuario = ManejadorUsuario
        .getInstance();
    
    Empresa empresa = manejadorUsuario
        .obtenerEmpresa(nicknameEmpresa);
    List<OfertaLaboral> ofertas = empresa
        .getOfertasLaborales();
    
    List<String> nombreOfertas = new ArrayList<String>();
    for (OfertaLaboral ofertaLaboral : ofertas) {
      nombreOfertas.add(ofertaLaboral.getNombre());
    }
    return nombreOfertas;
  }
  
  @Override
  public void registrarPaquete(String nombre,
      String descripcion, int periodoValDias,
      float descuento, BufferedImage imagen,
      LocalDate fechaAlta,
      List<DtcantidadTipoPublicacion> cantidadTipoPublicacion)
      throws PaquetePublicacionYaExisteException,
      TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes
        .getInstance();
    ManejadorSettings manejadorSettings = ManejadorSettings
        .getInstance();
    List<CantidadTotalTipoPublicacion> arrayCantidad = 
        new ArrayList<CantidadTotalTipoPublicacion>();
    
    if (cantidadTipoPublicacion != null) {
      for (DtcantidadTipoPublicacion dtCantidad : cantidadTipoPublicacion) {
        TipoPublicacion publicacionParticularPublicacion = manejadorSettings
            .obtenerTipoPublicacion(
                dtCantidad.getNombreTipoPublicacion());
        CantidadTotalTipoPublicacion nuevoTipo = new CantidadTotalTipoPublicacion(
            dtCantidad.getCantidad(),
            publicacionParticularPublicacion);
        arrayCantidad.add(nuevoTipo);
      }
    }
    
    PaquetePublicacion paquetePublicacion = new PaquetePublicacion(
        nombre, descripcion, periodoValDias, descuento,
        imagen, arrayCantidad, fechaAlta);
    
    manejadorPaquetes.agregarPaquete(paquetePublicacion);
  }
  
  @Override
  public PaquetePublicacion obtenerPaquetePublicacion(
      String nombre)
      throws PaquetePublicacionNoExisteException {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes
        .getInstance();
    PaquetePublicacion paquetePublicacion = manejadorPaquetes
        .obtenerPaquete(nombre);
    return paquetePublicacion;
  }
  
  @Override
  public boolean estaPostulado(String postulante,
      String nomOfertaLaboral)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException {
    IcontroladorUsuario controladorUsuario = Fabrica
        .getInstance().obtenerControladorUsuario();
    Postulante post = controladorUsuario
        .obtenerPostulante(postulante);
    return post.listarNombreOfertasUsuario()
        .contains(nomOfertaLaboral);
  }
  
  @Override
  public List<String> obtenerKeywordsDeOfertaLaboral(
      String nomOfertaLab)
      throws OfertaLaboralNoExisteException {
    ManejadorOfertas manejadorOfertas = ManejadorOfertas
        .getInstance();
    OfertaLaboral ofertaLaboral = manejadorOfertas
        .obtenerOfertaLaboral(nomOfertaLab);
    List<String> listaKeywords = new ArrayList<String>();
    for (int i = 0; i < ofertaLaboral.getKeywords()
        .size(); i++) {
      listaKeywords.add(
          ofertaLaboral.getKeywords().get(i).getNombre());
    }
    return listaKeywords;
  }
  
  @Override
  public void aceptarRechazarOfertaLaboral(
      String nombreOferta, EstadoOferta estadoOferta,
      LocalDate fechaResolucion)
      throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
        .obtenerOfertaLaboral(nombreOferta);
    oferta.resolucionOferta(estadoOferta, fechaResolucion);
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerDtOfertasConfirmadas()
      throws IOException {
    return ManejadorOfertas.getInstance()
        .obtenerDtofertasConfirmadas();
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerDtofertasPorKeyword(
      String keyword) throws IOException {
    return ManejadorOfertas.getInstance()
        .obtenerDtofertasPorKeyword(keyword);
  }
  
  @Override
  public List<Dtpostulacion> obtenerDtPostulacionesDeOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
        .obtenerOfertaLaboral(nombreOferta);
    return oferta.obtenerDtPostulacion();
    
  }
  
  @Override
  public boolean estaCompradoPorPaqueteOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
        .obtenerOfertaLaboral(nombreOferta);
    return oferta.estaCompradaPorPaquete();
  }
  
  @Override
  public DtpaquetePublicacion obtenerDtPaquetePublicacion(
      String nombreOferta)
      throws OfertaLaboralNoExisteException,
      OfertaLaboralNoTienePaquete, IOException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
        .obtenerOfertaLaboral(nombreOferta);
    if (oferta.estaCompradaPorPaquete()) {
      return oferta.obtenerDtpaquete();
    } else {
      throw new OfertaLaboralNoTienePaquete("La oferta: "
          + nombreOferta + " no fue comprada por paquete");
    }
  }
  
  @Override
  public List<String> listarTipoPublicacionDePaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    PaquetePublicacion paquete = ManejadorPaquetes
        .getInstance().obtenerPaquete(nombrePaquete);
    return paquete.obtenerNombresTipoPublicaciones();
  }
  
  @Override
  public DtpaquetePublicacion obtenerDtpaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException,
      IOException {
    PaquetePublicacion paquete = ManejadorPaquetes
        .getInstance().obtenerPaquete(nombrePaquete);
    return paquete.obtenerDtPaquete();
  }
  
  @Override
  public DttipoPublicacion obtenerDttipoPublicacion(
      String nombreTipo)
      throws TipoPublicacionNoExisteException {
    TipoPublicacion tipoPublicacion = ManejadorSettings
        .getInstance().obtenerTipoPublicacion(nombreTipo);
    return tipoPublicacion.obtenerDttipoPublicacion();
  }
  
  @Override
  public List<String> listarPaquetesNoComprados() {
    return ManejadorPaquetes.getInstance()
        .listarPaquetesNoComprados();
  }
  
  @Override
  public List<DtpaquetePublicacion> listarDtpaquetes()
      throws IOException {
    return ManejadorPaquetes.getInstance()
        .listarDtpaquetes();
  }
  
  @Override
  public Boolean estaCompradoPaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    PaquetePublicacion paquete = ManejadorPaquetes
        .getInstance().obtenerPaquete(nombrePaquete);
    return paquete.getEstaComprado();
  }
  
  @Override
  public Boolean existeOfertaLaboral(String nombreOferta) {
    return ManejadorOfertas.getInstance().existeOfertaLaboral(nombreOferta);
  }
  
  @Override
  public void agregarVisitaOferta(String nombreOferta) throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance().obtenerOfertaLaboral(nombreOferta);
    oferta.agregarVisita();
  }
  
  @Override
  public List<DtOfertaLaboral> obtenerOfertasMasVisitadas() throws IOException {
    ArrayList<DtOfertaLaboral> ofertas = (ArrayList<DtOfertaLaboral>) ManejadorOfertas
        .getInstance().obtenerDtOfertas();
    Comparator<DtOfertaLaboral> comparador = (oferta1,
        oferta2) -> (int) (oferta1.getVisitas() - oferta2.getVisitas());
    ofertas.sort(Collections.reverseOrder(comparador));
    // ofertas.sort(comparador.reversed()); puede ser otra opcion hay que probar
    // cual anda
    return ofertas;
  }
  
  @Override
  public List<DtOfertaLaboral> buscarOfertas(String parametro) throws IOException {
    List<DtOfertaLaboral> resultado = new ArrayList<DtOfertaLaboral>();
    ArrayList<DtOfertaLaboral> ofertas = (ArrayList<DtOfertaLaboral>) ManejadorOfertas
        .getInstance().obtenerDtofertasConfirmadas();
    for (DtOfertaLaboral oferta : ofertas) {
      if ((oferta.getNombre().contains(parametro)
          || oferta.getDescripcion().contains(parametro)) && !oferta.getEstaVencida()) {
        resultado.add(oferta);
      }
    }
    Comparator<DtOfertaLaboral> comparadorExposicion = (oferta1, oferta2) -> oferta1
        .getExposicion().compareTo(oferta2.getExposicion());
    Comparator<DtOfertaLaboral> comparadorFechan = (oferta1, oferta2) -> oferta1.getFechaAlta()
        .compareTo(oferta2.getFechaAlta());
    Collections.sort(resultado,
        comparadorExposicion.thenComparing(comparadorFechan.reversed()));
    return resultado;
  }
  
}
