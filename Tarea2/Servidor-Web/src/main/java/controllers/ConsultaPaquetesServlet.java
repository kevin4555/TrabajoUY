package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.webservices.DtPaquetePublicacion;
import logica.webservices.IOException_Exception;
import logica.webservices.PublicadorService;

/**
 * Servlet implementation class ConsultaPaquetes
 */
@WebServlet("/consultaPaquetes")
public class ConsultaPaquetesServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public ConsultaPaquetesServlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PublicadorService publicadorService = new PublicadorService();
    logica.webservices.Publicador cliente = publicadorService.getPublicadorPort();
    ArrayList<DtPaquetePublicacion> listaPaquetes;
    try {
      listaPaquetes = (ArrayList<DtPaquetePublicacion>) cliente.listarDtpaquetes().getItem();
      request.setAttribute("listaPaquetes", listaPaquetes);
      request.getRequestDispatcher("/WEB-INF/consultas/ConsultaPaquetes.jsp").forward(request,
          response);
    } catch (IOException_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    procesarRequest(request, response);
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    procesarRequest(request, response);
  }
  
}
