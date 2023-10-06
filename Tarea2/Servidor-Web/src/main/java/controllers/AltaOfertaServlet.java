package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.imageio.ImageIO;

import excepciones.KeywordNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.controllers.Fabrica;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.DttipoPublicacion;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

import model.EstadoSesion;
import model.TipoUsuario;

@MultipartConfig()

@WebServlet("/altaOferta")
public class AltaOfertaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AltaOfertaServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession sesion = request.getSession();

		if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO)
		{
			// agregar pagina de error
		}
		Enumeration<String> nombresEncabezados = request.getHeaderNames();
        
        // Iterar a través de los nombres de encabezados y mostrar sus valores en la consola
        for (Enumeration<String> e = nombresEncabezados; e.hasMoreElements();) {
        	 String nombreEncabezado = e.nextElement();
        	 String valorEncabezado = request.getHeader(nombreEncabezado);
        	 System.out.println(nombreEncabezado + ": " + valorEncabezado);

        }
	    
		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		Dtusuario empresa = (Dtusuario) sesion.getAttribute("usuarioLogueado");
		String tipoPublicacion = request.getParameter("tipoPublicacion");
		String nombreOferta = request.getParameter("nombreOferta");
		String descripcion = request.getParameter("descripcion");
		String[] keywords = request.getParameterValues("keywords");
		String horaInicio = request.getParameter("horaInicio");
		String horaFin = request.getParameter("horaFin");
		float remuneracion = 0;
		String departamento = request.getParameter("departamento");
		String ciudad = request.getParameter("ciudad");
		//Part imagen = request.getPart("imagenOferta");
//		if (imagen != null)
//			request.getRequestDispatcher("/WEB-INF/home/Login.jsp").forward(request, response);
		ArrayList<String> listKeywords = new ArrayList<>();
		LocalDate fechaAlta = LocalDate.now();
		String nombrePaquete = null;
		BufferedImage imagenParametroAlta = null;


//		if (imagen != null)
//		{
//			InputStream inputStream = imagen.getInputStream();
//			imagenParametroAlta = ImageIO.read(inputStream);
//
//		}

		if (keywords != null)
		{
			for (String keyword : keywords)
			{
				listKeywords.add(keyword);
			}
		}

		if (request.getParameter("remuneracion") != null)
		{
			remuneracion = Float.valueOf(request.getParameter("remuneracion"));
		}

		if (!(nombreOferta == null))
		{
			try
			{
				controladorOferta.altaOfertaLaboral(nombreOferta, descripcion, horaInicio, horaFin, remuneracion,
						ciudad, departamento, fechaAlta, tipoPublicacion, empresa.getNickname(), listKeywords,
						imagenParametroAlta, nombrePaquete);
				String url = request.getContextPath() + "/perfil?nicknameUsuario=" + empresa.getNickname();
				response.sendRedirect(url);

			}
			catch (TipoPublicacionNoExisteException | KeywordNoExisteException | UsuarioNoExisteException e)
			{
				// agregar pagina de error
				e.printStackTrace();

			}
			catch (OfertaLaboralYaExisteException e)
			{
				request.setAttribute("mensajeError", "nombre de oferta repetido");
				request.getRequestDispatcher("/WEB-INF/registros/AltaOferta.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		else
		{

			if (request.getAttribute("nombreOferta") == null)
			{
				ArrayList<String> listaTipos = (ArrayList<String>) controladorOferta.listarTipoDePublicaciones();
				ArrayList<DttipoPublicacion> listaDtTipos = new ArrayList<DttipoPublicacion>();
				IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
				for (String nombreTipo : listaTipos)
				{
					try
					{
						listaDtTipos.add(controladorOferta.obtenerDttipoPublicacion(nombreTipo));
					}
					catch (TipoPublicacionNoExisteException e)
					{
						// agregar pagina de error
						e.printStackTrace();
					}
				}

				try
				{
					ArrayList<DtCompraPaquete> listaCompraPaquetes = (ArrayList<DtCompraPaquete>) controladorUsuario
							.obtenerDtCompraPaqueteDeEmpresa(empresa.getNickname());
					request.setAttribute("listaCompraPaquetes", listaCompraPaquetes);
				}
				catch (UsuarioNoExisteException e)
				{
					// agregar pagina de error
					e.printStackTrace();
				}

				ArrayList<String> listKeywordsAtributo = (ArrayList<String>) controladorOferta.listarKeywords();
				request.setAttribute("listaKeywords", listKeywordsAtributo);
				request.setAttribute("listaTipoPublicacion", listaDtTipos);
				request.getRequestDispatcher("/WEB-INF/registros/AltaOferta.jsp").forward(request, response);
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
		procesarRequest(request, response);
	}

}