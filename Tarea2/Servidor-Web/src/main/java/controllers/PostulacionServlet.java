package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

import java.io.IOException;
import java.time.LocalDate;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExistePostulacion;

/**
 * Servlet implementation class PostulacionServlet
 */
@WebServlet("/postulacion")
public class PostulacionServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostulacionServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO
				|| sesion.getAttribute("tipoUsuario") != TipoUsuario.POSTULANTE)
		{
			request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
			return;
		}

		String nombreOferta = request.getParameter("nombreOferta");
		String cVReducido = request.getParameter("cVReducido");
		String motivacion = request.getParameter("motivacion");
		Dtusuario usuario = (Dtusuario) sesion.getAttribute("usuarioLogueado");
		IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		try
		{
			controladorUsuario.registrarPostulacion(cVReducido, motivacion, LocalDate.now(), usuario.getNickname(),
					nombreOferta, null);
			String url = request.getContextPath() + "/perfil?nicknameUsuario=" + usuario.getNickname();
			response.sendRedirect(url);
			return;
		}
		catch (UsuarioNoExisteException | OfertaLaboralNoExisteException | UsuarioYaExistePostulacion e)
		{
			request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userAgent = request.getHeader("User-Agent");
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO
				|| request.getParameter("nombreOferta") == null)
		{
			request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			return;
		}

		String nombreOferta = request.getParameter("nombreOferta");
		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();

		try
		{
			DtOfertaLaboral oferta = controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
			request.setAttribute("oferta", oferta);
			if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
			{
				request.getRequestDispatcher("/WEB-INF/mobile/registros/PostulacionMobile.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("/WEB-INF/registros/Postulacion.jsp").forward(request, response);
			}
		}
		catch (OfertaLaboralNoExisteException e)
		{
			request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		procesarRequest(request, response);
	}

}
