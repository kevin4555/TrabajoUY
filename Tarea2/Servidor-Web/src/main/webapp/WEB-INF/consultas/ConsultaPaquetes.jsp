<%@page import="logica.datatypes.DtpaquetePublicacion"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
                    <% java.util.List<DtpaquetePublicacion> listaPaquetes = (java.util.List<DtpaquetePublicacion>) request.getAttribute("listaPaquetes");
                    for (DtpaquetePublicacion paquete : listaPaquetes) { %>
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <img
                                        class="imgPaquete img img-fluid"
                                        src="data:image/png;base64,<%= paquete.getImagenBase64() %>"
                                        alt="Imagen Paquete"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            <%= paquete.getNombre() %>
                                        </h5>
                                        <br>
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            <%= paquete.getDescripcion() %>
                                        </h5>
                                        <br>
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            $<span id="costo"><%= paquete.getCosto() %></span>
                                        </h5>
                                        <br>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <% String contextPath = request.getContextPath(); %>
                                        <a href="<%= contextPath %>/paquete?nombrePaquete=<%= paquete.getNombre() %>" class="btn btn-primary">Mas Info</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    <% } %>
                </section>
            </div>
        </div>
    </main>
</body>
</html>