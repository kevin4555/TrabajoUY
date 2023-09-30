package controllers;

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

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;

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
    	
    	if(request.getParameter("accion") == null || request.getParameter("accion") != "comrpar") {
    		String nombrePaquete = request.getParameter("nombrePaquete");
    		IcontroladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
    		try {
    			DtpaquetePublicacion paquete = controladorOfertas.obtenerDtpaquete(nombrePaquete);
				request.setAttribute("paquete", paquete);
				request.getRequestDispatcher("/WEB-INF/consultas/Paquete.jsp").forward(request, response);
			} catch (PaquetePublicacionNoExisteException e) {
				// agregar pagina de error
				e.printStackTrace();
			}
    	}
    	else if(request.getParameter("accion") == "comprar") {
    		HttpSession sesion = request.getSession();
    		if(sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO || sesion.getAttribute("tipoUsuario") != TipoUsuario.EMPRESA) {
    			//agregar pagina de error
    		}
    		DtpaquetePublicacion paquete = (DtpaquetePublicacion) request.getAttribute("paquete");
    		Dtusuario usuario = (Dtusuario) sesion.getAttribute("usuarioLogueado");
    		IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    		String nombrePaquete = paquete.getNombre();
    		try {
				ArrayList<String> paquetesNoComprados = controladorUsuario.listarPaquetesNoCompradosDeEmpresa(usuario.getNickname());
				if(paquetesNoComprados.contains(nombrePaquete)) {
					controladorUsuario.comprarPaquete(usuario.getNickname(), nombrePaquete, LocalDate.now());
					request.getRequestDispatcher("/WEB-INF/consultas/Perfil.jsp").forward(request, response);
				}
				else {
					request.setAttribute("mensaje", "paquete ya comprado");
					request.getRequestDispatcher("/WEB-INF/consultas/Paquete.jsp").forward(request, response);
				}
				
			} catch (UsuarioNoExisteException | PaquetePublicacionNoExisteException e) {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
