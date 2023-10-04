package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class OfertasServlet
 */
@WebServlet("/consultaOfertas")
public class ConsultaOfertasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaOfertasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	IcontroladorOferta controladorOfertas = Fabrica.getInstance().obtenerControladorOferta();
    	ArrayList<String> listaEmpresas = (ArrayList<String>) controladorUsuario.listarEmpresas();
    	request.setAttribute("listaEmpresas", listaEmpresas);
    	String nicknameEmpresa = request.getParameter("empresaSeleccionada");
    	String keyword = request.getParameter("keyword");
    	HashMap<String, BufferedImage> perfilOfertaHashMap = new HashMap<>();
    	if( nicknameEmpresa != null && nicknameEmpresa != "" ) {
    		try {
				ArrayList<DtOfertaLaboral> ofertas = (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa);
				for (DtOfertaLaboral dtOfertaLaboral : ofertas)
				{
					perfilOfertaHashMap.put(dtOfertaLaboral.getNombre(), dtOfertaLaboral.getImagen());
				}
				request.setAttribute("perfilOfertas", perfilOfertaHashMap);
				request.setAttribute("listaOfertas", ofertas);
			} catch (UsuarioNoExisteException e) {
				// agregar pagina de error
				e.printStackTrace();
			}
    	}
    	else if(keyword != null && keyword != "") {
    		ArrayList<DtOfertaLaboral> ofertas = (ArrayList<DtOfertaLaboral>) controladorOfertas.obtenerDtofertasPorKeyword(keyword);
    		request.setAttribute("listaOfertas", ofertas);
    	}
    	request.getRequestDispatcher("/WEB-INF/consultas/ConsultaOfertas.jsp").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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


