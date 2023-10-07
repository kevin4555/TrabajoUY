package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

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
    
    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession sesion = request.getSession();
    	IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	String nickname = request.getParameter("nickname");
    	String nombre = request.getParameter("nombre");
    	String apellido = request.getParameter("apellido");
    	String email = request.getParameter("email");
    	String contrasenia = request.getParameter("contrasenia");
    	BufferedImage imagen = null;
		try {
			Part filePart = request.getPart("imagen");
			if(filePart != null && filePart.getSize() > 0) {
				InputStream fileContent = filePart.getInputStream();
				imagen = ImageIO.read(fileContent);
			}
		} catch (IOException | ServletException e) {
		  request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
		}
		if(request.getParameter("tipoUsuario") == "postulante") {
			String nacionalidad = request.getParameter("nacionalidad");
			LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
			try {
				controladorUsuario.altaPostulante(nickname, nombre, apellido, email, fechaNacimiento, nacionalidad, imagen, contrasenia);
				sesion.setAttribute("tipoUsuario", TipoUsuario.POSTULANTE);
			} catch (UsuarioYaExisteException | UsuarioEmailRepetidoException e) {
				request.setAttribute("mensajeError", "nickname o email repetido");
				request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp").forward(request, response);
			}
		}
		if(request.getParameter("tipoUsuario") == "empresa") {
			String descriopcion = request.getParameter("descripcion");
			String sitioWeb = request.getParameter("sitioWeb");
			try {
				controladorUsuario.altaEmpresa(nickname, nombre, apellido, email, descriopcion, sitioWeb, imagen, contrasenia);
				sesion.setAttribute("tipoUsuario", TipoUsuario.EMPRESA);
			} catch (UsuarioYaExisteException | UsuarioEmailRepetidoException e) {
				request.setAttribute("mensajeError", "nickname o email repetido");
				request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp").forward(request, response);
			}
		}
		sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_CORRECTO);
		try {
			Dtusuario usuario = controladorUsuario.obtenerDtusuario(nickname);
			sesion.setAttribute("usuarioLogueado", usuario);
			request.getRequestDispatcher("/home").forward(request, response);
		} catch (UsuarioNoExisteException e) {
		  request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.getRequestDispatcher("/WEB-INF/registros/AltaUsuario.jsp").forward(request, response);
	}

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  procesarRequest(request, response);
 }

}
