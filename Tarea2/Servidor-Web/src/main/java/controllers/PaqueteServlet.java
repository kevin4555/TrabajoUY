package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.webservices.DtPaquetePublicacion;
import main.java.webservices.IOException_Exception;
import main.java.webservices.PaquetePublicacionNoExisteException_Exception;
import main.java.webservices.PublicadorService;

/**
 * Servlet implementation class PaqueteServlet
 */
@WebServlet("/paquete")
public class PaqueteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public PaqueteServlet() {
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
    try {
      String nombrePaquete =
            request.getParameter("nombrePaquete");
      DtPaquetePublicacion paquete =
            port.obtenerDtpaquete(nombrePaquete);
      request.setAttribute("paquete", paquete);
      request
            .getRequestDispatcher(
                  "/WEB-INF/consultas/Paquete.jsp")
            .forward(request, response);
      return;
    } catch (IOException_Exception
          | PaquetePublicacionNoExisteException_Exception e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
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
