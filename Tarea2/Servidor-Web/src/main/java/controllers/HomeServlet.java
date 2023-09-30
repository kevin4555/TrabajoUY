package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.controllers.Fabrica;
import logica.controllers.Loader;
import logica.datatypes.DtofertaLaboral;
import logica.interfaces.IcontroladorOferta;
import model.EstadoSesion;
import model.TipoUsuario;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import excepciones.UsuarioYaExistePostulacion;



@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    


	private static void initSesion(HttpServletRequest request) {
		if (!Loader.datosCargados()) {
			Loader loader = new Loader();
			try {
				loader.cargarDatos();
				loader.confirmarOfertas();
			} catch (UsuarioNoExisteException | OfertaLaboralNoExisteException | ParseException
					| UsuarioYaExisteException | UsuarioEmailRepetidoException | TipoPublicacionYaExisteException
					| KeywordYaExisteException | KeywordNoExisteException | TipoPublicacionNoExisteException
					| OfertaLaboralYaExisteException | PaquetePublicacionYaExisteException | UsuarioYaExistePostulacion e) {
				// COMPLETAR con paginas de errores
				e.printStackTrace();
			}
		}
		HttpSession sesion = request.getSession();
		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		ArrayList<String> listaKeywords = controladorOferta.listarKeywords();
		sesion.setAttribute("listaKeywords", listaKeywords);
		
		if (sesion.getAttribute("estadoSesion") == null) {
			sesion.setAttribute("estadoSesion", EstadoSesion.NO_LOGIN);
		}
		if (sesion.getAttribute("tipoUsuario") == null) {
			sesion.setAttribute("tipoUsuario", TipoUsuario.VISITANTE);
		}
	}

	private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initSesion(request);
		IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		ArrayList<DtofertaLaboral> dTOfertas = controladorOferta.obtenerDtOfertasConfirmadas();
		request.setAttribute("listaOfertasConfirmadas", dTOfertas);
		request.getRequestDispatcher("/WEB-INF/home/Home.jsp").forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
