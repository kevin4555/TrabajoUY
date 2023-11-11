package main.java.webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import main.java.controllers.ConfigManager;
import main.java.controllers.Fabrica;
import main.java.datatypes.DtCantidadTipoPublicacion;
import main.java.datatypes.DtCompraPaquete;
import main.java.datatypes.DtDatosPdf;
import main.java.datatypes.DtEmpresa;
import main.java.datatypes.DtOfertaLaboral;
import main.java.datatypes.DtPaquetePublicacion;
import main.java.datatypes.DtPostulacion;
import main.java.datatypes.DtTipoPublicacion;
import main.java.datatypes.DtUsuario;
import main.java.excepciones.KeywordNoExisteException;
import main.java.excepciones.KeywordYaExisteException;
import main.java.excepciones.OfertaLaboralNoExisteException;
import main.java.excepciones.OfertaLaboralNoSePuedeFinalizar;
import main.java.excepciones.OfertaLaboralNoTienePaquete;
import main.java.excepciones.OfertaLaboralYaExisteException;
import main.java.excepciones.PaquetePublicacionNoExisteException;
import main.java.excepciones.PaquetePublicacionYaExisteException;
import main.java.excepciones.PaquetePublicacionYaFueComprado;
import main.java.excepciones.PostulanteNoEsOfertaFavoritaException;
import main.java.excepciones.PostulanteYaEsOfertaFavoritaException;
import main.java.excepciones.TipoDePublicacionYaFueIngresado;
import main.java.excepciones.TipoPublicacionNoExisteException;
import main.java.excepciones.TipoPublicacionYaExisteException;
import main.java.excepciones.UsuarioEmailRepetidoException;
import main.java.excepciones.UsuarioNoEstaSeguidoException;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.excepciones.UsuarioNoExistePostulacion;
import main.java.excepciones.UsuarioYaEstaSeguidoException;
import main.java.excepciones.UsuarioYaExisteException;
import main.java.excepciones.UsuarioYaExistePostulacion;
import main.java.interfaces.IcontroladorOferta;
import main.java.interfaces.IcontroladorUsuario;

/**
 * Clase publicador.
 */

@WebService
@SOAPBinding(style = Style.RPC,
      parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
  private Fabrica factory = Fabrica.getInstance();
  private ConfigManager configManager =
        ConfigManager.getInstance();
  private IcontroladorOferta controladorOferta =
        factory.obtenerControladorOferta();
  private IcontroladorUsuario controladorUsuario =
        factory.obtenerControladorUsuario();
  private Endpoint endpoint = null;
  private final String dominio =
        configManager.getProperty("dominio");
  private final String puerto =
        configManager.getProperty("puerto");

  private String urlBase = dominio + puerto;

  // Constructor
  public Publicador() {
  }

  /**
   * Metodo publicar.
   */
  
  @WebMethod(exclude = true)
  public void publicar() {
    String urlWebSrv = urlBase + "/webservices";
    System.out.println("URL_WEB_SERVICE: " + urlWebSrv);
    
    endpoint =
          Endpoint.publish(urlWebSrv, this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
    return endpoint;
  }

  @WebMethod
  public String[] listarTipoDePublicaciones() {
    return controladorOferta.listarTipoDePublicaciones()
          .toArray(new String[0]);
  }

  /**
   * Metodo WS altaOfertaLaboral.
   */

  @WebMethod
  public void altaOfertaLaboral(String nombre,
        String descripcion, String horarioInicial,
        String horarioFinal, float remuneracion,
        String ciudad, String departamento,
        String fechaAlta, String nomTipoPublicacion,
        String nicknameEmpresa,
        String[] listakeywords, String imagen,
        String nombrePaquete)
        throws OfertaLaboralYaExisteException,
        TipoPublicacionNoExisteException,
        KeywordNoExisteException, UsuarioNoExisteException {

    List<String> listaDeElementosKeywords =
          new ArrayList<>();
    for (String elemento : listakeywords) {
      listaDeElementosKeywords.add(elemento);
    }
    BufferedImage imagenBufferedImage = null;
    if (!imagen.equals("")) {
      imagenBufferedImage = base64StringToImage(imagen);
    }
    if (nombrePaquete.equals("")) {
      nombrePaquete = null;
    }
    LocalDate fechaAltaLocalDate =
          LocalDate.parse(fechaAlta);
    controladorOferta.altaOfertaLaboral(nombre, descripcion,
          horarioInicial, horarioFinal,
          remuneracion, ciudad, departamento,
          fechaAltaLocalDate, nomTipoPublicacion,
          nicknameEmpresa, listaDeElementosKeywords,
          imagenBufferedImage, nombrePaquete);
  }

  @WebMethod
  public void agregarTipoPublicacionAlPaquete(
        int cantIncluida, String nomTipoPublicacion,
        String nomTipoPaquete)
        throws TipoPublicacionNoExisteException,
        PaquetePublicacionNoExisteException,
        PaquetePublicacionYaFueComprado,
        TipoDePublicacionYaFueIngresado {
    controladorOferta.agregarTipoPublicacionAlPaquete(
          cantIncluida, nomTipoPublicacion, nomTipoPaquete);
  }

  @WebMethod
  public String[] listarPaquetes() {
    return controladorOferta.listarPaquetes()
          .toArray(new String[0]);
  }

  /**
   * Metodo WS altaTipoPublicacion.
   */

  @WebMethod
  public void altaTipoPublicacion(String nombre,
        String descripcion, String exposicion, int duracion,
        float costo,
        String fechaPub)
        throws TipoPublicacionYaExisteException {
    LocalDate fecha = LocalDate.parse(fechaPub);
    controladorOferta.altaTipoPublicacion(nombre,
          descripcion, exposicion, duracion, costo, fecha);
  }

  @WebMethod
  public void altaKeyword(String nomKeyword)
        throws KeywordYaExisteException {
    controladorOferta.altaKeyword(nomKeyword);
  }

  @WebMethod
  public String[] listarKeywords() {
    return controladorOferta.listarKeywords()
          .toArray(new String[0]);
  }

  @WebMethod
  public DtOfertaLaboral obtenerDtOfertaLaboral(
        String nomOferta)
        throws OfertaLaboralNoExisteException, IOException {
    return controladorOferta
          .obtenerDtOfertaLaboral(nomOferta);
  }

  /**
   * Metodo WS obtenerOfertasEmpresa.
   */

  @WebMethod
  public String[]
        obtenerOfertasEmpresa(String nicknameEmpresa)
              throws UsuarioNoExisteException {
    return controladorOferta
          .obtenerOfertasEmpresa(nicknameEmpresa)
          .toArray(new String[0]);
  }

  /**
   * Metodo WS registrarPaquete.
   */

  @WebMethod
  public void registrarPaquete(String nombre,
        String descripcion, int periodoValDias,
        float descuento,
        BufferedImage imagen, String fechaAlta,
        DtCantidadTipoPublicacion[] cantidadTipoPublicacion)
        throws PaquetePublicacionYaExisteException,
        TipoPublicacionYaExisteException,
        TipoPublicacionNoExisteException {
    List<DtCantidadTipoPublicacion> listaDeDtcantidadTipoPublicacion =
          new ArrayList<DtCantidadTipoPublicacion>();
    for (DtCantidadTipoPublicacion elemento : cantidadTipoPublicacion) {
      listaDeDtcantidadTipoPublicacion.add(elemento);
    }
    LocalDate fecha = LocalDate.parse(fechaAlta);
    controladorOferta.registrarPaquete(nombre, descripcion,
          periodoValDias, descuento, imagen, fecha,
          listaDeDtcantidadTipoPublicacion);
  }

  @WebMethod
  public boolean estaPostulado(String postulante,
        String nomOfertaLaboral)
        throws UsuarioNoExisteException,
        OfertaLaboralNoExisteException {
    return controladorOferta.estaPostulado(postulante,
          nomOfertaLaboral);
  }

  /**
   * Metodo WS obtenerKeywordsDeOfertaLaboral.
   */

  @WebMethod
  public String[]
        obtenerKeywordsDeOfertaLaboral(String nomOfertaLab)
              throws OfertaLaboralNoExisteException {
    return controladorOferta
          .obtenerKeywordsDeOfertaLaboral(nomOfertaLab)
          .toArray(new String[0]);
  }

  @WebMethod
  public DtOfertaLaboral[] obtenerDtOfertasConfirmadas()
        throws IOException {
    return controladorOferta.obtenerDtOfertasConfirmadas()
          .toArray(new DtOfertaLaboral[0]);
  }

  /**
   * Metodo WS obtenerDtOfertasPorKeyword.
   */

  @WebMethod
  public DtOfertaLaboral[] obtenerDtOfertasPorKeyword(
        String keyword) throws IOException {
    return controladorOferta
          .obtenerDtOfertasPorKeyword(keyword)
          .toArray(new DtOfertaLaboral[0]);
  }

  /**
   * Metodo WS DtPostulacion.
   */

  @WebMethod
  public DtPostulacion[]
        obtenerDtPostulacionesDeOferta(String nombreOferta)
              throws OfertaLaboralNoExisteException {
    return controladorOferta
          .obtenerDtPostulacionesDeOferta(nombreOferta)
          .toArray(new DtPostulacion[0]);
  }

  @WebMethod
  public boolean
        estaCompradoPorPaqueteOferta(String nombreOferta)
              throws OfertaLaboralNoExisteException {
    return controladorOferta
          .estaCompradoPorPaqueteOferta(nombreOferta);
  }

  @WebMethod
  public DtPaquetePublicacion
        obtenerDtPaquetePublicacion(String nombreOferta)
              throws OfertaLaboralNoExisteException,
              OfertaLaboralNoTienePaquete, IOException {
    return controladorOferta
          .obtenerDtPaquetePublicacion(nombreOferta);
  }

  @WebMethod
  public String[]
        listarTipoPublicacionDePaquete(String nombrePaquete)
              throws PaquetePublicacionNoExisteException {
    return controladorOferta.listarTipoDePublicaciones()
          .toArray(new String[0]);
  }

  @WebMethod
  public DtPaquetePublicacion
        obtenerDtpaquete(String nombrePaquete)
              throws PaquetePublicacionNoExisteException,
              IOException {
    return controladorOferta
          .obtenerDtPaquete(nombrePaquete);
  }

  @WebMethod
  public DtTipoPublicacion
        obtenerDtTipoPublicacion(String nombreTipo)
              throws TipoPublicacionNoExisteException {
    return controladorOferta
          .obtenerDtTipoPublicacion(nombreTipo);
  }

  @WebMethod
  public String[] listarPaquetesNoComprados() {
    return controladorOferta.listarPaquetesNoComprados()
          .toArray(new String[0]);
  }

  @WebMethod
  public DtPaquetePublicacion[] listarDtPaquetes()
        throws IOException {
    return controladorOferta.listarDtPaquetes()
          .toArray(new DtPaquetePublicacion[0]);
  }

  @WebMethod
  public Boolean estaCompradoPaquete(String nombrePaquete)
        throws PaquetePublicacionNoExisteException {
    return controladorOferta
          .estaCompradoPaquete(nombrePaquete);
  }

  @WebMethod
  public String[] listarEmpresas() {
    return controladorUsuario.listarEmpresas()
          .toArray(new String[0]);
  }

  @WebMethod
  public String[] listaDeUsuarios() {
    return controladorUsuario.listaDeUsuarios()
          .toArray(new String[0]);
  }

  /**
   * Metodo WS obtenerOfertasEmpresaUsuario.
   */

  @WebMethod
  public String[]
        obtenerOfertasEmpresaUsuario(String nicknameEmpresa)
              throws UsuarioNoExisteException {
    return controladorUsuario
          .obtenerOfertasEmpresaUsuario(nicknameEmpresa)
          .toArray(new String[0]);
  }

  @WebMethod
  public String[] listarPostulantes() {
    return controladorUsuario.listarPostulantes()
          .toArray(new String[0]);
  }

  /**
   * Metodo WS registrarPostulacion.
   */

  @WebMethod
  public void registrarPostulacion(String cvReducido,
        String motivacion, String fechaPostulacion,
        String nickname,
        String nomOferta, String linkVideo)
        throws UsuarioNoExisteException,
        OfertaLaboralNoExisteException,
        UsuarioYaExistePostulacion {
    LocalDate fecha = LocalDate.parse(fechaPostulacion);
    controladorUsuario.registrarPostulacion(cvReducido,
          motivacion, fecha, nickname, nomOferta,
          linkVideo);
  }

  /**
   * Metodo WS altaPostulante.
   */

  @WebMethod
  public void altaPostulante(String nickname, String nombre,
        String apellido, String email, String fechaNac,
        String nacionalidad, String imagen,
        String constrasenia)
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException {
    LocalDate fecha = LocalDate.parse(fechaNac);

    controladorUsuario.altaPostulante(nickname, nombre,
          apellido, email, fecha, nacionalidad,
          base64StringToImage(imagen), constrasenia);
    
  }

  /**
   * Metodo WS altaEmpresa.
   */

  @WebMethod
  public void altaEmpresa(String nickname, String nombre,
        String apellido, String email, String descripcion,
        String link, String imagen, String contrasenia)
        throws UsuarioYaExisteException,
        UsuarioEmailRepetidoException {

    BufferedImage imagenBufferImage =
          base64StringToImage(imagen);
    controladorUsuario.altaEmpresa(nickname, nombre,
          apellido, email, descripcion, link,
          imagenBufferImage,
          contrasenia);
  }

  @WebMethod
  public DtUsuario obtenerDtUsuario(String nickname)
        throws UsuarioNoExisteException, IOException {
    return controladorUsuario.obtenerDtUsuario(nickname);
  }

  @WebMethod
  public String[] listaOfertasUsuario(String nickname)
        throws UsuarioNoExisteException {
    return controladorUsuario.listaOfertasUsuario(nickname)
          .toArray(new String[0]);
  }

  /**
   * Metodo WS editarPostulante.
   */

  @WebMethod
  public void editarPostulante(String nickname,
        String nombre, String apellido,
        String fechaNacimiento,
        String nacionalidad, String imagen,
        String contrasenia)
        throws UsuarioNoExisteException {
    LocalDate fecha = LocalDate.parse(fechaNacimiento);
    BufferedImage imagenBufferImage =
          base64StringToImage(imagen);
    controladorUsuario.editarPostulante(nickname, nombre,
          apellido, fecha, nacionalidad, imagenBufferImage,
          contrasenia);
  }

  /**
   * Metodo WS editarEmpresa.
   */

  @WebMethod
  public void editarEmpresa(String nickname, String nombre,
        String apellido, String sitioWeb,
        String descripcion,
        String imagen, String contrasenia)
        throws UsuarioNoExisteException {
    BufferedImage imagenBufferImage =
          base64StringToImage(imagen);
    controladorUsuario.editarEmpresa(nickname, nombre,
          apellido, sitioWeb, descripcion,
          imagenBufferImage, contrasenia);
  }

  /**
   * Metodo WS obtenerDtPostulacion.
   */

  @WebMethod
  public DtPostulacion obtenerDtPostulacion(
        String nicknamePostulante, String nombreOferta)
        throws UsuarioNoExisteException,
        UsuarioNoExistePostulacion {
    return controladorUsuario.obtenerDtPostulacion(
          nicknamePostulante, nombreOferta);
  }

  /**
   * Metodo WS obtenerDtOfertasIngresadasDeEmpresa.
   */

  @WebMethod
  public DtOfertaLaboral[]
        obtenerDtOfertasIngresadasDeEmpresa(
              String nicknameEmpresa)
              throws UsuarioNoExisteException, IOException {
    return controladorUsuario
          .obtenerDtOfertasIngresadasDeEmpresa(
                nicknameEmpresa)
          .toArray(new DtOfertaLaboral[0]);
  }

  /**
   * Metodo WS obtenerDtOfertasConfirmadasDeEmpresa.
   */

  @WebMethod
  public DtOfertaLaboral[]
        obtenerDtOfertasConfirmadasDeEmpresa(
              String nicknameEmpresa)
              throws UsuarioNoExisteException, IOException {
    return controladorUsuario
          .obtenerDtOfertasConfirmadasDeEmpresa(
                nicknameEmpresa)
          .toArray(new DtOfertaLaboral[0]);
  }

  /**
   * Metodo WS obtenerDtOfertasRechazadasDeEmpresa.
   */

  @WebMethod
  public DtOfertaLaboral[]
        obtenerDtOfertasRechazadasDeEmpresa(
              String nicknameEmpresa)
              throws UsuarioNoExisteException, IOException {
    return controladorUsuario
          .obtenerDtOfertasRechazadasDeEmpresa(
                nicknameEmpresa)
          .toArray(new DtOfertaLaboral[0]);
  }

  @WebMethod
  public Boolean confirmarContrasenia(String clave,
        String contrasenia)
        throws UsuarioNoExisteException {
    return controladorUsuario.confirmarContrasenia(clave,
          contrasenia);
  }

  @WebMethod
  public DtUsuario[] obtenerDtUsuarios()
        throws IOException {
    return controladorUsuario.obtenerDtUsuarios()
          .toArray(new DtUsuario[0]);
  }

  /**
   * Metodo WS comprarPaquete.
   */

  @WebMethod
  public void comprarPaquete(String nicknameEmpresa,
        String nombrePaquete, String fechaCompra)
        throws UsuarioNoExisteException,
        PaquetePublicacionNoExisteException {
    LocalDate fecha = LocalDate.parse(fechaCompra);
    controladorUsuario.comprarPaquete(nicknameEmpresa,
          nombrePaquete, fecha);
  }

  /**
   * Metodo WS obtenerDtPaquetesDeEmpresa.
   */

  @WebMethod
  public DtPaquetePublicacion[]
        obtenerDtPaquetesDeEmpresa(String nicknameEmpresa)
              throws UsuarioNoExisteException, IOException {
    return controladorUsuario
          .obtenerDtPaquetesDeEmpresa(nicknameEmpresa)
          .toArray(new DtPaquetePublicacion[0]);
  }

  /**
   * Metodo WS obtenerDtPostulacionesDePostulante.
   */

  @WebMethod
  public DtPostulacion[] obtenerDtPostulacionesDePostulante(
        String nicknamePostulante)
        throws UsuarioNoExisteException {
    return controladorUsuario
          .obtenerDtPostulacionesDePostulante(
                nicknamePostulante)
          .toArray(new DtPostulacion[0]);
  }

  /**
   * Metodo WS listarPaquetesNoCompradosDeEmpresa.
   */

  @WebMethod
  public String[] listarPaquetesNoCompradosDeEmpresa(
        String nicknameEmpresa)
        throws UsuarioNoExisteException {
    return controladorUsuario
          .listarPaquetesNoCompradosDeEmpresa(
                nicknameEmpresa)
          .toArray(new String[0]);
  }

  /**
   * Metodo WS obtenerDtCompraPaqueteDeEmpresa.
   */

  @WebMethod
  public DtCompraPaquete[] obtenerDtCompraPaqueteDeEmpresa(
        String nicknameEmpresa)
        throws UsuarioNoExisteException, IOException {
    return controladorUsuario
          .obtenerDtCompraPaqueteDeEmpresa(nicknameEmpresa)
          .toArray(new DtCompraPaquete[0]);
  }

  @WebMethod
  public DtEmpresa[] buscarEmpresas(String parametro)
        throws IOException {
    return controladorUsuario.buscarEmpresas(parametro)
          .toArray(new DtEmpresa[0]);
  }

  @WebMethod
  public DtOfertaLaboral[] buscarOfertas(String parametro)
        throws IOException {
    return controladorOferta.buscarOfertas(parametro)
          .toArray(new DtOfertaLaboral[0]);
  }

  /**
   * Metodo WS base64StringToImage.
   */

  public static BufferedImage
        base64StringToImage(String imageString) {
    try {
      byte[] imageBytes =
            Base64.getDecoder().decode(imageString);
      ByteArrayInputStream bis =
            new ByteArrayInputStream(imageBytes);
      return ImageIO.read(bis);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @WebMethod
  public void agregarSeguidor(String nicknameUsuario,
        String nicknameSeguidor)
        throws UsuarioNoExisteException,
        UsuarioNoEstaSeguidoException,
        UsuarioYaEstaSeguidoException {
    controladorUsuario.agregarSeguidor(nicknameUsuario,
          nicknameSeguidor);
  }

  @WebMethod
  public void dejarDeSeguir(String nicknameUsuario,
        String nicknameSeguidor)
        throws UsuarioNoExisteException,
        UsuarioNoEstaSeguidoException {
    controladorUsuario.dejarDeSeguir(nicknameUsuario,
          nicknameSeguidor);
  }

  @WebMethod
  public void finalizarOferta(String nombreOferta)
        throws OfertaLaboralNoExisteException,
        OfertaLaboralNoSePuedeFinalizar {
    controladorOferta.finalizarOferta(nombreOferta);
  }

  @WebMethod
  public DtDatosPdf obtenerDatosPdf(
        String nicknamePostulante, String nombreOferta)
        throws OfertaLaboralNoExisteException,
        UsuarioNoExisteException {
    return controladorUsuario.obtenerDatosPdf(
          nicknamePostulante, nombreOferta);
  }

  @WebMethod
  public void agregarVisitaAoferta(String nombreOferta)
        throws OfertaLaboralNoExisteException {
    controladorOferta.agregarVisitaOferta(nombreOferta);
  }

  @WebMethod
  public void agregarOfertaFavorita(
        String nicknamePostulante, String nombreOferta)
        throws UsuarioNoExisteException,
        PostulanteYaEsOfertaFavoritaException,
        OfertaLaboralNoExisteException {
    controladorUsuario.agregarOfertaFavorita(
          nicknamePostulante, nombreOferta);
  }

  @WebMethod
  public void removerOfertaFavorita(
        String nicknamePostulante, String nombreOferta)
        throws UsuarioNoExisteException,
        PostulanteNoEsOfertaFavoritaException {
    controladorUsuario.removerOfertaFavorita(
          nicknamePostulante, nombreOferta);
  }

  /**
   * Metodo ordenarPostulaciones.
   */
  
  @WebMethod
  public void ordenarPostulaciones(String nombreOferta,
        String[] nicknamePostulantes)
        throws OfertaLaboralNoExisteException {
    List<String> listaPostulantes =
          new ArrayList<String>();
    for (String postulante : nicknamePostulantes) {
      listaPostulantes.add(postulante);
    }
    controladorOferta.ordenarPostulaciones(nombreOferta,
          listaPostulantes);
  }
}