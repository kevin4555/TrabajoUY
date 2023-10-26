<%@page import="logica.webservices.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="<%= request.getContextPath() %>/recourse/css/general.css" />
    <title>Consulta Ofertas</title>
    

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
                    
                    ArrayList<DtOfertaLaboral> listaOfertas = (ArrayList<DtOfertaLaboral>) request.getAttribute("listaOfertas");
                    if(listaOfertas.isEmpty()){
                      %>
                      
                                <h1 class="card-text">No hay ofertas disponibles</h1>
                                
                         
                      <%
                    }
                    for (DtOfertaLaboral oferta : listaOfertas) {
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-4 justify-content-center align-items-center d-flex">
                                    <img src="data:image/png;base64,<%= oferta.getImagenBase64() %>" class="img img-fluid rounded-start" alt="Imagen Oferta" />
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            <p><%= oferta.getNombre() %></p>
                                        </h5>
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
                    }
                    %>
                </section>
            </div>
        </div>
    </main>
</body>
</html>
