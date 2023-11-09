package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtEmpresa;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.DtPostulacion;
import main.java.webservices.DtPostulante;
import main.java.webservices.DtUsuario;
import main.java.webservices.EstadoOferta;
import main.java.webservices.IOException_Exception;
import main.java.webservices.OfertaLaboralNoExisteException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;
import model.EstadoSesion;

/**
 * Servlet implementation class OfertaServlet
 */
@WebServlet("/oferta")
public class OfertaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public OfertaServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    String nombreOferta =
          request.getParameter("nombreOferta");
    HttpSession sesion = request.getSession();
    String userAgent = request.getHeader("User-Agent");

    try {
      DtOfertaLaboral oferta =
            port.obtenerDtOfertaLaboral(nombreOferta);
      Map<String, String> mapImagen =
            new HashMap<String, String>();
      for (DtPostulacion postulacion : oferta
            .getPostulaciones()) {
        DtUsuario postulante = port.obtenerDtUsuario(
              postulacion.getNicknamePostulante());
        mapImagen.put(postulante.getNickname(),
              postulante.getImagenBase64());
      }
      request.setAttribute("mapImagenes", mapImagen);
      request.setAttribute("oferta", oferta);
      if (sesion.getAttribute("estadoSesion")
            == EstadoSesion.LOGIN_CORRECTO) {
        DtUsuario usuario = (DtUsuario) sesion
              .getAttribute("usuarioLogueado");
        if (usuario instanceof DtPostulante) {
          Boolean estaPostulado = port.estaPostulado(
                usuario.getNickname(), nombreOferta);
          if (!estaPostulado && (oferta.isEstaVencida()
                || oferta.getEstadoOferta()
                      != EstadoOferta.CONFIRMADA)) {
            request.setAttribute("error",
                  "acceso denegado");
            request
                  .getRequestDispatcher(
                        "/WEB-INF/error/404.jsp")
                  .forward(request, response);
            return;
          }
          request.setAttribute("estaPostulado",
                estaPostulado);
        }
        if (usuario instanceof DtEmpresa) {
          Boolean miOferta = usuario.getNickname()
                .equals(oferta.getEmpresa());
          if (!miOferta && (oferta.isEstaVencida()
                || oferta.getEstadoOferta()
                      != EstadoOferta.CONFIRMADA)) {
            request.setAttribute("error",
                  "acceso denegado");
            request
                  .getRequestDispatcher(
                        "/WEB-INF/error/404.jsp")
                  .forward(request, response);
            return;
          }
          request.setAttribute("miOferta", miOferta);
        }
      } else if (oferta.isEstaVencida()
            || oferta.getEstadoOferta()
                  != EstadoOferta.CONFIRMADA) {
        request.setAttribute("error", "acceso denegado");
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/404.jsp")
              .forward(request, response);
        return;
      }

    } catch (OfertaLaboralNoExisteException_Exception
          | UsuarioNoExisteException_Exception
          | IOException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }

    if (userAgent != null
          && userAgent.toLowerCase().contains("mobile")) {
      request.getRequestDispatcher(
            "/WEB-INF/mobile/consultas/OfertaMobile.jsp")
            .forward(request, response);
    } else {
      request
            .getRequestDispatcher(
                  "/WEB-INF/consultas/Oferta.jsp")
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
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
