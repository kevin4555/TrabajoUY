package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;

import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.controllers.Fabrica;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;


/**
 * Servlet implementation class AltaUsuario
 */
@MultipartConfig()
@WebServlet("/chequeoNickname")
public class ChequeoNicknameServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChequeoNicknameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
     HttpSession sesion = request.getSession();
     IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
     String nickname = request.getParameter("nickname");
     List<String> listaUsuarios = controladorUsuario.listaDeUsuarios();
     boolean nicknameDisponible  = listaUsuarios.contains(nickname);
  // Crear una cadena JSON manualmente
     String jsonResponse = "{ \"disponible\": " + nicknameDisponible + " }";

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
