package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.controllers.Fabrica;
import logica.datatypes.DtpaquetePublicacion;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import excepciones.PaquetePublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class ConsultaPaquetes
 */
@WebServlet("/compraPaquete")
public class CompraPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraPaqueteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void procesarRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    	String nicknameempresa = request.getParameter("nicknameEmpresa");
    	String nombrePaquete = request.getParameter("nombrePaquete");
    	try {
        ArrayList<String> paquetesNoComprados = (ArrayList<String>) controladorUsuario.listarPaquetesNoCompradosDeEmpresa(nicknameempresa);
        Boolean permitido = false;
        for(String paquete : paquetesNoComprados) {
          if(paquete.equals(nombrePaquete)) {
            permitido = true;
            break;
          }
        }
        if(permitido) {
          controladorUsuario.comprarPaquete(nicknameempresa, nombrePaquete, LocalDate.now());
          String url = request.getContextPath() + "/perfil?nicknameUsuario="
              + nicknameempresa;
          response.sendRedirect(url);
          return;
        }
        else {
          String url = request.getContextPath() + "/paquete?nombrePaquete="
              + nombrePaquete + "&error=" + URLEncoder.encode("Paquete ya comprado", "UTF-8");
          response.sendRedirect(url);
          return;
        }
      } catch (UsuarioNoExisteException | PaquetePublicacionNoExisteException e) {
    	  request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
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
