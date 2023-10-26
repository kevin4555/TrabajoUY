package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.webservices.PublicadorService;
import logica.webservices.DtOfertaLaboral;
import logica.webservices.DtUsuario;
import logica.webservices.IOException_Exception;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.Publicador;
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

		logica.webservices.PublicadorService service = new PublicadorService();
		logica.webservices.Publicador port = service.getPublicadorPort();

		if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO
				|| sesion.getAttribute("tipoUsuario") != TipoUsuario.POSTULANTE)
		{
			request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
			return;
		}

		String nombreOferta = request.getParameter("nombreOferta");
		String cVReducido = request.getParameter("cVReducido");
		String motivacion = request.getParameter("motivacion");
		String video = request.getParameter("video");
		DtUsuario usuario = (DtUsuario) sesion.getAttribute("usuarioLogueado");

		try
		{
			port.registrarPostulacion(cVReducido, motivacion, LocalDate.now(), usuario.getNickname(), nombreOferta,
					video);
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

		logica.webservices.PublicadorService service = new PublicadorService();
		logica.webservices.Publicador port = service.getPublicadorPort();

		if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO
				|| request.getParameter("nombreOferta") == null)
		{
			request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
			return;
		}

		String nombreOferta = request.getParameter("nombreOferta");

		try
		{
			DtOfertaLaboral oferta = port.obtenerDtOfertaLaboral(nombreOferta);
			request.setAttribute("oferta", oferta);
			if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
			{
				request.getRequestDispatcher("/WEB-INF/mobile/registros/PostulacionMobile.jsp").forward(request,
						response);
			}
			else
			{
				request.getRequestDispatcher("/WEB-INF/registros/Postulacion.jsp").forward(request, response);
			}
		}
		catch (OfertaLaboralNoExisteException | IOException_Exception | OfertaLaboralNoExisteException_Exception e)
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
