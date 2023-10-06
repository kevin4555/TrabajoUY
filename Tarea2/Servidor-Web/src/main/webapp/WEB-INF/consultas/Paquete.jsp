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
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
		rel="stylesheet" />
	<link href="../../resource/css/general.css" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
	<title>Paquete</title>
	
	
	<jsp:include page="../include/Head.jsp" />
</head>
  <body>
  	<% DtpaquetePublicacion paquete = (DtpaquetePublicacion) request.getAttribute("paquete"); %>
	<jsp:include page="../include/NavBar.jsp" />
    <main class="container pt-5">
      <div class="row">
        <jsp:include page="../include/Menu.jsp" />
        <div class="col-9 d-flex flex-column align-items-center flex-grow-1">
          <div class="card mb-3" style="max-width: 540px">
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
					                String tipoPublicacionUrl = "/consultaTipoPostulacion?tipoPublicacion=" 
					            								+ cantidadTipoPublicacion.getNombreTipoPublicacion();
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