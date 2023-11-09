package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.webservices.DtTipoPublicacion;
import logica.webservices.Publicador;
import logica.webservices.PublicadorService;
import logica.webservices.TipoPublicacionNoExisteException_Exception;

/**
 * Servlet implementation class TipoPostulacionServlet
 */
@WebServlet("/tipoPublicacion")
public class TipoPublicacionServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public TipoPublicacionServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    String nombreTipo =
          request.getParameter("nombreTipoPublicacion");
    PublicadorService publicadorService =
          new PublicadorService();
    Publicador cliente =
          publicadorService.getPublicadorPort();
    try {
      DtTipoPublicacion tipoPublicacion =
            cliente.obtenerDtTipoPublicacion(nombreTipo);
      request.setAttribute("tipoPublicacion",
            tipoPublicacion);
      request
            .getRequestDispatcher(
                  "/WEB-INF/consultas/TipoPublicacion.jsp")
            .forward(request, response);
    } catch (TipoPublicacionNoExisteException_Exception e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
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
