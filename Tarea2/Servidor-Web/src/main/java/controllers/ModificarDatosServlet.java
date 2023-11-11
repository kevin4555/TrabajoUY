package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Base64;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class ModificarDatosServlet
 */
@MultipartConfig()

@WebServlet("/editarPerfil")
public class ModificarDatosServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ModificarDatosServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    if (sesion.getAttribute("estadoSesion")
          != EstadoSesion.LOGIN_CORRECTO) {
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
      return;
    }
    DtUsuario usuario = (DtUsuario) sesion
          .getAttribute("usuarioLogueado");
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String contrasenia =
          request.getParameter("contrasenia");
    String contraseniaConf =
          request.getParameter("contraseniaConf");
    BufferedImage imagen = null;
    String imagenString = "";
    if (!contraseniaConf.equals(contrasenia)) {
      request.setAttribute("mensajeError",
            "contraseña incorrecta");
      request
            .getRequestDispatcher(
                  "/WEB-INF/registros/EditarDatos.jsp")
            .forward(request, response);
      return;
    }
    try {
      Part filePart = request.getPart("imagen");
      if (filePart != null && filePart.getSize() > 0) {
        InputStream fileContent = filePart.getInputStream();
        imagen = ImageIO.read(fileContent);
      }
    } catch (IOException | ServletException e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    if(imagen != null) {
      imagenString = imageToBase64String(imagen);
    }
    if (sesion.getAttribute("tipoUsuario")
          == TipoUsuario.EMPRESA) {
      String descripcion =
            request.getParameter("descripcion");
      String sitioWeb = request.getParameter("sitioWeb");   
      try {
        port.editarEmpresa(usuario.getNickname(), nombre,
              apellido, sitioWeb, descripcion, imagenString,
              contrasenia);
        DtUsuario usuariomodificado =
            port.obtenerDtUsuario(usuario.getNickname());
      sesion.setAttribute("usuarioLogueado",
            usuariomodificado);
        String url = request.getContextPath()
              + "/perfil?nicknameUsuario="
              + usuario.getNickname();
        response.sendRedirect(url);
        return;
      } catch (UsuarioNoExisteException_Exception
            | IOException | IOException_Exception e) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/500.jsp")
              .forward(request, response);
        e.printStackTrace();
        return;
      }
    } else if (sesion.getAttribute("tipoUsuario")
          == TipoUsuario.POSTULANTE) {
      String nacionalidad =
            request.getParameter("nacionalidad");
      String fechaNacimiento = LocalDate
            .parse(request.getParameter("fechaNacimiento"))
            .toString();
      if(imagen != null) {
        imagenString = imageToBase64String(imagen);
      }
      
      try {
        port.editarPostulante(usuario.getNickname(), nombre,
              apellido, fechaNacimiento, nacionalidad,
              imagenString, contrasenia);
        DtUsuario usuariomodificado =
              port.obtenerDtUsuario(usuario.getNickname());
        sesion.setAttribute("usuarioLogueado",
              usuariomodificado);
        String url = request.getContextPath()
              + "/perfil?nicknameUsuario="
              + usuario.getNickname();
        response.sendRedirect(url);
        return;
      } catch (UsuarioNoExisteException_Exception
            | IOException | IOException_Exception e) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/500.jsp")
              .forward(request, response);
        e.printStackTrace();
        return;
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
    request
          .getRequestDispatcher(
                "/WEB-INF/registros/EditarDatos.jsp")
          .forward(request,
                response);
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

  public static String
        imageToBase64String(BufferedImage image) {
    try {
      ByteArrayOutputStream baos =
            new ByteArrayOutputStream();
      ImageIO.write(image, "png", baos); // Puedes cambiar
                                         // "png" al formato
                                         // de imagen
                                         // deseado (por
                                         // ejemplo, "jpg"
      // para JPEG)
      byte[] imageBytes = baos.toByteArray();
      return Base64.getEncoder().encodeToString(imageBytes);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
