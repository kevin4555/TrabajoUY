package controllers;

import jakarta.servlet.http.HttpFilter;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.PublicadorService;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class VisitasFilter
 */
@WebFilter("/oferta")
public class VisitasFilter extends HttpFilter
      implements
      Filter {

  /**
   * @see HttpFilter#HttpFilter()
   */
  public VisitasFilter() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see Filter#destroy()
   */
  public void destroy() {
    // TODO Auto-generated method stub
  }

  /**
   * @see Filter#doFilter(ServletRequest, ServletResponse,
   *      FilterChain)
   */
  public void doFilter(ServletRequest request,
        ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    PublicadorService publicadorService =
          new PublicadorService();
    logica.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    String nombreOferta =
          request.getParameter("nombreOferta");
    try {
      port.agregarVisitaAoferta(nombreOferta);
    } catch (OfertaLaboralNoExisteException_Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    chain.doFilter(request, response);
  }

  /**
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig fConfig)
        throws ServletException {
    // TODO Auto-generated method stub
  }

}
