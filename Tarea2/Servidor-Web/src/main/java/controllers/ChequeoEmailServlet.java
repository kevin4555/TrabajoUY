package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;


/**
 * Servlet implementation class AltaUsuario
 */
@MultipartConfig()
@WebServlet("/chequeoEmail")
public class ChequeoEmailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ChequeoEmailServlet() {
    super();
  }
  
  private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    String email = request.getParameter("email").toLowerCase();
    List<Dtusuario> listaDtUsuarios = controladorUsuario.obtenerDtusuarios();
    boolean emailEnUso = false;
    for (Dtusuario dtUsuario : listaDtUsuarios) {
      if (dtUsuario.getEmail().equals(email)) {
        emailEnUso = true;
        break;
      }
    }
    
 // Crear una cadena JSON manualmente
    String jsonResponse = "{ \"disponible\": " + emailEnUso + " }";

    // Enviar la respuesta JSON
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.print(jsonResponse);
    out.close();

 
 
   }
/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  procesarRequest(request, response);
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 procesarRequest(request, response);
}
  
}
