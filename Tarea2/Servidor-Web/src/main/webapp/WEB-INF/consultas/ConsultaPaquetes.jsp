<%@page import="logica.classes.PaquetePublicacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Consulta de Paquetes</title>
    <link rel="stylesheet" type="text/css" href="../../webapp/recourse/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
                <section>
                    <c:forEach var="paquetes" items="${listaPaquetes}">
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <c:set var="nombreClaveHash" value="${paquetes.getNombre()}"/>
                                    <img
                                        class="imgPaquete"
                                        src="${perfilPaquetes[nombreClaveHash]}"
                                        alt="Imagen Paquete"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            ${paquetes.getNombre()}
                                        </h5>
                                        <br>
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            ${paquetes.getDescripcion()}
                                        </h5>
                                        <br>
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            $<span id="costo">${paquetes.getCosto()}</span>
                                        </h5>
                                        <br>
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            ${paquetes.getNombre()}
                                        </h5>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <c:url var="paquetePerfil" value="/paquete">
                                            <c:param name="paqueteNombre" value="${paquetes.getNombre()}" />
                                        </c:url> 
                                        <a href="${paquetePerfil}">Mas Info</a>
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