package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.DtPostulacion;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.OfertaLaboralNoExisteException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;
import main.java.webservices.UsuarioNoExistePostulacion_Exception;
import model.EstadoSesion;

import java.io.IOException;

/**
 * Servlet implementation class VerPostulacionServlet
 */
@WebServlet("/verPostulacion")
public class VerPostulacionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public VerPostulacionServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    main.java.webservices.PublicadorService service =
          new PublicadorService();
    main.java.webservices.Publicador port =
          service.getPublicadorPort();

    String nicknamePostulante =
          request.getParameter("nicknamePostulante");
    String nombreOferta =
          request.getParameter("nombreOferta");
    String userAgent = request.getHeader("User-Agent");
    HttpSession sesion = request.getSession();

    if (nombreOferta == null || nicknamePostulante == null
          || sesion.getAttribute("estadoSesion")
                != EstadoSesion.LOGIN_CORRECTO) {
      request.setAttribute("error", "acceso denegado");
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
      return;
    }

    try {
      DtUsuario postulante =
            port.obtenerDtUsuario(nicknamePostulante);
      DtOfertaLaboral dtOferta =
            port.obtenerDtOfertaLaboral(nombreOferta);
      System.out.println(dtOferta.getFechaResolucionString());
      DtPostulacion postulacion = port.obtenerDtPostulacion(
            nicknamePostulante, nombreOferta);
      DtUsuario usuario = (DtUsuario) sesion
            .getAttribute("usuarioLogueado");
      if (!usuario.getNickname()
            .equals(dtOferta.getEmpresa())
            && !usuario.getNickname()
                  .equals(postulante.getNickname())) {
        request.setAttribute("error", "acceso denegado");
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/404.jsp")
              .forward(request, response);
        return;
      }

      request.setAttribute("ofertas", dtOferta);
      request.setAttribute("postulacion", postulacion);
      request.setAttribute("postulante", postulante);

    } catch (UsuarioNoExisteException_Exception
          | UsuarioNoExistePostulacion_Exception
          | IOException_Exception
          | OfertaLaboralNoExisteException_Exception e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    if (userAgent != null
          && userAgent.toLowerCase().contains("mobile")) {
      request.getRequestDispatcher(
            "/WEB-INF/mobile/consultas/VerPostulacionMobile.jsp")
            .forward(request,
                  response);
    } else {
      request
            .getRequestDispatcher(
                  "/WEB-INF/consultas/VerPostulacion.jsp")
            .forward(request, response);
    }
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    procesarRequest(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    procesarRequest(request, response);
  }

}
