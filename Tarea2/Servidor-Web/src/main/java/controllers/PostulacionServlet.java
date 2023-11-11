package controllers;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.OfertaLaboralNoExisteException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;
import main.java.webservices.UsuarioYaExistePostulacion_Exception;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class PostulacionServlet
 */
@WebServlet("/postulacion")
public class PostulacionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public PostulacionServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    String userAgent = request.getHeader("User-Agent");
    main.java.webservices.PublicadorService service =
          new PublicadorService();
    main.java.webservices.Publicador port =
          service.getPublicadorPort();

    if (sesion.getAttribute("estadoSesion")
          != EstadoSesion.LOGIN_CORRECTO
          || sesion.getAttribute("tipoUsuario")
                != TipoUsuario.POSTULANTE) {
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
      return;
    }

    String nombreOferta =
          request.getParameter("nombreOferta");
    String cVReducido = request.getParameter("cVReducido");
    String motivacion = request.getParameter("motivacion");
    String video = request.getParameter("video");
    LocalDate fechaActual = LocalDate.now();
    String fecha = fechaActual.toString();
    DtUsuario usuario = (DtUsuario) sesion
          .getAttribute("usuarioLogueado");

    try {
      port.registrarPostulacion(cVReducido, motivacion,
            fecha, usuario.getNickname(), nombreOferta,
            video);
      if (userAgent != null
              && userAgent.toLowerCase().contains("mobile")) {
    	  String url = request.getContextPath()
  	            + "/home";
    	  response.sendRedirect(url);
    	  return;
      }else
      {
    	  String url = request.getContextPath()
    	            + "/perfil?nicknameUsuario="
    	            + usuario.getNickname();
    	      response.sendRedirect(url);
    	      return;
      }
    } catch (OfertaLaboralNoExisteException_Exception
          | UsuarioNoExisteException_Exception
          | UsuarioYaExistePostulacion_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    String userAgent = request.getHeader("User-Agent");
    HttpSession sesion = request.getSession();

    main.java.webservices.PublicadorService service =
          new PublicadorService();
    main.java.webservices.Publicador port =
          service.getPublicadorPort();

    if (sesion.getAttribute("estadoSesion")
          != EstadoSesion.LOGIN_CORRECTO
          || request.getParameter("nombreOferta") == null) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      return;
    }

    String nombreOferta =
          request.getParameter("nombreOferta");

    try {
      DtOfertaLaboral oferta =
            port.obtenerDtOfertaLaboral(nombreOferta);
      request.setAttribute("oferta", oferta);
      if (userAgent != null
            && userAgent.toLowerCase().contains("mobile")) {
        request.getRequestDispatcher(
              "/WEB-INF/mobile/registros/PostulacionMobile.jsp")
              .forward(request,
                    response);
      } else {
        request
              .getRequestDispatcher(
                    "/WEB-INF/registros/Postulacion.jsp")
              .forward(request, response);
      }
    } catch (IOException_Exception
          | OfertaLaboralNoExisteException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    // TODO Auto-generated method stub
    procesarRequest(request, response);
  }

}
