<%@page import="model.EstadoSesion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Iniciar Sesión</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/resource/css/general.css" />
<jsp:include page="../../include/Head.jsp" />
</head>
<body class="login">
	<%
    String contextPath = request.getContextPath();
	EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
    %>

	<form action="<%= contextPath %>/login" method="post" class="formLogin">
		<%if (estadoSesion != null && estadoSesion.equals(EstadoSesion.LOGIN_INCORRECTO)) { %>
		<div class="alert alert-danger  alert-dismissible fade show"
			role="alert">
			<%= estadoSesion.name() %>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<% } %>
		<div class="mb-3 mt-3">
			<label for="email" class="form-label"><strong>*Email o nickname:</strong></label> <input type="text" class="form-control" id="email"
				name="nombreEmail" />
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label"><strong>*Contraseña:</strong></label>
			<input type="password" class="form-control" id="pwd"
				name="contrasenia" />
		</div>
		<div class="mb-3">
			<input class="btn btn-primary" type="submit" value="Confirmar" /> <a
				class="btn btn-primary" href="<%= contextPath %>/login">Cancelar</a>
		</div>
	</form>
</body>
</html>
