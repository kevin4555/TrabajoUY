
package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.controllers.Fabrica;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;
import logica.webservices.DtCompraPaqueteArray;
import logica.webservices.DtOfertaLaboral;
import logica.webservices.DtOfertaLaboralArray;
import logica.webservices.DtcantidadTipoPublicacionArray;
import logica.webservices.DtpaquetePublicacion;
import logica.webservices.DtpaquetePublicacionArray;
import logica.webservices.Dtpostulacion;
import logica.webservices.DtpostulacionArray;
import logica.webservices.DttipoPublicacion;
import logica.webservices.DtusuarioArray;
import logica.webservices.EstadoOferta;
import logica.webservices.IOException_Exception;
import logica.webservices.KeywordNoExisteException_Exception;
import logica.webservices.KeywordYaExisteException_Exception;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.OfertaLaboralNoTienePaquete_Exception;
import logica.webservices.OfertaLaboralYaExisteException_Exception;
import logica.webservices.PaquetePublicacionNoExisteException_Exception;
import logica.webservices.PaquetePublicacionYaExisteException_Exception;
import logica.webservices.PaquetePublicacionYaFueComprado_Exception;
import logica.webservices.Publicador;
import logica.webservices.TipoDePublicacionYaFueIngresado_Exception;
import logica.webservices.TipoPublicacionNoExisteException_Exception;
import logica.webservices.TipoPublicacionYaExisteException_Exception;
import logica.webservices.UsuarioEmailRepetidoException_Exception;
import logica.webservices.UsuarioNoExisteException_Exception;
import logica.webservices.UsuarioNoExistePostulacion_Exception;
import logica.webservices.UsuarioYaExisteException_Exception;
import logica.webservices.UsuarioYaExistePostulacion_Exception;
import logica.webservices.net.java.dev.jaxb.array.StringArray;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class AltaUsuario
 */
@MultipartConfig()
@WebServlet("/altaUsuario")
public class AltaUsuarioServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AltaUsuarioServlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  private void procesarRequest(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    IcontroladorUsuario controladorUsuario = Fabrica.getInstance()
        .obtenerControladorUsuario();
    String nickname = request.getParameter("nickname");
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String email = request.getParameter("email");
    String contrasenia = request.getParameter("contrasenia");
    String contraseniaConf = request.getParameter("contraseniaConf");
    BufferedImage imagen = null;
    if (!contraseniaConf.equals(contrasenia)) {
      request.setAttribute("mensajeError", "contraseña incorrecta");
      request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp")
          .forward(request, response);
      return;
    }
    try {
      Part filePart = request.getPart("imagen");
      if (filePart != null && filePart.getSize() > 0) {
        InputStream fileContent = filePart.getInputStream();
        imagen = ImageIO.read(fileContent);
      }
    } catch (IOException | ServletException e) {
      System.out.println("sale en imagen");
      request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request,
          response);
      e.printStackTrace();
      return;
    }
    if (request.getParameter("tipoUsuario").equals("postulante")) {
      String nacionalidad = request.getParameter("nacionalidad");
      LocalDate fechaNacimiento = LocalDate
          .parse(request.getParameter("fechaNacimiento"));
      try {
        controladorUsuario.altaPostulante(nickname, nombre, apellido, email,
            fechaNacimiento, nacionalidad, imagen, contrasenia);
        sesion.setAttribute("tipoUsuario", TipoUsuario.POSTULANTE);
      } catch (UsuarioYaExisteException | UsuarioEmailRepetidoException e) {
        request.setAttribute("mensajeError", "nickname o email repetido");
        request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp")
            .forward(request, response);
        return;
      }
    }
    if (request.getParameter("tipoUsuario").equals("empresa")) {
      String descriopcion = request.getParameter("descripcion");
      String sitioWeb = request.getParameter("sitioWeb");
      try {
        controladorUsuario.altaEmpresa(nickname, nombre, apellido, email,
            descriopcion, sitioWeb, imagen, contrasenia);
        sesion.setAttribute("tipoUsuario", TipoUsuario.EMPRESA);
      } catch (UsuarioYaExisteException | UsuarioEmailRepetidoException e) {
        request.setAttribute("mensajeError", "nickname o email repetido");
        request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp")
            .forward(request, response);
        return;
      }
    }
    sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_CORRECTO);
    try {
      Dtusuario usuario = controladorUsuario.obtenerDtusuario(nickname);
      sesion.setAttribute("usuarioLogueado", usuario);
      request.getRequestDispatcher("/home").forward(request, response);
    } catch (UsuarioNoExisteException e) {
      System.out.println("sale al buscar usuario");
      request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request,
          response);
      e.printStackTrace();
      return;
    }
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest
   *      request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp")
        .forward(request, response);
    logica.webservices.Publicador service = new Publicador() {
      @Override
      public void registrarPostulacion(String arg0, String arg1,
          logica.webservices.LocalDate arg2, String arg3, String arg4)
          throws OfertaLaboralNoExisteException_Exception,
          UsuarioNoExisteException_Exception,
          UsuarioYaExistePostulacion_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void registrarPaquete(String arg0, String arg1, int arg2,
          float arg3,
          logica.webservices.BufferedImage arg4,
          logica.webservices.LocalDate arg5,
          DtcantidadTipoPublicacionArray arg6)
          throws PaquetePublicacionYaExisteException_Exception,
          TipoPublicacionNoExisteException_Exception,
          TipoPublicacionYaExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public StringArray obtenerOfertasEmpresaUsuario(String arg0)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray obtenerOfertasEmpresa(String arg0)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray obtenerKeywordsDeOfertaLaboral(String arg0)
          throws OfertaLaboralNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtusuarioArray obtenerDtusuarios() throws IOException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public logica.webservices.Dtusuario obtenerDtusuario(String arg0)
          throws IOException_Exception, UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DttipoPublicacion obtenerDttipoPublicacion(String arg0)
          throws TipoPublicacionNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtpostulacionArray obtenerDtpostulacionesDePostulante(String arg0)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public Dtpostulacion obtenerDtpostulacion(String arg0, String arg1)
          throws UsuarioNoExisteException_Exception,
          UsuarioNoExistePostulacion_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtpaquetePublicacionArray obtenerDtpaquetesDeEmpresa(String arg0)
          throws IOException_Exception, UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtpaquetePublicacion obtenerDtpaquete(String arg0)
          throws IOException_Exception,
          PaquetePublicacionNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtOfertaLaboralArray obtenerDtofertasRechazadasDeEmpresa(
          String arg0)
          throws IOException_Exception, UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtOfertaLaboralArray obtenerDtofertasPorKeyword(String arg0)
          throws IOException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtOfertaLaboralArray obtenerDtofertasIngresadasDeEmpresa(
          String arg0)
          throws IOException_Exception, UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtOfertaLaboralArray obtenerDtofertasConfirmadasDeEmpresa(
          String arg0)
          throws IOException_Exception, UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtpostulacionArray obtenerDtPostulacionesDeOferta(String arg0)
          throws OfertaLaboralNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtpaquetePublicacion obtenerDtPaquetePublicacion(String arg0)
          throws IOException_Exception,
          OfertaLaboralNoExisteException_Exception,
          OfertaLaboralNoTienePaquete_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtOfertaLaboralArray obtenerDtOfertasConfirmadas()
          throws IOException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtOfertaLaboral obtenerDtOfertaLaboral(String arg0)
          throws IOException_Exception,
          OfertaLaboralNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtCompraPaqueteArray obtenerDtCompraPaqueteDeEmpresa(String arg0)
          throws IOException_Exception, UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarTipoPublicacionDePaquete(String arg0)
          throws PaquetePublicacionNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarTipoDePublicaciones() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarPostulantes() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarPaquetesNoCompradosDeEmpresa(String arg0)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarPaquetesNoComprados() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarPaquetes() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarKeywords() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listarEmpresas() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public DtpaquetePublicacionArray listarDtpaquetes()
          throws IOException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listaOfertasUsuario(String arg0)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public StringArray listaDeUsuarios() {
        // TODO Auto-generated method stub
        return null;
      }
      
      @Override
      public boolean estaPostulado(String arg0, String arg1)
          throws OfertaLaboralNoExisteException_Exception,
          UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public boolean estaCompradoPorPaqueteOferta(String arg0)
          throws OfertaLaboralNoExisteException_Exception {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public boolean estaCompradoPaquete(String arg0)
          throws PaquetePublicacionNoExisteException_Exception {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public void editarPostulante(String arg0, String arg1, String arg2,
          logica.webservices.LocalDate arg3, String arg4,
          logica.webservices.BufferedImage arg5, String arg6)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void editarEmpresa(String arg0, String arg1, String arg2,
          String arg3,
          String arg4, logica.webservices.BufferedImage arg5, String arg6)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void editarDatosBasicos(logica.webservices.Dtusuario arg0,
          String arg1,
          String arg2) throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public boolean confirmarContrasenia(String arg0, String arg1)
          throws UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
        return false;
      }
      
      @Override
      public void comprarPaquete(String arg0, String arg1,
          logica.webservices.LocalDate arg2)
          throws PaquetePublicacionNoExisteException_Exception,
          UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void altaTipoPublicacion(String arg0, String arg1, String arg2,
          int arg3, float arg4, logica.webservices.LocalDate arg5)
          throws TipoPublicacionYaExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void altaPostulante(String arg0, String arg1, String arg2,
          String arg3,
          logica.webservices.LocalDate arg4, String arg5,
          logica.webservices.BufferedImage arg6, String arg7)
          throws UsuarioEmailRepetidoException_Exception,
          UsuarioYaExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void altaOfertaLaboral(String arg0, String arg1, String arg2,
          String arg3, float arg4, String arg5, String arg6,
          logica.webservices.LocalDate arg7, String arg8, String arg9,
          StringArray arg10, logica.webservices.BufferedImage arg11,
          String arg12)
          throws KeywordNoExisteException_Exception,
          OfertaLaboralYaExisteException_Exception,
          TipoPublicacionNoExisteException_Exception,
          UsuarioNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void altaKeyword(String arg0)
          throws KeywordYaExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void altaEmpresa(String arg0, String arg1, String arg2,
          String arg3,
          String arg4, String arg5, logica.webservices.BufferedImage arg6,
          String arg7) throws UsuarioEmailRepetidoException_Exception,
          UsuarioYaExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void agregarTipoPublicacionAlPaquete(int arg0, String arg1,
          String arg2) throws PaquetePublicacionNoExisteException_Exception,
          PaquetePublicacionYaFueComprado_Exception,
          TipoDePublicacionYaFueIngresado_Exception,
          TipoPublicacionNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
      
      @Override
      public void aceptarRechazarOfertaLaboral(String arg0, EstadoOferta arg1,
          logica.webservices.LocalDate arg2)
          throws OfertaLaboralNoExisteException_Exception {
        // TODO Auto-generated method stub
      }
    };
    logica.webservices.Publicador port = service.getWebServicesPort();
    complejoservidor.publicar.DataMaestro maestro = port
        .obtenerConvocados("Tabárez");
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest
   *      request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    procesarRequest(request, response);
  }
}
