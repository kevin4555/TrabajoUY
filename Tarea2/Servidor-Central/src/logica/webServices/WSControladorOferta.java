
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
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.ws.Endpoint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        "http://localhost:8085/webservices/ofertas",
        this);
  }
  
  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
    return endpoint;
  }
  @WebMethod
  @XmlList
  public ArrayList<String> listarTipoDePublicaciones() {
    return (ArrayList<String>) controladorOferta.listarTipoDePublicaciones();
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
      ArrayList<String> listakeywords,
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
  @XmlList
  public ArrayList<String> listarPaquetes() {
    return (ArrayList<String>) controladorOferta.listarPaquetes();
  }
  @WebMethod
  public void altaTipoPublicacion(String nombre,
      String descripcion, String exposicion, int duracion,
      float costo, LocalDate fechaPub)
      throws TipoPublicacionYaExisteException {
    controladorOferta.altaTipoPublicacion(nombre, descripcion, exposicion,
        duracion, costo, fechaPub);
  }
  @WebMethod
  public void altaKeyword(String nomKeyword)
      throws KeywordYaExisteException {
    controladorOferta.altaKeyword(nomKeyword);
  }
  @WebMethod
  @XmlList
  public ArrayList<String> listarKeywords() {
    return (ArrayList<String>) controladorOferta.listarKeywords();
  }
  @WebMethod
  public DtOfertaLaboral obtenerDtOfertaLaboral(String nomOferta)
      throws OfertaLaboralNoExisteException, IOException {
    return controladorOferta.obtenerDtOfertaLaboral(nomOferta);
  }
  @WebMethod
  @XmlList
  public ArrayList<String> obtenerOfertasEmpresa(String nicknameEmpresa)
      throws UsuarioNoExisteException {
    return (ArrayList<String>) controladorOferta.obtenerOfertasEmpresa(nicknameEmpresa);
  }
  @WebMethod
  public void registrarPaquete(String nombre,
      String descripcion, int periodoValDias,
      float descuento, BufferedImage imagen,
      LocalDate fechaAlta,
      ArrayList<DtCantidadTipoPublicacion> cantidadTipoPublicacion)
      throws PaquetePublicacionYaExisteException,
      TipoPublicacionYaExisteException,
      TipoPublicacionNoExisteException {
    controladorOferta.registrarPaquete(
        nombre, descripcion, periodoValDias, descuento, imagen, fechaAlta,
        cantidadTipoPublicacion);
  }
  @WebMethod
  public boolean estaPostulado(String postulante,
      String nomOfertaLaboral)
      throws UsuarioNoExisteException,
      OfertaLaboralNoExisteException {
    return controladorOferta.estaPostulado(postulante, nomOfertaLaboral);
  }
  @WebMethod
  @XmlList
  public ArrayList<String> obtenerKeywordsDeOfertaLaboral(String nomOfertaLab)
      throws OfertaLaboralNoExisteException {
    return (ArrayList<String>) controladorOferta.obtenerKeywordsDeOfertaLaboral(nomOfertaLab);
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
  public ArrayList<DtOfertaLaboral> obtenerDtOfertasConfirmadas()
      throws IOException {
    return (ArrayList<DtOfertaLaboral>) controladorOferta.obtenerDtOfertasConfirmadas();
  }
  @WebMethod
  public ArrayList<DtOfertaLaboral> obtenerDtofertasPorKeyword(
      String keyword) throws IOException {
    return (ArrayList<DtOfertaLaboral>) controladorOferta.obtenerDtOfertasPorKeyword(keyword);
  }
  @WebMethod
  public ArrayList<DtPostulacion> obtenerDtPostulacionesDeOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    return (ArrayList<DtPostulacion>) controladorOferta.obtenerDtPostulacionesDeOferta(nombreOferta);
  }
  @WebMethod
  public boolean estaCompradoPorPaqueteOferta(
      String nombreOferta)
      throws OfertaLaboralNoExisteException {
    return controladorOferta.estaCompradoPorPaqueteOferta(nombreOferta);
  }
  @WebMethod
  public DtPaquetePublicacion obtenerDtPaquetePublicacion(
      String nombreOferta)
      throws OfertaLaboralNoExisteException,
      OfertaLaboralNoTienePaquete, IOException {
    return controladorOferta.obtenerDtPaquetePublicacion(nombreOferta);
  }
  @WebMethod
  @XmlList
  public ArrayList<String> listarTipoPublicacionDePaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    return (ArrayList<String>) controladorOferta.listarTipoDePublicaciones();
  }
  @WebMethod
  public DtPaquetePublicacion obtenerDtpaquete(
      String nombrePaquete)
      throws PaquetePublicacionNoExisteException,
      IOException {
    return controladorOferta.obtenerDtPaquete(nombrePaquete);
  }
  @WebMethod
  public DtTipoPublicacion obtenerDtTipoPublicacion(
      String nombreTipo)
      throws TipoPublicacionNoExisteException {
    return controladorOferta.obtenerDtTipoPublicacion(nombreTipo);
  }
  @WebMethod
  @XmlList
  public ArrayList<String> listarPaquetesNoComprados() {
    return (ArrayList<String>) controladorOferta.listarPaquetesNoComprados();
  }
  @WebMethod
  public ArrayList<DtPaquetePublicacion> listarDtpaquetes()
      throws IOException {
    return (ArrayList<DtPaquetePublicacion>) controladorOferta.listarDtPaquetes();
  }
  @WebMethod
  public Boolean estaCompradoPaquete(String nombrePaquete)
      throws PaquetePublicacionNoExisteException {
    return controladorOferta.estaCompradoPaquete(nombrePaquete);
  }
  
  
  
  
  
  
  /*
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
  */
}