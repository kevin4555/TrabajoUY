<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>

    <% ArrayList<String> listaKeywords = (ArrayList<String>)session.getAttribute("listaKeywords"); %>

    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="container pt-5">
        <div class="row">
            <jsp:include page="../include/Menu.jsp"/>
            <div class="col-8">
                <h1>Ofertas</h1>

                <c:forEach var="oferta" items="${listaOfertasConfirmadas}">
                    <h2>Nombre Oferta</h2>
                    <c:url var="ofertaUrl" value="/oferta">
                        <c:param name="nombreOferta" value="${oferta.nombre}" />
                    </c:url>

                    <a href="${ofertaUrl}">${oferta.nombre}</a>

                    <h2>Descripción</h2>
                    ${oferta.descripcion}<br>
                </c:forEach>

                <c:url var="loginUrl" value="/login"></c:url>
                <a href="${loginUrl}">login</a>
                
                <c:url var="loginUrl" value="/consultaOfertas"></c:url>
                <a href="${loginUrl}">AAAAAAAAAAAAAAAAAAAAAAAAAAAA</a>
            </div>
        </div>
    </main>
</body>
</html>