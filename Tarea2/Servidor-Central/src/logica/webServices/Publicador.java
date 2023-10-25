
package logica.webServices;

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
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.controllers.Fabrica;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtcantidadTipoPublicacion;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.DttipoPublicacion;
import logica.datatypes.Dtusuario;
import logica.datatypes.EstadoOferta;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
  private Fabrica factory = Fabrica
      .getInstance();
  private IcontroladorOferta controladorOferta = factory
      .obtenerControladorOferta();
  private IcontroladorUsuario controladorUsuario = factory
      .obtenerControladorUsuario();
  private Endpoint endpoint = null;
  
  // Constructor
  public Publicador() {
  }
  // Operaciones las cuales quiero publicar
  
  @WebMethod(exclude = true)
  public void publicar() {
    endpoint = Endpoint.publish(
        "http://localhost:8085/webservices",
        this);
  }
  
  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
    return endpoint;
  }
  
  @WebMethod
  public String[] listarTipoDePublicaciones() {
    return controladorOferta
        .listarTipoDePublicaciones().toArray(new String[0]);
  }
  
  @WebMethod
  public void altaOfertaLaboral(
      String nombre,
      String descripcion,
      String horarioInicial,
      String horarioFinal,
      float remuneracion,
      String ciudad,
      String departamento,
      LocalDate fechaAlta,
      String nomTipoPublicacion,
      String nicknameEmpresa,
      String[] listakeywords,
      BufferedImage imagen,
      String nombrePaquete)
      throws OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException,
      KeywordNoExisteException,
      UsuarioNoExisteException {
    List<String> listaDeElementosKeywords = new ArrayList<>();
    for (String elemento : listakeywords) {
      listaDeElementosKeywords.add(elemento);
    }
    controladorOferta.altaOfertaLaboral(nombre,
        descripcion, horarioInicial,
        horarioFinal, remuneracion,
        ciudad, departamento,
        fechaAlta,
        nomTipoPublicacion,
        nicknameEmpresa,
        listaDeElementosKeywords, imagen,
        nombrePaquete);
  }
  /* @WebMethod public OfertaLaboral
   * obtenerOfertaLaboral( String nomOferta) throws
   * OfertaLaboralNoExisteException { return
   * controladorOferta.obtenerOfertaLaboral(
   * nomOferta); } */
  
  @WebMethod
  public void agregarTipoPublicacionAlPaquete(int cantIncluida,
      String nomTipoPublicacion,
      String nomTipoPaquete)
      throws TipoPublicacionNoExisteException,
      PaquetePublicacionNoExisteException,
      PaquetePublicacionYaFueComprado,
      TipoDePublicacionYaFueIngresado {
    controladorOferta.agregarTipoPublicacionAlPaquete(cantIncluida,
        nomTipoPublicacion, nomTipoPaquete);
  }
  
  @WebMethod
  public String[] listarPaquetes() {
    return controladorOferta.listarPaquetes().toArray(new String[0]);
  }
  
  @WebMethod
  public void altaTipoPublicacion(String nombre,
      String descripcion, String exposicion, int duracion,
      float costo, LocalDate fechaPub)
      throws TipoPublicacionYaExisteException {
    controladorOferta.altaTipoPublicacion(nombre, descripcion, exposicion,
        duracion, costo, fechaPub);
  }
  /* @WebMethod public TipoPublicacion
   * obtenerTipoPublicacion( String nomTpoPublic)
   * throws TipoPublicacionNoExisteException,
   * TipoPublicacionYaExisteException { return
   * controladorOferta.obtenerTipoPublicacion(
   * nomTpoPublic); } */
  
  @WebMethod
  public void altaKeyword(String nomKeyword)
      throws KeywordYaExisteException {
    controladorOferta.altaKeyword(nomKeyword);
  }
  /* @WebMethod public Keyword
   * obtenerKeywords(String nomKeyword) throws
   * KeywordNoExisteException,
   * TipoPublicacionNoExisteException { return
   * controladorOferta.obtenerKeywords(nomKeyword);
   * } */
  
  @WebMethod
  public String[] listarKeywords() {
    return controladorOferta.listarKeywords().toArray(new String[0]);
  }
  
  @WebMethod
  public DtOfertaLaboral obtenerDtOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException, IOException {
    return controladorOferta.obtenerDtOfertaLaboral(nomOferta);
  }
  
  @WebMethod
  public String[] obtenerOfertasEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException {
    return controladorOferta.obtenerOfertasEmpresa(nicknameEmpresa)
        .toArray(new String[0]);
  }
  
  @WebMethod
  public void registrarPaquete(String nombre,
      String descripcion, int periodoValDias,
      float descuento, BufferedImage imagen,
      LocalDate fechaAlta,
      DtcantidadTipoPublicacion[] cantidadTipoPublicacion)
      throws PaquetePublicacionYaExisteException,
      TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException {
    List<DtcantidadTipoPublicacion> listaDeDtcantidadTipoPublicacion = new ArrayList<DtcantidadTipoPublicacion>();
    for (DtcantidadTipoPublicacion elemento : cantidadTipoPublicacion) {
      listaDeDtcantidadTipoPublicacion.add(elemento);
    }
    controladorOferta.registrarPaquete(
        nombre, descripcion, periodoValDias, descuento, imagen, fechaAlta,
        listaDeDtcantidadTipoPublicacion);
  }
  /* @WebMethod public PaquetePublicacion
   * obtenerPaquetePublicacion( String nombre)
   * throws PaquetePublicacionNoExisteException {
   * return
   * controladorOferta.obtenerPaquetePublicacion(
   * nombre); } */
  
  @WebMethod
  public boolean estaPostulado(String postulante,
      String nomOfertaLaboral)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException {
    return controladorOferta.estaPostulado(postulante, nomOfertaLaboral);
  }
  
  @WebMethod
  public String[] obtenerKeywordsDeOfertaLaboral(String nomOfertaLab)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.obtenerKeywordsDeOfertaLaboral(nomOfertaLab)
        .toArray(new String[0]);
  }
  
  @WebMethod
  public void aceptarRechazarOfertaLaboral(
      String nombreOferta, EstadoOferta estadoOferta,
      LocalDate fechaResolucion)
      throws OfertaLaboralNoExisteException {
    controladorOferta.aceptarRechazarOfertaLaboral(nombreOferta, estadoOferta,
        fechaResolucion);
  }
  
  @WebMethod
  public DtOfertaLaboral[] obtenerDtOfertasConfirmadas()
      throws IOException {
    return controladorOferta.obtenerDtOfertasConfirmadas()
        .toArray(new DtOfertaLaboral[0]);
  }
  
  @WebMethod
  public DtOfertaLaboral[] obtenerDtofertasPorKeyword(
      String keyword) throws IOException {
    return controladorOferta.obtenerDtofertasPorKeyword(keyword)
        .toArray(new DtOfertaLaboral[0]);
  }
  
  @WebMethod
  public Dtpostulacion[] obtenerDtPostulacionesDeOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.obtenerDtPostulacionesDeOferta(nombreOferta)
        .toArray(new Dtpostulacion[0]);
  }
  
  @WebMethod
  public boolean estaCompradoPorPaqueteOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.estaCompradoPorPaqueteOferta(nombreOferta);
  }
  
  @WebMethod
  public DtpaquetePublicacion obtenerDtPaquetePublicacion(
      String nombreOferta)
      throws OfertaLaboralNoExisteException,
      OfertaLaboralNoTienePaquete, IOException {
    return controladorOferta.obtenerDtPaquetePublicacion(nombreOferta);
  }
  
  @WebMethod
  public String[] listarTipoPublicacionDePaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    return controladorOferta.listarTipoDePublicaciones().toArray(new String[0]);
  }
  
  @WebMethod
  public DtpaquetePublicacion obtenerDtpaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException,
      IOException {
    return controladorOferta.obtenerDtpaquete(nombrePaquete);
  }
  
  @WebMethod
  public DttipoPublicacion obtenerDttipoPublicacion(
      String nombreTipo)
      throws TipoPublicacionNoExisteException {
    return controladorOferta.obtenerDttipoPublicacion(nombreTipo);
  }
  
  @WebMethod
  public String[] listarPaquetesNoComprados() {
    return controladorOferta.listarPaquetesNoComprados().toArray(new String[0]);
  }
  
  @WebMethod
  public DtpaquetePublicacion[] listarDtpaquetes()
      throws IOException {
    return controladorOferta.listarDtpaquetes()
        .toArray(new DtpaquetePublicacion[0]);
  }
  
  @WebMethod
  public Boolean estaCompradoPaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    return controladorOferta.estaCompradoPaquete(nombrePaquete);
  }
  /* @WebMethod public byte[] getFile(
   * 
   * @WebParam(name = "fileName") String name)
   * throws IOException { byte[] byteArray = null;
   * try { File f = new File( "files/" + name);
   * FileInputStream streamer = new FileInputStream(
   * f); byteArray = new byte[streamer
   * .available()]; streamer.read(byteArray); }
   * catch (IOException e) { throw e; } return
   * byteArray; } */
  // Controlador Usuario
  /* @WebMethod public Empresa obtenerEmpresa(String
   * nicknameEmpresa) throws
   * UsuarioNoExisteException { return
   * controladorUsuario.obtenerEmpresa(
   * nicknameEmpresa); } */
  
  @WebMethod
  public String[] listarEmpresas() {
    return controladorUsuario.listarEmpresas().toArray(new String[0]);
  }
  
  @WebMethod
  public String[] listaDeUsuarios() {
    return controladorUsuario.listaDeUsuarios().toArray(new String[0]);
  }
  
  @WebMethod
  public void editarDatosBasicos(Dtusuario dtusuario,
      String nombreNuevo, String apellidoNuevo)
      throws UsuarioNoExisteException {
    controladorUsuario.editarDatosBasicos(dtusuario, nombreNuevo,
        apellidoNuevo);
  }
  
  @WebMethod
  public String[] obtenerOfertasEmpresaUsuario(
      String nicknameEmpresa)
      throws UsuarioNoExisteException {
    return controladorUsuario.obtenerOfertasEmpresaUsuario(nicknameEmpresa)
        .toArray(new String[0]);
  }
  
  @WebMethod
  public String[] listarPostulantes() {
    return controladorUsuario.listarPostulantes().toArray(new String[0]);
  }
  
  @WebMethod
  public void registrarPostulacion(String cvReducido,
      String motivacion, LocalDate fechaPostulacion,
      String nickname, String nomOferta)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException,
      UsuarioYaExistePostulacion {
    controladorUsuario.registrarPostulacion(cvReducido, motivacion,
        fechaPostulacion, nickname, nomOferta);
  }
  
  @WebMethod
  public void altaPostulante(String nickname, String nombre,
      String apellido, String email, LocalDate fechaNac,
      String nacionalidad, BufferedImage imagen,
      String constrasenia) throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException {
    controladorUsuario.altaEmpresa(nickname, nombre, apellido, email, email,
        nacionalidad, imagen, constrasenia);
  }
  
  @WebMethod
  public void altaEmpresa(String nickname, String nombre,
      String apellido, String email, String descripcion,
      String link, BufferedImage imagen, String contrasenia)
      throws UsuarioYaExisteException,
      UsuarioEmailRepetidoException {
    controladorUsuario.altaEmpresa(nickname, nombre, apellido, email,
        descripcion, link, imagen, contrasenia);
  }
  /* @WebMethod
   * 
   * public Usuario obtenerUsuario(String nickname)
   * throws UsuarioNoExisteException { return
   * controladorUsuario.obtenerUsuario(nickname);
   * } */
  
  @WebMethod
  public Dtusuario obtenerDtusuario(String nickname)
      throws UsuarioNoExisteException, IOException {
    return controladorUsuario.obtenerDtusuario(nickname);
  }
  
  @WebMethod
  public String[] listaOfertasUsuario(String nickname)
      throws UsuarioNoExisteException {
    return controladorUsuario.listaOfertasUsuario(nickname)
        .toArray(new String[0]);
  }
  /* @WebMethod public Postulante
   * obtenerPostulante(String nomPostulante) throws
   * UsuarioNoExisteException { return
   * controladorUsuario.obtenerPostulante(
   * nomPostulante); } */
  
  @WebMethod
  public void editarPostulante(String nickname,
      String nombre, String apellido,
      LocalDate fechaNacimiento, String nacionalidad,
      BufferedImage imagen, String contrasenia)
      throws UsuarioNoExisteException {
    controladorUsuario.editarPostulante(nickname, nombre, apellido,
        fechaNacimiento, nacionalidad, imagen, contrasenia);
  }
  
  @WebMethod
  public void editarEmpresa(String nickname, String nombre,
      String apellido, String sitioWeb, String descripcion,
      BufferedImage imagen, String contrasenia)
      throws UsuarioNoExisteException {
    controladorUsuario.editarEmpresa(nickname, nombre, apellido, sitioWeb,
        descripcion, imagen, contrasenia);
  }
  
  @WebMethod
  public Dtpostulacion obtenerDtpostulacion(
      String nicknamePostulante, String nombreOferta)
      throws UsuarioNoExisteException,
      UsuarioNoExistePostulacion {
    return controladorUsuario.obtenerDtpostulacion(nicknamePostulante,
        nombreOferta);
  }
  
  @WebMethod
  public DtOfertaLaboral[] obtenerDtofertasIngresadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    return controladorUsuario
        .obtenerDtofertasIngresadasDeEmpresa(nicknameEmpresa)
        .toArray(new DtOfertaLaboral[0]);
  }
  
  @WebMethod
  public DtOfertaLaboral[] obtenerDtofertasConfirmadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    return controladorUsuario
        .obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa)
        .toArray(new DtOfertaLaboral[0]);
  }
  
  @WebMethod
  public DtOfertaLaboral[] obtenerDtofertasRechazadasDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    return controladorUsuario
        .obtenerDtofertasRechazadasDeEmpresa(nicknameEmpresa)
        .toArray(new DtOfertaLaboral[0]);
  }
  
  @WebMethod
  public Boolean confirmarContrasenia(String clave,
      String contrasenia) throws UsuarioNoExisteException {
    return controladorUsuario.confirmarContrasenia(clave, contrasenia);
  }
  
  @WebMethod
  public Dtusuario[] obtenerDtusuarios()
      throws IOException {
    return controladorUsuario.obtenerDtusuarios().toArray(new Dtusuario[0]);
  }
  
  @WebMethod
  public void comprarPaquete(String nicknameEmpresa,
      String nombrePaquete, LocalDate fechaCompra)
      throws UsuarioNoExisteException,
      PaquetePublicacionNoExisteException {
    controladorUsuario.comprarPaquete(nicknameEmpresa, nombrePaquete,
        fechaCompra);
  }
  
  @WebMethod
  public DtpaquetePublicacion[] obtenerDtpaquetesDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    return controladorUsuario.obtenerDtpaquetesDeEmpresa(nicknameEmpresa)
        .toArray(new DtpaquetePublicacion[0]);
  }
  
  @WebMethod
  public Dtpostulacion[] obtenerDtpostulacionesDePostulante(
      String nicknamePostulante)
      throws UsuarioNoExisteException {
    return controladorUsuario
        .obtenerDtpostulacionesDePostulante(nicknamePostulante)
        .toArray(new Dtpostulacion[0]);
  }
  
  @WebMethod
  public String[] listarPaquetesNoCompradosDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException {
    return controladorUsuario
        .listarPaquetesNoCompradosDeEmpresa(nicknameEmpresa)
        .toArray(new String[0]);
  }
  
  @WebMethod
  public DtCompraPaquete[] obtenerDtCompraPaqueteDeEmpresa(
      String nicknameEmpresa)
      throws UsuarioNoExisteException, IOException {
    return controladorUsuario.obtenerDtCompraPaqueteDeEmpresa(nicknameEmpresa)
        .toArray(new DtCompraPaquete[0]);
  }
}