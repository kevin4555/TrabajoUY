package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class PaqueteServlet
 */
@WebServlet("/paquete")
public class PaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaqueteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      IcontroladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
      try {
       String nombrePaquete = request.getParameter("nombrePaquete");
       DtpaquetePublicacion paquete = controladorOfertas.obtenerDtpaquete(nombrePaquete);
       request.setAttribute("paquete", paquete);       
       request.getRequestDispatcher("/WEB-INF/consultas/Paquete.jsp").forward(request, response);
       return;
      } catch (PaquetePublicacionNoExisteException e) {
       request.setAttribute("error", e.getMessage());
       request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
       e.printStackTrace();
       return;       
      }
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
