package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.webservices.DtCompraPaquete;
import logica.webservices.DtEmpresa;
import logica.webservices.DtOfertaLaboral;
import logica.webservices.DtPostulacion;
import logica.webservices.DtPostulante;
import logica.webservices.DtUsuario;
import logica.webservices.IOException_Exception;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.OfertaLaboralNoSePuedeFinalizar_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioNoExisteException_Exception;
import model.TipoUsuario;



/**
 * Servlet implementation class PerfilSerlvlet
 */
@WebServlet("/finalizarOferta")
public class FinalizarOfertaServlet extends HttpServlet
{
 private static final long serialVersionUID = 1L;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public FinalizarOfertaServlet()
 {
  super();
  // TODO Auto-generated constructor stub
 }
 
 private void procesarRequest(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
    
    
    String nombreOferta = request.getParameter("nombreOferta");
    logica.webservices.PublicadorService service = new PublicadorService();
    logica.webservices.Publicador port = service.getPublicadorPort();
    try {
      port.finalizarOferta(nombreOferta);
    } catch (OfertaLaboralNoExisteException_Exception | OfertaLaboralNoSePuedeFinalizar_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    request.getRequestDispatcher("/WEB-INF/consultas/miPerfil.jsp").forward(request, response);
    
   
   }
 
 
 
 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {

   procesarRequest(request, response);

   // TODO Auto-generated catch block

 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
  *      response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {
  // TODO Auto-generated method stub
  doGet(request, response);
 }
}
