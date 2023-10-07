package controllers;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtempresa;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;

/**
 * Servlet implementation class PerfilSerlvlet
 */
@WebServlet("/perfil")
public class PerfilSerlvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PerfilSerlvlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    Dtusuario usuarioLogueado = (Dtusuario) sesion.getAttribute("usuarioLogueado");
    String nicknameUsuario = request.getParameter("nicknameUsuario");
    IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    try {
      Dtusuario usuario = controladorUsuario.obtenerDtusuario(nicknameUsuario);
      request.setAttribute("usuario", usuario);
      if (usuario instanceof Dtempresa) {
        ArrayList<DtOfertaLaboral> ofertasConfirmadas = (ArrayList<DtOfertaLaboral>) controladorUsuario
            .obtenerDtofertasConfirmadasDeEmpresa(nicknameUsuario);
        ArrayList<DtOfertaLaboral> ofertasIngresadas = (ArrayList<DtOfertaLaboral>) controladorUsuario
            .obtenerDtofertasIngresadasDeEmpresa(nicknameUsuario);
        ArrayList<DtOfertaLaboral> ofertasRechazadas = (ArrayList<DtOfertaLaboral>) controladorUsuario
            .obtenerDtofertasRechazadasDeEmpresa(nicknameUsuario);
        ArrayList<DtpaquetePublicacion> paquetes = (ArrayList<DtpaquetePublicacion>) controladorUsuario
            .obtenerDtpaquetesDeEmpresa(nicknameUsuario);
        request.setAttribute("ofertasConfirmadas", ofertasConfirmadas);
        request.setAttribute("ofertasIngresadas", ofertasIngresadas);
        request.setAttribute("ofertasRechazadas", ofertasRechazadas);
        request.setAttribute("paquetes", paquetes);
        request.setAttribute("tipoUsuario", "empresa");
        
      }
      if (usuario instanceof Dtpostulante) {
        ArrayList<Dtpostulacion> postulaciones = (ArrayList<Dtpostulacion>) controladorUsuario
            .obtenerDtpostulacionesDePostulante(nicknameUsuario);
        request.setAttribute("postulaciones", postulaciones);
        request.setAttribute("tipoUsuario", "postulante");
        
      }
      if (usuarioLogueado != null && nicknameUsuario.equals(usuarioLogueado.getNickname())) {
        request.getRequestDispatcher("/WEB-INF/consultas/miPerfil.jsp").forward(request,
            response);
      }
      request.getRequestDispatcher("/WEB-INF/consultas/Perfil.jsp").forward(request, response);
    } catch (UsuarioNoExisteException e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
      e.printStackTrace();
    }
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    procesarRequest(request, response);
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
  
}
