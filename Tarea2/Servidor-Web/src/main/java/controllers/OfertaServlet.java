package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.DataTypes.DTOfertaLaboral;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorOferta;

import java.io.IOException;

import excepciones.OfertaLaboralNoExisteException;

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
    	IControladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    	String nombreOferta = request.getParameter("nombreOferta");
    	try {
			DTOfertaLaboral oferta = controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
			request.setAttribute("oferta", oferta);
			request.getRequestDispatcher("/WEB-INF/consulta/Oferta.jsp").forward(request, response);
			
		} catch (OfertaLaboralNoExisteException e) {
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
