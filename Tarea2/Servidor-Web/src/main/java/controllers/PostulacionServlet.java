package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTUsuario;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

import java.io.IOException;
import java.time.LocalDate;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class PostulacionServlet
 */
@WebServlet("/postulacion")
public class PostulacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostulacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sesion = request.getSession();
    	if(sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO || sesion.getAttribute("tipoUsuario") != TipoUsuario.POSTULANTE){
    		//agregar pagina de error
    	}
    	if(request.getAttribute("oferta") == null) {
    		String nombreOferta = request.getParameter("nombreOferta");
    		IControladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    		try {
				DTOfertaLaboral oferta = controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
				request.setAttribute("oferta", oferta);
				request.getRequestDispatcher("/WEB-INF/registros/Postulacion.jsp").forward(request, response);
			} catch (OfertaLaboralNoExisteException e) {
				//agregar pàgina de error
				e.printStackTrace();
			}
    	}
    	else {
    		String cVReducido = request.getParameter("cVReducido");
    		String motivacion = request.getParameter("motivacion");
    		DTUsuario usuario = (DTUsuario) sesion.getAttribute("usuarioLogueado");
    		DTOfertaLaboral oferta = (DTOfertaLaboral) request.getAttribute("oferta");
    		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    		try {
				controladorUsuario.registrarPostulacion(cVReducido, motivacion, LocalDate.now(), usuario.getNickname(), oferta.getNombre());
			} catch (UsuarioNoExisteException | OfertaLaboralNoExisteException e) {
				// agregar pagina error
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
		procesarRequest(request, response);
	}

}
