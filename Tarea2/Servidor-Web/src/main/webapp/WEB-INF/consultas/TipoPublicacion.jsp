<%@page import="logica.datatypes.DttipoPublicacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.ZoneId" %>
<!DOCTYPE html>
<html lang="es">
<head>
  	<meta charset="ISO-8859-1">
    <title>Tipo de publicación</title>
    <jsp:include page="../include/Head.jsp" />
</head>
<body>
<% DttipoPublicacion tipoPublicacion = (DttipoPublicacion) request.getAttribute("tipoPublicacion"); %>
	<jsp:include page="../include/NavBar.jsp" />

<main class="container pt-5">
    <div class="row">
        <jsp:include page="../include/Menu.jsp" />
        <div class="col-9 d-flex flex-column align-items-center flex-grow-1">
            <div class="card mb-3" style="max-width: 540px">
                <div class="row g-0">
                    <div class="col">
                        <div class="card-body">
                            <div class="card-header bg-white px-0">
                                <h1 class="m-0 fs-2"><%= tipoPublicacion.getNombre() %></h1>
                            </div>
                            <p class="card-text py-2 fst-italic">
                                <%= tipoPublicacion.getDescripcion() %>
                            </p>
                            <ul class="list-unstyled">
                                <li class="text-secondary">
                                    <i class="bi bi-arrow-right-circle text-primary"></i>
                                    Exposición: <%= tipoPublicacion.getExposicion() %>
                                </li>
                                <li class="text-secondary">
                                    <i class="bi bi-arrow-right-circle text-primary"></i>
                                    Duración: <%= tipoPublicacion.getDuracionDia() %>
                                </li>
                                <li class="text-secondary">
                                    <i class="bi bi-arrow-right-circle text-primary"></i>
                                    <%
										LocalDate fechaAlta = tipoPublicacion.getFechaAlta(); // Obtén la fecha como un objeto LocalDate desde tu objeto paquete
										Date fechaAltaDate = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
										SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
										String newParsedDate = outputDateFormat.format(fechaAltaDate);
									%>
										
										Fecha <%= newParsedDate %>
                                </li>
                            </ul>
                            <div class="text-start">
                                <h2 class=""><%= tipoPublicacion.getCosto() %> <sub>/Oferta laboral</sub></h2>
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