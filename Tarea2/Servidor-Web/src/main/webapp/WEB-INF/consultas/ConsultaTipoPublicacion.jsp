<%@page import="main.java.webservices.DtTipoPublicacion"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulta de Tipos de publicaciones</title>
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
                    ArrayList<DtTipoPublicacion> listaTipoPublicacion = (ArrayList<DtTipoPublicacion>) request.getAttribute("listaTipoPublicacion");
                                            for (DtTipoPublicacion tipoPublicacion : listaTipoPublicacion) {
                    %>
                    <div class="card">
                        <div class="row g-0">
                            <div class="col-md-9">
                                <div class="card-body">
                                    <h5 class="card-header p-0 border-0 bg-white text-start">
                                        <%= tipoPublicacion.getNombre() %>
                                    </h5>
                                    <p class="card-text">
                                        <%= tipoPublicacion.getDescripcion() %>
                                    </p>
                                </div>
                                <div class="card-footer border-0 bg-white text-end">
                                    <%
                                    String contextPath = request.getContextPath();
                                    %>
                                    <a href="<%= contextPath %>/tipoPublicacion?nombreTipoPublicacion=<%=tipoPublicacion.getNombre()%>" class="btn btn-primary">Mas Info</a>
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