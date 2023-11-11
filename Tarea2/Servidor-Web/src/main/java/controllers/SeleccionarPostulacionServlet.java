package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.webservices.DtOfertaLaboral;
import main.java.webservices.DtPostulacion;
import main.java.webservices.DtUsuario;
import main.java.webservices.IOException_Exception;
import main.java.webservices.OfertaLaboralNoExisteException_Exception;
import main.java.webservices.PublicadorService;
import main.java.webservices.UsuarioNoExisteException_Exception;
import net.java.dev.jaxb.array.StringArray;

@MultipartConfig()

@WebServlet("/seleccionarPostulacion")
public class SeleccionarPostulacionServlet
      extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public SeleccionarPostulacionServlet() {
    super();
  }

  private void procesarRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    PublicadorService publicadorService =
          new PublicadorService();
    main.java.webservices.Publicador port =
          publicadorService.getPublicadorPort();

    String nombreOferta = request.getParameter("nomOferta");
    String[] sortedData =
          request.getParameterValues("sorted-data");
    ArrayList<String> arrayListPostulantes =
          new ArrayList<String>();
    if (sortedData != null) {
      for (String element : sortedData) {
        String[] ids = element.split(",");
        for (String elemento : ids) {
          String correg =
                elemento.replaceAll("[\\[\\]\",\\s]", "");
          arrayListPostulantes.add(correg);
        }

      }
    }

    StringArray listaPsotulantes = new StringArray();
    listaPsotulantes.getItem().addAll(arrayListPostulantes);

    try {
      port.ordenarPostulaciones(nombreOferta,
            listaPsotulantes);

      DtOfertaLaboral oferta =
            port.obtenerDtOfertaLaboral(nombreOferta);
      Map<String, String> mapImagen =
            new HashMap<String, String>();
      for (DtPostulacion postulacion : oferta
            .getPostulaciones()) {
        DtUsuario postulante = port.obtenerDtUsuario(
              postulacion.getNicknamePostulante());
        mapImagen.put(postulante.getNickname(),
              postulante.getImagenBase64());
      }
      request.setAttribute("mapImagenes", mapImagen);
      request.setAttribute("oferta", oferta);

    } catch (OfertaLaboralNoExisteException_Exception
          | UsuarioNoExisteException_Exception
          | IOException_Exception e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp")
            .forward(request, response);
      e.printStackTrace();
      return;
    }
    request
          .getRequestDispatcher(
                "/WEB-INF/consultas/Oferta.jsp")
          .forward(request, response);
  }

  protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    procesarRequest(request, response);
  }

  protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    doGet(request, response);
  }
}
