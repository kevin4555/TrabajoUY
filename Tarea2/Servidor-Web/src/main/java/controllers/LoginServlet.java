package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.webservices.DtEmpresa;
import main.java.webservices.DtPostulante;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoginServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    String nombreEmail =
          (String) request.getParameter("nombreEmail");
    String contraseniaIngresada =
          (String) request.getParameter("contrasenia");

    String userAgent = request.getHeader("User-Agent");

    HttpSession sesion = request.getSession();
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();

    if (nombreEmail == null
          || contraseniaIngresada == null) {
      if (userAgent != null
            && userAgent.toLowerCase().contains("mobile")) {
        sesion.setAttribute("estadoSesion",
              EstadoSesion.NO_LOGIN);
        request
              .getRequestDispatcher(
                    "/WEB-INF/mobile/home/LoginMobile.jsp")
              .forward(request, response);
        return;
      } else {
        sesion.setAttribute("estadoSesion",
              EstadoSesion.NO_LOGIN);
        request
              .getRequestDispatcher(
                    "/WEB-INF/home/Login.jsp")
              .forward(request, response);
        return;
      }
    }
    try {

      DtUsuario usuario =
            port.obtenerDtUsuario(nombreEmail);
      if (userAgent != null
            && userAgent.toLowerCase().contains("mobile")) {
        if (port.confirmarContrasenia(nombreEmail,
              contraseniaIngresada)) {
          if (usuario instanceof DtPostulante) {
            sesion.setAttribute("tipoUsuario",
                  TipoUsuario.POSTULANTE);
            sesion.setAttribute("estadoSesion",
                  EstadoSesion.LOGIN_CORRECTO);
            sesion.setAttribute("usuarioLogueado", usuario);
            request.getRequestDispatcher("/home")
                  .forward(request, response);
          } else {
            sesion.setAttribute("estadoSesion",
                  EstadoSesion.LOGIN_INCORRECTO);
            request.setAttribute("error",
                  "usuario incorrecto");
            request.getRequestDispatcher(
                  "/WEB-INF/mobile/home/LoginMobile.jsp")
                  .forward(request, response);
          }
        } else {
          sesion.setAttribute("estadoSesion",
                EstadoSesion.LOGIN_INCORRECTO);
          request.setAttribute("error",
                "password incorrecta");
          request.getRequestDispatcher(
                "/WEB-INF/mobile/home/LoginMobile.jsp")
                .forward(request, response);
        }
      } else {
        if (port.confirmarContrasenia(nombreEmail,
              contraseniaIngresada)) {
          if (usuario instanceof DtEmpresa) {
            sesion.setAttribute("tipoUsuario",
                  TipoUsuario.EMPRESA);
          } else if (usuario instanceof DtPostulante) {
            sesion.setAttribute("tipoUsuario",
                  TipoUsuario.POSTULANTE);
          }
          sesion.setAttribute("estadoSesion",
                EstadoSesion.LOGIN_CORRECTO);
          sesion.setAttribute("usuarioLogueado", usuario);
          // request.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(request,
          // response);
          request.getRequestDispatcher("/home")
                .forward(request, response); // Agregue esto
        } else {
          sesion.setAttribute("estadoSesion",
                EstadoSesion.LOGIN_INCORRECTO);
          request.setAttribute("error",
                "password incorrecta");
          request
                .getRequestDispatcher(
                      "/WEB-INF/home/Login.jsp")
                .forward(request, response);

        }
      }
    } catch (UsuarioNoExisteException_Exception
          | IOException_Exception e) {
      sesion.setAttribute("estadoSesion",
            EstadoSesion.LOGIN_INCORRECTO);
      request.setAttribute("error", "usuario incorrecta");
      if (userAgent != null
            && userAgent.toLowerCase().contains("mobile")) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/mobile/home/LoginMobile.jsp")
              .forward(request, response);
      } else {
        request
              .getRequestDispatcher(
                    "/WEB-INF/home/Login.jsp")
              .forward(request, response);
      }

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
