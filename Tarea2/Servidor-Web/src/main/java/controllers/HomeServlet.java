package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.IOException_Exception;
import main.java.webservices.PublicadorService;
import model.EstadoSesion;
import model.TipoUsuario;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public HomeServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private static void
        initSesion(HttpServletRequest request) {
    HttpSession sesion = request.getSession();
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();

    ArrayList<String> listaKeywords = (ArrayList<
          String>) port.listarKeywords().getItem();
    sesion.setAttribute("listaKeywords", listaKeywords);

    if (sesion.getAttribute("estadoSesion") == null) {
      sesion.setAttribute("estadoSesion",
            EstadoSesion.NO_LOGIN);
    }
    if (sesion.getAttribute("tipoUsuario") == null) {
      sesion.setAttribute("tipoUsuario",
            TipoUsuario.VISITANTE);
    }
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    initSesion(request);

    HttpSession sesion = request.getSession();
    String userAgent = request.getHeader("User-Agent");
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador cliente =
          publicadorService.getPublicadorPort();
    ArrayList<DtOfertaLaboral> dTOfertas;
    try {
      dTOfertas = (ArrayList<DtOfertaLaboral>) cliente
            .obtenerDtOfertasConfirmadas().getItem();
    } catch (IOException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    request.setAttribute("listaOfertasConfirmadas",
          dTOfertas);
    if (userAgent != null
          && userAgent.toLowerCase().contains("mobile")) {
      if (sesion.getAttribute("estadoSesion")
            == EstadoSesion.LOGIN_CORRECTO) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/mobile/home/HomeMobile.jsp")
              .forward(request, response);
      } else {
        request
              .getRequestDispatcher(
                    "/WEB-INF/mobile/home/LoginMobile.jsp")
              .forward(request, response);
      }
    } else {
      request.getRequestDispatcher("/WEB-INF/home/Home.jsp")
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
