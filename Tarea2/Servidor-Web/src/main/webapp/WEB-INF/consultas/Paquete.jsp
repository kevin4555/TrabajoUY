<%@page import="model.TipoUsuario"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.datatypes.DtpaquetePublicacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="logica.datatypes.DtcantidadTipoPublicacion"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.ZoneId" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
	<title>Paquete</title>
	
	
	<jsp:include page="../include/Head.jsp" />
</head>
  <body>
  	<% DtpaquetePublicacion paquete = (DtpaquetePublicacion) request.getAttribute("paquete"); 
	EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
	Dtusuario usuario = (Dtusuario) session.getAttribute("usuarioLogueado");
	TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
	%>
	<jsp:include page="../include/NavBar.jsp" />
    <main class="container pt-5">
      <div class="row">
        <jsp:include page="../include/Menu.jsp" />
        <div class="col-9 d-flex flex-column align-items-center flex-grow-1">
          <div class="card mb-3" style="max-width: 540px">
          <%if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger  alert-dismissible fade show" role="alert">
			   <%= (String) request.getAttribute("error") %>
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
          <% } %>
            <div class="row g-0">
              <div class="col-4 d-flex align-items-center">
                <img
                  src="data:image/png;base64,<%= paquete.getImagenBase64() %>"
                  class="img-fluid rounded-start"
                  alt="Imagen Paquete"
                />
              </div>
              <div class="col-8">
                <div class="card-body">
                  <div class="card-header bg-white px-0">
                    <h1 class="m-0 fs-2">
                      <i class="bi bi-box-seam text-primary"></i> Paquete <%= paquete.getNombre() %>
                    </h1>
                  </div>
                  <p class="card-text py-2 fst-italic">
                    <%= paquete.getDescripcion() %>
                  </p>
                  <ul class="list-unstyled">
                    <li class="text-secondary">
                      <i class="bi bi-arrow-right-circle text-primary"></i>
                      Período: <%= paquete.getPeriodoValidez() %> días
                    </li>
                    <li class="text-secondary">
                      <i class="bi bi-arrow-right-circle text-primary"></i>
                      Descuento del <%= paquete.getDescuento() %> % por publicación
                    </li>
                    <li class="text-secondary">
                      <i class="bi bi-arrow-right-circle text-primary"></i>
                      <%
						LocalDate fechaAlta = paquete.getFechaAlta(); // Obtén la fecha como un objeto LocalDate desde tu objeto paquete
						Date fechaAltaDate = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
						SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String newParsedDate = outputDateFormat.format(fechaAltaDate);
						%>
						
						Fecha <%= newParsedDate %>
                    </li>
                  </ul>
                  <div class="publicacionesPaquete">
                    <table class="table borden border-0">
                      <thead>
                        <tr>
                          <th scope="col">Tipo Publicaciones</th>
                          <th scope="col">Cantidad</th>
                        </tr>
                      </thead>
                      <tbody>
                      <%
                      List<DtcantidadTipoPublicacion> cantidadTipoPublicaciones = paquete.getCantidadTipoPublicaciones();
						%>
                      	<% for (DtcantidadTipoPublicacion cantidadTipoPublicacion : cantidadTipoPublicaciones) { %>
					        <tr>
					            <%
					            
			                    String contextPath = request.getContextPath();
			                   	String tipoPublicacionUrl = contextPath + "/tipoPublicacion?nombreTipoPublicacion=" +
			                            java.net.URLEncoder.encode(cantidadTipoPublicacion.getNombreTipoPublicacion(), "UTF-8");					 
					            %>
					            <td><a href="<%= tipoPublicacionUrl %>"><%= cantidadTipoPublicacion.getNombreTipoPublicacion() %></a></td>
					            <td><%= cantidadTipoPublicacion.getCantidad() %></td>
					        </tr>
					    <% } %>
                      </tbody>
                    </table>
                  </div>
                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <h2 class="">$<%= paquete.getCosto() %></h2>
                    <% if (usuario != null && tipoUsuario.equals(TipoUsuario.EMPRESA)) {        
                    String contextPath = request.getContextPath();
                   	String comprarUrl = contextPath + "/paquete?accion=comprar&nombrePaquete=" +
                   			java.net.URLEncoder.encode(paquete.getNombre(), "UTF-8");					 
		            %>
                    
                    <a href="<%= comprarUrl %>" class="btn btn-primary">Comprar</a>
                    <% } %>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
</body>
</html>