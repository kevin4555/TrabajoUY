package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtCompraPaquete;
import main.java.webservices.DtEmpresa;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.DtPostulacion;
import main.java.webservices.DtPostulante;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.OfertaLaboralNoExisteException_Exception;
import main.java.webservices.OfertaLaboralNoSePuedeFinalizar_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class PerfilSerlvlet
 */
@WebServlet("/finalizarOferta")
public class FinalizarOfertaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public FinalizarOfertaServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession sesion = request.getSession();
    DtUsuario usuarioLogueado = (DtUsuario) sesion
          .getAttribute("usuarioLogueado");
    String nicknameUsuario =
          request.getParameter("nicknameUsuario");
    String nombreOferta =
          request.getParameter("nombreOferta");
    main.java.webservices.PublicadorService service =
          new PublicadorService();
    main.java.webservices.Publicador port =
          service.getPublicadorPort();
    try {
      port.finalizarOferta(nombreOferta);
      DtUsuario usuario =
            port.obtenerDtUsuario(nicknameUsuario);
      request.setAttribute("usuario", usuario);
      if (usuario instanceof DtEmpresa) {
        ArrayList<DtOfertaLaboral> ofertasConfirmadas =
              (ArrayList<DtOfertaLaboral>) port
                    .obtenerDtOfertasConfirmadasDeEmpresa(
                          nicknameUsuario)
                    .getItem();

        ArrayList<DtOfertaLaboral> ofertasIngresadas =
              (ArrayList<DtOfertaLaboral>) port
                    .obtenerDtOfertasIngresadasDeEmpresa(
                          nicknameUsuario)
                    .getItem();

        ArrayList<DtOfertaLaboral> ofertasRechazadas =
              (ArrayList<DtOfertaLaboral>) port
                    .obtenerDtOfertasRechazadasDeEmpresa(
                          nicknameUsuario)
                    .getItem();

        ArrayList<DtCompraPaquete> compraPaquetes =
              (ArrayList<DtCompraPaquete>) port
                    .obtenerDtCompraPaqueteDeEmpresa(
                          nicknameUsuario)
                    .getItem();

        request.setAttribute("ofertasConfirmadas",
              ofertasConfirmadas);
        request.setAttribute("ofertasIngresadas",
              ofertasIngresadas);
        request.setAttribute("ofertasRechazadas",
              ofertasRechazadas);
        request.setAttribute("compraPaquetes",
              compraPaquetes);
        request.setAttribute("tipoUsuario", "empresa");
      }
      if (usuario instanceof DtPostulante) {
        ArrayList<DtPostulacion> postulaciones =
              (ArrayList<DtPostulacion>) port
                    .obtenerDtPostulacionesDePostulante(
                          nicknameUsuario)
                    .getItem();

        request.setAttribute("postulaciones",
              postulaciones);
        request.setAttribute("tipoUsuario", "postulante");
      }
    } catch (OfertaLaboralNoExisteException_Exception
          | OfertaLaboralNoSePuedeFinalizar_Exception
          | IOException_Exception
          | UsuarioNoExisteException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
    }

    if (usuarioLogueado != null && nicknameUsuario
          .equals(usuarioLogueado.getNickname())) {
      request
            .getRequestDispatcher(
                  "/WEB-INF/consultas/miPerfil.jsp")
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

    // TODO Auto-generated catch block

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
