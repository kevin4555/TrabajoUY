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
		HttpSession sesion = request.getSession();
		String userAgent = request.getHeader("User-Agent");
		Dtusuario usuario = (Dtusuario) sesion.getAttribute("usuarioLogueado");
		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		ArrayList<DtOfertaLaboral> dTOfertas = (ArrayList<DtOfertaLaboral>) controladorOferta
				.obtenerDtOfertasConfirmadas();

		try {
			ArrayList<Dtpostulacion> postulaciones = (ArrayList<Dtpostulacion>) controladorUsuario
					.obtenerDtpostulacionesDePostulante(usuario.getNickname());
			for (Dtpostulacion dtpostulacion : postulaciones)
			{
				dTOfertas.add(controladorOferta.obtenerDtOfertaLaboral(dtpostulacion.getNombreOferta()));
			}
			request.setAttribute("postulaciones", postulaciones);
			request.setAttribute("ofertasPostuladas", dTOfertas);
			request.setAttribute("tipoUsuario", "postulante");
			request.setAttribute("usuario", usuario);
		} catch(UsuarioNoExisteException | OfertaLaboralNoExisteException e)
		{
			request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
		{
			request.getRequestDispatcher("/WEB-INF/mobile/consultas/ConsultaPostulacionesMobile.jsp").forward(request, response);
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
