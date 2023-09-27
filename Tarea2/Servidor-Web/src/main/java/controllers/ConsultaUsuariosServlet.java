package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.DataTypes.DTUsuario;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorUsuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class ConsultaUsuariosServlet
 */
@WebServlet("/consultaUsuarios")
public class ConsultaUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaUsuariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	ArrayList<String> listaUsuarios = controladorUsuario.listaDeUsuarios();
    	Collections.sort(listaUsuarios);
    	ArrayList<DTUsuario> listaResultado = new ArrayList<DTUsuario>();
    	for(String nick : listaUsuarios) {
    		try {
				listaResultado.add(controladorUsuario.obtenerDTUsuario(nick));
			} catch (UsuarioNoExisteException e) {
				// agregar pagina de error
				e.printStackTrace();
			}
    	}
    	request.setAttribute("listaUsuarios", listaResultado);
    	request.getRequestDispatcher("/WEB-INF/consultas/ConsultaUsuarios.jsp").forward(request, response);
    	
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
