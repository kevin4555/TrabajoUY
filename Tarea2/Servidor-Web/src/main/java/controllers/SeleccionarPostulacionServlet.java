package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.webservices.DtOfertaLaboral;
import logica.webservices.DtPostulacion;
import logica.webservices.DtUsuario;
import logica.webservices.IOException_Exception;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioNoExisteException_Exception;
import net.java.dev.jaxb.array.StringArray;

@WebServlet("/seleccionarPostulacion")
public class SeleccionarPostulacionServlet extends HttpServlet {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;

  public SeleccionarPostulacionServlet() {
		super();
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PublicadorService publicadorService = new PublicadorService();
		logica.webservices.Publicador port = publicadorService.getPublicadorPort();
		
		String nombreOferta = request.getParameter("nombreOferta");
		String sortedData = request.getParameter("sorted-data"); // Ordenamiento de la pagina
		
		String[] sortedIds = sortedData.split(",");
		ArrayList<String> arrayListPostulantes = new ArrayList<String>();
		for(String nickname: sortedIds) {
		  arrayListPostulantes.add(nickname);
		}
		StringArray listaPsotulantes = new StringArray();
		listaPsotulantes.getItem().addAll(arrayListPostulantes);
		
		try {
			port.ordenarPostulaciones(nombreOferta, listaPsotulantes);
			
			DtOfertaLaboral oferta = port.obtenerDtOfertaLaboral(nombreOferta);
			Map<String, String> mapImagen = new HashMap<String, String>();
			for (DtPostulacion postulacion : oferta.getPostulaciones())
			{
				DtUsuario postulante = port.obtenerDtUsuario(postulacion.getNicknamePostulante());
				mapImagen.put(postulante.getNickname(), postulante.getImagenBase64());
			}
			request.setAttribute("mapImagenes", mapImagen);
			request.setAttribute("oferta", oferta);
			
		} catch (OfertaLaboralNoExisteException_Exception | UsuarioNoExisteException_Exception | IOException_Exception e) {
			request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
			return;
		}
		request.getRequestDispatcher("/WEB-INF/consultas/Oferta.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		procesarRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
