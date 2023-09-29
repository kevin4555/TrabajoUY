package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.DataTypes.DTUsuario;
import logica.controllers.Fabrica;
import logica.interfaces.IControladorOferta;
import model.EstadoSesion;
import model.TipoUsuario;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import excepciones.KeywordNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class AltaOfertaServlet
 */
public class AltaOfertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaOfertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	IControladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    	String tipoPublicacion = request.getParameter("tipoPublicacion");
    	String nombreOferta = request.getParameter("nombreOferta");
    	String descripcion = request.getParameter("descripcion");
    	String [] keywords = request.getParameterValues("keywords");
    	ArrayList<String> listKeywords = new ArrayList<String>();
    	for(String keyword : keywords) {
    		listKeywords.add(keyword);
    	}
    	String horaInicio = request.getParameter("horaInicio");
    	String horaFin = request.getParameter("horaFin");
    	float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));
    	String departamento = request.getParameter("departamento");
    	String ciudad = request.getParameter("ciudad");
    	BufferedImage imagen = null;
    	try {
			Part filePart = request.getPart("imagen");
			if(filePart != null && filePart.getSize() > 0) {
				InputStream fileContent = filePart.getInputStream();
				imagen = ImageIO.read(fileContent);
			}
		} catch (IOException | ServletException e) {
			//agregar pagina de error
			e.printStackTrace();
		}
    	String nombrePaquete = request.getParameter("nombrePaquete");
    	LocalDate fechaAlta = LocalDate.now();
    	HttpSession sesion = request.getSession();
    	DTUsuario empresa = (DTUsuario) sesion.getAttribute("usuarioLogueado");
    	if(empresa == null || sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO || sesion.getAttribute("tipoUsuario" )!= TipoUsuario.EMPRESA) {
    		//agregar pagina de error
    	}
    	String nicknameEmpresa = empresa.getNickname();
    	try {
			controladorOferta.altaOfertaLaboral(nombreOferta, descripcion, horaInicio, horaFin, remuneracion, ciudad, departamento, fechaAlta, tipoPublicacion, nicknameEmpresa, listKeywords, imagen, nombrePaquete);
			request.getRequestDispatcher("/perfil").forward(request, response);
		} catch (OfertaLaboralYaExisteException e) {
			request.setAttribute("mensajeError", "nombre de oferta repetido");
			request.getRequestDispatcher("/WEB-INF/registros/AltaOferta.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TipoPublicacionNoExisteException | KeywordNoExisteException | UsuarioNoExisteException e) {
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
		procesarRequest(request, response);
	}

}
