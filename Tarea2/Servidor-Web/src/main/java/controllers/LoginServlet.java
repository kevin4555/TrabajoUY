package controllers;

import java.io.IOException;

import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sesion = request.getSession();
    	IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	String claveIngresada = (String) request.getParameter("clave");
    	String contraseniaIngresada = (String) request.getParameter("contrasenia");
    	try {
			if(controladorUsuario.confirmarContrasenia(claveIngresada, contraseniaIngresada)) {
				sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_CORRECTO);
				DTUsuario usuario = controladorUsuario.obtenerDTUsuario(claveIngresada);
				sesion.setAttribute("usuarioLogueado", usuario);
				if(usuario instanceof DTEmpresa) {
					sesion.setAttribute("tipoUsuario", TipoUsuario.EMPRESA);
				}
				else if(usuario instanceof DTPostulante) {
					sesion.setAttribute("tipoUsuario", TipoUsuario.POSTULANTE);
				}
				request.getRequestDispatcher("/home").forward(request, response);
			}
			else {
				sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
				request.setAttribute("mensajeError", "password incorrecta");
				request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
			}
		} catch (UsuarioNoExisteException e) {
			sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
			request.setAttribute("mensajeError", "usuario incorrecta");
			request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
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
