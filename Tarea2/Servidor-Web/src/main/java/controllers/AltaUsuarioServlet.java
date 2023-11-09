package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Locale;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.webservices.DtUsuario;
import logica.webservices.IOException_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioEmailRepetidoException_Exception;
import logica.webservices.UsuarioNoExisteException_Exception;
import logica.webservices.UsuarioYaExisteException_Exception;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class AltaUsuario
 */
@MultipartConfig()
@WebServlet("/altaUsuario")
public class AltaUsuarioServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AltaUsuarioServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException,
        IOException_Exception {
    HttpSession sesion = request.getSession();
    PublicadorService publicadorService =
          new PublicadorService();
    logica.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    String nickname = request.getParameter("nickname");
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String email = request.getParameter("email");
    String contrasenia =
          request.getParameter("contrasenia");
    String contraseniaConf =
          request.getParameter("contraseniaConf");
    BufferedImage imagen = null;
    if (!contraseniaConf.equals(contrasenia)) {
      request.setAttribute("mensajeError",
            "contraseña incorrecta");
      request
            .getRequestDispatcher(
                  "/WEB-INF/registros/AltaUsuario.jsp")
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
      System.out.println("sale en imagen");
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    if (request.getParameter("tipoUsuario")
          .equals("postulante")) {
      String nacionalidad =
            request.getParameter("nacionalidad");
      LocalDate fechaNacimiento = LocalDate
            .parse(request.getParameter("fechaNacimiento"));
      DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd/MM/yyyy", Locale.ENGLISH);
      String fechaNacimientoString =
            fechaNacimiento.format(formatter);
      String imagenString;
      if (imagen != null) {
        imagenString = imageToBase64String(imagen);
      } else {
        imagenString = "";
      }
      try {
        port.altaPostulante(nickname, nombre, apellido,
              email, fechaNacimientoString, nacionalidad,
              imagenString, contrasenia);
        sesion.setAttribute("tipoUsuario",
              TipoUsuario.POSTULANTE);
      } catch (UsuarioYaExisteException_Exception
            | UsuarioEmailRepetidoException_Exception e) {
        request.setAttribute("mensajeError",
              "nickname o email repetido");
        request
              .getRequestDispatcher(
                    "/WEB-INF/registros/AltaUsuario.jsp")
              .forward(request, response);
        return;
      }
    }
    if (request.getParameter("tipoUsuario")
          .equals("empresa")) {
      String descripcion =
            request.getParameter("descripcion");
      String sitioWeb = request.getParameter("sitioWeb");
      try {
        String imagenEmpresaString;
        if (imagen != null) {
          imagenEmpresaString = imageToBase64String(imagen);
        } else {
          imagenEmpresaString = "";
        }
        port.altaEmpresa(nickname, nombre, apellido, email,
              descripcion, sitioWeb, imagenEmpresaString,
              contrasenia);
        sesion.setAttribute("tipoUsuario",
              TipoUsuario.EMPRESA);
      } catch (UsuarioYaExisteException_Exception
            | UsuarioEmailRepetidoException_Exception e) {
        request.setAttribute("mensajeError",
              "nickname o email repetido");
        request
              .getRequestDispatcher(
                    "/WEB-INF/registros/AltaUsuario.jsp")
              .forward(request, response);
        return;
      }
    }
    sesion.setAttribute("estadoSesion",
          EstadoSesion.LOGIN_CORRECTO);
    try {
      DtUsuario usuario = port.obtenerDtUsuario(nickname);
      sesion.setAttribute("usuarioLogueado", usuario);
      request.getRequestDispatcher("/home").forward(request,
            response);
    } catch (UsuarioNoExisteException_Exception e) {
      System.out.println("sale al buscar usuario");
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    try {
      Part filePart = request.getPart("imagen");
      if (filePart != null && filePart.getSize() > 0) {
        InputStream fileContent = filePart.getInputStream();
        imagen = ImageIO.read(fileContent);
      }
    } catch (IOException | ServletException e) {
      System.out.println("sale en imagen");
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    if (request.getParameter("tipoUsuario")
          .equals("postulante")) {
      String nacionalidad =
            request.getParameter("nacionalidad");
      LocalDate fechaNacimiento = LocalDate
            .parse(request.getParameter("fechaNacimiento"));
      String fechaNacimientoString =
            fechaNacimiento.toString();
      try {
        String imagenStringParseo;
        if (imagen != null) {
          imagenStringParseo =
                imageToBase64String(imagen);
        } else {
          imagenStringParseo = "";
        }
        port.altaPostulante(nickname, nombre, apellido,
              email, fechaNacimientoString, nacionalidad,
              imagenStringParseo, contrasenia);

        sesion.setAttribute("tipoUsuario",
              TipoUsuario.POSTULANTE);
      } catch (UsuarioYaExisteException_Exception
            | UsuarioEmailRepetidoException_Exception e) {
        request.setAttribute("mensajeError",
              "nickname o email repetido");
        request
              .getRequestDispatcher(
                    "/WEB-INF/registros/AltaUsuario.jsp")
              .forward(request, response);
        return;
      }
    }
    if (request.getParameter("tipoUsuario")
          .equals("empresa")) {
      String descripcion =
            request.getParameter("descripcion");
      String sitioWeb = request.getParameter("sitioWeb");
      try {
        String imagenEmpresa;
        if (imagen != null) {
          imagenEmpresa = imageToBase64String(imagen);
        } else {
          imagenEmpresa = "";
        }
        port.altaEmpresa(nickname, nombre, apellido, email,
              descripcion, sitioWeb, imagenEmpresa,
              contrasenia);
        sesion.setAttribute("tipoUsuario",
              TipoUsuario.EMPRESA);
      } catch (UsuarioYaExisteException_Exception
            | UsuarioEmailRepetidoException_Exception e) {
        request.setAttribute("mensajeError",
              "nickname o email repetido");
        request
              .getRequestDispatcher(
                    "/WEB-INF/registros/AltaUsuario.jsp")
              .forward(request, response);
        return;
      }
    }
    sesion.setAttribute("estadoSesion",
          EstadoSesion.LOGIN_CORRECTO);
    try {
      DtUsuario usuario = port.obtenerDtUsuario(nickname);
      sesion.setAttribute("usuarioLogueado", usuario);
      request.getRequestDispatcher("/home").forward(request,
            response);
    } catch (UsuarioNoExisteException_Exception e) {
      System.out.println("sale al buscar usuario");
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
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
                "/WEB-INF/registros/AltaUsuario.jsp")
          .forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    try {
      procesarRequest(request, response);
    } catch (ServletException | IOException
          | IOException_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
