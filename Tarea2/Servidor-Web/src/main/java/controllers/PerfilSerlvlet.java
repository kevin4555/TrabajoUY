package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.DataTypes.DTEmpresa;
import logica.DataTypes.DTOfertaLaboral;
import logica.DataTypes.DTPaquetePublicacion;
import logica.DataTypes.DTPostulacion;
import logica.DataTypes.DTPostulante;
import logica.DataTypes.DTUsuario;
import logica.classes.Postulante;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorUsuario;

import java.io.IOException;
import java.util.ArrayList;

import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class PerfilSerlvlet
 */
@WebServlet("/perfil")
public class PerfilSerlvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfilSerlvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nicknameUsuario = request.getParameter("nicknameUsuario");
    	IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	try {
			DTUsuario usuario = controladorUsuario.obtenerDTUsuario(nicknameUsuario);
			request.setAttribute("usuario", usuario);
			if(usuario instanceof DTEmpresa) {
				ArrayList<DTOfertaLaboral> ofertasConfirmadas = controladorUsuario.obtenerDTOfertasConfirmadasDeEmpresa(nicknameUsuario);
				ArrayList<DTOfertaLaboral> ofertasIngresadas = controladorUsuario.obtenerDTOfertasIngresadasDeEmpresa(nicknameUsuario);
				ArrayList<DTOfertaLaboral> ofertasRechazadas = controladorUsuario.obtenerDTOfertasRechazadasDeEmpresa(nicknameUsuario);
				ArrayList<DTPaquetePublicacion> paquetes = controladorUsuario.obtenerDTPaquetesDeEmpresa(nicknameUsuario);
				request.setAttribute("ofertasConfirmadas", ofertasConfirmadas);
				request.setAttribute("ofertasIngresadas", ofertasIngresadas);
				request.setAttribute("ofertasRechazadas", ofertasRechazadas);
				request.setAttribute("paquetes", paquetes);
				request.setAttribute("tipoUsuario", "empresa");
			}
			if(usuario instanceof DTPostulante) {
				ArrayList<DTPostulacion> postulaciones = controladorUsuario.obtenerDTPostulacionesDePostulante(nicknameUsuario);
				request.setAttribute("postulaciones", postulaciones);
				request.setAttribute("tipoUsuario", "postulante");
			}
			request.getRequestDispatcher("/WEB-INF/consultas/Perfil.jsp").forward(request, response);
		} catch (UsuarioNoExisteException e) {
			// agregar pagina de error
			e.printStackTrace();
		}
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
