<%@page import="logica.classes.OfertaLaboral"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta Ofertas Laborales</title>
<link rel="stylesheet" type="text/css" href="../../webapp/resource/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
                <section>
                    <c:forEach var="ofertas" items="${listaOfertas}">
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <img
                                        class="imgOfertaLaboral"
                                        src="${ofertas.getImagen()}"
                                        alt="Imagen Oferta"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            <strong>${ofertas.getNombre()}</strong>
                                        </h5>
                                        <br>
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            ${ofertas.getDescripcion()}
                                        </h5>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <c:url var="ofertaPerfil" value="/oferta">
                                            <c:param name="nombreOferta" value="${ofertas.getNombre()}" />
                                        </c:url> 
                                        <a href=" ${ofertaPerfil}" class="btn btn-primary">Mas Info</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </c:forEach>
                </section>
            </div>
        </div>
    </main>

</body>
</html>