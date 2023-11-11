package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.webservices.IOException_Exception;
import main.java.webservices.OfertaLaboralNoExisteException_Exception;
import main.java.webservices.PublicadorService;

/**
 * Servlet implementation class Chequeo Nombre Oferta
 */
@MultipartConfig()
@WebServlet("/chequeoNombreOferta")
public class ChequeoNombreOferta extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ChequeoNombreOferta() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  private void procesarRequest(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
  PublicadorService publicadorService =
        new PublicadorService();
  main.java.webservices.Publicador port =
        publicadorService.getPublicadorPort();
  String nombreOferta = request.getParameter("nombreOferta");
  boolean nombreEnUso = false;
  try {
    port.obtenerDtOfertaLaboral(nombreOferta);
    nombreEnUso = true;
  } catch (IOException_Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (OfertaLaboralNoExisteException_Exception e) {
    // TODO Auto-generated catch block
    nombreEnUso = false;
  }

  
  // Crear una cadena JSON manualmente
  String jsonResponse =
        "{ \"disponible\": " + nombreEnUso + " }";

  // Enviar la respuesta JSON
  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8");
  PrintWriter out = response.getWriter();
  out.print(jsonResponse);
  out.close();

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
