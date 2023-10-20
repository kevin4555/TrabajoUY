<%@page import="logica.datatypes.DtOfertaLaboral"%>
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

    <jsp:include page="../../include/Head.jsp" />
</head>
<body class="mb-4">
    <jsp:include page="../../mobile/include/NavBarMobile.jsp" />
    <jsp:include page="../../mobile/include/MenuMobile.jsp" />
    <main class="container pt-5">
        <div class="row">

            <div class="col-8">
                <section>
                    <%
                    
                    ArrayList<DtOfertaLaboral> listaOfertasConfirmadas = (ArrayList<DtOfertaLaboral>) request.getAttribute("listaOfertasConfirmadas");
                    for (DtOfertaLaboral oferta : listaOfertasConfirmadas) {
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-4 justify-content-center align-items-center d-flex">
                                    <img src="data:image/png;base64,<%= oferta.getImagenBase64() %>" class="img-fluid rounded-start" alt="Imagen Oferta" />
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
