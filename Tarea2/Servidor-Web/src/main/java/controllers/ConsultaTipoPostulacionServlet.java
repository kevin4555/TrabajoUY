package controllers;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.TipoPublicacionNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DttipoPublicacion;
import logica.interfaces.IcontroladorOferta;

/**
 * Servlet implementation class ConsultaTipoPostulacionServlet
 */
@WebServlet("/consultaTipoPostulacion")
public class ConsultaTipoPostulacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaTipoPostulacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarResquest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IcontroladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
    	ArrayList<String> listaTipoPublicacion = (ArrayList<String>) controladorOfertas.listarTipoDePublicaciones();
    	ArrayList<DttipoPublicacion> listaDTTipo = new ArrayList<DttipoPublicacion>();
    	for(String tipo : listaTipoPublicacion) {
    		try {
				listaDTTipo.add(controladorOfertas.obtenerDttipoPublicacion(tipo));
			} catch (TipoPublicacionNoExisteException e) {
				// pagina de error
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
