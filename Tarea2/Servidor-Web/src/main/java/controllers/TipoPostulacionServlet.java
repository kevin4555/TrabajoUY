package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.DataTypes.DTTipoPublicacion;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorOferta;

import java.io.IOException;

import excepciones.TipoPublicacionNoExisteException;

/**
 * Servlet implementation class TipoPostulacionServlet
 */
@WebServlet("/tipoPostulacion")
public class TipoPostulacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoPostulacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombreTipo = request.getParameter("nombreTipoPublicacion");
    	IControladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
    	try {
			DTTipoPublicacion tipoPublicacion = controladorOfertas.obtenerDTTipoPublicacion(nombreTipo);
			request.setAttribute("tipoPublicacion", tipoPublicacion);
			request.getRequestDispatcher("/WEB-INF/consultas/TipoPublicacion.jsp").forward(request, response);
		} catch (TipoPublicacionNoExisteException e) {
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
