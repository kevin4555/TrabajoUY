package controllers;

import java.io.IOException;
import java.util.ArrayList;

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
		DtUsuario usuario = (DtUsuario) sesion.getAttribute("usuarioLogueado");
		PublicadorService publicadorService = new PublicadorService();
  logica.webservices.Publicador cliente = publicadorService.getPublicadorPort();
		ArrayList<DtOfertaLaboral> dTOfertas = new ArrayList<DtOfertaLaboral>(); 

		try {
			ArrayList<DtPostulacion> postulaciones = (ArrayList<DtPostulacion>) cliente
					.obtenerDtpostulacionesDePostulante(usuario.getNickname()).getItem();
			for (DtPostulacion dtpostulacion : postulaciones)
			{
				dTOfertas.add(cliente.obtenerDtOfertaLaboral(dtpostulacion.getNombreOferta()));
				System.out.println(cliente.obtenerDtOfertaLaboral(dtpostulacion.getNombreOferta()));
			}
			
			request.setAttribute("postulaciones", postulaciones);
			request.setAttribute("ofertasPostuladas", dTOfertas);
			request.setAttribute("tipoUsuario", "postulante");
			request.setAttribute("usuario", usuario);
		} catch( UsuarioNoExisteException_Exception | IOException_Exception | OfertaLaboralNoExisteException_Exception e)
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
