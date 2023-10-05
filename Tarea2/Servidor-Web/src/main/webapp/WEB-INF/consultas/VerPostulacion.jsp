<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="logica.classes.Postulante"%>
<%@page import="logica.classes.Postulacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../../webapp/resource/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
            <% Object dtOferta = request.getAttribute("ofertas"); %>
			<% Object postulacion = request.getAttribute("postulacion"); %>
			<% Object postulante = request.getAttribute("postulante"); %>
            
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="${postulante.getImagen()}" 
                                    class="img-fluid rounded-start" 
                                    alt="Imagen Postulacion">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">Postulación a oferta laboral</h5>
                                    <p class="card-text">
                                        <strong>Postulante: </strong> <a href="consultarMiPerfilPostulante.html">${postulacion.getNicknamePostulante()}</a>
                                        <br><br>
                                        <strong>CV reducido: </strong> ${postulacion.getCvReducido()}
                                        <br><br>
                                        <strong>Motivación: </strong> ${postulacion.getDescripMotivacion()}
                                        <br><br>
                                        <strong>Fecha de postulación: </strong> ${postulacion.getFechaPostulacion}
                                        <br><br>
                                        <div class="row justify-content-center buttons-container">
                                            <!-- Aquí puedes agregar contenido relacionado con los botones si es necesario -->
                                        </div>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <c:url var="ofertaPerfil" value="/oferta">
                                            <c:param name="nombreOferta" value="${ofertas.getNombre()}" />
                                        </c:url> 
                                        <a href=" ${ofertaPerfil}" class="btn btn-primary">${ofertas.getNombre()}</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>