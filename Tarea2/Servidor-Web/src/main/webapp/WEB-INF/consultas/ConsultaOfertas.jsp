<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="model.TipoUsuario"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta Ofertas Laborales</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resource/css/general.css">
</head>
<body>

<jsp:include page="../include/Head.jsp" />
<jsp:include page="../include/NavBar.jsp" />
<main class="container pt-5">
    <div class="row">
    
    	<jsp:include page="../include/Menu.jsp" />
        
        <div class="col-8">
            <section>
                <%
                ArrayList<DtOfertaLaboral> listaOfertas = (ArrayList<DtOfertaLaboral>) request.getAttribute("listaOfertas");
                for (DtOfertaLaboral oferta : listaOfertas) {
                %>
                <div class="card">
                    <div class="row g-0">
                        <div class="col-md-3 justify-content-center align-items-center d-flex">
                            <img
                                class="imgOfertaLaboral"
                                src="data:image/png;base64,<%= oferta.getImagenBase64() %>"
                                alt="Imagen Oferta"
                            />
                        </div>
                        <div class="col-md-9">
                            <div class="card-body">
                                <h5 class="card-header p-0 border-0 bg-white text-start">
                                    <strong><%= oferta.getNombre() %></strong>
                                </h5>
                                <br>
                                <h5 class="card-header p-0 border-0 bg-white text-start">
                                    <%= oferta.getDescripcion() %>
                                </h5>
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