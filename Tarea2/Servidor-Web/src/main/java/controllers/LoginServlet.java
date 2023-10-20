package controllers;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtempresa;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import model.EstadoSesion;
import model.TipoUsuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String nombreEmail = (String) request.getParameter("nombreEmail");
		String contraseniaIngresada = (String) request.getParameter("contrasenia");

		String userAgent = request.getHeader("User-Agent");

		HttpSession sesion = request.getSession();
		IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		if (nombreEmail == null || contraseniaIngresada == null)
		{
			if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
			{
				sesion.setAttribute("estadoSesion", EstadoSesion.NO_LOGIN);
				request.getRequestDispatcher("/WEB-INF/mobile/home/LoginMobile.jsp").forward(request, response);
				return;
			}
			else
			{
				sesion.setAttribute("estadoSesion", EstadoSesion.NO_LOGIN);
				request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
				return;
			}
		}
		try
		{
			Dtusuario usuario = controladorUsuario.obtenerDtusuario(nombreEmail);
			if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
			{
				if (controladorUsuario.confirmarContrasenia(nombreEmail, contraseniaIngresada))
				{
					if (usuario instanceof Dtpostulante)
					{
						IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
						ArrayList<DtOfertaLaboral> dTOfertas = (ArrayList<DtOfertaLaboral>) controladorOferta.obtenerDtOfertasConfirmadas();
						request.setAttribute("listaOfertasConfirmadas", dTOfertas);
						sesion.setAttribute("tipoUsuario", TipoUsuario.POSTULANTE);
						sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_CORRECTO);
						sesion.setAttribute("usuarioLogueado", usuario);
						request.getRequestDispatcher("/WEB-INF/mobile/home/HomeMobile.jsp").forward(request, response);
					}
					else 
					{
						sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
						request.setAttribute("error", "usuario incorrecto");
						request.getRequestDispatcher("/WEB-INF/mobile/home/LoginMobile.jsp").forward(request, response);
					}
				}
				else 
				{
					sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
					request.setAttribute("error", "password incorrecta");
					request.getRequestDispatcher("/WEB-INF/mobile/home/LoginMobile.jsp").forward(request, response);
				}
			}
			else
			{
				if (controladorUsuario.confirmarContrasenia(nombreEmail, contraseniaIngresada))
				{
					if (usuario instanceof Dtempresa)
					{
						sesion.setAttribute("tipoUsuario", TipoUsuario.EMPRESA);
					}
					else if (usuario instanceof Dtpostulante)
					{
						sesion.setAttribute("tipoUsuario", TipoUsuario.POSTULANTE);
					}
					sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_CORRECTO);
					sesion.setAttribute("usuarioLogueado", usuario);
					request.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(request, response);
				}
				else
				{
					sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
					request.setAttribute("error", "password incorrecta");
					request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);

				}
			}
		}
		catch (UsuarioNoExisteException e)
		{
			sesion.setAttribute("estadoSesion", EstadoSesion.LOGIN_INCORRECTO);
			request.setAttribute("error", "usuario incorrecta");
			if(userAgent != null && userAgent.toLowerCase().contains("mobile"))
			{
				request.getRequestDispatcher("/WEB-INF/mobile/home/LoginMobile.jsp").forward(request, response);
			}
			else 
			{
				request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
			}
			
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
