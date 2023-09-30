package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtofertaLaboral;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

import java.io.IOException;
import java.time.LocalDate;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExistePostulacion;

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
    	if(sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO || request.getAttribute("nombreOferta") == null){
    		//agregar pagina de error
    	}
    	if(request.getAttribute("nombreOferta") != null && request.getAttribute("accion") != "postularse" ) {
    		String nombreOferta = request.getParameter("nombreOferta");
    		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    		try {
				DtofertaLaboral oferta = controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
				request.setAttribute("oferta", oferta);
				request.getRequestDispatcher("/WEB-INF/registros/Postulacion.jsp").forward(request, response);
			} catch (OfertaLaboralNoExisteException e) {
				//agregar pàgina de error
				e.printStackTrace();
			}
    	}
    	if(sesion.getAttribute("tipoUsuario") == TipoUsuario.POSTULANTE &&  request.getAttribute("accion") == "postularse" ) {
    		String cVReducido = request.getParameter("cVReducido");
    		String motivacion = request.getParameter("motivacion");
    		Dtusuario usuario = (Dtusuario) sesion.getAttribute("usuarioLogueado");
    		DtofertaLaboral oferta = (DtofertaLaboral) request.getAttribute("oferta");
    		IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    		try {
				controladorUsuario.registrarPostulacion(cVReducido, motivacion, LocalDate.now(), usuario.getNickname(), oferta.getNombre());
				request.getRequestDispatcher("/home").forward(request, response);
			} catch (UsuarioNoExisteException | OfertaLaboralNoExisteException | UsuarioYaExistePostulacion e) {
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
