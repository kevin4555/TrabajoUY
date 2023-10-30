package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.webservices.DtTipoPublicacion;
import logica.webservices.PublicadorService;
import logica.webservices.TipoPublicacionNoExisteException_Exception;

/**
 * Servlet implementation class ConsultaTipoPostulacionServlet
 */
@WebServlet("/consultaTipoPublicacion")
public class ConsultaTipoPublicacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaTipoPublicacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarResquest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PublicadorService publicadorService = new PublicadorService();
		logica.webservices.Publicador cliente = publicadorService.getPublicadorPort();
    	
    	
    	ArrayList<String> listaTipoPublicacion = (ArrayList<String>) cliente.listarTipoDePublicaciones().getItem();
    	ArrayList<DtTipoPublicacion> listaDTTipo = new ArrayList<DtTipoPublicacion>();
    	for(String tipo : listaTipoPublicacion) {
    		try {
				listaDTTipo.add(cliente.obtenerDtTipoPublicacion(tipo));
			} catch (TipoPublicacionNoExisteException_Exception e) {
			  request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
				e.printStackTrace();
			}
    	}
    	request.setAttribute("listaTipoPublicacion", listaDTTipo);
    	request.getRequestDispatcher("/WEB-INF/consultas/ConsultaTipoPublicacion.jsp").forward(request, response);
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarResquest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
