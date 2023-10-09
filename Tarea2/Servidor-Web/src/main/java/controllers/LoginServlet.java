package controllers;

import java.io.IOException;

import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.Dtempresa;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;
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
    	String nombreEmail = (String) request.getParameter("nombreEmail");
    	String contraseniaIngresada = (String) request.getParameter("contrasenia");
    	HttpSession sesion = request.getSession();
    	IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	if(nombreEmail == null || contraseniaIngresada == null) {
    		sesion.setAttribute("estadoSesion", EstadoSesion.NO_LOGIN);
    		request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
    		return;
    	}
    	try {
			if(controladorUsuario.confirmarContrasenia(nombreEmail, contraseniaIngresada)) {
				sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_CORRECTO);
				Dtusuario usuario = controladorUsuario.obtenerDtusuario(nombreEmail);
				sesion.setAttribute("usuarioLogueado", usuario);
				if(usuario instanceof Dtempresa) {
					sesion.setAttribute("tipoUsuario", TipoUsuario.EMPRESA);
				}
				else if(usuario instanceof Dtpostulante) {
					sesion.setAttribute("tipoUsuario", TipoUsuario.POSTULANTE);
				}
				request.getRequestDispatcher("/home").forward(request, response);
			}
			else {
				sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
				request.setAttribute("error", "password incorrecta");
				request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
			}
		} catch (UsuarioNoExisteException e) {
			sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
			request.setAttribute("error", "usuario incorrecta");
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
