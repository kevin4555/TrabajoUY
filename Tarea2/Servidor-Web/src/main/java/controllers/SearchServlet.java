package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtEmpresa;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.IOException_Exception;
import main.java.webservices.PublicadorService;
import model.EstadoSesion;
import model.TipoUsuario;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public SearchServlet() {
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

    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador cliente =
          publicadorService.getPublicadorPort();
    String toSearch = request.getParameter("toSearch");

    if (toSearch == null) {
      toSearch = "";
    }
    ArrayList<DtOfertaLaboral> dtOfertas;
    ArrayList<DtEmpresa> dtEmpresas;
    try {

      dtOfertas = (ArrayList<DtOfertaLaboral>) cliente
            .buscarOfertas(toSearch).getItem();
      dtEmpresas = (ArrayList<DtEmpresa>) cliente
            .buscarEmpresas(toSearch).getItem();
    } catch (IOException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }

    boolean alfabetico = false;
    if (request.getParameter("orden") != null
          && "alfabetico"
                .equals(request.getParameter("orden"))) {
      Collections.sort(dtOfertas, Comparator
            .comparing(DtOfertaLaboral::getNombre));
      Collections.sort(dtEmpresas,
            Comparator.comparing(DtEmpresa::getNickname));
      alfabetico = true;
    }

    request.setAttribute("listaOfertas", dtOfertas);
    request.setAttribute("listaEmpresas", dtEmpresas);
    request.setAttribute("toSearch", toSearch);
    request.setAttribute("alfabetico", alfabetico);
    request
          .getRequestDispatcher(
                "/WEB-INF/consultas/Search.jsp")
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
