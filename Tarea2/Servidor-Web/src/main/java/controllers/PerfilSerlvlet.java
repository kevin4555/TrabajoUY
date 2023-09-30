package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.classes.Postulante;
import logica.controllers.Fabrica;
import logica.datatypes.Dtempresa;
import logica.datatypes.DtofertaLaboral;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtpostulante;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorUsuario;

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
    	IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	try {
			Dtusuario usuario = controladorUsuario.obtenerDtusuario(nicknameUsuario);
			request.setAttribute("usuario", usuario);
			if(usuario instanceof Dtempresa) {
				ArrayList<DtofertaLaboral> ofertasConfirmadas = controladorUsuario.obtenerDtofertasConfirmadasDeEmpresa(nicknameUsuario);
				ArrayList<DtofertaLaboral> ofertasIngresadas = controladorUsuario.obtenerDtofertasIngresadasDeEmpresa(nicknameUsuario);
				ArrayList<DtofertaLaboral> ofertasRechazadas = controladorUsuario.obtenerDtofertasRechazadasDeEmpresa(nicknameUsuario);
				ArrayList<DtpaquetePublicacion> paquetes = controladorUsuario.obtenerDtpaquetesDeEmpresa(nicknameUsuario);
				request.setAttribute("ofertasConfirmadas", ofertasConfirmadas);
				request.setAttribute("ofertasIngresadas", ofertasIngresadas);
				request.setAttribute("ofertasRechazadas", ofertasRechazadas);
				request.setAttribute("paquetes", paquetes);
				request.setAttribute("tipoUsuario", "empresa");
			}
			if(usuario instanceof Dtpostulante) {
				ArrayList<Dtpostulacion> postulaciones = controladorUsuario.obtenerDtpostulacionesDePostulante(nicknameUsuario);
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
