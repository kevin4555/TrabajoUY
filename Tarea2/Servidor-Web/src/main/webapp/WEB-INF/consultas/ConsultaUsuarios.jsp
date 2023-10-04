<%@page import="logica.classes.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Consulta de Usuarios</title>
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
                    <c:forEach var="usuario" items="${listaUsuarios}">
                        <div class="card">
                            <div class="row g-0">
                                <div class="col-md-3 justify-content-center align-items-center d-flex">
                                    <c:set var="nombreClaveHash" value="${usuario.getNickname()}"/>
                                    <img
                                        src="${fotoPerfil[nombreClaveHash]}"
                                        class="img-fluid rounded-start"
                                        alt="Imagen PerfilUsuario"
                                    />
                                </div>
                                <div class="col-md-9">
                                    <div class="card-body">
                                        <h5 class="card-header p-0 border-0 bg-white text-start">
                                            ${usuario.getNickname()}
                                        </h5>
                                        <c:set var="tipoEsperadoVar" value="${tipoEsperado}"/>
                                        <c:set var="tipoUser" value="${usuario.getClass().getSimpleName()}"/>
                                        <c:choose>
                                            <c:when test="${tipoUser eq tipoEsperadoVar}">
                                                <p class="card-text">Tipo: Postulante</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="card-text">Tipo: Empresa</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="card-footer border-0 bg-white text-end">
                                        <c:url var="perfilURL" value="/perfil">
                                            <c:param name="nicknameUsuario" value="${usuario.getNickname()}" />
                                        </c:url>
                                        <a href="${perfilURL}">Perfil</a>
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