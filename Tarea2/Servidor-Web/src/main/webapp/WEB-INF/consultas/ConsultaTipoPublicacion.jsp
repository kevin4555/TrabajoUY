<%@page import="logica.datatypes.DttipoPublicacion"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Consulta de Paquetes</title>
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
                        ArrayList<DttipoPublicacion> listaTipoPublicacion = (ArrayList<DttipoPublicacion>) request.getAttribute("listaTipoPublicacion");
                        for (DttipoPublicacion tipoPublicacion : listaTipoPublicacion) {
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
                                    <a href="<%= contextPath %>/paquete?nombrePaquete=<%=tipoPublicacion.getNombre()%>" class="btn btn-primary">Mas Info</a>
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