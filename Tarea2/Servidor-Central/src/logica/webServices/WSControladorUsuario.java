package logica.webServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import logica.controllers.Fabrica;
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
