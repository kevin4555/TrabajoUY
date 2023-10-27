<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String error = (String) request.getAttribute("error");
String errorMessage = "Error servidor";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
	<title>404: Not Found</title>
	
	
	<jsp:include page="../include/Head.jsp" />
</head>
<body class="login" id="error_page">
		<% String contextPath = request.getContextPath(); 
		String url=contextPath + "/home";
		%>
		<h1>¡Ha ocurrido un error!</h1>
    <div class="alert alert-danger">
        <strong>Error <%= error %>:</strong> <%= errorMessage %> <a href="<%= url %>">Volver al inicio</a>
    </div>
</body>
</html>