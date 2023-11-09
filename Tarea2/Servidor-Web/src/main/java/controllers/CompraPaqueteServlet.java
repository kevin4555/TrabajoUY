package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.webservices.DtPostulacion;
import main.java.webservices.PaquetePublicacionNoExisteException;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException;

/**
 * Servlet implementation class ConsultaPaquetes
 */
@WebServlet("/compraPaquete")
public class CompraPaqueteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CompraPaqueteServlet() {
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
    String nicknameempresa =
          request.getParameter("nicknameEmpresa");
    String nombrePaquete =
          request.getParameter("nombrePaquete");
    try {
      ArrayList<String> paquetesNoComprados =
            (ArrayList<String>) port
                  .listarPaquetesNoCompradosDeEmpresa(
                        nicknameempresa)
                  .getItem();
      Boolean permitido = false;
      for (String paquete : paquetesNoComprados) {
        if (paquete.equals(nombrePaquete)) {
          permitido = true;
          break;
        }
      }
      if (permitido) {
        port.comprarPaquete(nicknameempresa, nombrePaquete,
              LocalDate.now().toString());
        String url = request.getContextPath()
              + "/perfil?nicknameUsuario="
              + nicknameempresa;
        response.sendRedirect(url);
        return;
      } else {
        String url = request.getContextPath()
              + "/paquete?nombrePaquete="
              + URLEncoder.encode(nombrePaquete, "UTF-8")
              + "&error=" + URLEncoder
                    .encode("Paquete ya comprado", "UTF-8");
        response.sendRedirect(url);
        return;
      }
    } catch (Exception e) {
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
    procesarRequest(request, response);
  }

}
