package logica.webServices;

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
import java.util.ArrayList;
import java.util.List;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.PaquetePublicacionNoExisteException;
import excepciones.PostulanteNoEsOfertaFavoritaException;
import excepciones.PostulanteYaEsOfertaFavoritaException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoEstaSeguidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;
import excepciones.UsuarioYaEstaSeguidoException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;
import logica.classes.Empresa;
import logica.classes.Postulante;
import logica.classes.Usuario;
import logica.controllers.Fabrica;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtEmpresa;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.DtPaquetePublicacion;
import logica.datatypes.DtPostulacion;
import logica.datatypes.DtUsuario;
import logica.interfaces.IcontroladorUsuario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WSControladorUsuario {
  
  private Endpoint endpoint = null;
  
  private Fabrica factory = Fabrica.getInstance();
  private IcontroladorUsuario controladorUsuario = factory.obtenerControladorUsuario();
  
  //Constructor
  public WSControladorUsuario(){}

  //Operaciones las cuales quiero publicar

  @WebMethod(exclude = true)
  public void publicar(){
       endpoint = Endpoint.publish("http://localhost:8085/webservices/usuarios", this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
          return endpoint;
  }
  @WebMethod
  public ArrayList<DtCompraPaquete> obtenerDtCompraPaqueteDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException, IOException{ 
	return  (ArrayList<DtCompraPaquete>) controladorUsuario
  .obtenerDtCompraPaqueteDeEmpresa(nicknameEmpresa);
  }
  @WebMethod
  public void altaPostulante(String nickname, String nombre,
	      String apellido, String email, LocalDate fechaNacimiento,
	      String nacionalidad, BufferedImage imagen,
	      String contrasenia) throws UsuarioYaExisteException,
	      UsuarioEmailRepetidoException{ 
		  controladorUsuario.altaPostulante(nickname, nombre, apellido, email, fechaNacimiento, nacionalidad, imagen, contrasenia);
	  }
  @WebMethod
  public void altaEmpresa(String nickname, String nombre,
	      String apellido, String email, String descripcion,
	      String link, BufferedImage imagen, String contrasenia)
	      throws UsuarioYaExisteException,
	      UsuarioEmailRepetidoException{ 
	  	controladorUsuario.altaEmpresa(nickname, nombre, apellido, email, descripcion, link, imagen, contrasenia);
	  }
  @WebMethod
  public DtUsuario obtenerDtusuario(String nickname) throws UsuarioNoExisteException, IOException{ 
		return  controladorUsuario.obtenerDtUsuario(nickname);
	  }
  @WebMethod
  public ArrayList<DtUsuario> obtenerDtUsuarios() throws IOException{ 
		return  (ArrayList<DtUsuario>) controladorUsuario.obtenerDtUsuarios();
	  }
  
  public Empresa obtenerEmpresa(String nicknameEmpresa)
	      throws UsuarioNoExisteException{
	  return controladorUsuario.obtenerEmpresa(nicknameEmpresa);
  }
	  
	  public ArrayList<String> listarEmpresas(){
		  return (ArrayList<String>) controladorUsuario.listarEmpresas();
	  }
	  
	  public ArrayList<String> listaDeUsuarios(){
		  return (ArrayList<String>) controladorUsuario.listaDeUsuarios();
	  }
	  
	  //public void editarDatosBasicos(DtUsuario usuario,
	  //    String nombreNuevo, String apellidoNuevo)
	  //   throws UsuarioNoExisteException;
	  
	  public ArrayList<String> obtenerOfertasEmpresa(
	      String nicknameEmpresa)
	      throws UsuarioNoExisteException{
			  return (ArrayList<String>) controladorUsuario.obtenerOfertasEmpresa(nicknameEmpresa);
		  }
	  
	  //public void registrarPostulacion(String cvReducido,
	  //    String motivacion, LocalDate fechaPostulacion,
	  //    String nickname, String nomOferta, String linkVideo)
	  //    throws UsuarioNoExisteException,
	  //    OfertaLaboralNoExisteException,
	  //    UsuarioYaExistePostulacion;
	  
	  public Postulante obtenerPostulante(String nomPostulante) throws UsuarioNoExisteException{
			  return controladorUsuario.obtenerPostulante(nomPostulante);
		  }
	  
	  
	  public ArrayList<String> listaOfertasUsuario(
	      String nicknameUsuario)
	      throws UsuarioNoExisteException{
			  return (ArrayList<String>) controladorUsuario.listaOfertasUsuario(nicknameUsuario);
		  }
	  
	  public Usuario obtenerUsuario(String nickname)
	      throws UsuarioNoExisteException{
			  return controladorUsuario.obtenerUsuario(nickname);
		  }
	  
	  public ArrayList<String> listarPostulantes(){
		  return (ArrayList<String>) controladorUsuario.listarPostulantes();
	  }
	  
	  //public void editarPostulante(String nickname,
	  //    String nombre, String apellido,
	  //    LocalDate fechaNacimiento, String nacionalidad,
	  //    BufferedImage imagen, String contrasenia)
	  //    throws UsuarioNoExisteException;
	  
	  //public void editarEmpresa(String nickname, String nombre,
	  //    String apellido, String sitioWeb, String descripcion,
	  //    BufferedImage imagen, String contrasenia)
	  //    throws UsuarioNoExisteException;
	  
	  public DtPostulacion obtenerDtPostulacion(
	      String nicknamePostulante, String nombreOferta)throws UsuarioNoExisteException, UsuarioNoExistePostulacion{
			  return controladorUsuario.obtenerDtPostulacion(nicknamePostulante, nombreOferta);
		  }
	  
	 public ArrayList<DtOfertaLaboral> obtenerDtOfertasIngresadasDeEmpresa(
	      String nicknameEmpresa)
	      throws UsuarioNoExisteException, IOException{
			  return (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtOfertasIngresadasDeEmpresa(nicknameEmpresa);
		  }
	  
	 public ArrayList<DtOfertaLaboral> obtenerDtOfertasConfirmadasDeEmpresa(
	      String nicknameEmpresa)
	      throws UsuarioNoExisteException, IOException {
			  return (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtOfertasConfirmadasDeEmpresa(nicknameEmpresa);
		  }
	  
	 public ArrayList<DtOfertaLaboral> obtenerDtOfertasRechazadasDeEmpresa(
	      String nicknameEmpresa)
	      throws UsuarioNoExisteException, IOException{
			  return (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtOfertasRechazadasDeEmpresa(nicknameEmpresa);
		  }
	  
	 public Boolean confirmarContrasenia(String clave,
	      String contrasenia) throws UsuarioNoExisteException{
		  return controladorUsuario.confirmarContrasenia(clave, contrasenia);
	  }
	  
	  
	 public ArrayList<DtPaquetePublicacion> obtenerDtPaquetesDeEmpresa(
	      String nicknameEmpresa)
	      throws UsuarioNoExisteException, IOException{
			  return (ArrayList<DtPaquetePublicacion>) controladorUsuario.obtenerDtPaquetesDeEmpresa(nicknameEmpresa);
		  }
	  
	 public ArrayList<DtPostulacion> obtenerDtPostulacionesDePostulante(
	      String nicknamePostulante)
	      throws UsuarioNoExisteException{
			  return (ArrayList<DtPostulacion>) controladorUsuario.obtenerDtPostulacionesDePostulante(nicknamePostulante);
		  }
	  
	 public ArrayList<String> listarPaquetesNoCompradosDeEmpresa(
	      String nicknameEmpresa)
	      throws UsuarioNoExisteException{
			  return (ArrayList<String>) controladorUsuario.listarPaquetesNoCompradosDeEmpresa(nicknameEmpresa);
		  }
	  
	  //void comprarPaquete(String nicknameEmpresa,
	  //    String nombrePaquete, LocalDate fechaCompra)
	  //   throws UsuarioNoExisteException,
	  //    PaquetePublicacionNoExisteException;

	  //void agregarSeguidor(String nicknameUsuario, String nicknameSeguidor)
	  //    throws UsuarioNoExisteException, UsuarioYaEstaSeguidoException;
	  
	  //void dejarDeSeguir(String nicknameUsuario, String nicknameSeguidor)
	  //    throws UsuarioNoExisteException, UsuarioNoEstaSeguidoException;
	  
	 // void agregarOfertaFavorita(String nicknamePostulante, String nombreOferta) throws UsuarioNoExisteException, PostulanteYaEsOfertaFavoritaException,
	 //     OfertaLaboralNoExisteException;

	  //void removerOfertaFavorita(String nicknamePsotulante, String nombreOferta) throws UsuarioNoExisteException, PostulanteNoEsOfertaFavoritaException;

	public ArrayList<DtOfertaLaboral> obtenerDtOfertasFinalizadasDeEmpresa(String nicknameEmpresa)
	      throws UsuarioNoExisteException, IOException{
			  return (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtOfertasFinalizadasDeEmpresa(nicknameEmpresa);
		  }

	public ArrayList<DtEmpresa> buscarEmpresas(String parametro) throws IOException{
		  return (ArrayList<DtEmpresa>) controladorUsuario.buscarEmpresas(parametro);
	 }

  /*@WebMethod
  public String obtenerApellido(DataPersona dp){
      Logica l = new Logica();
      return l.obtenerApellido(dp);
  }
  @WebMethod
  public DataMaestro obtenerConvocados(String apellido){
      Logica l = new Logica();
      return l.obtenerConvocados(apellido);
  }
  
  
  @WebMethod
  public byte[] getFile(@WebParam(name = "fileName") String name)
                  throws  IOException {
      byte[] byteArray = null;
      try {
              File f = new File("files/" + name);
              FileInputStream streamer = new FileInputStream(f);
              byteArray = new byte[streamer.available()];
              streamer.read(byteArray);
      } catch (IOException e) {
              throw e;
      }
      return byteArray;
  }*/
  
}
