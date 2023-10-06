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
    	
    	
    	String nicknameEmpresa = request.getParameter("nicknameEmpresa");
    	String keyword = request.getParameter("keyword");
    	
    	if(nicknameEmpresa == null && keyword == null ) {
    	  ArrayList<String> listaNickEmpresas = (ArrayList<String>) controladorUsuario.listarEmpresas();
    	  ArrayList<Dtusuario> listaEmpresas = new ArrayList<Dtusuario>();
       for (String nickEmpresa : listaNickEmpresas) {
         try {
           Dtusuario empresa = controladorUsuario.obtenerDtusuario(nickEmpresa);
           listaEmpresas.add(empresa);
         } catch (UsuarioNoExisteException e) {
           // agregar pagina de error
           e.printStackTrace();
         }
    	  }
    	  request.setAttribute("listaEmpresas", listaEmpresas);
    	  request.getRequestDispatcher("/WEB-INF/consultas/listarEmpresas.jsp").forward(request, response);
    	}
    	if( nicknameEmpresa != null) {
    		try {
				ArrayList<DtOfertaLaboral> ofertas = (ArrayList<DtOfertaLaboral>) controladorUsuario.obtenerDtofertasConfirmadasDeEmpresa(nicknameEmpresa);
				request.setAttribute("listaOfertas", ofertas);
			} catch (UsuarioNoExisteException e) {
				// agregar pagina de error
				e.printStackTrace();
			}
    	}
    	else if(keyword != null) {
    		ArrayList<DtOfertaLaboral> ofertas = (ArrayList<DtOfertaLaboral>) controladorOfertas.obtenerDtofertasPorKeyword(keyword);
    		request.setAttribute("listaOfertas", ofertas);
    	}
    	request.getRequestDispatcher("/WEB-INF/consultas/ConsultaOfertas.jsp").forward(request, response);
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
	  procesarRequest(request, response);
	}

}


