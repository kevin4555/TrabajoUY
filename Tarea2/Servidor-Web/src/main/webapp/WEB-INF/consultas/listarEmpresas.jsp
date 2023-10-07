<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista de Empresas</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/css/general.css">
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
                    ArrayList<Dtusuario> listaEmpresas = (ArrayList<Dtusuario>) request.getAttribute("listaEmpresas");
                    for (Dtusuario empresa : listaEmpresas) {
                    %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <img
                                        src="data:image/png;base64,<%= empresa.getImagenBase64() %>"
                                        class="img-fluid rounded-start"
                                        alt="Imagen PerfilUsuario"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            <%= empresa.getNickname() %>
                                        </h5>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <%
                                    		String contextPath = request.getContextPath();
                                    	%>
                                       <a href="<%= contextPath %>/consultaOfertas?nicknameEmpresa=<%= empresa.getNickname() %>" class="btn btn-primary">Ver Ofertas</a>
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