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
import jakarta.servlet.http.HttpSession;
import logica.webservices.DtPostulacion;
import logica.webservices.DtPostulante;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.PaquetePublicacionNoExisteException;
import logica.webservices.PostulanteNoEsOfertaFavoritaException_Exception;
import logica.webservices.PostulanteYaEsOfertaFavoritaException_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioNoExisteException;
import logica.webservices.UsuarioNoExisteException_Exception;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class ConsultaPaquetes
 */
@WebServlet("/ofertaFavorita")
public class AddOrRemoveOfertaFavorita extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddOrRemoveOfertaFavorita() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    if (!sesion.getAttribute("estadoSesion")
          .equals(EstadoSesion.LOGIN_CORRECTO)
          || !sesion.getAttribute("tipoUsuario")
                .equals(TipoUsuario.POSTULANTE)) {
      response.sendError(403, "No autorizado");
      return;
    }
    DtPostulante dtPostulante = (DtPostulante) sesion
          .getAttribute("usuarioLogueado");

    PublicadorService publicadorService =
          new PublicadorService();
    logica.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    String nicknamePostulante =
          request.getParameter("nicknamePostulante");
    String nombreOferta =
          request.getParameter("nombreOferta");
    try {
      if (dtPostulante.getOfertasFavoritas()
            .contains(nombreOferta)) {
        port.removerOfertaFavorita(nicknamePostulante,
              nombreOferta);
        dtPostulante.getOfertasFavoritas()
              .remove(nombreOferta);
      } else {
        port.agregarOfertaFavorita(nicknamePostulante,
              nombreOferta);
        dtPostulante.getOfertasFavoritas()
              .add(nombreOferta);
      }
      sesion.setAttribute("usuarioLogueado", dtPostulante);
      response.setStatus(200);
    } catch (Exception e) {
      response.sendError(400, e.getMessage());
    }
    return;
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
