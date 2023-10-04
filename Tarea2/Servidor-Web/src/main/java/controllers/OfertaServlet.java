package controllers;

import java.io.IOException;

import excepciones.OfertaLaboralNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.interfaces.IcontroladorOferta;

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
    	try {
			DtOfertaLaboral oferta = controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
			request.setAttribute("oferta", oferta);
			request.getRequestDispatcher("/WEB-INF/consultas/Oferta.jsp").forward(request, response);
			
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
