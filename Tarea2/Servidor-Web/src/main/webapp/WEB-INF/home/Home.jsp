<%@page import="logica.datatypes.DtOfertaLaboral"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>

<% ArrayList<String> listaKeywords = (ArrayList<String>)session.getAttribute("listaKeywords");

%>


</head>
<body>
	<h1>Keywords</h1>
	<c:forEach var="key" items="${listaKeywords}">
	${key} <br>
	</c:forEach>

	<h1>Ofertas</h1>

	<c:forEach var="oferta" items="${listaOfertasConfirmadas}">

		<h2>Nombre Oferta</h2>
		<c:url var="ofertaUrl" value="/oferta">
			<c:param name="nombreOferta" value="${oferta.nombre}" />
		</c:url>

		<a href="${ofertaUrl}">${oferta.nombre}</a>
		<br>
		<h3>Kewords</h3>
		<c:forEach var="keyword" items="${oferta.keywords}">
			<li>${keyword}</li>
		</c:forEach>


		<h2>Descripción</h2>
		${oferta.descripcion}<br>

	</c:forEach>
	<c:url var="loginUrl" value="/home/Login.jsp"></c:url>
	<a href = "${loginUrl}" >login</a>
</body>
</html>