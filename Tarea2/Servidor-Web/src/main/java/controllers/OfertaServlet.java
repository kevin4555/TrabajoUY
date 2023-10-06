package controllers;

import java.io.IOException;

import excepciones.OfertaLaboralNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtempresa;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import model.EstadoSesion;

import java.io.IOException;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class OfertaServlet
 */
@WebServlet("/oferta")
public class OfertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    	String nombreOferta = request.getParameter("nombreOferta");
    	HttpSession sesion = request.getSession();
     
    	try {
			DtOfertaLaboral oferta = controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
			request.setAttribute("oferta", oferta);
			if (sesion.getAttribute("estadoSesion") == EstadoSesion.LOGIN_CORRECTO) {
     Dtusuario usuario = (Dtusuario) sesion.getAttribute("usuarioLogueado");
     if(usuario instanceof Dtpostulante) {
       Boolean estaPostulado = controladorOferta.estaPostulado(usuario.getNickname(), nombreOferta);
       request.setAttribute("estaPostulado", estaPostulado);
     }
     if(usuario instanceof Dtempresa) {
       Boolean miOferta = usuario.getNickname() == oferta.getEmpresa();
       request.setAttribute("miOferta", miOferta);
     }
   }
			request.getRequestDispatcher("/WEB-INF/consultas/Oferta.jsp").forward(request, response);
			
		} catch (OfertaLaboralNoExisteException | UsuarioNoExisteException e) {
			// agregar pagina de error
			e.printStackTrace();
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
