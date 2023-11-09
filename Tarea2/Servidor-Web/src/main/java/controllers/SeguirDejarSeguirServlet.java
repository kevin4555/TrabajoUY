package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import main.java.webservices.DtCompraPaquete;
import main.java.webservices.DtEmpresa;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.DtPostulacion;
import main.java.webservices.DtPostulante;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoEstaSeguidoException_Exception;
import main.java.webservices.UsuarioNoExisteException_Exception;
import main.java.webservices.UsuarioYaEstaSeguidoException_Exception;

@WebServlet("/seguirDejarSeguir")
public class SeguirDejarSeguirServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public SeguirDejarSeguirServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    DtUsuario usuarioLogueado = (DtUsuario) sesion
          .getAttribute("usuarioLogueado");
    if (usuarioLogueado == null) {
      response.sendError(403, "No autorizado");
      return;
    }

    String nicknameSeguido =
          request.getParameter("perfilUsuario");

    if (nicknameSeguido
          .equals(usuarioLogueado.getNickname())) {
      response.sendError(400, "Bad Request");
      return;
    }
    boolean seguir = !usuarioLogueado.getSeguidos()
          .contains(nicknameSeguido);
    main.java.webservices.PublicadorService service =
          new PublicadorService();
    main.java.webservices.Publicador port =
          service.getPublicadorPort();
    Boolean flag = false;

    try {
      if (seguir) {
        port.agregarSeguidor(nicknameSeguido,
              usuarioLogueado.getNickname());
        DtUsuario usuarioActualizado =
              port.obtenerDtUsuario(
                    usuarioLogueado.getNickname());
        sesion.setAttribute("usuarioLogueado",
              usuarioActualizado);
        flag = true;
      } else {
        port.dejarDeSeguir(nicknameSeguido,
              usuarioLogueado.getNickname());
        DtUsuario usuarioActualizado =
              port.obtenerDtUsuario(
                    usuarioLogueado.getNickname());
        sesion.setAttribute("usuarioLogueado",
              usuarioActualizado);
        flag = false;
      }
    } catch (UsuarioNoExisteException_Exception
          | IOException_Exception
          | UsuarioNoEstaSeguidoException_Exception
          | UsuarioYaEstaSeguidoException_Exception e) {
      response.sendError(500, e.getMessage());
      e.printStackTrace();
      return;

    }
    request.setAttribute("seguidoOno", flag);
    response.setStatus(200);
    return;
  }

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
    doGet(request, response);
  }

}