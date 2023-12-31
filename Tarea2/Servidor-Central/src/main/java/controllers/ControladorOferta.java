package main.java.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import main.java.classes.CantidadTotalTipoPublicacion;
import main.java.classes.Empresa;
import main.java.classes.Keyword;
import main.java.classes.OfertaLaboral;
import main.java.classes.PaquetePublicacion;
import main.java.classes.Postulante;
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
import main.java.handlers.ManejadorOfertas;
import main.java.handlers.ManejadorPaquetes;
import main.java.handlers.ManejadorSettings;
import main.java.handlers.ManejadorUsuario;
import main.java.interfaces.IcontroladorOferta;
import main.java.interfaces.IcontroladorUsuario;

/**
 * Clase controlador oferta.
 */

public class ControladorOferta
      implements
      IcontroladorOferta {

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
    PaquetePublicacion paquetePublicacion =
          manejadorPaquetes
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
        List<DtCantidadTipoPublicacion> cantidadTipoPublicacion)
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
      for (DtCantidadTipoPublicacion dtCantidad : cantidadTipoPublicacion) {
        TipoPublicacion publicacionParticularPublicacion =
              manejadorSettings
                    .obtenerTipoPublicacion(
                          dtCantidad
                                .getNombreTipoPublicacion());
        CantidadTotalTipoPublicacion nuevoTipo =
              new CantidadTotalTipoPublicacion(
                    dtCantidad.getCantidad(),
                    publicacionParticularPublicacion);
        arrayCantidad.add(nuevoTipo);
      }
    }

    PaquetePublicacion paquetePublicacion =
          new PaquetePublicacion(
                nombre, descripcion, periodoValDias,
                descuento,
                imagen, arrayCantidad, fechaAlta);

    manejadorPaquetes.agregarPaquete(paquetePublicacion);
  }

  @Override
  public PaquetePublicacion obtenerPaquetePublicacion(
        String nombre)
        throws PaquetePublicacionNoExisteException {
    ManejadorPaquetes manejadorPaquetes = ManejadorPaquetes
          .getInstance();
    PaquetePublicacion paquetePublicacion =
          manejadorPaquetes
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
  public List<DtOfertaLaboral> obtenerDtOfertasPorKeyword(
        String keyword) throws IOException {
    return ManejadorOfertas.getInstance()
          .obtenerDtofertasPorKeyword(keyword);
  }

  @Override
  public List<DtPostulacion> obtenerDtPostulacionesDeOferta(
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
  public DtPaquetePublicacion obtenerDtPaquetePublicacion(
        String nombreOferta)
        throws OfertaLaboralNoExisteException,
        OfertaLaboralNoTienePaquete, IOException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
          .obtenerOfertaLaboral(nombreOferta);
    if (oferta.estaCompradaPorPaquete()) {
      return oferta.obtenerDtpaquete();
    } else {
      throw new OfertaLaboralNoTienePaquete("La oferta: "
            + nombreOferta
            + " no fue comprada por paquete");
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
  public DtPaquetePublicacion obtenerDtPaquete(
        String nombrePaquete)
        throws PaquetePublicacionNoExisteException,
        IOException {
    PaquetePublicacion paquete = ManejadorPaquetes
          .getInstance().obtenerPaquete(nombrePaquete);
    return paquete.obtenerDtPaquete();
  }

  @Override
  public DtTipoPublicacion obtenerDtTipoPublicacion(
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
  public List<DtPaquetePublicacion> listarDtPaquetes()
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
    return ManejadorOfertas.getInstance()
          .existeOfertaLaboral(nombreOferta);
  }

  @Override
  public void agregarVisitaOferta(String nombreOferta)
        throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
          .obtenerOfertaLaboral(nombreOferta);
    oferta.agregarVisita();
  }

  @Override
  public List<DtOfertaLaboral> obtenerOfertasMasVisitadas()
        throws IOException {
    List<DtOfertaLaboral> ofertas =
          (List<DtOfertaLaboral>) ManejadorOfertas
                .getInstance().obtenerDtOfertas();
    Comparator<DtOfertaLaboral> comparador = (oferta1,
          oferta2) -> (int) (oferta1.getVisitas()
                - oferta2.getVisitas());
    ofertas.sort(Collections.reverseOrder(comparador));
    return ofertas;
  }

  @Override
  public List<DtOfertaLaboral>
        buscarOfertas(String parametro) throws IOException {
    List<DtOfertaLaboral> resultado =
          new ArrayList<DtOfertaLaboral>();
    List<DtOfertaLaboral> ofertas =
          (List<DtOfertaLaboral>) ManejadorOfertas
                .getInstance()
                .obtenerDtofertasConfirmadas();
    for (DtOfertaLaboral oferta : ofertas) {
      if ((oferta.getNombre().toLowerCase()
            .contains(parametro.toLowerCase())
            || oferta.getDescripcion().toLowerCase()
                  .contains(parametro.toLowerCase()))
            && !oferta.getEstaVencida()) {
        resultado.add(oferta);
      }
    }
    Comparator<DtOfertaLaboral> comparadorExposicion =
          (oferta1, oferta2) -> oferta1
                .getExposicion()
                .compareTo(oferta2.getExposicion());
    Comparator<DtOfertaLaboral> comparadorFechan =
          (oferta1, oferta2) -> oferta1.getFechaAlta()
                .compareTo(oferta2.getFechaAlta());
    Collections.sort(resultado,
          comparadorExposicion.thenComparing(
                comparadorFechan.reversed()));
    return resultado;
  }

  @Override
  public void finalizarOferta(String nombreOferta)
        throws OfertaLaboralNoExisteException,
        OfertaLaboralNoSePuedeFinalizar {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
          .obtenerOfertaLaboral(nombreOferta);
    if (oferta.getEstado()
          != EstadoOferta.CONFIRMADA
          || !oferta.estaVencida()) {
      throw new OfertaLaboralNoSePuedeFinalizar(
            "La oferta " + nombreOferta
                  + " no puede ser finalizada");
    }
    oferta.finalizarOferta();
  }

  @Override
  public void ordenarPostulaciones(String nombreOferta,
        List<String> nicknamesPostulantes)
        throws OfertaLaboralNoExisteException {
    OfertaLaboral oferta = ManejadorOfertas.getInstance()
          .obtenerOfertaLaboral(nombreOferta);
    oferta.ordenarPostulaciones(nicknamesPostulantes);
  }
}
