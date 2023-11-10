<%@page import="main.java.webservices.DtPostulante"%>
<%@page import="main.java.webservices.DtUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Usuarios</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
                <section>
                    <%
                    ArrayList<DtUsuario> listaUsuarios = (ArrayList<DtUsuario>) request.getAttribute("listaUsuarios");
                                                            String tipoEsperadoVar = (String) request.getAttribute("tipoEsperado");
                                                            for (DtUsuario usuario : listaUsuarios) {
                                                                String tipoUser = usuario.getClass().getSimpleName();
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <img
                                    	src="data:image/png;base64,<%=usuario.getImagenBase64()%>"
                                        class="img-fluid rounded-start"
                                        alt="Imagen PerfilUsuario"
                                    />
                                </div>
                                <div class="col-md-9">
                                <div class="card-header d-flex justify-content-between align-items-start">
                                	<h5  class=" card-title">
                                            <%= usuario.getNickname() %>
	                                </h5>
	                                </div>                
                                    <div class="card-body">
                                        
                                        <p class="card-text"><%=usuario.getNombre()+" " + usuario.getApellido()%></p>
                                        <%
                                        if (usuario instanceof DtPostulante) {
                                        %>
                                            <p class="card-text">Tipo: Postulante</p>
                                        <%
                                        } else {
                                        %>
                                            <p class="card-text">Tipo: Empresa</p>
                                        <%
                                        }
                                        %>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                    <%
                                    String contextPath = request.getContextPath();
                                    %>
                                       <a href="<%= contextPath %>/perfil?nicknameUsuario=<%= usuario.getNickname() %>" class="btn btn-primary">Perfil</a>
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