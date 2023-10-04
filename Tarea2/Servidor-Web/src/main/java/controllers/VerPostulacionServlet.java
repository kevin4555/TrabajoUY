package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DtOfertaLaboral;
import logica.datatypes.Dtpostulacion;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

import java.io.IOException;

import excepciones.OfertaLaboralNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoExistePostulacion;

/**
 * Servlet implementation class VerPostulacionServlet
 */
@WebServlet("/verPostulacion")
public class VerPostulacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPostulacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
      IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
      String nicknamePostulante = request.getParameter("nicknamePostulante");
      String nombreOferta = request.getParameter("nombreOferta");
      
      if(nombreOferta == null || nicknamePostulante == null) {
        //agregar pagina de error
      }
      try {
        Dtusuario postulante = controladorUsuario.obtenerDtusuario(nicknamePostulante);
        Dtpostulacion postulacion = controladorUsuario.obtenerDtpostulacion(nicknamePostulante, nombreOferta);
        DtOfertaLaboral dtOferta= controladorOferta.obtenerDtOfertaLaboral(nombreOferta);
        request.setAttribute("ofertas", dtOferta);
        request.setAttribute("postulacion", postulacion);
        request.setAttribute("postulante", postulante);
        request.getRequestDispatcher("/WEB-INF/consultas/VerPostulacion.jsp").forward(request, response);
      } catch (UsuarioNoExisteException | UsuarioNoExistePostulacion | OfertaLaboralNoExisteException e) {
        // TODO Auto-generated catch block
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
	  procesarRequest(request, response);
	}

}
