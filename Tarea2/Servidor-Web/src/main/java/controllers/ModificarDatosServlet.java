package controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import excepciones.UsuarioNoExisteException;
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

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sesion = request.getSession();
    	IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	if(sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO) {
    	  request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
    	  return;
    	}
    	Dtusuario usuario = (Dtusuario) sesion.getAttribute("usuarioLogueado");
    	String nombre = request.getParameter("nombre");
    	String apellido = request.getParameter("apellido");
    	String contrasenia = request.getParameter("contrasenia");
    	String contraseniaConf = request.getParameter("contraseniaConf");
    	BufferedImage imagen = usuario.getImagen();
    	if(!contraseniaConf.equals(contrasenia)) {
       request.setAttribute("mensajeError", "contraseña incorrecta");
       request.getRequestDispatcher("/WEB-INF/registros/EditarDatos.jsp").forward(request, response);
       return;
     }
		try {
			Part filePart = request.getPart("imagen");
			if(filePart != null && filePart.getSize() > 0) {
				InputStream fileContent = filePart.getInputStream();
				imagen = ImageIO.read(fileContent);
			}
		} catch (IOException | ServletException e) {
		  request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
    	if(sesion.getAttribute("tipoUsuario") == TipoUsuario.EMPRESA) {
    		String descripcion = request.getParameter("descripcion");
    		String sitioWeb = request.getParameter("sitioWeb");
    		
    		try {
				controladorUsuario.editarEmpresa(usuario.getNickname(), nombre, apellido, sitioWeb, descripcion, imagen, contrasenia);
				String url = request.getContextPath() + "/perfil?nicknameUsuario=" + usuario.getNickname();
				response.sendRedirect(url);
				return;
			} catch (UsuarioNoExisteException | IOException e) {
			  request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
				e.printStackTrace();
				return;
			}
    	}
    	else if(sesion.getAttribute("tipoUsuario") == TipoUsuario.POSTULANTE) {
    		String nacionalidad = request.getParameter("nacionalidad");
    		LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
    		try {
				controladorUsuario.editarPostulante(usuario.getNickname(), nombre, apellido, fechaNacimiento, nacionalidad, imagen, contrasenia);
				String url = request.getContextPath() + "/perfil?nicknameUsuario=" + usuario.getNickname();
				response.sendRedirect(url);
				return;
			} catch (UsuarioNoExisteException | IOException e) {
			  request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
				e.printStackTrace();
				return;
			}
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.getRequestDispatcher("/WEB-INF/registros/EditarDatos.jsp").forward(request,
       response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarRequest(request, response);
	}

}
