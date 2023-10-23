
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
import excepciones.UsuarioNoExisteException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtCantidadTipoPublicacion;
import logica.datatypes.DtPaquetePublicacion;
import logica.datatypes.DtPostulacion;
import logica.datatypes.DtTipoPublicacion;
import logica.datatypes.EstadoOferta;
import logica.interfaces.IcontroladorOferta;

//Le faltan los metodos que retoran una clase, pense que no iban 


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSControladorOferta {
  
  private Fabrica factory = Fabrica.getInstance();
  private IcontroladorOferta controladorOferta = factory.obtenerControladorOferta();
  private Endpoint endpoint = null;
  
  // Constructor
  public WSControladorOferta() {
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
  
  public List<String> listarTipoDePublicaciones() {
    return controladorOferta.listarTipoDePublicaciones();
  }
  
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
      List<String> listakeywords,
      BufferedImage imagen,
      String nombrePaquete)
      throws OfertaLaboralYaExisteException,
      TipoPublicacionNoExisteException,
      KeywordNoExisteException,
      UsuarioNoExisteException {
    controladorOferta.altaOfertaLaboral(nombre,
        descripcion, horarioInicial,
        horarioFinal, remuneracion,
        ciudad, departamento,
        fechaAlta,
        nomTipoPublicacion,
        nicknameEmpresa,
        listakeywords, imagen,
        nombrePaquete);
  }
  
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
  
  public List<String> listarPaquetes() {
    return controladorOferta.listarPaquetes();
  }
  
  public void altaTipoPublicacion(String nombre,
      String descripcion, String exposicion, int duracion,
      float costo, LocalDate fechaPub)
      throws TipoPublicacionYaExisteException {
    controladorOferta.altaTipoPublicacion(nombre, descripcion, exposicion,
        duracion, costo, fechaPub);
  }
  
  public void altaKeyword(String nomKeyword)
      throws KeywordYaExisteException {
    controladorOferta.altaKeyword(nomKeyword);
  }
  
  public List<String> listarKeywords() {
    return controladorOferta.listarKeywords();
  }
  
  public DtOfertaLaboral obtenerDtOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException, IOException {
    return controladorOferta.obtenerDtOfertaLaboral(nomOferta);
  }
  
  public List<String> obtenerOfertasEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException {
    return controladorOferta.obtenerOfertasEmpresa(nicknameEmpresa);
  }
  
  public void registrarPaquete(String nombre,
      String descripcion, int periodoValDias,
      float descuento, BufferedImage imagen,
      LocalDate fechaAlta,
      List<DtCantidadTipoPublicacion> cantidadTipoPublicacion)
      throws PaquetePublicacionYaExisteException,
      TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException {
    controladorOferta.registrarPaquete(
        nombre, descripcion, periodoValDias, descuento, imagen, fechaAlta,
        cantidadTipoPublicacion);
  }
  
  public boolean estaPostulado(String postulante,
      String nomOfertaLaboral)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException {
    return controladorOferta.estaPostulado(postulante, nomOfertaLaboral);
  }
  
  public List<String> obtenerKeywordsDeOfertaLaboral(String nomOfertaLab)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.obtenerKeywordsDeOfertaLaboral(nomOfertaLab);
  }
  
  public void aceptarRechazarOfertaLaboral(
      String nombreOferta, EstadoOferta estadoOferta,
      LocalDate fechaResolucion)
      throws OfertaLaboralNoExisteException {
    controladorOferta.aceptarRechazarOfertaLaboral(nombreOferta, estadoOferta,
        fechaResolucion);
  }
  
  public List<DtOfertaLaboral> obtenerDtOfertasConfirmadas()
      throws IOException {
    return controladorOferta.obtenerDtOfertasConfirmadas();
  }
  
  public List<DtOfertaLaboral> obtenerDtofertasPorKeyword(
      String keyword) throws IOException {
    return controladorOferta.obtenerDtofertasPorKeyword(keyword);
  }
  
  public List<DtPostulacion> obtenerDtPostulacionesDeOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.obtenerDtPostulacionesDeOferta(nombreOferta);
  }
  
  public boolean estaCompradoPorPaqueteOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.estaCompradoPorPaqueteOferta(nombreOferta);
  }
  
  public DtPaquetePublicacion obtenerDtPaquetePublicacion(
      String nombreOferta)
      throws OfertaLaboralNoExisteException,
      OfertaLaboralNoTienePaquete, IOException {
    return controladorOferta.obtenerDtPaquetePublicacion(nombreOferta);
  }
  
  public List<String> listarTipoPublicacionDePaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    return controladorOferta.listarTipoDePublicaciones();
  }
  
  public DtPaquetePublicacion obtenerDtpaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException,
      IOException {
    return controladorOferta.obtenerDtpaquete(nombrePaquete);
  }
  
  public DtTipoPublicacion obtenerDttipoPublicacion(
      String nombreTipo)
      throws TipoPublicacionNoExisteException {
    return controladorOferta.obtenerDttipoPublicacion(nombreTipo);
  }
  
  public List<String> listarPaquetesNoComprados() {
    return controladorOferta.listarPaquetesNoComprados();
  }
  
  public List<DtPaquetePublicacion> listarDtpaquetes()
      throws IOException {
    return controladorOferta.listarDtpaquetes();
  }
  
  public Boolean estaCompradoPaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    return controladorOferta.estaCompradoPaquete(nombrePaquete);
  }
  
  
  
  
  
  
  
  
  @WebMethod
  public byte[] getFile(
      @WebParam(name = "fileName") String name)
      throws IOException {
    byte[] byteArray = null;
    try {
      File f = new File(
          "files/" + name);
      FileInputStream streamer = new FileInputStream(
          f);
      byteArray = new byte[streamer
          .available()];
      streamer.read(byteArray);
    } catch (IOException e) {
      throw e;
    }
    return byteArray;
  }
}