<%@page import="logica.webservices.DtPostulante"%>
<%@page import="logica.webservices.DtUsuario"%>
<%@page import="model.TipoUsuario"%>
<%@page import="logica.webservices.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="<%= request.getContextPath() %>/recourse/css/general.css" />
    <title>Home</title>
    <%
    ArrayList<String> listaKeywords = (ArrayList<String>) session.getAttribute("listaKeywords");
    %>

    <jsp:include page="../include/Head.jsp" />
</head>
<body class="mb-4">
    <jsp:include page="../include/NavBar.jsp" />
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp" />
            <div class="col-8">
                <section>
                    <%
                    
                    ArrayList<DtOfertaLaboral> listaOfertasConfirmadas = (ArrayList<DtOfertaLaboral>) request.getAttribute("listaOfertasConfirmadas");
                    for (DtOfertaLaboral oferta : listaOfertasConfirmadas) {
                      if(!oferta.isEstaVencida()){
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-4 justify-content-center align-items-center d-flex">
                                    <img src="data:image/png;base64,<%= oferta.getImagenBase64() %>" class="img-fluid rounded-start" alt="Imagen Oferta" />
                                </div>
                                <div class="col-md-8">
                                <div class="card-header d-flex justify-content-between align-items-start">
                                	<h5  class=" card-title">
                                            <%= oferta.getNombre() %>
	                                </h5>
	                                <%
				                    TipoUsuario tipoUsuarioSesion = (TipoUsuario) session.getAttribute("tipoUsuario");
				                    if (tipoUsuarioSesion == TipoUsuario.POSTULANTE) { 
				                    	DtPostulante postulante = (DtPostulante) session.getAttribute("usuarioLogueado"); %>
				                    		<div class="star-icon">
			     								<i class="bi bi-star<%= postulante.getOfertasFavoritas().contains(oferta.getNombre())? "-fill":"" %> fs-5 orange-star"></i>
			    							</div>	                        
				                    <% } %>	                                
			    				</div>       
	                             <div class="card-body">
	                                 
	                                 <p class="card-text"><%= oferta.getDescripcion() %></p>
	                             </div>
	                             <div class="card-footer border-0 bg-white text-end">
	                                 <%
	                             		String contextPath = request.getContextPath();
	                             	%>
	                                <a href="<%= contextPath %>/oferta?nombreOferta=<%= oferta.getNombre() %>" class="btn btn-primary">Mas Info</a>
	
	                             </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    <%
                    }}
                    %>
                </section>
            </div>
        </div>
    </main>
</body>
</html>
