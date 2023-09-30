package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.DataTypes.DTUsuario;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class ModificarDatosServlet
 */
public class ModificarDatosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarDatosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession sesion = request.getSession();
    	IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	if(sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO) {
    		//agregar pagina de error
    	}
    	DTUsuario usuario = (DTUsuario) sesion.getAttribute("usuarioLogueado");
    	String nombre = request.getParameter("nombre");
    	String apellido = request.getParameter("apellido");
    	String contrasenia = request.getParameter("contrasenia");
    	BufferedImage imagen = usuario.getImagen();
		try {
			Part filePart = request.getPart("imagen");
			if(filePart != null && filePart.getSize() > 0) {
				InputStream fileContent = filePart.getInputStream();
				imagen = ImageIO.read(fileContent);
			}
		} catch (IOException | ServletException e) {
			//agregar pagina de error
			e.printStackTrace();
		}
    	if(sesion.getAttribute("tipoUsuario") == TipoUsuario.EMPRESA) {
    		String descripcion = request.getParameter("descripcion");
    		String sitioWeb = request.getParameter("sitioWebb");
    		
    		try {
				controladorUsuario.editarEmpresa(usuario.getNickname(), nombre, apellido, sitioWeb, descripcion, imagen, contrasenia);
				String url = "/perfil?nicknameUsuario=" + usuario.getNickname();
				response.sendRedirect(url);
			} catch (UsuarioNoExisteException | IOException e) {
				// agregar pagina de error
				e.printStackTrace();
			}
    	}
    	else if(sesion.getAttribute("tipoUsuario") == TipoUsuario.POSTULANTE) {
    		String nacionalidad = request.getParameter("nacionalidad");
    		LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
    		try {
				controladorUsuario.editarPostulante(usuario.getNickname(), nombre, apellido, fechaNacimiento, nacionalidad, imagen, contrasenia);
				String url = "/perfil?nicknameUsuario=" + usuario.getNickname();
				response.sendRedirect(url);
			} catch (UsuarioNoExisteException | IOException e) {
				// agregar pagina de error
				e.printStackTrace();
			}
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
		procesarRequest(request, response);
	}

}
