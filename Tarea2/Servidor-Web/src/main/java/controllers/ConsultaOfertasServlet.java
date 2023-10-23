package controllers;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

/**
 * Servlet implementation class OfertasServlet
 */
@WebServlet("/consultaOfertas")
public class ConsultaOfertasServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaOfertasServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		IcontroladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();

		String nicknameEmpresa = request.getParameter("nicknameEmpresa");
		String keyword = request.getParameter("keyword");
		String userAgent = request.getHeader("User-Agent");

		if (userAgent != null && userAgent.toLowerCase().contains("mobile"))
		{
			if (nicknameEmpresa == null && keyword == null)
			{
				ArrayList<String> listaNickEmpresas = (ArrayList<String>) controladorUsuario.listarEmpresas();
				ArrayList<Dtusuario> listaEmpresas = new ArrayList<Dtusuario>();

				for (String nickEmpresa : listaNickEmpresas)
				{
					try
					{
						Dtusuario empresa = controladorUsuario.obtenerDtusuario(nickEmpresa);
						listaEmpresas.add(empresa);
					}
					catch (UsuarioNoExisteException e)
					{
						request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
						e.printStackTrace();
					}
				}

				request.setAttribute("listaEmpresas", listaEmpresas);
				String forwardPath = "/WEB-INF/mobile/consultas/listarEmpresasMobile.jsp";
				request.getRequestDispatcher(forwardPath).forward(request, response);
				return;
			}
		}
		else
		{
			if (nicknameEmpresa == null && keyword == null)
			{
				ArrayList<String> listaNickEmpresas = (ArrayList<String>) controladorUsuario.listarEmpresas();
				ArrayList<Dtusuario> listaEmpresas = new ArrayList<Dtusuario>();

				for (String nickEmpresa : listaNickEmpresas)
				{
					try
					{
						Dtusuario empresa = controladorUsuario.obtenerDtusuario(nickEmpresa);
						listaEmpresas.add(empresa);
					}
					catch (UsuarioNoExisteException e)
					{
						request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
						e.printStackTrace();
					}
				}

				request.setAttribute("listaEmpresas", listaEmpresas);
				String forwardPath = "/WEB-INF/consultas/listarEmpresas.jsp";
				request.getRequestDispatcher(forwardPath).forward(request, response);
				return;
			}
		}
		if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
		    ArrayList<DtOfertaLaboral> ofertas = null;

		    if (nicknameEmpresa != null) {
		        try {
		            ofertas = (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa);
		        } catch (UsuarioNoExisteException e) {
		            request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
		            e.printStackTrace();
		            return;
		        }
		    } else if (keyword != null) {
		        ofertas = (ArrayList<DtOfertaLaboral>) controladorOfertas.obtenerDtofertasPorKeyword(keyword);
		    }

		    request.setAttribute("listaOfertas", ofertas);
		    request.getRequestDispatcher("/WEB-INF/mobile/consultas/ConsultaOfertasMobile.jsp").forward(request, response);
		} else {
		    ArrayList<DtOfertaLaboral> ofertas = null;

		    if (nicknameEmpresa != null) {
		        try {
		            ofertas = (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa);
		        } catch (UsuarioNoExisteException e) {
		            request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
		            e.printStackTrace();
		            return;
		        }
		    } else if (keyword != null) {
		        ofertas = (ArrayList<DtOfertaLaboral>) controladorOfertas.obtenerDtofertasPorKeyword(keyword);
		    }

		    request.setAttribute("listaOfertas", ofertas);
		    request.getRequestDispatcher("/WEB-INF/consultas/ConsultaOfertas.jsp").forward(request, response);
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
