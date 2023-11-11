package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import main.java.webservices.DtCantidadTipoPublicacionRestante;
import main.java.webservices.DtCompraPaquete;
import main.java.webservices.DtTipoPublicacion;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.KeywordNoExisteException_Exception;
import main.java.webservices.OfertaLaboralYaExisteException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.TipoPublicacionNoExisteException_Exception;
import main.java.webservices.UsuarioNoExisteException_Exception;
import model.EstadoSesion;
import model.TipoUsuario;
import net.java.dev.jaxb.array.StringArray;

@MultipartConfig()

@WebServlet("/altaOferta")
public class AltaOfertaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AltaOfertaServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private void procesarGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException,
        IOException_Exception {
    HttpSession sesion = request.getSession();
    DtUsuario empresa = (DtUsuario) sesion
          .getAttribute("usuarioLogueado");
    if (sesion.getAttribute("estadoSesion")
          != EstadoSesion.LOGIN_CORRECTO
          || sesion.getAttribute("tipoUsuario")
                != TipoUsuario.EMPRESA) {
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
    }
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();

    ArrayList<String> listaTipos = (ArrayList<String>) port
          .listarTipoDePublicaciones().getItem();
    ArrayList<DtTipoPublicacion> listaDtTipos =
          new ArrayList<DtTipoPublicacion>();
    for (String nombreTipo : listaTipos) {
      try {
        listaDtTipos.add(
              port.obtenerDtTipoPublicacion(nombreTipo));
      } catch (TipoPublicacionNoExisteException_Exception e) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/500.jsp")
              .forward(request, response);
        e.printStackTrace();
      }
    }

    try {
      ArrayList<
            DtCompraPaquete> listaCompraPaquetesaux =
                  (ArrayList<DtCompraPaquete>) port
                        .obtenerDtCompraPaqueteDeEmpresa(
                              empresa.getNickname())
                        .getItem();
      ArrayList<DtCompraPaquete> listaCompraPaquetes =
            new ArrayList<DtCompraPaquete>();
      LocalDate fechaActual = LocalDate.now();
      for (DtCompraPaquete compra : listaCompraPaquetesaux) {
        String fechaVencimientoParseo =
              compra.getFechaVencimientoString();
        LocalDate fechaVencimiento =
              LocalDate.parse(fechaVencimientoParseo);
        if (fechaVencimiento.isAfter(fechaActual)) {
          listaCompraPaquetes.add(compra);
        }
      }
      request.setAttribute("listaCompraPaquetes",
            listaCompraPaquetes);
    } catch (UsuarioNoExisteException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
    }

    ArrayList<String> listKeywordsAtributo = (ArrayList<
          String>) port.listarKeywords().getItem();
    request.setAttribute("listaKeywords",
          listKeywordsAtributo);
    request.setAttribute("listaTipoPublicacion",
          listaDtTipos);
    request
          .getRequestDispatcher(
                "/WEB-INF/registros/AltaOferta.jsp")
          .forward(request, response);
  }

  private void procesarPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException,
        IOException_Exception {
    HttpSession sesion = request.getSession();

    if (sesion.getAttribute("estadoSesion")
          != EstadoSesion.LOGIN_CORRECTO
          || sesion.getAttribute("tipoUsuario")
                != TipoUsuario.EMPRESA) {
      request.getRequestDispatcher("/WEB-INF/error/404.jsp")
            .forward(request, response);
    }
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    DtUsuario empresa = (DtUsuario) sesion
          .getAttribute("usuarioLogueado");
    String tipoPublicacion =
          request.getParameter("tipoPublicacion");
    String nombreOferta =
          request.getParameter("nombreOferta");
    String descripcion =
          request.getParameter("descripcion");
    String[] keywords =
          request.getParameterValues("keywords");
    String horaInicio = request.getParameter("horaInicio");
    String horaFin = request.getParameter("horaFin");
    float remuneracion = 0;
    String departamento =
          request.getParameter("departamento");
    String ciudad = request.getParameter("ciudad");
    BufferedImage imagen = null;
    ArrayList<String> listKeywords = new ArrayList<>();
    LocalDate fechaAlta = LocalDate.now();
    String fechaAltaString = fechaAlta.toString();
    String nombrePaquete =
          request.getParameter("nombrePaquete");
    String imagenString = "";
    if (request.getParameter("remuneracion") != null) {
      remuneracion = Float
            .valueOf(request.getParameter("remuneracion"));
    }

    try {
      Part filePart = request.getPart("imagenOferta");
      if (filePart != null && filePart.getSize() > 0) {
        InputStream fileContent = filePart.getInputStream();
        imagen = ImageIO.read(fileContent);
      }
    } catch (IOException | ServletException e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
    }

    if (keywords != null) {
      for (String keyword : keywords) {
        listKeywords.add(keyword);
      }
    }
    if (nombrePaquete != null
          && !nombrePaquete.equals("")) {
      try {
        ArrayList<
              DtCompraPaquete> listaCompras =
                    (ArrayList<DtCompraPaquete>) port
                          .obtenerDtCompraPaqueteDeEmpresa(
                                empresa.getNickname())
                          .getItem();
        Boolean hayPublicaciones = false;
        for (DtCompraPaquete compra : listaCompras) {
          if (nombrePaquete
                .equals(compra.getPaquete().getNombre())) {
            for (DtCantidadTipoPublicacionRestante cantidadRestante : compra
                  .getPublicacionesRestantes()) {
              if (tipoPublicacion.equals(cantidadRestante
                    .getTipoPublicacion().getNombre())) {
                if (cantidadRestante.getCantidad() > 0) {
                  hayPublicaciones = true;
                }
              }
            }
          }
        }
        if (!hayPublicaciones) {
          request.setAttribute("mensajeError",
                "el paquete no tiene publicaciones del tipo seleccionado");
          procesarGet(request, response);
          return;
        }
      } catch (UsuarioNoExisteException_Exception e) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/500.jsp")
              .forward(request, response);
        e.printStackTrace();
      } catch (IOException e) {
        request
              .getRequestDispatcher(
                    "/WEB-INF/error/500.jsp")
              .forward(request, response);
        e.printStackTrace();
      }
    }
    try {
      if (imagen != null) {
        imagenString = imageToBase64String(imagen);
      }
      StringArray stringArray = new StringArray();
      List<String> keywordsList = stringArray.getItem();
      keywordsList.addAll(listKeywords);
      port.altaOfertaLaboral(nombreOferta, descripcion,
            horaInicio, horaFin, remuneracion, ciudad,
            departamento,
            fechaAltaString, tipoPublicacion,
            empresa.getNickname(), stringArray,
            imagenString, nombrePaquete);

       String url = request.getContextPath()
            + "/perfil?nicknameUsuario="
            + URLEncoder.encode(empresa.getNickname(),
                  "UTF-8");
      response.sendRedirect(url);

    } catch (TipoPublicacionNoExisteException_Exception
          | KeywordNoExisteException_Exception
          | UsuarioNoExisteException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);

    } catch (OfertaLaboralYaExisteException_Exception e) {
      request.setAttribute("mensajeError",
            "nombre de oferta repetido");
      procesarGet(request, response);

    }

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    try {
      procesarGet(request, response);
    } catch (ServletException | IOException
          | IOException_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    try {
      procesarPost(request, response);
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
