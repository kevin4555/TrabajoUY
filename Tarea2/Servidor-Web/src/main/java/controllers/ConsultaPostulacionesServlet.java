package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;

/**
 * Servlet implementation class VerPostulacionServlet
 */
@WebServlet("/listarOfertas")
public class ConsultaPostulacionesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaPostulacionesServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String userAgent = request.getHeader("User-Agent");
		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		ArrayList<DtOfertaLaboral> dTOfertas = (ArrayList<DtOfertaLaboral>) controladorOferta
				.obtenerDtOfertasConfirmadas();
		request.setAttribute("listaOfertasConfirmadas", dTOfertas);
		if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
		{
			request.getRequestDispatcher("/WEB-INF/mobile/consultas/ConsultaPostulaciones.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		procesarRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		procesarRequest(request, response);
	}

}
