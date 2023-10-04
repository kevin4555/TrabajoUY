package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DtpaquetePublicacion;
import logica.interfaces.IcontroladorOferta;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servlet implementation class ConsultaPaquetes
 */
@WebServlet("/consultaPaquetes")
public class ConsultaPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaPaquetes() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IcontroladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
    	ArrayList<DtpaquetePublicacion> listaPaquetes = (ArrayList<DtpaquetePublicacion>) controladorOfertas.listarDtpaquetes();
    	HashMap<String, BufferedImage> perfilPaqueteHashMap = new HashMap<>();
    	for(DtpaquetePublicacion name: listaPaquetes)
    	{
    		perfilPaqueteHashMap.put(name.getNombre(), name.getImagen());
    	}
    	request.setAttribute("perfilPaquetes", perfilPaqueteHashMap);
    	request.setAttribute("listaPaquetes", listaPaquetes);
    	request.getRequestDispatcher("/WEB-INF/consultas/ConsultaPaquetes.jsp").forward(request, response);
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
		procesarRequest(request, response);
	}

}
