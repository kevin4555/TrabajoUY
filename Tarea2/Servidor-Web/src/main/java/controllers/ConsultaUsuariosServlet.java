package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.Publicador;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class ConsultaUsuariosServlet
 */
@WebServlet("/consultaUsuarios")
public class ConsultaUsuariosServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ConsultaUsuariosServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    PublicadorService publicadorService =
          new PublicadorService();
    Publicador cliente =
          publicadorService.getPublicadorPort();

    ArrayList<String> listaUsuarios = (ArrayList<
          String>) cliente.listaDeUsuarios().getItem();
    Collections.sort(listaUsuarios);
    ArrayList<DtUsuario> listaResultado =
          new ArrayList<DtUsuario>();
    for (String nick : listaUsuarios) {
      try {
        listaResultado.add(cliente.obtenerDtUsuario(nick));
      } catch (IOException_Exception
            | UsuarioNoExisteException_Exception e) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/500.jsp")
              .forward(request, response);
        e.printStackTrace();
      }
    }
    request.setAttribute("listaUsuarios", listaResultado);
    request
          .getRequestDispatcher(
                "/WEB-INF/consultas/ConsultaUsuarios.jsp")
          .forward(request, response);
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
