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

import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.controllers.Fabrica;
import logica.datatypes.DtCompraPaquete;
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
       endpoint = Endpoint.publish("http://localhost:8085/webservices", this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
          return endpoint;
  }
  
  public ArrayList<DtCompraPaquete> obtenerDtCompraPaqueteDeEmpresa(String nicknameEmpresa) throws UsuarioNoExisteException, IOException{ 
	return  (ArrayList<DtCompraPaquete>) controladorUsuario
  .obtenerDtCompraPaqueteDeEmpresa(nicknameEmpresa);
  }
  
  public void altaPostulante(String nickname, String nombre,
	      String apellido, String email, LocalDate fechaNacimiento,
	      String nacionalidad, BufferedImage imagen,
	      String contrasenia) throws UsuarioYaExisteException,
	      UsuarioEmailRepetidoException{ 
		  controladorUsuario.altaPostulante(nickname, nombre, apellido, email, fechaNacimiento, nacionalidad, imagen, contrasenia);
	  }
  public void altaEmpresa(String nickname, String nombre,
	      String apellido, String email, String descripcion,
	      String link, BufferedImage imagen, String contrasenia)
	      throws UsuarioYaExisteException,
	      UsuarioEmailRepetidoException{ 
	  	controladorUsuario.altaEmpresa(nickname, nombre, apellido, email, descripcion, link, imagen, contrasenia);
	  }
  
  public DtUsuario obtenerDtusuario(String nickname) throws UsuarioNoExisteException, IOException{ 
		return  controladorUsuario.obtenerDtUsuario(nickname);
	  }
  
  public ArrayList<DtUsuario> obtenerDtUsuarios() throws IOException{ 
		return  (ArrayList<DtUsuario>) controladorUsuario.obtenerDtUsuarios();
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
  }*/
  
  
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
  }
  
}
